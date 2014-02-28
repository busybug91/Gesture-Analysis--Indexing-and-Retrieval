package phase2;

import java.io.IOException;
import java.util.List;
import mCode.Extractor;
import mCode.FrequencyWrapper;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.*;

public class QueryLDA
{
	public static TopicWrapper[][][] XTopic_dist;
	public static TopicWrapper[][][] YTopic_dist;
	public static TopicWrapper[][][] ZTopic_dist;
	public static TopicWrapper[][][] WTopic_dist;
	public static String[][][] XLDAData;
	public static String[][][] YLDAData;
	public static String[][][] ZLDAData;
	public static String[][][] WLDAData;
	public static FrequencyWrapper[][] XLDAVectors;
	public static FrequencyWrapper[][] YLDAVectors;
	public static FrequencyWrapper[][] ZLDAVectors;
	public static FrequencyWrapper[][] WLDAVectors;
	public static double[][] queryXLDAVectors;
	public static double[][] queryYLDAVectors;
	public static double[][] queryZLDAVectors;
	public static double[][] queryWLDAVectors;

	public static double[][] XSimilarityMatrix;
	public static double[][] YSimilarityMatrix;
	public static double[][] ZSimilarityMatrix;
	public static double[][] WSimilarityMatrix;

	public static double[][] GestureSimilarityMatrix;

	public static void generateLDAData(GesturesLibrary gestureLibrary)
	{
		String [][][] originalData=gestureLibrary.getComponentWordsArray('X');
		XTopic_dist=LDA.performLDA(gestureLibrary, 'X', 3);
		XLDAData=LDA.transform(XTopic_dist, originalData);
		originalData=gestureLibrary.getComponentWordsArray('Y');
		YTopic_dist=LDA.performLDA(gestureLibrary, 'Y', 3);
		YLDAData=LDA.transform(YTopic_dist, originalData);
		originalData=gestureLibrary.getComponentWordsArray('Z');
		ZTopic_dist=LDA.performLDA(gestureLibrary, 'Z', 3);
		ZLDAData=LDA.transform(ZTopic_dist, originalData);
		originalData=gestureLibrary.getComponentWordsArray('W');
		WTopic_dist=LDA.performLDA(gestureLibrary, 'W', 3);
		WLDAData=LDA.transform(WTopic_dist, originalData);


		List<String> newdic = Extractor.BuildUniqueDictionary(XLDAData);
		XLDAVectors=LDA.vectorizeNewData(XLDAData, newdic);
		//		System.out.println(LDA.getNewDic());
		YLDAVectors=LDA.vectorizeNewData(YLDAData,newdic);
		///		System.out.println(LDA.getNewDic());
		ZLDAVectors=LDA.vectorizeNewData(ZLDAData,newdic);
		//
		//		System.out.println(LDA.getNewDic());
		WLDAVectors=LDA.vectorizeNewData(WLDAData, newdic);

		LDAGestureSimilarityMatrix( XLDAVectors,  XLDAVectors,  XLDAVectors, XLDAVectors);
		XSimilarityMatrix=LDASimilarityMatrix( XLDAVectors);
		YSimilarityMatrix=LDASimilarityMatrix( YLDAVectors);
		ZSimilarityMatrix=LDASimilarityMatrix( ZLDAVectors);
		WSimilarityMatrix=LDASimilarityMatrix( WLDAVectors);
		//System.out.println(LDA.getNewDic());
	}
	@SuppressWarnings("unused")
	public static void mapQuery(Gesture queryGesture)
	{
		String [][] qXData=queryGesture.getXMultivariateData().getMulWords();
		String [][] qYData=queryGesture.getYMultivariateData().getMulWords();
		String [][] qZData=queryGesture.getZMultivariateData().getMulWords();
		String [][] qWData=queryGesture.getWMultivariateData().getMulWords();
		System.out.println("qWData" + qWData.length + " " + qWData[0].length);

		String qNewXData[][]=LDA.transformQuery(XTopic_dist,qXData);
		String qNewYData[][]=LDA.transformQuery(YTopic_dist,qYData);
		String qNewZData[][]=LDA.transformQuery(ZTopic_dist,qZData);
		String qNewWData[][]=LDA.transformQuery(WTopic_dist,qWData);

		//	System.out.println(LDA.getNewDic() + " before vectorize query");/		
		//		queryXLDAVectors=LDA.VectorizeQuery(qNewXData, XLDAVectors);
		//		queryYLDAVectors=LDA.VectorizeQuery(qNewYData, YLDAVectors);
		//		queryZLDAVectors=LDA.VectorizeQuery(qNewZData, ZLDAVectors);
		//		queryWLDAVectors=LDA.VectorizeQuery(qNewWData, WLDAVectors);

	}

