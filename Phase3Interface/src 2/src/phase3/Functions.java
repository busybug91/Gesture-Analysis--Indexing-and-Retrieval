package phase3;
import phase1.Gesture;
import phase1.GesturesLibrary;
import phase1.MultiVriateDataDocument;
import phase1.UnivariateDataDocument;
import phase1.WordDictionary;
import phase2.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.sound.midi.SysexMessage;

import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;

import phase2.Constants;

public class Functions 
{
	//to convert ArrayList of binary values to string 
	public static String convertBinaryToString(ArrayList<Integer> vectorOne)
	{
		return vectorOne.toString();
	}
	public static ArrayList<String> getUniqueWords(ArrayList<String> inputWords)
	{
		HashSet<String> temp= new HashSet<String>();
		temp.addAll(inputWords);
		inputWords.clear();
		inputWords.addAll(temp);
		return inputWords;
	}

	public static String [][] generateSensorComponentDimensions(GesturesLibrary gesturesLibrary,char documentAxis)
	{
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			documentAxis=Character.toUpperCase(documentAxis);
			path=path+"/dataDimensions/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\dataDimensions\\";
		}
		String newData[][]=new String[phase2.Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		for(int i=0;i<phase2.Constants.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			//			newData[i]=phase2.Functions.getSensorAllMultivariateWords(phase2.Functions.getSensorData(gesturesLibrary, i, documentAxis));
			newData[i]=phase2.Functions.appendSensorNumber(phase2.Functions.getSensorAllMultivariateUniqueWords(phase2.Functions.getSensorData(gesturesLibrary, i, documentAxis)),i);
		}
		phase2.Functions.saveFileWithNumStringArray(path, documentAxis +".csv",newData);
		return newData;
	}
	@Deprecated
	public static void generateCompleteSensorDimensions(GesturesLibrary gesturesLibrary)
	{
		for(int i=0; i<gesturesLibrary.directories.size(); i++)
		{			
			generateSensorComponentDimensions(gesturesLibrary,gesturesLibrary.directories.get(i).charAt(0));
		}
	}
	public static String[][] generateComponentDimensions(GesturesLibrary gesturesLibrary,char documentAxis)
	{

		String newData[][]=generateSensorComponentDimensions(gesturesLibrary,documentAxis);
		LinkedHashSet<String> componentDimensions=converString2DtoLinkedHashSet(newData);
		String vector[]=(String[])componentDimensions.toArray(new String[componentDimensions.size()]);
		String writeVector[][]=new String[1][];
		writeVector[0]=vector;
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			documentAxis=Character.toUpperCase(documentAxis);
			path=path+"/componentVectorDimensions/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\componentVectorDimensions\\";
		}	
		phase2.Functions.saveFileWithNumStringArray(path, documentAxis +"_dimensions.csv",writeVector);		
		return writeVector;
	}
	public static String[][] generateAllComponentDimensions(GesturesLibrary gesturesLibrary)
	{
		String [][] allComponentDimensions=new String[gesturesLibrary.directories.size()][];
		for(int i=0; i<gesturesLibrary.directories.size(); i++)
		{	
			String componentDimensions[][]=new String[1][];
			componentDimensions[0]=appendDocumentAxis(generateComponentDimensions(gesturesLibrary,gesturesLibrary.directories.get(i).charAt(0))[0],gesturesLibrary.directories.get(i).charAt(0));
			allComponentDimensions[i]=componentDimensions[0];
		}
		return allComponentDimensions;
	}
	public static Double[][] generateGestureData(GesturesLibrary gesturesLibrary,ArrayList<String> gestureDimensions)
	{
		Double[][] gestureData=new Double[gesturesLibrary.size()][gestureDimensions.size()];
		for(int i=0;i<gesturesLibrary.size();i++)
		{
			Gesture curGesture=gesturesLibrary.getGesture(i);
			MultiVriateDataDocument curXMultiVriateDataDocument=curGesture.getXMultivariateData();
			for(int j=0;j<curXMultiVriateDataDocument.getAllUnivariateDataDocument().size();j++)
			{
				WordDictionary curWordDictionary=curXMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curWordDictionary.size();k++)
				{
					String curWord=curWordDictionary.getWord(k);
					String curWord1="X|S"+j+"|"+curWord;
					if(gestureDimensions.contains(curWord1))
					{
						gestureData[i][gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
					}
					else
						System.err.println("Error");
				}
			}
			MultiVriateDataDocument curYMultiVriateDataDocument=curGesture.getYMultivariateData();
			for(int j=0;j<curXMultiVriateDataDocument.getAllUnivariateDataDocument().size();j++)
			{
				WordDictionary curWordDictionary=curYMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curWordDictionary.size();k++)
				{
					String curWord=curWordDictionary.getWord(k);
					String curWord1="Y|S"+j+"|"+curWord;
					if(gestureDimensions.contains(curWord1))
					{
						gestureData[i][gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
					}

				}
			}
			MultiVriateDataDocument curZMultiVriateDataDocument=curGesture.getZMultivariateData();
			for(int j=0;j<curXMultiVriateDataDocument.getAllUnivariateDataDocument().size();j++)
			{
				WordDictionary curWordDictionary=curZMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curWordDictionary.size();k++)
				{
					String curWord=curWordDictionary.getWord(k);
					String curWord1="Z|S"+j+"|"+curWord;
					if(gestureDimensions.contains(curWord1))
					{
						gestureData[i][gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
					}

				}
			}
			MultiVriateDataDocument curWMultiVriateDataDocument=curGesture.getWMultivariateData();
			for(int j=0;j<curXMultiVriateDataDocument.getAllUnivariateDataDocument().size();j++)
			{
				WordDictionary curWordDictionary=curWMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curWordDictionary.size();k++)
				{
					String curWord=curWordDictionary.getWord(k);
					String curWord1="W|S"+j+"|"+curWord;
					if(gestureDimensions.contains(curWord1))
					{
						gestureData[i][gestureDimensions.indexOf(curWord1)]=curWordDictionary.getTFIDF(curWord);
					}

				}
			}
		}
		return gestureData;
	}
	public static String[] appendDocumentAxis(String[] data, char DX)
	{
		String newData[]=new String[data.length];
		for(int i=0;i<data.length;i++)
		{
			newData[i]=DX+"|"+data[i];
		}
		return newData;
	}
	public static void generateDataInaAllComponentCompleteDimensions(GesturesLibrary gesturesLibrary)
	{
		for(int i=0; i<gesturesLibrary.directories.size(); i++)
		{			
			generateDataInComponentCompleteDimensions(gesturesLibrary,gesturesLibrary.directories.get(i).charAt(0));
		}
	}
	public static void generateDataInComponentCompleteDimensions(GesturesLibrary gesturesLibrary, char documentAxis)
	{
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			documentAxis=Character.toUpperCase(documentAxis);
			path=path+"/dataInCompleteDimensions/"+documentAxis+"/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\dataInCompleteDimensions\\"+documentAxis+"\\";
		}
		String [][] completeCompDimensions=  generateComponentDimensions(gesturesLibrary,documentAxis);
		ArrayList<MultiVriateDataDocument> allMuls=gesturesLibrary.getAllMultiVariateDataDocument(documentAxis);
		for(int i=0;i<allMuls.size();i++)
		{ 
			MultiVriateDataDocument curMul=allMuls.get(i);
			ArrayList<UnivariateDataDocument> allUni=curMul.getAllUnivariateDataDocument();
			double [][]tfIDFInCompleteDimen=new double[1][];
			for(int j=0;j<allUni.size();j++)
			{
				UnivariateDataDocument curUni=allUni.get(j);
				//				ArrayList<String> allWords=curUni.getWords().getAllWords();
				double[] temp=new double[completeCompDimensions[0].length];	
				WordDictionary curDic= curUni.getWords();
				ArrayList<String> univariateAllWords=curUni.getIPWords();
				for(int k=0;k<completeCompDimensions[0].length;k++)
				{
					if(curDic.findWord(completeCompDimensions[0][k]))
					{
						temp[k]=curDic.getTFIDF(completeCompDimensions[0][k]);
					}
					else
					{
						temp[k]=0.0;
					}
				}
				tfIDFInCompleteDimen[0]=temp;
			}
			phase2.Functions.saveFileWithNumStringArray(path, Integer.toString(curMul.getGestureNumber())+"_InAllDim.csv",phase2.Functions.double2DToString2DArray(phase2.Functions.convertDoubleArray(tfIDFInCompleteDimen)));					
		}
	}

	public static String[][] generateGestureDimensions(GesturesLibrary gesturesLibrary)
	{
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			path=path+"/gestureDimensions/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\gestureDimensions\\";
		}
		String allComponentDimensions[][]=generateAllComponentDimensions(gesturesLibrary);
		String writeAllComponentDimensions[][]=	convertStringArrayList1DToStringArray(converString2DtoArrayList(allComponentDimensions));
		phase2.Functions.saveFileWithNumStringArray(path, "gestureDimensions.csv",writeAllComponentDimensions);
		return writeAllComponentDimensions;

	}


	public static double[][] findAvgOfArray(double [][]data)
	{
		double newData[][]=new double[1][data[0].length];
		double sum=0.0;
		double avg=0.0;
		for(int j=0;j<data[0].length;j++)
		{
			for(int i=0;i<data.length;i++)
			{
				sum+=data[i][j];
			}
			avg=sum/(double)data.length;
			newData[0][j]=avg;
		}
		return newData;

	}
	public static String[][] convertDoubleArrayListToStringArray(ArrayList<ArrayList<Double>> data)
	{
		String[][] newData=new String[data.size()][];
		for(int i=0;i<data.size();i++)
		{
			String temp[]=new String[data.get(i).size()];
			for(int j=0;j<data.get(i).size();j++)
			{
				temp[j]=data.get(i).get(j).toString();
			}
			newData[i]=temp;
		}
		return newData;
	}

	public static String[][] convertStringArrayListToStringArray(ArrayList<ArrayList<String>> data)
	{
		String[][] newData=new String[data.size()][];
		for(int i=0;i<data.size();i++)
		{
			String temp[]=new String[data.get(i).size()];
			for(int j=0;j<data.get(i).size();j++)
			{
				temp[j]=data.get(i).get(j);
			}
			newData[i]=temp;
		}
		return newData;
	}
	public static String[][] convertStringArrayList1DToStringArray(ArrayList<String> data)
	{
		String[][] newData=new String[1][];
		String temp[]=new String[data.size()];
		for(int i=0;i<data.size();i++)
		{		
			temp[i]=data.get(i);	
		}
		newData[0]=temp;
		return newData;
	}
	public static LinkedHashSet<String> converString2DtoLinkedHashSet(String [][]data)
	{
		LinkedHashSet<String> componentDimensions= new  LinkedHashSet<String>();
		for(int i=0; i<data.length;i++)
		{
			for(int j=0; j<data[i].length;j++)
			{
				componentDimensions.add(data[i][j]);
			}
		}
		return componentDimensions;
	}
	public static String[][] converDouble2DtoString2D(double [][]data)
	{
		String [][]newData=new String[data.length][];
		for(int i=0; i<data.length;i++)
		{
			newData[i]=new String[data[i].length];

			for(int j=0; j<data[i].length;j++)
			{
				newData[i][j]=Double.toString(data[i][j]);
			}
		}
		return newData;
	}
	public static String[][] convertIntegerArrayListToStringArray(ArrayList<ArrayList<Integer>> data)
	{
		String[][] newData=new String[data.size()][];
		for(int i=0;i<data.size();i++)
		{
			String temp[]=new String[data.get(i).size()];
			for(int j=0;j<data.get(i).size();j++)
			{
				temp[j]=data.get(i).get(j).toString();
			}
			newData[i]=temp;
		}
		return newData;
	}
	public static ArrayList<String> converString2DtoArrayList(String [][]data)
	{
		ArrayList<String> componentDimensions= new  ArrayList<String>();
		for(int i=0; i<data.length;i++)
		{
			for(int j=0; j<data[i].length;j++)
			{
				componentDimensions.add(data[i][j]);
			}
		}
		return componentDimensions;
	}

}

