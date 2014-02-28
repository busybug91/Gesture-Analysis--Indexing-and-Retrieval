package phase3;
import phase1.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.Gesture;
import phase1.GesturesLibrary;
import phase1.MultiVriateDataDocument;
import phase1.WordDictionary;
import phase2.Constants;
import phase2.Functions;
import phase2.Query;


public class LSH 
{
	private  static GesturesLibrary gesturesLibrary;
	private static ArrayList<String> gestureDimensions;
	public static double[][] gestureData;
	private static double [] queryData;
	private static int layers;
	private static int K;
	private static Random rand=new Random(System.nanoTime());
	public static ArrayList<LSHResult>index;
	private static Gesture qGesture;
	//public static queryData
	public static double[][] generateData(GesturesLibrary gesturesLibrary1) throws IOException
	{
		gesturesLibrary=gesturesLibrary1;
		String []gestureDimensions1=phase3.Functions.generateGestureDimensions(gesturesLibrary)[0];
		gestureDimensions=new ArrayList<String>();
		for(int i=0;i<gestureDimensions1.length;i++)
		{
			gestureDimensions.add(gestureDimensions1[i]);
		}
		System.out.println(gestureDimensions);
		gestureData=phase2.Functions.convertDoubleArraySmall(phase3.Functions.generateGestureData(gesturesLibrary, gestureDimensions));
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			path=path+"/gestureVectors/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\gestureVectors\\";
		}	
		phase2.Functions.saveFileWithNumStringArray(path, "gestureVectors.csv",phase3.Functions.converDouble2DtoString2D(gestureData));
		return gestureData;
	}
	@Deprecated
	public static Gesture initializeQuery(GesturesLibrary gesturesLibrary,String wPath, String xPath, String yPath, String zPath) throws IOException
	{
		MultiVriateDataDocument queryWMultiVariateDataDocument=new MultiVriateDataDocument(wPath,'W');
		MultiVriateDataDocument queryXMultiVariateDataDocument=new MultiVriateDataDocument(xPath,'X');
		MultiVriateDataDocument queryYMultiVariateDataDocument=new MultiVriateDataDocument(yPath,'Y');
		MultiVriateDataDocument queryZMultiVariateDataDocument=new MultiVriateDataDocument(zPath,'Z');
		
		
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
	public static double[] generateQueryData()
	{
		queryData=new double [gestureDimensions.size()];	
		
//		System.out.println(qGesture);
//		System.out.println(gestureDimensions);
		for(int j=0;j<qGesture.getWMultivariateData().getAllUnivariateDataDocument().size();j++)
		{
			WordDictionary curWordDictionary=qGesture.getWMultivariateData().getUnivariateDataDocument(j).getWords();
			for(int k=0;k<curWordDictionary.size();k++)
			{
				String curWord=curWordDictionary.getWord(k);
				String curWord1="W|S"+j+"|"+curWord;
				if(gestureDimensions.contains(curWord1))
				{
					queryData[gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
				}
				else
					System.err.println("W: Error Word not found in the gesture Dimensions");
			}
		}
		
		for(int j=0;j<qGesture.getXMultivariateData().getAllUnivariateDataDocument().size();j++)
		{
			WordDictionary curWordDictionary=qGesture.getXMultivariateData().getUnivariateDataDocument(j).getWords();
			for(int k=0;k<curWordDictionary.size();k++)
			{
				String curWord=curWordDictionary.getWord(k);
				String curWord1="X|S"+j+"|"+curWord;
				if(gestureDimensions.contains(curWord1))
				{
					queryData[gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
				}
				else
					System.err.println("X: Error Word not found in the gesture Dimensions");
			}
		}
		
		for(int j=0;j<qGesture.getYMultivariateData().getAllUnivariateDataDocument().size();j++)
		{
			WordDictionary curWordDictionary=qGesture.getYMultivariateData().getUnivariateDataDocument(j).getWords();
			for(int k=0;k<curWordDictionary.size();k++)
			{
				String curWord=curWordDictionary.getWord(k);
				String curWord1="Y|S"+j+"|"+curWord;
				if(gestureDimensions.contains(curWord1))
				{
					queryData[gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
				}
				else
					System.err.println("Y: Error Word not found in the gesture Dimensions");
			}
		}
		for(int j=0;j<qGesture.getZMultivariateData().getAllUnivariateDataDocument().size();j++)
		{
			WordDictionary curWordDictionary=qGesture.getZMultivariateData().getUnivariateDataDocument(j).getWords();
			for(int k=0;k<curWordDictionary.size();k++)
			{
				String curWord=curWordDictionary.getWord(k);
				String curWord1="Z|S"+j+"|"+curWord;
				if(gestureDimensions.contains(curWord1))
				{
					queryData[gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
				}
				else
					System.err.println("Z: Error Word not found in the gesture Dimensions");
			}
		}
		return queryData;
	}
	public static long hashQueryKTimes(int K, int layer)
	{
		int []queryHashes=new int[K];
		LSHResult lshResult=index.get(layer);
		//print1Ddouble(queryData);
		for(int i=0; i<lshResult.randomVectors.size();i++)
		{
			double[] randomVector=lshResult.randomVectors.get(i);
			queryHashes[i]=projectQuery(queryData, randomVector);
			//System.out.println("For layer "+layer+" hash value of query for k:"+i+"is "+queryHashes[i]);
		}
		long gHash=Math.abs((long)Arrays.hashCode(queryHashes));
		if(index.get(layer).gHashTable.containsKey(gHash))
		{
			index.get(layer).gHashTable.get(gHash).add(qGesture.getGestureNumber());
		}
		else
		{
			ArrayList<Integer> bucketItems=new ArrayList<Integer>();
			bucketItems.add(qGesture.getGestureNumber());
			index.get(layer).gHashTable.put(gHash, bucketItems);
		}	
		sortIndex();
		return Math.abs((long)Arrays.hashCode(queryHashes));
	}
	public static void hashQuery()
	{
		for(int i=0;i<layers;i++)
		{
			hashQueryKTimes(K, i);
		}
	}
	public static void gestureSearch() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter path for X component for query gesture");
		String xPath=sc.nextLine();
		System.out.println("Enter path for Y component for query gesture");
		String yPath=sc.nextLine();
		System.out.println("Enter path for Z component for query gesture");
		String zPath=sc.nextLine();
		System.out.println("Enter path for W component for query gesture");
		String wPath=sc.nextLine();
		qGesture=Query.initializeQuery(gesturesLibrary, xPath, yPath, zPath,wPath);
		generateQueryData();
		hashQuery();
		printIndex();
		System.out.println("Enter the number of nearest neighbors");
		int n=sc.nextInt();
		findKNN(n);
	}
	public static ArrayList<Integer> getAllQueryNeighbors(int n)
	{
		ArrayList<Integer> allNeighbors=new ArrayList<Integer>();
		int nl=1;
		while(allNeighbors.size()<n)
		{
			for(int i=0;i<layers;i++)
			{
				allNeighbors.addAll(index.get(i).getQueryNeighbors1(nl));	
			}
			nl++;
			HashSet<Integer> hashSet=new HashSet<Integer>();
			hashSet.addAll(allNeighbors);
			allNeighbors.clear();
			allNeighbors.addAll(hashSet);
			allNeighbors.remove(allNeighbors.indexOf(qGesture.getGestureNumber()));
			System.out.println("Unique:"+allNeighbors);
		}
			
		System.out.println("Union: "+allNeighbors);
		return allNeighbors;
	}
	private static void findKNN(int n)
	{
		double similarity=0.0;
		ResultSet resultSet =new ResultSet();
		ArrayList<Integer> allQueryNeighbors=getAllQueryNeighbors(n);
		
		for(int i=0;i<allQueryNeighbors.size();i++)
		{
//			System.out.println("Index: "+gesturesLibrary.getGestureIndex(allQueryNeighbors.get(i))+" Gesture Number: "+allQueryNeighbors.get(i));
			double []gesture=gestureData[gesturesLibrary.getGestureIndex(allQueryNeighbors.get(i))];
			similarity=Functions.cosine_similarity(gesture, queryData);
			resultSet.addResult(new Result(gesturesLibrary.getGestureByFileNumber(allQueryNeighbors.get(i)), similarity));
		}
		resultSet.sort();
		for(int i=0;i<n;i++)
		{
		Result result=resultSet.getResult(i);
		System.out.println(i+": "+result.getGestureNumber()+"\t"+result.getDistance());
		}
		return;
	}

	private static void sortIndex()
	{
		for(int i=0;i<index.size();i++)
		{
			LSHResult lshResult=index.get(i);
			lshResult.gHashTable=sortHashTable(lshResult.gHashTable);
		}
	}
	private static void sortNew()
	{
		for(int i=0;i<index.size();i++)
		{
			LSHResult lshResult=index.get(i);
			lshResult.gHashTable=sortHashTableNew(lshResult);
		}	
	}
	public static LinkedHashMap<Long,ArrayList<Integer>> sortHashTable(LinkedHashMap<Long,ArrayList<Integer>> input)
	{
		LinkedHashMap<Long,ArrayList<Integer>> output= new LinkedHashMap<Long,ArrayList<Integer>>();
		Set<Long> s=input.keySet();
		ArrayList<Long> temp=new ArrayList<Long>();
		temp.addAll(s);
		Collections.sort(temp);
		for(int i=0;i<temp.size();i++)
		{
			output.put(temp.get(i), input.get((temp).get(i)));
		}
		return output;	
	}
	public static LinkedHashMap<Long,ArrayList<Integer>> sortHashTableNew(LSHResult lshResult)
	{
		LinkedHashMap<Long,ArrayList<Integer>> output= new LinkedHashMap<Long,ArrayList<Integer>>();
		long indexPoint=lshResult.getQueryIndexLocation();
		LinkedHashMap<Long,ArrayList<Integer>> before= new LinkedHashMap<Long,ArrayList<Integer>>();
		LinkedHashMap<Long,ArrayList<Integer>> after= new LinkedHashMap<Long,ArrayList<Integer>>();
//		output.
//		Set<Long> s=input.keySet();
		ArrayList<Long> temp=new ArrayList<Long>();
//		temp.addAll(s);
		Collections.sort(temp);
		for(int i=0;i<temp.size();i++)
		{
//			output.put(temp.get(i), input.get((temp).get(i)));
		}
		return output;	
	}
	public static int projectQuery(double randomVector[], double queryData[])
	{
		int w=gestureDimensions.size()/4;
		double randomVariable =(double)rand.nextInt(w);
		double hashValue=((phase2.Functions.dot_product(queryData, randomVector)+randomVariable))/w;
		hashValue=(int)Math.round(hashValue);
		return (int)hashValue;
	}
	public static double[] randomVector()
	{
		//		double randomVector[]=new double[500];
		//		for(int i=0;i<500;i++)
		//		{
		double randomVector[]=new double[gestureDimensions.size()];
		for(int i=0;i<randomVector.length;i++)
		{
			randomVector[i]=rand.nextGaussian();
		}
		return randomVector;
	}
	public static int[] projectGestures(double[] randomVector)
	{
		Random rand=new Random();
		int []hashTable=new int[gestureData.length];
		
		System.out.println(gestureData.length);
		for(int i=0;i<gestureData.length;i++)
		{
			int w=gestureDimensions.size()/32;
			//double w=0.2;
			double randomVariable =(double)rand.nextInt(w);
		//	double randomVariable =0.1;
			double hashValue=((phase2.Functions.dot_product(gestureData[i], randomVector)+randomVariable))/w;
			hashTable[i]=(int)Math.round(hashValue);
		}
		//print1DInt(hashTable);
		return hashTable ;
	}
	//	public static double[]  getProjectedGestureData()
	//	{
	//		double[] randomVector=randomVector();
	//		double [] projectedData=new double[gestureData.length];
	//		for(int i=0;i<gestureData.length;i++)
	//		{
	//			projectedData[i]=projectGesture(gestureData[i], randomVector, 80, 0.2);
	//		}
	//		return projectedData;
	//	}
	public static LSHResult hashGesturesKTimes(int k)
	{
		ArrayList<double[]>randomVectors=new ArrayList<double[]>();
		//implies a layer
		long hashG;
		int [][]kHashTables=new int[k][];
		int []gestureHashValues=new int[k];
		LinkedHashMap<Long,ArrayList<Integer>> finalHashTable= new LinkedHashMap<Long,ArrayList<Integer>>();
		for(int i=0;i<k;i++)
		{
			double[] randomVector=randomVector();
			randomVectors.add(randomVector);
			kHashTables[i]=projectGestures(randomVector);
						//System.out.println(i+" hash value is "+hashValues[i]);
		}
		for(int l=0;l<gestureData.length;l++)
		{
			for(int j=0;j<kHashTables.length;j++)
			{
				int []tempTable=kHashTables[j];
				gestureHashValues[j]=tempTable[l];
			}
			hashG= Math.abs((long)(Arrays.hashCode(gestureHashValues)));
			if(finalHashTable.containsKey(hashG))
			{
				finalHashTable.get(hashG).add(gesturesLibrary.getGesture(l).getGestureNumber());
			}
			else
			{
				ArrayList<Integer> bucketItems=new ArrayList<Integer>();
				bucketItems.add(gesturesLibrary.getGesture(l).getGestureNumber());
				finalHashTable.put(hashG, bucketItems);
			}			
		}
		return new LSHResult(finalHashTable,randomVectors);		
	}
	//	@Deprecated
	//	public static HashMap<Integer,ArrayList<Integer>> hashAllGesturesKTimes()
	//	{
	//		int allFinalHashes[]=new int[gestureData.length];
	//		for(int i=0;i<gestureData.length;i++)
	//		{	
	//			System.out.println("For gesture number "+i);
	//			int w=gestureData[i].length/4;
	//			allFinalHashes[i]=hashGestureKTimes(gestureData[i], 5, 4);
	//		}
	//		HashMap<Integer,ArrayList<Integer>> hashTable= new HashMap<Integer,ArrayList<Integer>>();
	//		for(int j=0;j<allFinalHashes.length;j++)
	//		{
	//			if(hashTable.containsKey(allFinalHashes[j]))
	//			{
	//				System.out.println("Same key");
	//				hashTable.get(allFinalHashes[j]).add(j);
	//			}
	//			else
	//			{
	//				ArrayList<Integer> bucketItems=new ArrayList<Integer>();
	//				bucketItems.add(j);
	//				hashTable.put(allFinalHashes[j], bucketItems);
	//			}			
	//		}
	//		return hashTable;
	//	}
	public static void printHashTable(HashMap<Long,ArrayList<Integer>> hashTable)
	{
		Iterator<Long> itr=hashTable.keySet().iterator();
		while(itr.hasNext())
		{
			long bucket=(Long) itr.next();
			System.out.println("In bucket number "+bucket+" the gestures are "+hashTable.get(bucket));
		}
	}
	public static void prepareIndex(int l,int k)
	{
		layers=l;
		K=k;
		index= new ArrayList<LSHResult>();
		for(int i=0;i<layers;i++)
		{
			LSHResult lshResult=hashGesturesKTimes(k);
			index.add(lshResult);
		}
		sortIndex();
	}
	public static void printIndex()
	{
		for(int i =0;i<index.size();i++)
		{
			System.out.println("Table Number "+i);
			printHashTable(index.get(i).gHashTable);
		}
	}
	public static void printRandomVectors()
	{
		for(int i =0;i<index.size();i++)
		{
			System.out.println("Random vectors for layer "+i);
			ArrayList<double[]> randomVectors=index.get(i).randomVectors;
			for(int j=0;j<randomVectors.size();j++)
			{
				print1Ddouble(randomVectors.get(j));
			}
			System.out.println();
		}
	}
	public static void print1Ddouble(double []data)
	{
		for(int i=0;i<data.length;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}
	public static void print1DInt(int []data)
	{
		for(int i=0;i<data.length;i++)
		{
			String s=String.format("%5d", data[i]);
			System.out.print(s);
		}
		System.out.println();
	}

	//	
	//	public static int[] hashedGestures(double [] projectedGestureData)
	//	{
	//		int ret[] =new int [projectedGestureData.length];
	//		double median=getMedian(projectedGestureData);
	//		for(int i=0;i<projectedGestureData.length;i++)
	//		{
	//			if(projectedGestureData[i]>median)
	//			{
	//				ret[i]=1;
	//			}
	//		}
	//		return ret;
	//	}
	public static double getMedian(double[] projectedData)
	{
		double max=projectedData[0];
		double min=projectedData[0];
		for(int i=0;i<projectedData.length;i++)
		{
			if(min>projectedData[i])
				min=projectedData[i];
			if(max<projectedData[i])
				max=projectedData[i];
		}
		return max/min;
	}
}