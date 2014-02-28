package phase2;

import java.io.IOException;
import java.util.ArrayList;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.*;

public class QuerySVD 
{
	private static ArrayList<double[][]> XData;
	private static ArrayList<double[][]> YData;
	private static ArrayList<double[][]> ZData;
	private static ArrayList<double[][]> WData;
	private static ArrayList<double[][]> XRightFactorMatrix;
	private static ArrayList<double[][]> YRightFactorMatrix;
	private static ArrayList<double[][]> ZRightFactorMatrix;
	private static ArrayList<double[][]> WRightFactorMatrix;
	private static GesturesLibrary g;
	public static Gesture initializeQuery(GesturesLibrary gesturesLibrary,String QueryXPath,String QueryYPath,String QueryZPath,String QueryWPath) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static void generateSVDDataWithQuery(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		XData=new ArrayList<double[][]>();
		YData=new ArrayList<double[][]>();
		ZData=new ArrayList<double[][]>();
		WData=new ArrayList<double[][]>();
		XRightFactorMatrix=new ArrayList<double[][]>();
		YRightFactorMatrix=new ArrayList<double[][]>();
		ZRightFactorMatrix=new ArrayList<double[][]>();
		WRightFactorMatrix=new ArrayList<double[][]>();
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'X');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getXMultivariateData());
			XData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			XRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Y');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getYMultivariateData());
			YData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			YRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Z');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getZMultivariateData());
			ZData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			ZRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'W');
			sensorDatas=appendSensorDataWithQuery(sensorDatas, i, queryGesture.getWMultivariateData());
			WData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			WRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
	}
	public static void generateSVDData(GesturesLibrary gesturesLibrary) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		g=gesturesLibrary;
		XData=new ArrayList<double[][]>();
		YData=new ArrayList<double[][]>();
		ZData=new ArrayList<double[][]>();
		WData=new ArrayList<double[][]>();
		XRightFactorMatrix=new ArrayList<double[][]>();
		YRightFactorMatrix=new ArrayList<double[][]>();
		ZRightFactorMatrix=new ArrayList<double[][]>();
		WRightFactorMatrix=new ArrayList<double[][]>();
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'X');
			XData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			XRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Y');
			YData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			YRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Z');
			ZData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			ZRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'W');
			WData.add(SVD.getSVDData(getSensorArray(sensorDatas)));
			WRightFactorMatrix.add(SVD.getRightFactor(getSensorArray(sensorDatas)));
		}
	}
	public static double[][] mapXQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static double[][] mapYQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static double[][] mapZQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static double[][] mapWQuery(GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static double[][] getQuerySensorXData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
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
	public static double[][] getQuerySensorYData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
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
	public static double[][] getQuerySensorZData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
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
	public static double[][] getQuerySensorWData(int sensor,GesturesLibrary gesturesLibrary , Gesture queryGesture) throws MatlabInvocationException, IOException
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
	public static double[] getMappedQuerySensorData(double[][] rightFactorMatrix, double[][] querySensorData ) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double [][] returnDoubles;
		returnDoubles=Functions.matrixTranspose(Functions.matrixMultiplication(rightFactorMatrix, Functions.matrixTranspose(querySensorData)));
		return returnDoubles[0];
	}
	public static Double[][] getSensorArray(ArrayList<UnivariateDataDocument> sensorDatas) throws MatlabInvocationException, IOException
	{
		return Functions.getSensorArray(sensorDatas);
	}
	public static ArrayList<UnivariateDataDocument> getSensorData(GesturesLibrary gesturesLibrary,int sensorNumber,char documentAxis)
	{
		return Functions.getSensorData(gesturesLibrary,sensorNumber,documentAxis);
	}
	public static ArrayList<UnivariateDataDocument> appendSensorDataWithQuery(ArrayList<UnivariateDataDocument>original,int sensorNumber,MultiVriateDataDocument queryDataDocument)
	{
		original.add(queryDataDocument.getUnivariateDataDocument(sensorNumber));
		return original;
	}
	public static double compareGestures(GesturesLibrary gestureLibrary, Gesture queryGesture,int gestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double similarity=0.0;
		similarity+=QuerySVD.compareDocumentOnXAxis(QuerySVD.mapXQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=QuerySVD.compareDocumentOnYAxis(QuerySVD.mapYQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=QuerySVD.compareDocumentOnZAxis(QuerySVD.mapZQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity+=QuerySVD.compareDocumentOnWAxis(QuerySVD.mapWQuery(gestureLibrary, queryGesture), gestureNumber);
		similarity/=4;
		return similarity;
	}
	public static double compareDocumentOnXAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<XData.size();i++)
		{
			double[][] curData=XData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public static double compareSensorDatas(double[]a , double[]b)
	{
		return Functions.cosine_similarity(a, b);
	}
	public static double compareDocumentOnYAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<YData.size();i++)
		{
			double[][] curData=YData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public static double compareDocumentOnZAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<ZData.size();i++)
		{
			double[][] curData=ZData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public static double compareDocumentOnWAxis(double[][]mappedQuery , int documentNumber)
	{
		double similarity=0.0;
		for(int i=0;i<WData.size();i++)
		{
			double[][] curData=WData.get(i);
			double[]qmd=mappedQuery[i];
			double[]d=curData[documentNumber];
			similarity+=compareSensorDatas(qmd, d);
		}
		similarity/=Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		return similarity;
	}
	public static void printXSVDSensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(XData.get(sensorNumber)));
	}
	public static void printYSVDSensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(YData.get(sensorNumber)));
	}
	public static void printZSVDSensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(ZData.get(sensorNumber)));
	}
	public static void printWSVDSensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(WData.get(sensorNumber)));
	}
	public static void printResulDocumentsOnXAxis(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('X', gesturesLibrary.getGestureFileNumber(i));
			Double similarity=QuerySVD.compareDocumentOnXAxis(QuerySVD.mapXQuery(gesturesLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static void printResulDocumentsOnYAxis(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('Y', gesturesLibrary.getGestureFileNumber(i));
			Double similarity=QuerySVD.compareDocumentOnYAxis(QuerySVD.mapYQuery(gesturesLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static void printResulDocumentsOnZAxis(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('Z', gesturesLibrary.getGestureFileNumber(i));
			Double similarity=QuerySVD.compareDocumentOnZAxis(QuerySVD.mapZQuery(gesturesLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static void printResulDocumentsOnWAxis(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('W', gesturesLibrary.getGestureFileNumber(i));
			Double similarity=QuerySVD.compareDocumentOnWAxis(QuerySVD.mapWQuery(gesturesLibrary, queryGesture), i);
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}

	
	
	public static void printResultGesture(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g=gestureLibrary.getGesture(i);
			Double similarity=QuerySVD.compareGestures(gestureLibrary, queryGesture, i);
			Result r=new Result(g, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static double compareGesturesSVD(int aGesture,int bGesture)
	{
		double distance=0.0;
		distance+=compareDocumentOnXAxisSVD(aGesture, bGesture);
		if(compareDocumentOnXAxisSVD(aGesture, bGesture)>=1.001)
		{
			System.out.println("There is some problem in distance"+"1");
		}
		distance+=compareDocumentOnYAxisSVD(aGesture, bGesture);
		if(compareDocumentOnYAxisSVD(aGesture, bGesture)>=1.001)
		{
			System.out.println("There is some problem in distance"+"2");
		}if(compareDocumentOnWAxisSVD(aGesture, bGesture)>=1.001)
		{
			System.out.println("There is some problem in distance"+"4");
		}if(compareDocumentOnZAxisSVD(aGesture, bGesture)>=1.001)
		{
			System.out.println("There is some problem in distance"+"3");
		}
		distance+=compareDocumentOnZAxisSVD(aGesture, bGesture);
		distance+=compareDocumentOnWAxisSVD(aGesture, bGesture);
		
		if(distance/4.0>=1.001)
		{
			System.out.println("Final value is wrong!!"+"1");
		}
		return distance/4.0;
	}
	public static double compareDocumentOnXAxisSVD(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<XData.size();i++)
		{
			distance+=compareOnXSensorSVD(i, aDocument, bDocument);
		}
		return distance/XData.size();
	}
	public static double compareOnXSensorSVD(int sensor,int aDocument,int bDocument)
	{
		double[][] curSVDData=XData.get(sensor);
		return Functions.cosine_similarity(curSVDData[aDocument], curSVDData[bDocument]);
	}
	public static double compareDocumentOnYAxisSVD(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<YData.size();i++)
		{
			distance+=compareOnYSensorSVD(i, aDocument, bDocument);
		}
		return distance/YData.size();
	}
	public static double compareOnYSensorSVD(int sensor,int aDocument,int bDocument)
	{
		double[][] curSVDData=YData.get(sensor);
		return Functions.cosine_similarity(curSVDData[aDocument], curSVDData[bDocument]);
	}
	public static double compareDocumentOnZAxisSVD(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<ZData.size();i++)
		{
			distance+=compareOnZSensorSVD(i, aDocument, bDocument);
		}
		return distance/ZData.size();
	}
	public static double compareOnZSensorSVD(int sensor,int aDocument,int bDocument)
	{
		double[][] curSVDData=ZData.get(sensor);
		return Functions.cosine_similarity(curSVDData[aDocument], curSVDData[bDocument]);
	}
	public static double compareDocumentOnWAxisSVD(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<WData.size();i++)
		{
			distance+=compareOnWSensorSVD(i, aDocument, bDocument);
		}
		return distance/WData.size();
	}
	public static double compareOnWSensorSVD(int sensor,int aDocument,int bDocument)
	{
		double[][] curSVDData=WData.get(sensor);
		return Functions.cosine_similarity(curSVDData[aDocument], curSVDData[bDocument]);
	}
	
	public static void printXRightFactorSensor(int sensorNumber)
	{
		ArrayList<String> a;
		String s="";
		a=Functions.getUniqeWords(getSensorData(g, sensorNumber, 'X'));
		s="";
		for(int i=0;i<a.size();i++)
		{
			s+=String.format("%20s", a.get(i));
		}
		System.out.println(s);
		System.out.println(Functions.printDoubleArray1(XRightFactorMatrix.get(sensorNumber)));	
	}
	public static void printYRightFactorSensor(int sensorNumber)
	{
		ArrayList<String> a;
		String s="";
		a=Functions.getUniqeWords(getSensorData(g, sensorNumber, 'Y'));
		s="";
		for(int i=0;i<a.size();i++)
		{
			s+=String.format("%20s", a.get(i));
		}
		System.out.println(s);
		System.out.println(Functions.printDoubleArray1(YRightFactorMatrix.get(sensorNumber)));
	}public static void printZRightFactorSensor(int sensorNumber)
	{
		ArrayList<String> a;
		String s="";
		a=Functions.getUniqeWords(getSensorData(g, sensorNumber, 'Z'));
		s="";
		for(int i=0;i<a.size();i++)
		{
			s+=String.format("%20s", a.get(i));
		}
		System.out.println(s);
		System.out.println(Functions.printDoubleArray1(ZRightFactorMatrix.get(sensorNumber)));
	}public static void printWRightFactorSensor(int sensorNumber)
	{
		ArrayList<String> a;
		String s="";
		a=Functions.getUniqeWords(getSensorData(g, sensorNumber, 'W'));
		s="";
		for(int i=0;i<a.size();i++)
		{
			s+=String.format("%20s", a.get(i));
		}
		System.out.println(s);
		System.out.println(Functions.printDoubleArray1(WRightFactorMatrix.get(sensorNumber)));
	}
	
}
