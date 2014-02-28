package phase2;
import java.io.IOException;

import phase1.Gesture;
import phase1.GesturesLibrary;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

class LatentGestureDiscovery
{
	 static double [][] PCASimilarityOnPCA;
	 static double [][] PCASimilarityOnSVD;
	 static double [][] SVDSimilarityOnPCA;
	 static double [][] SVDSimilarityOnSVD;

	 static double [][] PCASimilarityOnTFIDF;
	 static double [][] PCASimilarityOnTFIDF1;
 static double [][] SVDSimilarityOnTFIDF;
	 static double [][] SVDSimilarityOnTFIDF1;


	 static double [][] rfPCASimilarityOnPCA;
 static double [][] rfPCASimilarityOnSVD;
	 static double [][] rfSVDSimilarityOnPCA;
	 static double [][] rfSVDSimilarityOnSVD;

	 static double [][] rfPCASimilarityOnTFIDF;
	 static double [][] rfPCASimilarityOnTFIDF1;
	 static double [][] rfSVDSimilarityOnTFIDF;
	 static double [][] rfSVDSimilarityOnTFIDF1;

	private static double [][] gestureSimilarityOnPCA;
	private static double [][] gestureSimilarityOnSVD;
	private static double [][] gestureSimilarityOnTFIDF;
	private static double [][] gestureSimilarityOnTFIDF1;
	public static double[][] getPCASimilarityOnSVD()
	{
		return PCASimilarityOnSVD;
	}
	public static void createGestureSimillarityOnPCA() throws MatlabConnectionException, MatlabInvocationException, IOException
	{	
		//On basis of top 3 semantics from PCA
		gestureSimilarityOnPCA =new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES][];
		for(int i=0; i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			gestureSimilarityOnPCA[i]=new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES];
			for(int j=0; j<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;j++)
			{
				gestureSimilarityOnPCA[i][j]=Query.compareGesturesPCA(i, j);
			}
		}
	}
	public static void createGestureSimillarityOnTFIDF(GesturesLibrary gestureLibrary) throws MatlabConnectionException, MatlabInvocationException, IOException
	{	
		//On basis of top 3 semantics from PCA
		gestureSimilarityOnTFIDF =new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES][];
		for(int i=0; i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture agesture=gestureLibrary.getGesture(i);
			gestureSimilarityOnTFIDF[i]=new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES];
			for(int j=0; j<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;j++)
			{
				Gesture bgesture=gestureLibrary.getGesture(j);
				gestureSimilarityOnTFIDF[i][j]=phase1.Functions.compareGestureOnTFIDFOnSimilarity(agesture, bgesture);
			}
		}
	}
	public static void createGestureSimillarityOnTFIDF1(GesturesLibrary gestureLibrary) throws MatlabConnectionException, MatlabInvocationException, IOException
	{	
		//On basis of top 3 semantics from PCA
		gestureSimilarityOnTFIDF1 =new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES][];
		for(int i=0; i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture agesture=gestureLibrary.getGesture(i);
			gestureSimilarityOnTFIDF1[i]=new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES];
			for(int j=0; j<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;j++)
			{
				Gesture bgesture=gestureLibrary.getGesture(j);
				gestureSimilarityOnTFIDF1[i][j]=phase1.Functions.compareGestureOnTFIDF1OnSimilarity(agesture, bgesture);
			}
		}
	}
	public static void createGestureSimillarityOnSVD() throws MatlabConnectionException, MatlabInvocationException, IOException
	{	
		gestureSimilarityOnSVD=null;
		//On basis of top 3 semantics from PCA
		gestureSimilarityOnSVD =new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES][];
		for(int i=0; i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			gestureSimilarityOnSVD[i]=new double[phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES];
			for(int j=0; j<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;j++)
			{
				gestureSimilarityOnSVD[i][j]=QuerySVD.compareGesturesSVD(i, j);
			//	if(QuerySVD.compareGesturesSVD(i, j)>1.001)
				System.out.println(QuerySVD.compareGesturesSVD(i, j)+"For i: "+i+" and J: "+j);
			}
		}
	}
	public static void printGestureSimilarityOnPCA()
	{
		System.out.println(Functions.printDoubleArray1(gestureSimilarityOnPCA));
	}
	public static void printGestureSimilarityOnTFIDF()
	{
		//System.out.println(gestureSimilarityOnTFIDF.length);
		System.out.println(Functions.printDoubleArray1(gestureSimilarityOnTFIDF));
	}
	public static void printGestureSimilarityOnTFIDF1()
	{
		//System.out.println(gestureSimilarityOnTFIDF.length);
		System.out.println(Functions.printDoubleArray1(gestureSimilarityOnTFIDF1));
	}
	public static void printGestureSimilarityOnSVD()
	{
		System.out.println(Functions.printDoubleArray1(gestureSimilarityOnSVD));
	}
	public static void createPCAGestureSimilarityOnPCA() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnPCA!=null) {
			PCASimilarityOnPCA=PCA.getPCADataNbyp(Functions.convertDoubleArray(gestureSimilarityOnPCA));	
			rfPCASimilarityOnPCA=PCA.getRightFactorNbyP(Functions.convertDoubleArray(gestureSimilarityOnPCA));
		}
		else
			System.out.println("Gesture Similarity is empty so PCA can't be calculated");
	}
	public static void printPCASimilarityOnPCA()
	{
		if(PCASimilarityOnPCA!=null)
			System.out.println(Functions.printDoubleArray1(PCASimilarityOnPCA));
		else
			System.out.println("PCA Similarity is empty");
	}
	public static void printRFPCASimilarityOnPCA()
	{
		if(rfPCASimilarityOnPCA!=null)
			System.out.println(Functions.printDoubleArray1(rfPCASimilarityOnPCA));
		else
			System.out.println("PCA Similarity is empty");
	}
	public static void createSVDGestureSimilarityOnPCA() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnPCA!=null){	
			SVDSimilarityOnPCA=SVD.getSVDData(Functions.convertDoubleArray(gestureSimilarityOnPCA));	
			rfSVDSimilarityOnPCA=SVD.getRightFactor(Functions.convertDoubleArray(gestureSimilarityOnPCA));	
		}
		else
			System.out.println("Gesture Similarity is empty so SVD can't be calculated");
	}
	public static void printSVDSimilarityOnPCA()
	{
		if(SVDSimilarityOnPCA!=null)
			System.out.println(Functions.printDoubleArray1(SVDSimilarityOnPCA));
		else
			System.out.println("SVD Similarity is empty");
	}
	public static void printRFSVDSimilarityOnPCA()
	{
		if(rfSVDSimilarityOnPCA!=null)
			System.out.println(Functions.printDoubleArray1(rfSVDSimilarityOnPCA));
		else
			System.out.println("SVD Similarity is empty");
	}

	public static void createPCAGestureSimilarityOnSVD() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnSVD!=null){
			PCASimilarityOnSVD=PCA.getPCADataNbyp(Functions.convertDoubleArray(gestureSimilarityOnSVD));	
			rfPCASimilarityOnSVD=PCA.getRightFactorNbyP(Functions.convertDoubleArray(gestureSimilarityOnSVD));	
		}
		else
			System.out.println("Gesture Similarity is empty so PCA can't be calculated");
	}
	public static void printPCASimilarityOnSVD()
	{
		if(PCASimilarityOnSVD!=null)
			System.out.println(Functions.printDoubleArray1(PCASimilarityOnSVD));
		else
			System.out.println("PCA Similarity is empty");
	}
	public static void printRFPCASimilarityOnSVD()
	{
		if(rfPCASimilarityOnSVD!=null)
			System.out.println(Functions.printDoubleArray1(rfPCASimilarityOnSVD));
		else
			System.out.println("PCA Similarity is empty");
	}
	public static void createSVDGestureSimilarityOnSVD() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnSVD!=null){
			SVDSimilarityOnSVD=SVD.getSVDData(Functions.convertDoubleArray(gestureSimilarityOnSVD));	
			rfSVDSimilarityOnSVD=SVD.getRightFactor(Functions.convertDoubleArray(gestureSimilarityOnSVD));
		}
		else
			System.out.println("Gesture Similarity is empty so SVD can't be calculated");
	}
	public static void printSVDSimilarityOnSVD()
	{
		if(SVDSimilarityOnSVD!=null)
			System.out.println(Functions.printDoubleArray1(SVDSimilarityOnSVD));
		else
			System.out.println("SVD Similarity is empty");
	}
	public static void printRFSVDSimilarityOnSVD()
	{
		if(rfSVDSimilarityOnSVD!=null)
			System.out.println(Functions.printDoubleArray1(rfSVDSimilarityOnSVD));
		else
			System.out.println("SVD Similarity is empty");
	}


	public static void createSVDGestureSimilarityOnTFIDF() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnTFIDF!=null)
		{
			SVDSimilarityOnTFIDF=SVD.getSVDData(Functions.convertDoubleArray(gestureSimilarityOnTFIDF));	
			rfSVDSimilarityOnTFIDF=SVD.getRightFactor(Functions.convertDoubleArray(gestureSimilarityOnTFIDF));	
		}
		else
			System.out.println("Gesture Similarity is empty so SVD can't be calculated");
	}
	public static void printSVDSimilarityOnTFIDF()
	{
		if(SVDSimilarityOnTFIDF!=null)
			System.out.println(Functions.printDoubleArray1(SVDSimilarityOnTFIDF));
		else
			System.out.println("TFIDF Similarity is empty");
	}
	public static void printRFSVDSimilarityOnTFIDF()
	{
		if(rfSVDSimilarityOnTFIDF!=null)
			System.out.println(Functions.printDoubleArray1(rfSVDSimilarityOnTFIDF));
		else
			System.out.println("TFIDF Similarity is empty");
	}
	public static void createSVDGestureSimilarityOnTFIDF1() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnTFIDF1!=null)
		{
			SVDSimilarityOnTFIDF1=SVD.getSVDData(Functions.convertDoubleArray(gestureSimilarityOnTFIDF1));	
		rfSVDSimilarityOnTFIDF1=SVD.getRightFactor(Functions.convertDoubleArray(gestureSimilarityOnTFIDF1));	
	}
		else
			System.out.println("Gesture Similarity is empty so SVD can't be calculated");
	}
	public static void printSVDSimilarityOnTFIDF1()
	{
		if(SVDSimilarityOnTFIDF1!=null)
			System.out.println(Functions.printDoubleArray1(SVDSimilarityOnTFIDF1));
		else
			System.out.println("TFIDF1 Similarity is empty");
	}
	public static void printRFSVDSimilarityOnTFIDF1()
	{
		if(rfSVDSimilarityOnTFIDF1!=null)
			System.out.println(Functions.printDoubleArray1(rfSVDSimilarityOnTFIDF1));
		else
			System.out.println("TFIDF1 Similarity is empty");
	}
	public static void createPCAGestureSimilarityOnTFIDF() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnTFIDF!=null)
		{
			PCASimilarityOnTFIDF=PCA.getPCADataNbyp(Functions.convertDoubleArray(gestureSimilarityOnTFIDF));	
			rfPCASimilarityOnTFIDF=PCA.getRightFactorNbyP(Functions.convertDoubleArray(gestureSimilarityOnTFIDF));	
	}
		else
			System.out.println("Gesture Similarity is empty so PCA can't be calculated");
	}
	public static void printPCASimilarityOnTFIDF()
	{
		if(PCASimilarityOnTFIDF!=null)
			System.out.println(Functions.printDoubleArray1(PCASimilarityOnTFIDF));
		else
			System.out.println("TFIDF Similarity is empty");
	}
	public static void printRFPCASimilarityOnTFIDF()
	{
		if(rfPCASimilarityOnTFIDF!=null)
			System.out.println(Functions.printDoubleArray1(rfPCASimilarityOnTFIDF));
		else
			System.out.println("TFIDF Similarity is empty");
	}
	
	public static void createPCAGestureSimilarityOnTFIDF1() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		if(gestureSimilarityOnTFIDF1!=null)
		{
			PCASimilarityOnTFIDF1=PCA.getPCADataNbyp(Functions.convertDoubleArray(gestureSimilarityOnTFIDF1));	
		rfPCASimilarityOnTFIDF1=PCA.getRightFactorNbyP(Functions.convertDoubleArray(gestureSimilarityOnTFIDF1));	
		
	}
		else
			System.out.println("Gesture Similarity is empty so PCA can't be calculated");
	}
	public static void printPCASimilarityOnTFIDF1()
	{
		if(PCASimilarityOnTFIDF1!=null)
			System.out.println(Functions.printDoubleArray1(PCASimilarityOnTFIDF1));
		else
			System.out.println("TFIDF1 Similarity is empty");
	}
	public static void printRFPCASimilarityOnTFIDF1()
	{
		if(rfPCASimilarityOnTFIDF1!=null)
			System.out.println(Functions.printDoubleArray1(rfPCASimilarityOnTFIDF1));
		else
			System.out.println("TFIDF1 Similarity is empty");
	}
}