	public static double[][] LDASimilarityMatrix(FrequencyWrapper[][] wrapper)
	{
		double[][] result = new double[wrapper.length][];

		for(int f = 0; f < wrapper.length; f++)
		{
			double[] file_result = new double[wrapper.length];
			double[][] file1_data = new double[wrapper[f].length][];
			for(int load = 0; load < file1_data.length; load++)
			{
				file1_data[load] = wrapper[f][load].getTFIDF();
			}
			//	System.out.println(Functions.printDoubleArray1(file1_data));
			//for each file, we need to calculate the similarity with each other file
			for(int f2 = 0; f2 < wrapper.length; f2++)
			{
				double[][] file2_data = new double[wrapper[f2].length][];
				for(int load = 0; load < file2_data.length; load++)
				{
					file2_data[load] = wrapper[f2][load].getTFIDF();
				}
				//	System.out.println(Functions.printDoubleArray1(file2_data));
				file_result[f2] = compareXDocument(file1_data, file2_data);
				//			System.out.println(file_result[f2]);
			}

			result[f] = file_result;
		}

		//	System.out.println(Functions.printDoubleArray1(result));
		return result;
	}


	public static double[][] LDAGestureSimilarityMatrix(FrequencyWrapper[][] wrapperX, FrequencyWrapper[][] wrapperY, FrequencyWrapper[][] wrapperZ, FrequencyWrapper[][] wrapperW)
	{
		double[][] resultX = new double[wrapperX.length][];
		double[][] resultY = new double[wrapperY.length][];
		double[][] resultZ = new double[wrapperZ.length][];
		double[][] resultW = new double[wrapperW.length][];

		resultX = LDASimilarityMatrix(wrapperX);
		resultY = LDASimilarityMatrix(wrapperY);
		resultZ = LDASimilarityMatrix(wrapperZ);
		resultW = LDASimilarityMatrix(wrapperW);

		XSimilarityMatrix = resultX;
		YSimilarityMatrix = resultY;
		ZSimilarityMatrix = resultZ;
		WSimilarityMatrix = resultW;

		double[][] total_result = new double[resultX.length][];
		for(int i = 0; i < total_result.length; i++)
		{
			total_result[i] = new double[resultX[i].length];
			for(int j = 0; j < total_result[i].length; j++)
			{
				total_result[i][j] = (resultX[i][j] + resultY[i][j] + resultZ[i][j] + resultW[i][j]) / 4;
			}
		}

		GestureSimilarityMatrix = total_result;
		return total_result;
	}

	public static double[][][] getTFIDFVectors(FrequencyWrapper[][] wrapper)
	{
		double[][][] returnDoubles=new double[wrapper.length][][];
		for(int i=0;i<wrapper.length;i++)
		{
			returnDoubles[i]= new double[wrapper[i].length][];
			for(int j=0;j<wrapper[i].length;j++)
			{
				returnDoubles[i][j]=wrapper[i][j].getTFIDF();	
			}
		}
		return returnDoubles;
	}
	public static double compareXDocumentOnSensor(double[] a , double[] b)
	{
		return Functions.cosine_similarity(a, b);
	}
	public static double compareXDocument(double[][] a,double[][] b)
	{
		double similarity=0.0;
		for(int i=0;i<a.length&&i<b.length;i++)
		{
			similarity+=compareXDocumentOnSensor(a[i], b[i]);
		}
		return similarity/a.length;
	}
	public static double[][] getXLDADocumentVector(int doucumentNumber)
	{
		return getTFIDFVectors(XLDAVectors)[doucumentNumber];
	}
	public static double[][] getYLDADocumentVector(int doucumentNumber)
	{
		return getTFIDFVectors(YLDAVectors)[doucumentNumber];
	}
	public static double[][] getZLDADocumentVector(int doucumentNumber)
	{
		return getTFIDFVectors(ZLDAVectors)[doucumentNumber];
	}
	public static double[][] getWLDADocumentVector(int doucumentNumber)
	{
		return getTFIDFVectors(WLDAVectors)[doucumentNumber];
	}
	public static double[][] getXQueryVector()
	{
		if(XLDAVectors!=null)
			return queryXLDAVectors;
		else{

			System.out.println("NULL");
			return null;
		}
	}
	public static double[][] getYQueryVector()
	{
		if(YLDAVectors!=null)
			return queryYLDAVectors;
		else{

			System.out.println("NULL");
			return null;
		}
	}
	public static double[][] getZQueryVector()
	{
		if(ZLDAVectors!=null)
			return queryZLDAVectors;
		else{

			System.out.println("NULL");
			return null;
		}
	}
	public static double[][] getWQueryVector()
	{
		if(WLDAVectors!=null)
			return queryWLDAVectors;
		else{

			System.out.println("NULL");
			return null;
		}
	}
	public static void printXTopic_distDocument(int documentNumber)
	{

		for(int i=0;i<1;i++)
		{

			TopicWrapper curt[]=XTopic_dist[i][documentNumber];
			for(int j=0;j<curt.length;j++)
			{
				System.out.println(curt[j]);
			}System.out.println(curt.length);
		}
		System.out.println(XTopic_dist.length);

	}
	
	

