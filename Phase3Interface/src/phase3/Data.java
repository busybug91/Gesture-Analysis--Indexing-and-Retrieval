package phase3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.Consatnts;
import phase1.Functions;
import phase1.Gesture;
import phase1.GesturesLibrary;
import phase1.MultiVriateDataDocument;
import phase1.Result;
import phase1.ResultSet;
import phase2.Query;

public class Data 
{
	private static ArrayList<Gesture> queryGestures;
	private static ArrayList<Integer> gestureNumbers;
	public static void setData() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		queryGestures=new ArrayList<Gesture>();
		ArrayList<String> dirs=TrainingData.getGestureLibrary().getDirectories();
		System.out.println(dirs);
		gestureNumbers= Functions.searchDirectory(new File(Consatnts.PATH1+dirs.get(0)));
		System.out.println(gestureNumbers);
		for(int i=0;i<TrainingData.getTrainingDataNumbers().size();i++)
		{
			if(gestureNumbers.contains(TrainingData.getTrainingDataNumbers().get(i)))
			{
				gestureNumbers.remove(TrainingData.getTrainingDataNumbers().get(i));
			}
		}
		Collections.sort(gestureNumbers);
		System.out.println(gestureNumbers);
		for(int i=0;i<gestureNumbers.size();i++)
		{
			String Xpath=Consatnts.PATH1+"X/"+gestureNumbers.get(i)+".csv";
			String Ypath=Consatnts.PATH1+"Y/"+gestureNumbers.get(i)+".csv";
			String Zpath=Consatnts.PATH1+"Z/"+gestureNumbers.get(i)+".csv";
			String Wpath=Consatnts.PATH1+"W/"+gestureNumbers.get(i)+".csv";
			Gesture g =initializeQuery(TrainingData.getGestureLibrary(), Xpath,Ypath,Zpath,Wpath,gestureNumbers.get(i));
			queryGestures.add(g);
		}
	}
	public static Gesture initializeQuery(GesturesLibrary gesturesLibrary,String QueryXPath,String QueryYPath,String QueryZPath,String QueryWPath,int queryGestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
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
		Gesture queryGesture=new Gesture(queryGestureNumber, queryXMultiVariateDataDocument, queryYMultiVariateDataDocument, queryZMultiVariateDataDocument, queryWMultiVariateDataDocument);
		return queryGesture;
	}
	public static double[] getQueryData(Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		ArrayList<String> gesPCADim=TrainingData.getGesturePCAdimensions();
		double[] qyeryData=new double[gesPCADim.size()];
		double[][] qyueryXmapped=Query.mapXQuery(TrainingData.getGestureLibrary(), queryGesture);
		double[][] qyueryYmapped=Query.mapYQuery(TrainingData.getGestureLibrary(), queryGesture);
		double[][] qyueryZmapped=Query.mapZQuery(TrainingData.getGestureLibrary(), queryGesture);
		double[][] qyueryWmapped=Query.mapWQuery(TrainingData.getGestureLibrary(), queryGesture);
		
		int coulmn=0;
		
		for(int i=0;i<qyueryXmapped.length;i++)
		{
			for(int j=0;j<qyueryXmapped[i].length;j++)
			{
				if(gesPCADim.get(coulmn+j).contains("X")&&gesPCADim.get(coulmn+j).contains(""+i))
				{
					if(qyueryXmapped[i][j]==0.0)
						System.out.print("X Problem sensor "+i+" ");
					qyeryData[coulmn+j]=qyueryXmapped[i][j];
				}
				
			}
			coulmn+=qyueryXmapped[i].length;
			
		}
		for(int i=0;i<qyueryYmapped.length;i++)
		{
			for(int j=0;j<qyueryYmapped[i].length;j++)
			{
				if(gesPCADim.get(coulmn+j).contains("Y")&&gesPCADim.get(coulmn+j).contains(""+i))
				{
					if(qyueryYmapped[i][j]==0.0)
						System.out.print("Y Problem sensor "+i+" ");
					qyeryData[coulmn+j]=qyueryYmapped[i][j];
				}
				
			}
			coulmn+=qyueryYmapped[i].length;
			
		}
		for(int i=0;i<qyueryZmapped.length;i++)
		{
			for(int j=0;j<qyueryZmapped[i].length;j++)
			{
				if(gesPCADim.get(coulmn+j).contains("Z")&&gesPCADim.get(coulmn+j).contains(""+i))
				{
					if(qyueryZmapped[i][j]==0.0)
						System.out.print("Z Problem sensor "+i);
					qyeryData[coulmn+j]=qyueryZmapped[i][j];
				}
			
			}
			coulmn+=qyueryZmapped[i].length;
		}
		for(int i=0;i<qyueryWmapped.length;i++)
		{
			for(int j=0;j<qyueryWmapped[i].length;j++)
			{
				if(gesPCADim.get(coulmn+j).contains("W")&&gesPCADim.get(coulmn+j).contains(""+i))
				{
					if(qyueryWmapped[i][j]==0.0)
						System.out.print("W Problem sensor "+i+ " ");
					qyeryData[coulmn+j]=qyueryWmapped[i][j];
				}
			
			}
			coulmn+=qyueryWmapped[i].length;
		}
		
			return qyeryData;	
	}

	public static void SVMClassify() throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		SVM.nArySVMTrainer();
		System.out.println("Results using KNN Classifier: ");
		for(int i=0;i<queryGestures.size();i++)
		{
			String clas=SVM.classify(getQueryData(queryGestures.get(i)));
			System.out.println("Gesture "+queryGestures.get(i).getGestureNumber()+" is of class "+" "+clas);
		}
	}
	public static String classify(int k, Gesture queryGesture) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		GesturesLibrary gesturesLibrary=TrainingData.getGestureLibrary();
		String labels[]=TrainingData.getTainingLabels();
		ResultSet resultSet=new ResultSet();
		for(int i=0;i<phase1.Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g=TrainingData.getGestureLibrary().getGesture(i);
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
	public static void KNNClassify(int  k) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		System.out.println("Results using KNN Classifier: ");
		for(int i=0;i<queryGestures.size();i++)
		{
			String clas=classify(k,queryGestures.get(i));
			System.out.println("Gesture "+queryGestures.get(i).getGestureNumber()+" is of class "+" "+clas);
		}
	}
	public static void KNNClassify(int  k,int gn) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		System.out.println("Results using KNN Classifier: ");
		Gesture queryGesture=null;
		for(int i=0;i<queryGestures.size();i++)
		{
			System.out.println("daddsd "+queryGestures.get(i).getGestureNumber());
			if(queryGestures.get(i).getGestureNumber()==gn)
			{
			 queryGesture=queryGestures.get(i);
			}
		}
		if(!(queryGesture==null))
		{
			String clas=classify(k,queryGesture);
			System.out.println("Gesture "+queryGesture.getGestureNumber()+" is of class "+" "+clas);
		}
		else
		{
			System.out.println("Gesture Number not found.");
		}
		
	}
}
