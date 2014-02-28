package phase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;
import phase1.*;

public class Functions
{
	public static double cosine_similarity(double[] vec1, double[] vec2) 
	{ 
		double dp = dot_product(vec1,vec2); 
		double magnitudeA = find_magnitude(vec1); 
		double magnitudeB = find_magnitude(vec2); 
		if(magnitudeA!=0.0&& magnitudeB!=0.0)
		{
			//        System.out.println("Value of dp"+dp);
			//        System.out.println("Value of A"+magnitudeA);
			//        System.out.println("Value of B"+magnitudeB);
			//        System.out.println("final Value"+(dp)/(magnitudeA*magnitudeB));
			//		if(dp==0.0||(magnitudeA*magnitudeB)==0.0)
			//		{
			//			for(int i=0;i<vec1.length;i++)
			//			{
			//				System.out.print(vec1[i]+" ");
			//			}
			//			System.out.println();
			//			for(int i=0;i<vec1.length;i++)
			//			{
			//				System.out.print(vec2[i]+" ");
			//			}
			//			System.out.println();
			//		}
			//		
			if((dp)/(magnitudeA*magnitudeB)>1.001)
			{
				System.out.println((dp)/(magnitudeA*magnitudeB));
				System.out.println("Cosine is greater");
			}
			return (dp)/(magnitudeA*magnitudeB); 
		}
		else
			return 0.0;
	} 

	public static double find_magnitude(double[] vec) { 
		double sum_mag=0; 
		for(int i=0;i<vec.length;i++) 
		{ 
			sum_mag = sum_mag + vec[i]*vec[i]; 
		} 
		return Math.sqrt(sum_mag); 
	} 