	public static void printResulDocumentsOnXAxis(GesturesLibrary gesturesLibrary, int dn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		FrequencyWrapper [] qdocu=XLDAVectors[dn];
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			double similarity=0.0;
			FrequencyWrapper [] docu=XLDAVectors[i];
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('X', gesturesLibrary.getGestureFileNumber(i));
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu[j].getTFIDF();
				double [] b =qdocu[j].getTFIDF();
				similarity+=Functions.cosine_similarity(a, b);
			}
			similarity/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static void printResulDocumentsOnYAxis(GesturesLibrary gesturesLibrary, int dn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		FrequencyWrapper [] qdocu=YLDAVectors[dn];
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			double similarity=0.0;
			FrequencyWrapper [] docu=YLDAVectors[i];
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('Y', gesturesLibrary.getGestureFileNumber(i));
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu[j].getTFIDF();
				double [] b =qdocu[j].getTFIDF();
				similarity+=Functions.cosine_similarity(a, b);
			}
			similarity/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}public static void printResulDocumentsOnZAxis(GesturesLibrary gesturesLibrary, int dn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		FrequencyWrapper [] qdocu=ZLDAVectors[dn];
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			double similarity=0.0;
			FrequencyWrapper [] docu=ZLDAVectors[i];
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('Z', gesturesLibrary.getGestureFileNumber(i));
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu[j].getTFIDF();
				double [] b =qdocu[j].getTFIDF();
				similarity+=Functions.cosine_similarity(a, b);
			}
			similarity/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}public static void printResulDocumentsOnWAxis(GesturesLibrary gesturesLibrary, int dn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		FrequencyWrapper [] qdocu=WLDAVectors[dn];
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			double similarity=0.0;
			FrequencyWrapper [] docu=WLDAVectors[i];
			MultiVriateDataDocument mvd=gesturesLibrary.getMultivariateData('W', gesturesLibrary.getGestureFileNumber(i));
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu[j].getTFIDF();
				double [] b =qdocu[j].getTFIDF();
				similarity+=Functions.cosine_similarity(a, b);
			}
			similarity/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			Result r=new Result(mvd, similarity);
			resultSet.addResult(r);
		}
		resultSet.printTop10();
	}
	public static void printResulGESTURE(GesturesLibrary gesturesLibrary, int gn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ResultSet resultSet=new ResultSet();
		
		FrequencyWrapper [] qdocu=XLDAVectors[gn];
		FrequencyWrapper [] qdocu2=YLDAVectors[gn];
		FrequencyWrapper [] qdocu3=ZLDAVectors[gn];
		FrequencyWrapper [] qdocu4=WLDAVectors[gn];
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			double similarity=0.0;
			Gesture g=gesturesLibrary.getGesture(i);
			
			double similarity1=0.0;
			FrequencyWrapper [] docu=XLDAVectors[i];
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu[j].getTFIDF();
				double [] b =qdocu[j].getTFIDF();
				similarity1+=Functions.cosine_similarity(a, b);
			}
			similarity1/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			
		similarity+=similarity1;
		
			double similarity2=0.0;
			FrequencyWrapper [] docu2=YLDAVectors[i];
			
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu2[j].getTFIDF();
				double [] b =qdocu2[j].getTFIDF();
				similarity2+=Functions.cosine_similarity(a, b);
			}
			similarity2/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		
			similarity+=similarity2;
			
			double similarity3=0.0;
			FrequencyWrapper [] docu3=ZLDAVectors[i];
			
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu3[j].getTFIDF();
				double [] b =qdocu3[j].getTFIDF();
				similarity3+=Functions.cosine_similarity(a, b);
			}
			similarity3/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			similarity+=similarity3;
			
			
		double similarity4=0.0;
			FrequencyWrapper [] docu4=WLDAVectors[i];
			
			for(int j=0;j<docu.length;j++)
			{
				double [] a=docu4[j].getTFIDF();
				double [] b =qdocu4[j].getTFIDF();
				similarity4+=Functions.cosine_similarity(a, b);
			}
			similarity4/=Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
			similarity+=similarity4;
			
			
			Result r =new Result(g, similarity/4);
			resultSet.addResult(r);
			
		}
		resultSet.printTop10();
	}
	
	
	
}
