package phase3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.Gesture;
import phase1.GesturesLibrary;
import phase1.Result;
import phase1.ResultSet;
import phase2.Query;

public class TrainingData 
{
	private static ArrayList<Integer> trainingDataNumbers;
	private  static GesturesLibrary gesturesLibrary;
	private static ArrayList<String> gestureDimensions;
	private static double[][] gestureData;
	private static ArrayList<String> gesturePCADimensions;
	private static double[][] gesturePCAData;
	private static String[] labels;
	private static int number_of_types_of_gestures;
	public static ArrayList<String> allLables;

	public static ArrayList<Integer> getTrainingDataNumbers()
	{
		return trainingDataNumbers;
	}
	public static ArrayList<String> getGesturePCAdimensions()
	{
		return gesturePCADimensions;
	}
	public static double[][] getGesturePCAData()
	{
		return gestureData;
	}
	public static GesturesLibrary getGestureLibrary()
	{
		return gesturesLibrary;
	}
	public static ArrayList<String> getAllLables()
	{
		return allLables;
	}
	public static String[] getTainingLabels()
	{
		return labels;
	}
	@SuppressWarnings("resource")
	public static ArrayList<Integer> getTrainingDataNumbers(String path) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = null;
		ArrayList<Integer> numbers=new ArrayList<Integer>();
		while ((line = reader.readLine()) != null) 
		{
			String[] s=line.split(",");
			String s1=s[0].trim();
			numbers.add(new Integer(s1));
		}
		return numbers;
	}

	@SuppressWarnings("resource")
	private static void assignLabels1(String path) throws NumberFormatException, IOException
	{
		ArrayList<String> nl=new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = null;
		while ((line = reader.readLine()) != null) 
		{
			String[] s=line.split(",");
			String label=s[1].trim().toUpperCase();
			String gesture=s[0].trim();
			int gesNum=Integer.parseInt(gesture);
			int index=gesturesLibrary.getGestureIndex(gesNum);
			labels[index]=label;
			if(!nl.contains(label))
			{
				nl.add(label);
			}
		}
		number_of_types_of_gestures=nl.size();
		allLables=nl;
	}
	public  static void setTrainingData(int typeOfGestures) throws IOException, MatlabConnectionException, MatlabInvocationException
	{
		gesturesLibrary=new GesturesLibrary();
		labels=new String[gesturesLibrary.size()];
		Query.generatePCAData(gesturesLibrary);
		number_of_types_of_gestures=typeOfGestures;
		assignLabels();
		generateData(gesturesLibrary);
	}
	public  static void setTrainingData(String path) throws IOException, MatlabConnectionException, MatlabInvocationException
	{
		trainingDataNumbers=getTrainingDataNumbers(path);
		gesturesLibrary=new GesturesLibrary(trainingDataNumbers);
		labels=new String[gesturesLibrary.size()];
		Query.generatePCAData(gesturesLibrary);
		assignLabels1(path);
		System.out.println("set training called");
		generateData(gesturesLibrary);
	}
	private static void generateData(GesturesLibrary gesturesLibrary) throws IOException
	{
		String []gestureDimensions1=Functions.generateGestureDimensions(gesturesLibrary)[0];
		gestureDimensions=new ArrayList<String>();
		for(int i=0;i<gestureDimensions1.length;i++)
		{
			gestureDimensions.add(gestureDimensions1[i]);
		}
	//	System.out.println(gestureDimensions);
		gestureData=phase2.Functions.convertDoubleArraySmall(Functions.generateGestureData(gesturesLibrary, gestureDimensions));
		String path=System.getProperty("user.dir");
		if(path.contains("/"))
		{ 
			path=path+"/gestureVectors/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\gestureVectors\\";
		}	
		phase2.Functions.saveFileWithNumStringArray(path, "gestureVectors.csv",Functions.converDouble2DtoString2D(gestureData));
		gesturePCADimensions=Query.getDimensions();
		gesturePCAData=Query.getCompleteData();
		phase2.Functions.saveFileWithNumStringArray(path, "gesturePCAVectors.csv",Functions.converDouble2DtoString2D(gesturePCAData));
		System.out.println("Gesture Data Called");

	}
	private static void assignLabels()
	{
		allLables=new ArrayList<String>();
		Scanner sc=ScannerInterface.getScanner();
		for(int i=0;i<number_of_types_of_gestures;i++)
		{
			System.out.println("Enter gesture staring index for ["+i+"] category of gestures.");
			int start=sc.nextInt();
			System.out.println("Enter gesture last index for ["+i+"] category of gestures.");
			int end=sc.nextInt();
			allLables.add(i+"");
			for(int j=start;j<=end;j++)
			{
				int index=gesturesLibrary.getGestureIndex(j);
				labels[index]=i+"";
			}
		}

	}
	
	public static String classify(int k) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		Scanner sc= ScannerInterface.getScanner();
		System.out.println("Enter path for X component for query gesture");
	//	String xPath=sc.nextLine();
		String xPath="/Users/nitin/Google Drive/workspace/mwdb2/3classdata/X/11.csv";
		System.out.println("Enter path for Y component for query gesture");
	//	String yPath=sc.nextLine();
		String yPath="/Users/nitin/Google Drive/workspace/mwdb2/3classdata/Y/11.csv";

		System.out.println("Enter path for Z component for query gesture");
	//	String zPath=sc.nextLine();
		String zPath="/Users/nitin/Google Drive/workspace/mwdb2/3classdata/Z/11.csv";

		System.out.println("Enter path for W component for query gesture");
		//String wPath=sc.nextLine();
		String wPath="/Users/nitin/Google Drive/workspace/mwdb2/3classdata/W/11.csv";
		Gesture queryGesture=Query.initializeQuery(gesturesLibrary, xPath, yPath, zPath, wPath);
		//System.out.print(queryGesture);
		
		//LSH.print1Ddouble(Data.getQueryData(queryGesture));
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g=gesturesLibrary.getGesture(i);
			Double similarity=Query.compareGestures(gesturesLibrary, queryGesture, i);
			Result r=new Result(g, similarity);
			resultSet.addResult(r);
		}
		resultSet.sort();
		HashMap<String, Integer > results=new HashMap<String, Integer>();
		for(int i=0;i<k;i++)
		{
			System.out.println(i+" Gesture Number: "+resultSet.getResult(i).getGestureNumber()+" Similarity: "+resultSet.getResult(i).getDistance());
			String key=labels[gesturesLibrary.getGestureIndex(resultSet.getResult(i).getGestureNumber())];
			System.out.println("Label of similar gesture is: "+key);
			if(results.containsKey(key))
			{
				Integer value=results.remove(key);
				results.put(key, value++);
			}
			else
				results.put(key, new Integer(1));
		}
		int max=0;
		String category="";
		for(String key: results.keySet())
		{
			Integer value=results.get(key);
			if(max<value)
			{
				max=value;
				category=key+"";
			}
		}
		if(category.equals(""))
		{
			category="Cannot be classified";
		}
		return category;
	}
	public static double[][] getLabledData(ArrayList<String> lab)
	{
		ArrayList<Integer> gestures=new ArrayList<Integer>();
		for(int i=0;i<labels.length;i++)
		{
			if(lab.contains(labels[i]))
				gestures.add(i);
		}
		double [][] retData=new double[gestures.size()][gestureDimensions.size()];
		int count=0;
		for(int i=0;i<gestureData.length;i++)
		{
			if(gestures.contains(i))
			{
				gestures.remove(new Integer(i));
				retData[count]=gestureData[i];
				count++;
			}
		}
		return retData;
	}
	public static double[][] getLabledPCAData(ArrayList<String> lab)
	{
		ArrayList<Integer> gestures=new ArrayList<Integer>();
		for(int i=0;i<labels.length;i++)
		{
			if(lab.contains(labels[i]))
				gestures.add(i);
		}
		double [][] retData=new double[gestures.size()][gesturePCADimensions.size()];
		int count=0;
		for(int i=0;i<gesturePCAData.length;i++)
		{
			if(gestures.contains(i))
			{
				gestures.remove(new Integer(i));
				retData[count]=gesturePCAData[i];
				count++;
			}
		}
		return retData;
	}
}
