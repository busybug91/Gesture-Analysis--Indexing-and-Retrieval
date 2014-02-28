package phase2;

import java.io.IOException;
import java.util.ArrayList;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.*;

public class Query 
{
	private static ArrayList<double[][]> XPCAData;
	private static ArrayList<double[][]> YPCAData;
	private static ArrayList<double[][]> ZPCAData;
	private static ArrayList<double[][]> WPCAData;
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
	public static void generatePCADataWithQuery(GesturesLibrary gesturesLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static void generatePCAData(GesturesLibrary gesturesLibrary) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		g=gesturesLibrary;
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
			System.out.println("Craeting PCA data for sensor X"+i);
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'X');
			XPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			XRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}

		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			System.out.println("Craeting PCA data for sensor Y"+i);
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Y');
			YPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			YRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			System.out.println("Craeting PCA data for sensor Z"+i);
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'Z');
			ZPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			ZRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
		}
		for(int i=0;i<Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			System.out.println("Craeting PCA data for sensor W"+i);
			ArrayList<UnivariateDataDocument> sensorDatas=getSensorData(gesturesLibrary, i, 'W');
			WPCAData.add(PCA.getPCADataNbyp(getSensorArray(sensorDatas)));
			WRightFactorMatrix.add(PCA.getRightFactorNbyP(getSensorArray(sensorDatas)));
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
		similarity+=Query.compareDocumentOnXAxis(Query.mapXQuery(gestureLibrary, queryGesture), gestureNumber);
		//System.out.println("X"+similarity);

		similarity+=Query.compareDocumentOnYAxis(Query.mapYQuery(gestureLibrary, queryGesture), gestureNumber);
		//	System.out.println("Y"+similarity);

		similarity+=Query.compareDocumentOnZAxis(Query.mapZQuery(gestureLibrary, queryGesture), gestureNumber);
		//		System.out.println("Z"+similarity);

		similarity+=Query.compareDocumentOnWAxis(Query.mapWQuery(gestureLibrary, queryGesture), gestureNumber);
		//		System.out.println("W"+similarity);

		similarity/=4;
		//		System.out.println("sdfsadf"+similarity);

		return similarity;
	}
	public static double compareDocumentOnXAxis(double[][]mappedQuery , int documentNumber)
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
	public static double compareSensorDatas(double[]a , double[]b)
	{
		return Functions.cosine_similarity(a, b);
	}
	public static double compareDocumentOnYAxis(double[][]mappedQuery , int documentNumber)
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
	public static double compareDocumentOnZAxis(double[][]mappedQuery , int documentNumber)
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
	public static double compareDocumentOnWAxis(double[][]mappedQuery , int documentNumber)
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
	public static void printXPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(XPCAData.get(sensorNumber)));
	}
	public static void printYPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(YPCAData.get(sensorNumber)));
	}
	public static void printZPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(ZPCAData.get(sensorNumber)));
	}
	public static void printWPCASensorData(int sensorNumber)
	{
		System.out.println(Functions.printDoubleArray1(WPCAData.get(sensorNumber)));
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
		a=Functions.getUniqeWords(getSensorData(g, sensorNumber, 'Y'));
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

	public static void printResulDocumentsOnXAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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

	public static void printResulDocumentsOnYAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static void printResulDocumentsOnZAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static void printResulDocumentsOnWAxis(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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

	public static void printResultGesture(GesturesLibrary gestureLibrary,Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
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
	public static double compareGesturesPCA(int aGesture,int bGesture)
	{
		double distance=0.0;
		distance+=compareDocumentOnXAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnYAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnZAxisPCA(aGesture, bGesture);
		distance+=compareDocumentOnWAxisPCA(aGesture, bGesture);
		return distance/4.0;
	}
	public static double compareDocumentOnXAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<XPCAData.size();i++)
		{
			distance+=compareOnXSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public static double compareOnXSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=XPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public static double compareDocumentOnYAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<YPCAData.size();i++)
		{
			distance+=compareOnYSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public static double compareOnYSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=YPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public static double compareDocumentOnZAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<ZPCAData.size();i++)
		{
			distance+=compareOnZSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public static double compareOnZSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=ZPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public static double compareDocumentOnWAxisPCA(int aDocument, int bDocument)
	{
		double distance=0.0;
		for(int i=0;i<WPCAData.size();i++)
		{
			distance+=compareOnWSensorPCA(i, aDocument, bDocument);
		}
		return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
	}
	public static double compareOnWSensorPCA(int sensor,int aDocument,int bDocument)
	{
		double[][] curPCAData=WPCAData.get(sensor);
		return Functions.cosine_similarity(curPCAData[aDocument], curPCAData[bDocument]);
	}
	public static double[][] getCompleteData()
	{
		ArrayList<String> dim=getDimensions();
		System.out.println(dim);
		int column=0;
		double[][] gestureData=new double[Consatnts.TOTAL_NUMBER_OF_GESTURES][dim.size()];
		System.out.println("Dim Size"+dim.size());
		for(int i=0;i<XPCAData.size();i++)
		{
			double[][]curData=XPCAData.get(i);
			for(int j=0;j<curData.length;j++)
			{

				for(int k=0;k<curData[j].length;k++)
				{
					System.out.print(dim.get(column+k)+" "+i);
					if(dim.get(column+k).contains(i+""))
						gestureData[j][column+k]=curData[j][k];
					else
						System.out.println("Error");
				}
			}
			column+=3;
		}
		for(int i=0;i<YPCAData.size();i++)
		{
			double[][]curData=YPCAData.get(i);
			for(int j=0;j<curData.length;j++)
			{

				for(int k=0;k<curData[j].length;k++)
				{			
					System.out.print(dim.get(column+k)+" "+i);
					if(dim.get(column+k).contains(i+""))
						gestureData[j][column+k]=curData[j][k];
					else
						System.out.println("Error");
				}
			}
			column+=3;
		}
		for(int i=0;i<ZPCAData.size();i++)
		{
			double[][]curData=ZPCAData.get(i);
			for(int j=0;j<curData.length;j++)
			{

				for(int k=0;k<curData[j].length;k++)
				{		System.out.print(dim.get(column+k)+" "+i);	
				if(dim.get(column+k).contains(i+""))
					gestureData[j][column+k]=curData[j][k];
				else
					System.out.println("Error");
				}
			}
			column+=3;
		}
		for(int i=0;i<WPCAData.size();i++)
		{
			double[][]curData=WPCAData.get(i);
			for(int j=0;j<curData.length;j++)
			{

				for(int k=0;k<curData[j].length;k++)
				{	
					System.out.print(dim.get(column+k)+" "+i);
					if(dim.get(column+k).contains(i+""))
						gestureData[j][column+k]=curData[j][k];
					else
						System.out.println("Error");
				}
			}
			column+=3;
		}
		return gestureData;
	}
	public static ArrayList<String> getDimensions()
	{
		ArrayList<String> dim=new ArrayList<String>();
		for(int i=0;i<XPCAData.size();i++)
		{
			dim.add("X|S"+i+"|0");
			dim.add("X|S"+i+"|1");
			dim.add("X|S"+i+"|2");
		}
		for(int i=0;i<YPCAData.size();i++)
		{
			dim.add("Y|S"+i+"|0");
			dim.add("Y|S"+i+"|1");
			dim.add("Y|S"+i+"|2");
		}
		for(int i=0;i<ZPCAData.size();i++)
		{
			dim.add("Z|S"+i+"|0");
			dim.add("Z|S"+i+"|1");
			dim.add("Z|S"+i+"|2");
		}
		for(int i=0;i<XPCAData.size();i++)
		{
			dim.add("W|S"+i+"|0");
			dim.add("W|S"+i+"|1");
			dim.add("W|S"+i+"|2");
		}
		System.out.println(dim);
		return dim;
	}
}
