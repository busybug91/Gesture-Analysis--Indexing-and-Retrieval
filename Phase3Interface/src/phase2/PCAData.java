package phase2;

import java.io.IOException;
import java.util.ArrayList;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.*;

public class PCAData 
{
	private ArrayList<double[][]> XPCAData;
	private ArrayList<double[][]> YPCAData;
	private ArrayList<double[][]> ZPCAData;
	private ArrayList<double[][]> WPCAData;
	private ArrayList<double[][]> XRightFactorMatrix;
	private ArrayList<double[][]> YRightFactorMatrix;
	private ArrayList<double[][]> ZRightFactorMatrix;
	private ArrayList<double[][]> WRightFactorMatrix;

	public Gesture initializeQuery(GesturesLibrary gesturesLibrary,String QueryXPath,String QueryYPath,String QueryZPath,String QueryWPath) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		//Call this method first. Then call print method.
		MultiVriateDataDocument queryXMultiVariateDataDocument=new MultiVriateDataDocument(QueryXPath,'X');
		MultiVriateDataDocument queryYMultiVariateDataDocument=new MultiVriateDataDocument(QueryYPath,'Y');
		MultiVriateDataDocument queryZMultiVariateDataDocument=new MultiVriateDataDocument(QueryZPath,'Z');
		MultiVriateDataDocument queryWMultiVariateDataDocument=new MultiVriateDataDocument(QueryWPath,'W');
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument XmultiVariateDataDocument=gesturesLibrary.getMultivariateData('X', gesturesLibrary.getGestureFileNumber(i));
			phase1.Functions.calculateQueryTFIDF(queryXMultiVariateDataDocument, gesturesLibrary, XmultiVariateDataDocument, 'X');
			MultiVriateDataDocument YmultiVariateDataDocument=gesturesLibrary.getMultivariateData('Y', gesturesLibrary.getGestureFileNumber(i));
			phase1.Functions.calculateQueryTFIDF(queryYMultiVariateDataDocument, gesturesLibrary, YmultiVariateDataDocument, 'Y');
			MultiVriateDataDocument ZmultiVariateDataDocument=gesturesLibrary.getMultivariateData('Z', gesturesLibrary.getGestureFileNumber(i));
			phase1.Functions.calculateQueryTFIDF(queryZMultiVariateDataDocument, gesturesLibrary, ZmultiVariateDataDocument, 'Z');
			MultiVriateDataDocument WmultiVariateDataDocument=gesturesLibrary.getMultivariateData('W', gesturesLibrary.getGestureFileNumber(i));
			phase1.Functions.calculateQueryTFIDF(queryWMultiVariateDataDocument, gesturesLibrary, WmultiVariateDataDocument, 'W');
		}
		Gesture queryGesture=new Gesture(99999, queryXMultiVariateDataDocument, queryYMultiVariateDataDocument, queryZMultiVariateDataDocument, queryWMultiVariateDataDocument);
		return queryGesture;
	}
	@Deprecated
	public void generatePCADataWithQuery(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		XPCAData=new ArrayList<double[][]>();
		YPCAData=new ArrayList<double[][]>();
		ZPCAData=new ArrayList<double[][]>();
		WPCAData=new ArrayList<double[][]>();
		XRightFactorMatrix=new ArrayList<double[][]>();
		YRightFactorMatrix=new ArrayList<double[][]>();
		ZRightFactorMatrix=new ArrayList<double[][]>();
		WRightFactorMatrix=new ArrayList<double[][]>();
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'X');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getXMultivariateData());
			XPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			XRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Y');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getYMultivariateData());
			YPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			YRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Z');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getZMultivariateData());
			ZPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			ZRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'W');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getWMultivariateData());
			WPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			WRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
	}
	public PCAData(GesturesLibrary gesturesLibrary) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		XPCAData=new ArrayList<double[][]>();
		YPCAData=new ArrayList<double[][]>();
		ZPCAData=new ArrayList<double[][]>();
		WPCAData=new ArrayList<double[][]>();
		XRightFactorMatrix=new ArrayList<double[][]>();
		YRightFactorMatrix=new ArrayList<double[][]>();
		ZRightFactorMatrix=new ArrayList<double[][]>();
		WRightFactorMatrix=new ArrayList<double[][]>();
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'X');
			XPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			XRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Y');
			YPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			YRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Z');
			ZPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			ZRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'W');
			WPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			WRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
	}
	public double[][] mapXQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] queryMappedData=new double[Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			double[][]qd=getQuerySensorXData(i, gesturesLibrary, queryGesture);
			double[]qmd=getMappedQuerySensorData(XRightFactorMatrix.get(i),qd);
			queryMappedData[i]=qmd;
		}
		return queryMappedData;
	}
	public double[][] mapYQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] queryMappedData=new double[Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			double[][]qd=getQuerySensorYData(i, gesturesLibrary, queryGesture);
			double[]qmd=getMappedQuerySensorData(YRightFactorMatrix.get(i),qd);
			queryMappedData[i]=qmd;
		}
		return queryMappedData;
	}
	public double[][] mapZQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] queryMappedData=new double[Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			double[][]qd=getQuerySensorZData(i, gesturesLibrary, queryGesture);
			double[]qmd=getMappedQuerySensorData(ZRightFactorMatrix.get(i),qd);
			queryMappedData[i]=qmd;
		}
		return queryMappedData;
	}
	public double[][] mapWQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] queryMappedData=new double[Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			double[][]qd=getQuerySensorWData(i, gesturesLibrary, queryGesture);
			double[]qmd=getMappedQuerySensorData(WRightFactorMatrix.get(i),qd);
			queryMappedData[i]=qmd;
		}
		return queryMappedData;
	}
	public double[][] getQuerySensorXData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
	{
		Double[] rDoubles;
		ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, sensor, 'X');
		int originalLength= getSensorArray(sensorDatas)[0].length;
		rDoubles=new Double[originalLength];
		sensorDatas=appendSensorDataWithQuery(sensorDatas, sensor, queryGesture.getXMultivariateData());
		Double[] temp=getSensorArray(sensorDatas)[getSensorArray(sensorDatas).length-1];
		for(int i=0;i<originalLength;i++)
		{
			rDoubles[i]=temp[i];
		}
		Double[][] temp1=new Double[1][];
		temp1[0]=rDoubles;
		double[][] temp2=Functions.convertDoubleArraySmall(temp1);
		return temp2;
	}
	public double[][] getQuerySensorYData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
	{
		Double[] rDoubles;
		ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, sensor, 'Y');
		int originalLength= getSensorArray(sensorDatas)[0].length;
		rDoubles=new Double[originalLength];
		sensorDatas=appendSensorDataWithQuery(sensorDatas, sensor, queryGesture.getYMultivariateData());
		Double[] temp=getSensorArray(sensorDatas)[getSensorArray(sensorDatas).length-1];
		for(int i=0;i<originalLength;i++)
		{
			rDoubles[i]=temp[i];
		}
		Double[][] temp1=new Double[1][];
		temp1[0]=rDoubles;
		double[][] temp2=Functions.convertDoubleArraySmall(temp1);
		return temp2;
	}
	public double[][] getQuerySensorZData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
	{
		Double[] rDoubles;
		ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, sensor, 'Z');
		int originalLength= getSensorArray(sensorDatas)[0].length;
		rDoubles=new Double[originalLength];
		sensorDatas=appendSensorDataWithQuery(sensorDatas, sensor, queryGesture.getZMultivariateData());
		Double[] temp=getSensorArray(sensorDatas)[getSensorArray(sensorDatas).length-1];
		for(int i=0;i<originalLength;i++)
		{
			rDoubles[i]=temp[i];
		}
		Double[][] temp1=new Double[1][];
		temp1[0]=rDoubles;
		double[][] temp2=Functions.convertDoubleArraySmall(temp1);
		return temp2;
	}
	public double[][] getQuerySensorWData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
	{
		Double[] rDoubles;
		ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, sensor, 'W');
		int originalLength= getSensorArray(sensorDatas)[0].length;
		rDoubles=new Double[originalLength];
		sensorDatas=appendSensorDataWithQuery(sensorDatas, sensor, queryGesture.getWMultivariateData());
		Double[] temp=getSensorArray(sensorDatas)[getSensorArray(sensorDatas).length-1];
		for(int i=0;i<originalLength;i++)
		{
			rDoubles[i]=temp[i];
		}
		Double[][] temp1=new Double[1][];
		temp1[0]=rDoubles;
		double[][] temp2=Functions.convertDoubleArraySmall(temp1);
		return temp2;
	}
	public double[] getMappedQuerySensorData(double[][] rightFactorMatrix, double[][] querySensorData ) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double [][] returnDoubles;
		returnDoubles=Functions.matrixTranspose(Functions.matrixMultiplication(rightFactorMatrix, Functions.matrixTranspose(querySensorData)));
		return returnDoubles[0];
	}
	public Double[][] getSensorArray(ArrayList<UnivariateDataDocument> sensorDatas) throws MatlabInvocationException, IOException
	{
		return Functions.getSensorArray(sensorDatas);
	}
	public ArrayList<UnivariateDataDocument> getSensorData(GesturesLibrary gesturesLibrary,int sensorNumber,char documentAxis)
	{
		return Functions.getSensorData(gesturesLibrary,sensorNumber,documentAxis);
	}
	public ArrayList<UnivariateDataDocument> appendSensorDataWithQuery(ArrayList<UnivariateDataDocument>original,int sensorNumber,MultiVriateDataDocument queryDataDocument)
	{
		original.add(queryDataDocument.getUnivariateDataDocument(sensorNumber));
		return original;
	}
	public double compareGestures(GesturesLibrary gestureLibrary, Gesture queryGesture,int gestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double similarity=0.0;
		similarity+=Query.compareDocumentOnXAxis(Query.mapXQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=Query.compareDocumentOnYAxis(Query.mapYQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=Query.compareDocumentOnZAxis(Query.mapZQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=Query.compareDocumentOnWAxis(Query.mapWQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity/=4;
		return similarity;
	}
	public double compareDocumentOnXAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<XPCAData.size();i++)
		{
			double[][] curPCAData=XPCAData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curPCAData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public  double compareSensorDatas(double[]a , double[]b)
	{
		return Functions.cosine_similarity(a, b);
	}
	public double compareDocumentOnYAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<YPCAData.size();i++)
		{
			double[][] curPCAData=YPCAData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curPCAData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public double compareDocumentOnZAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<ZPCAData.size();i++)
		{
			double[][] curPCAData=ZPCAData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curPCAData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public double compareDocumentOnWAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<WPCAData.size();i++)
		{
			double[][] curPCAData=WPCAData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curPCAData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public void printXPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(XPCAData.get(sensorNumber)));
	}
	public void printYPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(YPCAData.get(sensorNumber)));
	}
	public  void printZPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(ZPCAData.get(sensorNumber)));
	}
	public void printWPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(WPCAData.get(sensorNumber)));
	}
	public void printXRightFactorSensor(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(XRightFactorMatrix.get(sensorNumber)));
	}
	public void printYRightFactorSensor(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(YRightFactorMatrix.get(sensorNumber)));
	}public void printZRightFactorSensor(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(ZRightFactorMatrix.get(sensorNumber)));
	}public void printWRightFactorSensor(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(WRightFactorMatrix.get(sensorNumber)));
	}

	public void printResulDocumentsOnXAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gestureLibrary.getMultivariateData('X', gestureLibrary.getGestureFileNumber(i));
			Double similarity=Query.compareDocumentOnXAxis(Query.mapXQuery(gestureLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}

	public void printResulDocumentsOnYAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gestureLibrary.getMultivariateData('Y', gestureLibrary.getGestureFileNumber(i));
			Double similarity=Query.compareDocumentOnYAxis(Query.mapYQuery(gestureLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public void printResulDocumentsOnZAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gestureLibrary.getMultivariateData('Z', gestureLibrary.getGestureFileNumber(i));
			Double similarity=Query.compareDocumentOnZAxis(Query.mapZQuery(gestureLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public void printResulDocumentsOnWAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		System.out.println("Generating Results...");
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gestureLibrary.getMultivariateData('W', gestureLibrary.getGestureFileNumber(i));
			Double similarity=Query.compareDocumentOnWAxis(Query.mapWQuery(gestureLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}

	public void printResultGesture(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g=gestureLibrary.getGesture(i);
			Double similarity=Query.compareGestures(gestureLibrary, queryGesture, i);
			Result r=new Result(g, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public double compareGesturesPCA(int aGesture,int bGesture)
	{
		double distance=0.0;
		distance+=compareDocumentOnXAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnYAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnZAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnWAxisPCA(aGesture, bGesture);
		return distance/4.0;
	}
	public double compareDocumentOnXAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<XPCAData.size();i++)
		{
			distance+=compareOnXSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public double compareOnXSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=XPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public double compareDocumentOnYAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<YPCAData.size();i++)
		{
			distance+=compareOnYSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public double compareOnYSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=YPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public double compareDocumentOnZAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<ZPCAData.size();i++)
		{
			distance+=compareOnZSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public double compareOnZSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=ZPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public double compareDocumentOnWAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<WPCAData.size();i++)
		{
			distance+=compareOnWSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public double compareOnWSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=WPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
}