	public static double dot_product(double[] vec1, double[] vec2) { 
		double sum=0; 
		for(int i=0;i<vec1.length;i++) 
		{ 
			sum = sum + vec1[i]*vec2[i]; 
		} 
		if(sum<0.0)
			sum=-sum;
		return sum; 
	} 
	public static ArrayList<UnivariateDataDocument> getSensorData(GesturesLibrary gesturesLibrary,int sensorNumber,char documentAxis)
	{	//return all univariates of a particular sensor in a component
		ArrayList<UnivariateDataDocument> returnSensorData=new ArrayList<UnivariateDataDocument>();
		ArrayList <MultiVriateDataDocument>  allMul=gesturesLibrary.getAllMultiVariateDataDocument(documentAxis);
		for(int i=0;i<allMul.size();i++)
		{
			MultiVriateDataDocument curMulDataDocument=allMul.get(i);
			returnSensorData.add(curMulDataDocument.getUnivariateDataDocument(sensorNumber));
		}

		//		String path=System.getProperty("user.dir");
		////				System.out.println(path);
		//		if(path.contains("/"))
		//		{ 
		//			documentAxis=Character.toUpperCase(documentAxis);
		//			path=path+"/dataDimensions/"+documentAxis+"/";
		//		}
		//		if(path.contains("\\"))
		//		{
		//			path=path+"\\dataDimensions\\"+documentAxis+"\\";
		//		}
		//		String newData[][]=new String[1][];
		//		newData[0]=getSensorAllMultivariateWords(returnSensorData);
		//		saveFileWithNumStringArray(path, Integer.toString(sensorNumber)+".csv",newData);	
		return returnSensorData;
	}
	public static Double[][] getSensorArray(ArrayList<UnivariateDataDocument> sensor)
	{	// generates a tf-idf of a particular sensor data
		ArrayList<String> uniqueWords=getUniqeWords(sensor); //returns unique words of a particular sensor of concatenated sensor arrayList
		Double [][]rturnArDouble=new Double[sensor.size()][uniqueWords.size()];
		for(int i=0;i<sensor.size();i++)
		{
			WordDictionary curDictionary= sensor.get(i).getWords();
			for(int j=0;j<uniqueWords.size();j++)
			{
				if(curDictionary.findWord(uniqueWords.get(j)))
				{
					rturnArDouble[i][j]=curDictionary.getTFIDF(uniqueWords.get(j));
				}
				else
				{
					rturnArDouble[i][j]=0.0;
				}
			}
			//saveFileWithNumStringArray(path, name, double2DToString2DArray(rturnArDouble));
		}
		return rturnArDouble;
	}
	public static ArrayList<String> getUniqeWords(ArrayList<UnivariateDataDocument> sensor)
	{
		ArrayList<String> rWords=new ArrayList<String>();
		for(int i=0;i<sensor.size();i++)
		{
			ArrayList<String> words=sensor.get(i).getWords().getAllWords();
			for(int j=0;j<words.size();j++)
			{
				if(!rWords.contains(words.get(j)))
				{
					rWords.add(words.get(j));
				}
			}
		}
		return rWords;
	}
	public static String printDoubleArray(Double[][] array)
	{		
		String op="";

		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				op+=String.format("%20.8s",Double.toString(array[i][j])+" ");
			}
			op+="\n";
		}	
		return op;
	}
	public static String printDoubleArray1(double[][] array)
	{		
		String op="";
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				op+=String.format("%20.8s",Double.toString(array[i][j])+" ");
			}
			op+="\n";
		}	
		return op;
	}
	public static double[][] matrixMultiplication(double[][]a,double[][]b) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("a="+phase1.Functions.getDoubleString(convertDoubleArray(a))+";");
		proxy.eval("b="+phase1.Functions.getDoubleString(convertDoubleArray(b))+";");
		proxy.eval("output=a*b;");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("output");
		return array.getRealArray2D();	
	}
	public static double[][] matrixTranspose(double[][]a) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] c;
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("a="+phase1.Functions.getDoubleString(convertDoubleArray(a))+";");
		proxy.eval("outTranspose=a';");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("outTranspose");
		c = array.getRealArray2D();	
		return c;
	}
	public static Double[][] convertDoubleArray(double[][] array)
	{	
		Double [][] doubleArray=new Double[array.length][];
		for(int i=0;i<array.length;i++)
		{
			doubleArray[i]=new Double[array[i].length];
			for(int j=0;j<array[i].length;j++)
			{
				doubleArray[i][j]=array[i][j];
			}
		}	
		return doubleArray;
	}
	public static double[][] convertDoubleArraySmall(Double[][] array)
	{	
		double [][] doubleArray=new double[array.length][];
		for(int i=0;i<array.length;i++)
		{
			doubleArray[i]=new double[array[i].length];
			for(int j=0;j<array[0].length;j++)
			{
				if(array[i][j]!=null)
					doubleArray[i][j]=array[i][j].doubleValue();			
			}

		}	
		return doubleArray;
	}
	@Deprecated
	public static String [] getSensorAllMultivariateWords(ArrayList<UnivariateDataDocument> sensor)
	{
		ArrayList<String> newData= new ArrayList<String>();
		for(int i=0;i<sensor.size();i++)
		{
			UnivariateDataDocument curDataDocument= sensor.get(i);
			WordDictionary curDic=curDataDocument.getWords();
			newData.addAll(curDic.getAllWords());
		}
		return newData.toArray(new String[newData.size()]);
	}
	public static String [] getSensorAllMultivariateUniqueWords(ArrayList<UnivariateDataDocument> sensor)
	{
		ArrayList<String> newData= getUniqeWords(sensor);
		return newData.toArray(new String[newData.size()]);
	}
	public static String[] appendSensorNumber(String []data,int sensorNum)
	{
		String []newData=new String[data.length];
		for(int i=0;i<data.length;i++)
		{
			newData[i]="S"+sensorNum+"|"+data[i];
		}
		return newData;
	}

	public static String[][] sensorDataToArray(ArrayList<UnivariateDataDocument> sensor)
	{
		String [][] array= new String[sensor.size()][];
		for(int i=0;i<sensor.size();i++)
		{
			UnivariateDataDocument curDataDocument= sensor.get(i);
			WordDictionary curDic=curDataDocument.getWords();
			ArrayList<String> allWords=curDic.getAllWords();
			array[i]=new String[allWords.size()];
			for(int j=0;j<allWords.size();j++)
			{
				array[i][j]=allWords.get(j);
			}
		}
		return array;
	}
	public static boolean SaveFileWithNumber(String path, String name, String[][] data)
	{
		try{
			File file = new File(path);
			if(!file.exists())
			{
				file.mkdirs();
			}
			file = new File(path+name);
			{
				if(!file.exists())
				{
					file.createNewFile();
				}
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(data.length));
			bw.newLine();
			//System.out.println(data.length);
			// for each document
			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length;j++)
				{
					if(j != 0)
						bw.write(" ");
					bw.write(data[i][j]);
				}
				if(i != (data.length - 1))
					bw.newLine();
				bw.flush();
			}
			bw.close();
		}
		catch(Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		return true;
	}

	public static boolean saveFileWithNumStringArray(String path, String name, String[][] data)
	{
		try{
			File file = new File(path);
			if(!file.exists())
			{
				file.mkdirs();
			}
			file = new File(path+name);
			{
				if(!file.exists())
				{
					file.createNewFile();
				}
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			//System.out.println(data.length);
			// for each document
			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length;j++)
				{
					if(j != 0)
						bw.write(",");
					bw.write(data[i][j]);
				}
				if(i != (data.length - 1))
					bw.newLine();
				bw.flush();
			}
			bw.close();
		}
		catch(Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		return true;
	}
	public static String[][] double2DToString2DArray(Double [][]data)
	{
		String newData[][]=new String[data.length][];
		for(int i=0;i<data.length;i++)
		{
			String temp[]=new String[data[i].length];
			for(int j=0;j<data[i].length;j++)
			{
				temp[j]=data[i][j].toString();
			}
			newData[i]=temp;
		}
		return newData;
	}



	public static List<TopicWrapper[]> LoadTopicDistributions(String path, String name, int k, int num_words) 
	{
		List<TopicWrapper[]> results_arr = new ArrayList<TopicWrapper[]>();
		BufferedReader br = null;
		try
		{
			List<TopicWrapper> results = null;
			String current_line;
			String[] parts;
			String topic = "";
			TopicWrapper[] tw_arr;
			TopicWrapper tw;
			br = new BufferedReader(new FileReader(path + name));
			int count = 0, tcount = 0;
			while ((current_line = br.readLine()) != null)
			{
				System.out.println(current_line);
				current_line = current_line.trim();
				System.out.println(current_line + "|||");
				if(current_line.contains("Topic"))
				{
					topic = current_line.substring(0, current_line.length() - 1).replace(" ", "");			
					System.out.println(topic);
					count = 0;
					if(results != null)
					{
						tw_arr = new TopicWrapper[results.size()];
						//to make array of topic wrapper = TopicWrapper[] 
						results.toArray(tw_arr);
						results_arr.add(tw_arr);
						tcount++;
					}		
					results = new ArrayList<TopicWrapper>();
				}
				else
				{
					parts = current_line.split(" ");
					System.out.println(tcount + "&" + count + "parts[" + parts.length + "] = " + parts[0] + ";" + parts[1]);
					tw = new TopicWrapper(topic, parts[0], Double.parseDouble(parts[1]));
					results.add(tw);
					count++;
				}		
			}
			br.close();
			tw_arr = new TopicWrapper[results.size()];
			results.toArray(tw_arr);
			results_arr.add(tw_arr);	
		}
		catch(Exception ex)
		{
			System.out.println("ERROR:" + ex.getMessage());
			ex.printStackTrace();
		}	
		return results_arr;
	}
	public static void printStrings3D(String [][][]data)
	{
		for(int i=0; i<data.length;i++)
		{
			System.out.println("For file number "+i);
			for(int j=0; j<data[i].length;j++)
			{
				for(int k=0; k<data[i][j].length;k++)
				{
					System.out.print(data[i][j][k]+" ");
				}System.out.println();
			}System.out.println();
		}
	}
	public static String printDoubleArray1D1(double[] array)
	{  
		String op="";

		for(int i=0;i<array.length;i++)
		{
			op+=String.format("%20.8s",Double.toString(array[i])+" ");  
		}
		op+="\n";
		return op;
	}
	public static String printDoubleArray1D(Double[] array)
	{  
		String op="";

		for(int i=0;i<array.length;i++)
		{
			op+=String.format("%20.8s",Double.toString(array[i])+" ");    
		} 
		op+="\n";
		return op;
	}
	public static String [][][] convert2Dto3DString(String[][] input)
	{
		String[][][] output=new String[1][][];
		output[0]=input;
		return output;
	}
}