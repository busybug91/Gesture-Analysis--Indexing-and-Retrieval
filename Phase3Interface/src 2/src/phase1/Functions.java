package phase1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
//import org.tc33.jheatchart.HeatChart;

public class Functions 
{
	private static Double Levels [];
	public static ArrayList<Integer> searchDirectory(File directory)
	{
		ArrayList<Integer> files=null;
		if (directory.isDirectory())
		{
			files=search(directory);
		}
		else
		{		
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
		return files;
	}
	public static void printDoubleArray(double[][] array)
	{
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}	
	}
	//	public  static void craeteHeatMap(char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval("colormap('hot')");
	//		proxy.eval("b=imagesc(image);");
	//		proxy.eval("colorbar");
	//		proxy.disconnect();
	//	}
	//	public  static void craetetopTFIFDHeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		proxy.eval("disp('Hello world')");
	//		proxy.eval("cd(\'C:\\Users\\SONY\\Desktop\')");
	//		proxy.eval("disp('Hello world')");
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] map=top10TFIDFMatrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval(" map = "+getDoubleString(map)+";");
	//		proxy.eval("plotHetamap(image,map)");
	//		proxy.disconnect();
	//	}public  static void craetetopTFHeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		proxy.eval("disp('Hello world')");
	//		proxy.eval("cd(\'C:\\Users\\SONY\\Desktop\')");
	//		proxy.eval("disp('Hello world')");
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] map=top10TFMatrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval(" map = "+getDoubleString(map)+";");
	//		proxy.eval("plotHetamap(image,map)");
	//		proxy.disconnect();
	//	}
	//	public  static void craetetopTFIFD1HeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		proxy.eval("disp('Hello world')");
	//		proxy.eval("cd(\'C:\\Users\\SONY\\Desktop\')");
	//		proxy.eval("disp('Hello world')");
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] map=top10TFIDF1Matrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval(" map = "+getDoubleString(map)+";");
	//		proxy.eval("plotHetamap(image,map)");
	//		proxy.disconnect();
	//	}
	//	public  static void craetetopIFD1HeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		proxy.eval("disp('Hello world')");
	//		String path=Consatnts.PATH1.substring(0, Consatnts.PATH1.length()-1);
	//		System.out.println(path);
	//		proxy.eval("cd(\'C:\\Users\\SONY\\Desktop\')");
	//		proxy.eval("disp('Hello world')");
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] map=top10IDF1Matrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval(" map = "+getDoubleString(map)+";");
	//		proxy.eval("plotHetamap(image,map)");
	//		proxy.disconnect();
	//	}
	//	public  static void craetetopIFDHeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws MatlabConnectionException, MatlabInvocationException, IOException
	//	{
	//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
	//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
	//		MatlabProxy proxy = factory.getProxy();
	//		proxy.eval("disp('Hello world')");
	//		String path=Consatnts.PATH1.substring(0, Consatnts.PATH1.length()-1);
	//		System.out.println(path);
	//		proxy.eval("cd(\'C:\\Users\\SONY\\Desktop\')");
	//		proxy.eval("disp('Hello world')");
	//		double [][] image=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] map=top10IDFMatrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		proxy.eval(" image = "+getDoubleString(image)+";");
	//		proxy.eval(" map = "+getDoubleString(map)+";");
	//		proxy.eval("plotHetamap(image,map)");
	//		proxy.disconnect();
	//	}
	public static String getDoubleString(Double[][] map) throws IOException
	{
		String op="[";
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[i].length;j++)
			{
				op+=map[i][j]+",";
			}
			op+=";";
		}
		op+="]";
		return op;
	}
	public static String getDoubleString(double[][] map) throws IOException
	{
		String op="[";
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[i].length;j++)
			{
				op+=map[i][j]+",";
			}
			op+=";";
		}
		op+="]";
		return op;
	}
	public static double[][] mergeDoubleArrays(double [][] documentArray, double[][] topWordsArray)
	{
		double op[][]=new double[documentArray.length][documentArray[0].length];
		for(int i=0;i<documentArray.length;i++)
		{
			for(int j=0;j<documentArray[i].length;j++)
			{
				if(topWordsArray[i][j]==0.0)
					op[i][j]=2.0;
				else
					op[i][j]=documentArray[i][j];
			}
		}
		return op;
	}
	public static double[][] top10TFMatrix(char DocumentAxis, int GestureNumber,MultiVriateDataDocument multiVriateDataDocument) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		List<Double> tf=new ArrayList<Double>();
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			for(int j=0;j<ipWords.size();j++)
			{
				Double curTF=curUnivariateDictionary.getTF(ipWords.get(j));
				if(!tf.contains(curTF))
				{
					tf.add(curTF);
				}
			}
		}
		Collections.sort(tf);
		if(tf.size()>10)
		{
			tf= tf.subList(0,10);
		}
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int q=0;q<NormalizedMultiVariateData.get(i).size();q++)
			{
				op[i][q]=1.0;
			}
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			for(int k=0;k<DiscretizedUnivariate.size();k+=Consatnts.STEP_SIZE)
			{
				String Word="";
				int j;
				for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
					Word+="L"+DiscretizedUnivariate.get(j+k);
				while(j<Consatnts.WINDOW_SIZE)
				{
					j++;
					//Word+="L"+DiscretizedUnivariate.get(DiscretizedUnivariate.size()-1);
					Word+="LX";
				}
				if(tf.contains(curUnivariateDictionary.getTF(Word)))
				{
					for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
						op[i][j+k]=0.0;
				}
				else
				{
				}
			}

		}
		return op;
	}
	public static double[][] top10TFIDFMatrix(char DocumentAxis, int GestureNumber,MultiVriateDataDocument multiVriateDataDocument) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		List<Double> tfidf=new ArrayList<Double>();
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			//System.out.println(curUnivariateDictionary+" "+i);
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			//System.out.println(ipWords);
			for(int j=0;j<ipWords.size();j++)
			{
				Double curTFidf=curUnivariateDictionary.getTFIDF(ipWords.get(j));
				if(!tfidf.contains(curTFidf))
				{
					tfidf.add(curTFidf);
				}
			}
		}
		Collections.sort(tfidf);
		Collections.reverse(tfidf);
		if(tfidf.size()>10)
		{
			tfidf= tfidf.subList(0,10);
		}
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int q=0;q<NormalizedMultiVariateData.get(i).size();q++)
			{
				op[i][q]=1.0;
			}
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			for(int k=0;k<DiscretizedUnivariate.size();k+=Consatnts.STEP_SIZE)
			{
				String Word="";
				int j;
				for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
					Word+="L"+DiscretizedUnivariate.get(j+k);
				while(j<Consatnts.WINDOW_SIZE)
				{
					j++;
					Word+="LX";
				}
				if(tfidf.contains(curUnivariateDictionary.getTFIDF(Word)))
				{
					for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
						op[i][j+k]=0.0;
				}
				else
				{
				}
			}

		}
		return op;
	}
	public static double[][] top10TFIDF1Matrix(char DocumentAxis, int GestureNumber,MultiVriateDataDocument multiVriateDataDocument) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		List<Double> tfidf1=new ArrayList<Double>();
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			for(int j=0;j<ipWords.size();j++)
			{
				Double curTFidf1=curUnivariateDictionary.getTFIDF1(ipWords.get(j));
				if(!tfidf1.contains(curTFidf1))
				{
					tfidf1.add(curTFidf1);
				}
			}
		}
		Collections.sort(tfidf1);
		Collections.reverse(tfidf1);
		if(tfidf1.size()>10)
		{
			tfidf1= tfidf1.subList(0,10);
		}
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int q=0;q<NormalizedMultiVariateData.get(i).size();q++)
			{
				op[i][q]=1.0;
			}
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			for(int k=0;k<DiscretizedUnivariate.size();k+=Consatnts.STEP_SIZE)
			{
				String Word="";
				int j;
				for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
					Word+="L"+DiscretizedUnivariate.get(j+k);
				while(j<Consatnts.WINDOW_SIZE)
				{
					j++;
					//Word+="L"+DiscretizedUnivariate.get(DiscretizedUnivariate.size()-1);
					Word+="LX";
				}
				if(tfidf1.contains(curUnivariateDictionary.getTFIDF1(Word)))
				{
					for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
						op[i][j+k]=0.0;
				}
				else
				{
				}
			}

		}
		return op;
	}
	public static double[][] top10IDF1Matrix(char DocumentAxis, int GestureNumber,MultiVriateDataDocument multiVriateDataDocument) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		List<Double> idf1=new ArrayList<Double>();
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			for(int j=0;j<ipWords.size();j++)
			{
				Double curidf1=curUnivariateDictionary.getIDF1(ipWords.get(j));
				if(!idf1.contains(curidf1))
				{
					idf1.add(curidf1);
				}
			}
		}
		Collections.sort(idf1);
		Collections.reverse(idf1);
		if(idf1.size()>10)
		{
			idf1= idf1.subList(0,10);
		}
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int q=0;q<NormalizedMultiVariateData.get(i).size();q++)
			{
				op[i][q]=1.0;
			}
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			for(int k=0;k<DiscretizedUnivariate.size();k+=Consatnts.STEP_SIZE)
			{
				String Word="";
				int j;
				for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
					Word+="L"+DiscretizedUnivariate.get(j+k);
				while(j<Consatnts.WINDOW_SIZE)
				{
					j++;
					//Word+="L"+DiscretizedUnivariate.get(DiscretizedUnivariate.size()-1);
					Word+="LX";
				}
				if(idf1.contains(curUnivariateDictionary.getIDF1(Word)))
				{
					for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
						op[i][j+k]=0.0;
				}
				else
				{
				}
			}

		}
		return op;
	}
	public static double[][] top10IDFMatrix(char DocumentAxis, int GestureNumber,MultiVriateDataDocument multiVriateDataDocument) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		List<Double> idf=new ArrayList<Double>();
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			for(int j=0;j<ipWords.size();j++)
			{
				Double curidf=curUnivariateDictionary.getIDF(ipWords.get(j));
				if(!idf.contains(curidf))
				{
					idf.add(curidf);
				}
			}
		}
		Collections.sort(idf);
		Collections.reverse(idf);
		if(idf.size()>10)
		{
			idf= idf.subList(0,10);
		}
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int q=0;q<NormalizedMultiVariateData.get(i).size();q++)
			{
				op[i][q]=1.0;
			}
			WordDictionary curUnivariateDictionary=multiVriateDataDocument.getUnivariateDataDocument(i).getWords();
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			for(int k=0;k<DiscretizedUnivariate.size();k+=Consatnts.STEP_SIZE)
			{
				String Word="";
				int j;
				for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
					Word+="L"+DiscretizedUnivariate.get(j+k);
				while(j<Consatnts.WINDOW_SIZE)
				{
					j++;
					//Word+="L"+DiscretizedUnivariate.get(DiscretizedUnivariate.size()-1);
					Word+="LX";
				}
				if(idf.contains(curUnivariateDictionary.getIDF(Word)))
				{
					for(j=0;j<Consatnts.WINDOW_SIZE&&(j+k)<DiscretizedUnivariate.size();j++)
						op[i][j+k]=0.0;
				}
				else
				{
				}
			}

		}
		return op;
	}
	private static ArrayList<Integer> search(File file) 
	{
		ArrayList<Integer> files=null;
		if (file.isDirectory()) 
		{
			//System.out.println("Searching directory ... " + file.getAbsoluteFile());
			if (file.canRead()) 
			{
				files=new ArrayList<Integer>();
				for (File temp : file.listFiles()) 
				{
					if (temp.isDirectory()) 
					{
						search(temp);
					} 
					else 
					{
						if (temp.getName().toLowerCase().endsWith(".csv")) 
						{	
							String path=temp.getAbsoluteFile().toString().toLowerCase();
							String tempN="";
							if(path.contains("/"))
								tempN=path.substring(path.lastIndexOf("/")+1);
							else
								tempN=path.substring(path.lastIndexOf("\\")+1);						
							tempN=tempN.replaceAll(".csv", "");
							tempN.trim();
							files.add(new Integer(tempN));
						}
					}
				}
				return files;
			}

			else 
			{
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		}
		return files;
	}
	public static ArrayList<ArrayList<Double>> readCSV(String Path) throws IOException
	{
		ArrayList<ArrayList<Double>> MultiVariateData=new ArrayList<ArrayList<Double>>();
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(Path));
		String line = "";
		while ((line = in.readLine()) != null) 
		{
			lines.add(line);
		}
		in.close();
		int i=0;
		while(i<lines.size())
		{
			String[] values=lines.get(i).split(",");
			ArrayList<Double> UniVariateData = new ArrayList<Double>();
			for(int j=0;j<values.length;j++)
			{
				UniVariateData.add(Double.parseDouble(values[j]));
			}
			MultiVariateData.add(UniVariateData);
			i++;
		}
		return MultiVariateData;
	}
	public static void printLevels()
	{
		for(int i=0;i<Levels.length;i++)
		{
			System.out.println("Level "+i+": "+Levels[i]);
		}
	}
	@Deprecated
	public static ArrayList<Double> normalize1(ArrayList<Double> Univariate)
	{
		int MaxValIndex=0;
		int MinValIndex=0;
		Double AvgVal;
		Double Factor;
		ArrayList<Double> InterNormalizedUnivariate=new ArrayList<Double> ();
		ArrayList<Double> NormalizedUnivariate=new ArrayList<Double> ();
		for(int i=1;i<Univariate.size();i++)
		{
			if(Univariate.get(MaxValIndex)< Univariate.get(i))
				MaxValIndex=i;
			if(Univariate.get(MinValIndex)>Univariate.get(i))
				MinValIndex=i;
		}
		AvgVal=(Univariate.get(MaxValIndex)+Univariate.get(MinValIndex))/2.0;
		for(int i=0;i<Univariate.size();i++)
		{
			InterNormalizedUnivariate.add(Univariate.get(i)-AvgVal);
		}
		Factor=1.0/InterNormalizedUnivariate.get(MaxValIndex);
		for(int i=0;i<InterNormalizedUnivariate.size();i++)
		{
			NormalizedUnivariate.add(InterNormalizedUnivariate.get(i)*Factor);
		}
		return NormalizedUnivariate;
	}
	public static ArrayList<ArrayList<Double>> normalize(ArrayList<ArrayList<Double>> Multivariate)
	{
		Double MaxVal=0.0;
		Double MinVal=0.0;
		ArrayList<ArrayList<Double>> NormalizedMultivariate=new ArrayList<ArrayList<Double>> ();
		for(int i=0;i<Multivariate.size();i++)
		{
			for(int j=0;j<Multivariate.get(i).size();j++)
			{
				if(Multivariate.get(i).get(j)>MaxVal)
				{
					MaxVal=Multivariate.get(i).get(j);
				}
				if(Multivariate.get(i).get(j)<MinVal)
					MinVal=Multivariate.get(i).get(j);
			}
		}
		for(int i=0;i<Multivariate.size();i++)
		{
			ArrayList<Double> NormalizedUnivariate= new ArrayList<Double>();
			for(int j=0;j<Multivariate.get(i).size();j++)
			{
				NormalizedUnivariate.add(((((Multivariate.get(i).get(j)-MinVal)/(MaxVal-MinVal))*2)-1));
			}
			NormalizedMultivariate.add(NormalizedUnivariate);
		}
		return NormalizedMultivariate;
	}
	public static ArrayList<Integer> discretize(ArrayList<Double> NormalizedUnivariate)
	{
		ArrayList<Integer> DiscretizedUnivariate=new ArrayList<Integer>();
		for(int i=0;i<NormalizedUnivariate.size();i++)
		{
			DiscretizedUnivariate.add(getLevel(NormalizedUnivariate.get(i)));
		}
		return DiscretizedUnivariate;
	}
	public static void defineGuassianLevels(int r,double mean,double standardDeviation)
	{
		NormalDistribution nd=new NormalDistribution(mean,standardDeviation);
		Levels = new Double[2*r];
		Double dr=new Double(r);
		for(int i=r;i<2*r;i++)
		{
			Levels[2*r-i]=-2.0*(nd.cumulativeProbability((new Double(i)-dr)*1.0/dr)-nd.cumulativeProbability(0.0));
			Levels[i]=2.0*(nd.cumulativeProbability((new Double(i)-dr)*1.0/dr)-nd.cumulativeProbability(0.0));
		}
		Levels[0]=-1.0;
	}
	public static int getLevel(Double value)
	{
		if(value<=-1)
			return 0;
		for(int i=0;i<Levels.length;i++)
		{
			if(value>Levels[i]);
			else
			{
				if(value==Levels[i])return i;
				return i-1;
			}
		}
		return Levels.length-1;
	}
	public static ArrayList<String> getWords(ArrayList<Integer> DiscretizedUnivariate,int Window,int Step)
	{
		ArrayList<String> WordList=new ArrayList<String>();
		for(int i=0;i<DiscretizedUnivariate.size();i+=Step)
		{
			String Word="";
			int j;
			for(j=0;j<Window&&(j+i)<DiscretizedUnivariate.size();j++)Word+="L"+DiscretizedUnivariate.get(j+i);
			while(j<Window)
			{
				j++;
				//Word+="L"+DiscretizedUnivariate.get(DiscretizedUnivariate.size()-1);
				Word+="LX";
			}
			WordList.add(Word);
		}
		return WordList;
	}
	public static void calculateTFIDF(GesturesLibrary gesturesLibrary)
	{
		for(int i=0;i<gesturesLibrary.size();i++)
		{
			Gesture curGesture=gesturesLibrary.getGesture(i);
			MultiVriateDataDocument XmultiVariate=curGesture.getXMultivariateData();
			MultiVriateDataDocument YmultiVariate=curGesture.getYMultivariateData();
			MultiVriateDataDocument ZmultiVariate=curGesture.getZMultivariateData();
			MultiVriateDataDocument WmultiVariate=curGesture.getWMultivariateData();
			for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
			{
				if(XmultiVariate!=null)
				{
					WordDictionary XWords=XmultiVariate.getUnivariateDataDocument(j).getWords();
					for(int k=0;k<XWords.size();k++)
					{
						String curWord=XWords.getWord(k);
						XWords.setTF(curWord, (new Double(XWords.getCount(curWord))/new Double(XWords.getoTalNumberOfWordsInUnivariate())));
						XWords.setIDF(curWord, getXIDF(curWord,gesturesLibrary));
						XWords.setIDF1(curWord, getIDF1(curWord, XmultiVariate));
						XWords.setTFIDF(curWord, (XWords.getTF(curWord)*XWords.getIDF(curWord)));
						XWords.setTFIDF1(curWord, (XWords.getTF(curWord)*XWords.getIDF1(curWord)));
						XWords.setTFIDF3(curWord, (XWords.getTF(curWord)*getXIDF3(curWord,gesturesLibrary,j)));
					}
				}
				if(YmultiVariate!=null)
				{
					WordDictionary YWords=YmultiVariate.getUnivariateDataDocument(j).getWords();
					for(int k=0;k<YWords.size();k++)
					{
						String curWord=YWords.getWord(k);
						YWords.setTF(curWord, (new Double(YWords.getCount(curWord))/new Double(YWords.getoTalNumberOfWordsInUnivariate())));
						YWords.setIDF(curWord, getYIDF(curWord, gesturesLibrary));
						YWords.setIDF1(curWord, getIDF1(curWord, YmultiVariate));
						YWords.setTFIDF(curWord, (YWords.getTF(curWord)*YWords.getIDF(curWord)));
						YWords.setTFIDF1(curWord, (YWords.getTF(curWord)*YWords.getIDF1(curWord)));
						YWords.setTFIDF3(curWord, (YWords.getTF(curWord)*getYIDF3(curWord,gesturesLibrary,j)));
					}
				}
				if(ZmultiVariate!=null)
				{
					WordDictionary ZWords=ZmultiVariate.getUnivariateDataDocument(j).getWords();
					for(int k=0;k<ZWords.size();k++)
					{
						String curWord=ZWords.getWord(k);
						ZWords.setTF(curWord, (new Double(ZWords.getCount(curWord))/new Double(ZWords.getoTalNumberOfWordsInUnivariate())));
						ZWords.setIDF(curWord, getZIDF(curWord, gesturesLibrary));
						ZWords.setIDF1(curWord, getIDF1(curWord, ZmultiVariate));
						ZWords.setTFIDF(curWord, (ZWords.getTF(curWord)*ZWords.getIDF(curWord)));
						ZWords.setTFIDF1(curWord, (ZWords.getTF(curWord)*ZWords.getIDF1(curWord)));
						ZWords.setTFIDF3(curWord, (ZWords.getTF(curWord)*getZIDF3(curWord,gesturesLibrary,j)));
					}
				}
				if(WmultiVariate!=null)
				{
					WordDictionary WWords=WmultiVariate.getUnivariateDataDocument(j).getWords();
					for(int k=0;k<WWords.size();k++)
					{
						String curWord=WWords.getWord(k);
						WWords.setTF(curWord, (new Double(WWords.getCount(curWord))/new Double(WWords.getoTalNumberOfWordsInUnivariate())));
						WWords.setIDF(curWord, getWIDF(curWord, gesturesLibrary));
						WWords.setIDF1(curWord, getIDF1(curWord, WmultiVariate));
						WWords.setTFIDF(curWord, (WWords.getTF(curWord)*WWords.getIDF(curWord)));
						WWords.setTFIDF1(curWord, (WWords.getTF(curWord)*WWords.getIDF1(curWord)));
						WWords.setTFIDF3(curWord, (WWords.getTF(curWord)*getWIDF3(curWord,gesturesLibrary,j)));
					}
				}
			}
		}
	}
	private static Double getIDF1(String word,MultiVriateDataDocument multiVriateDataDocument)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS)/ new Double(multiVriateDataDocument.getWordDocumentFrequency().getCount(word))));
	}
	private static Double getXIDF(String word,GesturesLibrary gesturesLibrary)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_DOCUMENTS)/new Double(gesturesLibrary.getXWordDocumentFrequency().getCount(word))));
	}
	private static Double getXIDF3(String word,GesturesLibrary gesturesLibrary,int SN)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_GESTURES)/new Double(gesturesLibrary.getXwordDicforSensors(SN).getCount(word))));
	}
	private static Double getYIDF3(String word,GesturesLibrary gesturesLibrary,int SN)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_GESTURES)/new Double(gesturesLibrary.getYwordDicforSensors(SN).getCount(word))));
	}
	private static Double getZIDF3(String word,GesturesLibrary gesturesLibrary,int SN)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_GESTURES)/new Double(gesturesLibrary.getZwordDicforSensors(SN).getCount(word))));
	}
	private static Double getWIDF3(String word,GesturesLibrary gesturesLibrary,int SN)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_GESTURES)/new Double(gesturesLibrary.getWwordDicforSensors(SN).getCount(word))));
	}
	private static Double getYIDF(String word,GesturesLibrary gesturesLibrary)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_DOCUMENTS)/new Double(gesturesLibrary.getYWordDocumentFrequency().getCount(word))));
	}
	private static Double getZIDF(String word,GesturesLibrary gesturesLibrary)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_DOCUMENTS)/new Double(gesturesLibrary.getZWordDocumentFrequency().getCount(word))));
	}
	private static Double getWIDF(String word,GesturesLibrary gesturesLibrary)
	{
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_DOCUMENTS)/new Double(gesturesLibrary.getWWordDocumentFrequency().getCount(word))));
	}
	@Deprecated
	public static double[][] getDoubleArray1(GesturesLibrary gesturesLibrary,char DocumentAxis, int GestureNumber) throws IOException
	{
		MultiVriateDataDocument curMultivariateDataDocument;
		switch(DocumentAxis)
		{
		case 'X' :curMultivariateDataDocument=gesturesLibrary.getGesture(GestureNumber-1).getXMultivariateData();
		break;
		case 'Y' :curMultivariateDataDocument=gesturesLibrary.getGesture(GestureNumber-1).getYMultivariateData();
		break;
		case 'Z' :curMultivariateDataDocument=gesturesLibrary.getGesture(GestureNumber-1).getZMultivariateData();
		break;
		case 'W' :curMultivariateDataDocument=gesturesLibrary.getGesture(GestureNumber-1).getWMultivariateData();
		break;
		default :curMultivariateDataDocument=null;
		}
		double[][] op=new double[20][];
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			UnivariateDataDocument curUnivariateDataDocument=curMultivariateDataDocument.getUnivariateDataDocument(i);
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			op[i]=new double[ipWords.size()];
			for(int j=0;j<ipWords.size();j++)
			{
				String curWord=ipWords.get(j);
				op[i][j]=curUnivariateDataDocument.getWords().getTF(curWord);
			}
		}
		return op;
	}
	@Deprecated
	public static double[][] getDoubleArrayTF(MultiVriateDataDocument multivariateDataDocument ,char DocumentAxis, int GestureNumber) throws IOException
	{
		double[][] op=new double[20][];
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			UnivariateDataDocument curUnivariateDataDocument=multivariateDataDocument.getUnivariateDataDocument(i);
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			op[i]=new double[ipWords.size()];
			for(int j=0;j<ipWords.size();j++)
			{
				String curWord=ipWords.get(j);
				op[i][j]=curUnivariateDataDocument.getWords().getTF(curWord);
			}
		}
		return op;
	}
	public static double[][] getDoubleArray(char DocumentAxis, int GestureNumber) throws IOException
	{
		double[][] op=new double[Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS][];
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			op[i]=new double[NormalizedMultiVariateData.get(i).size()];
			for(int j=0;j<NormalizedMultiVariateData.get(i).size();j++)
			{
				op[i][j]=NormalizedMultiVariateData.get(i).get(j);
			}
		}
		return op;
	}
	//	public static void createHeatMap(MultiVriateDataDocument multiVriateDataDocument,char DocumentAxis, int GestureNumber) throws IOException
	//	{
	//		double [][] map=getDoubleArray(DocumentAxis, GestureNumber);
	//		double [][] topWordsArray=top10TFIDFMatrix(DocumentAxis, GestureNumber, multiVriateDataDocument);
	//		double[][] data=mergeDoubleArrays(map, topWordsArray);
	//		HeatChart chart = new HeatChart(data);
	//		chart.setTitle("HeatMap "+DocumentAxis+""+(GestureNumber));
	//		chart.setXAxisLabel("Time");
	//		chart.setYAxisLabel("Univariate datas");
	//		//chart.setHighValueColour(java.awt.Color.GREEN);
	//		chart.setBackgroundColour(java.awt.Color.GREEN);
	//		File chartPNG=new File(Consatnts.PATH1+"my-chart.png");
	//		chart.saveToFile(chartPNG);
	//	}
	public static void calculateQueryTFIDF(MultiVriateDataDocument queryMultiVariateDataDocument, GesturesLibrary gesturesLibrary, MultiVriateDataDocument multiVariateDataDocument, char DocumentAxis)
	{
		for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
		{
			WordDictionary Words=queryMultiVariateDataDocument.getUnivariateDataDocument(j).getWords();
			for(int k=0;k<Words.size();k++)
			{
				String curWord=Words.getWord(k);
				//Words.setTF(curWord, (0.5+(0.5*(new Double(Words.getCount(curWord))/new Double(Words.getoTalNumberOfWordsInUnivariate()))/Words.maxFrequency())));
				Words.setTF(curWord, (((new Double(Words.getCount(curWord))/new Double(Words.getoTalNumberOfWordsInUnivariate())))));
				Words.setIDF(curWord, getQueryIDF(curWord, queryMultiVariateDataDocument,gesturesLibrary,DocumentAxis));
				Words.setIDF1(curWord, getQueryIDF1(curWord,queryMultiVariateDataDocument, multiVariateDataDocument));
				Words.setTFIDF(curWord, (Words.getTF(curWord)*Words.getIDF(curWord)));
				Words.setTFIDF1(curWord, (Words.getTF(curWord)*Words.getIDF1(curWord)));
			}
		}
	}
	private static Double getQueryIDF(String word,MultiVriateDataDocument queryMultiVariateDataDocument ,GesturesLibrary gesturesLibrary,char DocumentAxis)
	{
		int documentCount=(gesturesLibrary.findWordDocumentFrequency(word, DocumentAxis)+queryMultiVariateDataDocument.getWordDocumentFrequency().getCount(word));
		return Math.log10((new Double((Consatnts.TOTAL_NUMBER_OF_DOCUMENTS)+queryMultiVariateDataDocument.getMultiVariateDataDocumentSize())/new Double(documentCount)));
	}
	private static Double getQueryIDF1(String word,MultiVriateDataDocument queryMultiVariateDataDocument,MultiVriateDataDocument multiVriateDataDocument)
	{
		//		if(multiVriateDataDocument.getWordDocumentFrequency().findWord(word))
		//			return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS)/ new Double(multiVriateDataDocument.getWordDocumentFrequency().getCount(word))));
		//		else
		return Math.log10((new Double(Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS)/ new Double(queryMultiVariateDataDocument.getWordDocumentFrequency().getCount(word))));
	}
	public static Double compareGestureOnTFIDF(Gesture gesture, Gesture queryGesture)
	{
		System.out.println(gesture+" "+queryGesture);
		Double distance=0.0;
		distance+=compareMultiVariate(2, queryGesture.getXMultivariateData(),gesture.getXMultivariateData());
		distance+=compareMultiVariate(2, queryGesture.getYMultivariateData(),gesture.getYMultivariateData());
		distance+=compareMultiVariate(2, queryGesture.getZMultivariateData(),gesture.getZMultivariateData());
		distance+=compareMultiVariate(2, queryGesture.getWMultivariateData(),gesture.getWMultivariateData());
		return distance;
	}
	public static Double compareGestureOnTFIDFOnSimilarity(Gesture gesture, Gesture queryGesture)
	{
		Double distance=0.0;
		distance+=compareMultiVariateONTFIDFSimilarity( queryGesture.getXMultivariateData(),gesture.getXMultivariateData());
		distance+=compareMultiVariateONTFIDFSimilarity( queryGesture.getYMultivariateData(),gesture.getYMultivariateData());
		distance+=compareMultiVariateONTFIDFSimilarity( queryGesture.getZMultivariateData(),gesture.getZMultivariateData());
		distance+=compareMultiVariateONTFIDFSimilarity( queryGesture.getWMultivariateData(),gesture.getWMultivariateData());
		return distance/4;
	}
	public static Double compareGestureOnTFIDF1OnSimilarity(Gesture gesture, Gesture queryGesture)
	{
		Double distance=0.0;
		distance+=compareMultiVariateONTFIDF1Similarity( queryGesture.getXMultivariateData(),gesture.getXMultivariateData());
		distance+=compareMultiVariateONTFIDF1Similarity( queryGesture.getYMultivariateData(),gesture.getYMultivariateData());
		distance+=compareMultiVariateONTFIDF1Similarity( queryGesture.getZMultivariateData(),gesture.getZMultivariateData());
		distance+=compareMultiVariateONTFIDF1Similarity( queryGesture.getWMultivariateData(),gesture.getWMultivariateData());
		return distance/4;
	}
	public static Double compareGestureOnTFIDF2(Gesture gesture, Gesture queryGesture)
	{
		Double distance=0.0;
		distance+=compareMultiVariate(3, queryGesture.getXMultivariateData(),gesture.getXMultivariateData());
		distance+=compareMultiVariate(3, queryGesture.getYMultivariateData(),gesture.getYMultivariateData());
		distance+=compareMultiVariate(3, queryGesture.getZMultivariateData(),gesture.getZMultivariateData());
		distance+=compareMultiVariate(3, queryGesture.getWMultivariateData(),gesture.getWMultivariateData());
		return distance;
	}
	public static Double compareMultiVariate(int option,MultiVriateDataDocument queryMultiVriateDataDocument, MultiVriateDataDocument multiVariateDataDocument)
	{
		switch(option)
		{
		case 1:
			Double distance=0.0;
			for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
			{
				Double differenceTF=compareUnivariateOnTF(queryMultiVriateDataDocument.getUnivariateDataDocument(i), multiVariateDataDocument.getUnivariateDataDocument(i));
				distance+=differenceTF;
			}
			return distance;
		case 2:
			distance=0.0;
			for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
			{
				Double differenceTFIDF=compareUnivariateOnTFIDF(queryMultiVriateDataDocument.getUnivariateDataDocument(i), multiVariateDataDocument.getUnivariateDataDocument(i));
				distance+=differenceTFIDF;
			}
			return distance;
		case 3:
			distance=0.0;
			for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
			{
				Double differenceTFIDF1=compareUnivariateOnTFIDF1(queryMultiVriateDataDocument.getUnivariateDataDocument(i), multiVariateDataDocument.getUnivariateDataDocument(i));
				distance+=differenceTFIDF1;
			}
			return distance;
		default: return null;
		}
	}
	public static Double compareMultiVariateONTFIDFSimilarity(MultiVriateDataDocument queryMultiVriateDataDocument, MultiVriateDataDocument multiVariateDataDocument)
	{
		
			double distance=0.0;
			for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
			{
				Double differenceTFIDF=compareUnivariateOnTFIDFSimilarity(queryMultiVriateDataDocument.getUnivariateDataDocument(i), multiVariateDataDocument.getUnivariateDataDocument(i));
				distance+=differenceTFIDF;
			}
			return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		
	}
	public static Double compareMultiVariateONTFIDF1Similarity(MultiVriateDataDocument queryMultiVriateDataDocument, MultiVriateDataDocument multiVariateDataDocument)
	{
		
			double distance=0.0;
			for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
			{
				Double differenceTFIDF=compareUnivariateOnTFIDF1Similarity(queryMultiVriateDataDocument.getUnivariateDataDocument(i), multiVariateDataDocument.getUnivariateDataDocument(i));
				distance+=differenceTFIDF;
			}
			return distance/Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;
		
	}
	public static Double compareUnivariateOnTF(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		WordDictionary qWords=queryUnivariateDataDocument.getWords();
		WordDictionary words=univariateDataDocument.getWords();
		Double DifferenceTF=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.getWord(i).equals(words.getWord(j)))
				{
					Double temp=(qWords.getTF(qWords.getWord(i))-words.getTF(words.getWord(j)));
					DifferenceTF+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTF+=(qWords.getTF(qWords.getWord(i))*qWords.getTF(qWords.getWord(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTF+=(words.getTF(words.getWord(j))*words.getTF(words.getWord(j)));
			}
		}
		return Math.sqrt(DifferenceTF);
	}
	public static Double compareUnivariateOnTFN(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		ArrayList<String> qWords=queryUnivariateDataDocument.getIPWords();
		ArrayList<String> words=univariateDataDocument.getIPWords();
		Double DifferenceTF=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.get(i).equals(words.get(j)))
				{
					Double temp=(queryUnivariateDataDocument.getWords().getTF(qWords.get(i))-univariateDataDocument.getWords().getTF(words.get(j)));
					DifferenceTF+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTF+=(queryUnivariateDataDocument.getWords().getTF(qWords.get(i))*queryUnivariateDataDocument.getWords().getTF(qWords.get(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTF+=(univariateDataDocument.getWords().getTF(words.get(j))*univariateDataDocument.getWords().getTF(words.get(j)));
			}
		}
		return Math.sqrt(DifferenceTF);
	}
	public static Double compareUnivariateOnTFIDFN(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		ArrayList<String> qWords=queryUnivariateDataDocument.getIPWords();
		ArrayList<String> words=univariateDataDocument.getIPWords();
		Double DifferenceTFIDF=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.get(i).equals(words.get(j)))
				{
					Double temp=(queryUnivariateDataDocument.getWords().getTFIDF(qWords.get(i))-univariateDataDocument.getWords().getTFIDF(words.get(j)));
					DifferenceTFIDF+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTFIDF+=(queryUnivariateDataDocument.getWords().getTFIDF(qWords.get(i))*queryUnivariateDataDocument.getWords().getTFIDF(qWords.get(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTFIDF+=(univariateDataDocument.getWords().getTFIDF(words.get(j))*univariateDataDocument.getWords().getTFIDF(words.get(j)));
			}
		}
		return Math.sqrt(DifferenceTFIDF);
	}
	public static Double compareUnivariateOnTFIDF1N(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		ArrayList<String> qWords=queryUnivariateDataDocument.getIPWords();
		ArrayList<String> words=univariateDataDocument.getIPWords();
		Double DifferenceTFIDF1=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.get(i).equals(words.get(j)))
				{
					Double temp=(queryUnivariateDataDocument.getWords().getTFIDF1(qWords.get(i))-univariateDataDocument.getWords().getTFIDF1(words.get(j)));
					DifferenceTFIDF1+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTFIDF1+=(queryUnivariateDataDocument.getWords().getTFIDF1(qWords.get(i))*queryUnivariateDataDocument.getWords().getTFIDF1(qWords.get(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTFIDF1+=(univariateDataDocument.getWords().getTFIDF1(words.get(j))*univariateDataDocument.getWords().getTFIDF1(words.get(j)));
			}
		}
		return Math.sqrt(DifferenceTFIDF1);
	}
	public static Double compareUnivariateOnTFIDF(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		WordDictionary qWords=queryUnivariateDataDocument.getWords();
		WordDictionary words=univariateDataDocument.getWords();
		Double DifferenceTFIDF=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.getWord(i).equals(words.getWord(j)))
				{
					Double temp=(qWords.getTFIDF(qWords.getWord(i))-words.getTFIDF(words.getWord(j)));
					DifferenceTFIDF+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTFIDF+=(qWords.getTFIDF(qWords.getWord(i))*qWords.getTFIDF(qWords.getWord(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTFIDF+=(words.getTFIDF(words.getWord(j))*words.getTFIDF(words.getWord(j)));
			}
		}
		return Math.sqrt(DifferenceTFIDF);
	}
	public static Double compareUnivariateOnTFIDFSimilarity(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
	
		ArrayList<String> qWords=queryUnivariateDataDocument.getIPWords();
		ArrayList<String> words=univariateDataDocument.getIPWords();
		for(int i=0;i<qWords.size();i++)
		{
			if(!words.contains(qWords.get(i)))
				words.add(qWords.get(i));
		}
		double[] woT=new double[words.size()];
		double[] qwoT=new double[words.size()];
		for(int i=0;i<words.size();i++)
		{
			if(univariateDataDocument.getWords().findWord(words.get(i)))
			{
				woT[i]=univariateDataDocument.getWords().getTFIDF(words.get(i));
			}
			else
			{
				woT[i]=0;
			}
			if(queryUnivariateDataDocument.getWords().findWord(words.get(i)))
			{
				qwoT[i]=queryUnivariateDataDocument.getWords().getTFIDF(words.get(i));
			}
			else
			{
				qwoT[i]=0;
			}
			
			
		}
		return cosine_similarity(qwoT, woT); 
	}
	public static Double compareUnivariateOnTFIDF1Similarity(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
	
		ArrayList<String> qWords=queryUnivariateDataDocument.getIPWords();
		ArrayList<String> words=univariateDataDocument.getIPWords();
		for(int i=0;i<qWords.size();i++)
		{
			if(!words.contains(qWords.get(i)))
				words.add(qWords.get(i));
		}
		double[] woT=new double[words.size()];
		double[] qwoT=new double[words.size()];
		for(int i=0;i<words.size();i++)
		{
			if(univariateDataDocument.getWords().findWord(words.get(i)))
			{
				woT[i]=univariateDataDocument.getWords().getTFIDF1(words.get(i));
			}
			else
			{
				woT[i]=0;
			}
			if(queryUnivariateDataDocument.getWords().findWord(words.get(i)))
			{
				qwoT[i]=queryUnivariateDataDocument.getWords().getTFIDF1(words.get(i));
			}
			else
			{
				qwoT[i]=0;
			}
			
			
		}
		return cosine_similarity(qwoT, woT); 
	}
	public static double cosine_similarity(double[] vec1, double[] vec2) 
	{ 
		double dp = dot_product(vec1,vec2); 
		double magnitudeA = find_magnitude(vec1); 
		double magnitudeB = find_magnitude(vec2); 
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
		return (dp)/(magnitudeA*magnitudeB); 
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

	public static Double compareUnivariateOnTFIDF1(UnivariateDataDocument queryUnivariateDataDocument, UnivariateDataDocument univariateDataDocument)
	{
		WordDictionary qWords=queryUnivariateDataDocument.getWords();
		WordDictionary words=univariateDataDocument.getWords();
		Double DifferenceTFIDF1=0.0;
		boolean ib[]= new boolean[qWords.size()];
		boolean jb[]=new boolean[words.size()];
		for(int i=0;i<qWords.size();i++)
		{
			for(int j=0;j<words.size();j++)
			{
				if(qWords.getWord(i).equals(words.getWord(j)))
				{
					Double temp=(qWords.getTFIDF1(qWords.getWord(i))-words.getTFIDF1(words.getWord(j)));
					DifferenceTFIDF1+=temp*temp;
					jb[j]=true;
					ib[i]=true;
				}
			}
		}
		for(int i=0;i<qWords.size();i++)
		{
			if(!ib[i])
			{
				DifferenceTFIDF1+=(qWords.getTFIDF1(qWords.getWord(i))*qWords.getTFIDF1(qWords.getWord(i)));
			}
		}
		for(int j=0;j<words.size();j++)
		{
			if(!jb[j])
			{
				DifferenceTFIDF1+=(words.getTFIDF1(words.getWord(j))*words.getTFIDF1(words.getWord(j)));
			}
		}
		return Math.sqrt(DifferenceTFIDF1);
	}
	/*	void testNormalize1(int n) throws IOException
	{
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV("/Users/ASUuser/Desktop/sampledata/X/1.csv");
		System.out.println(normalize1(MultiVariateData.get(n)));
	}
	void testReadCSV() throws IOException
	{
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV("C:/Users/fg4mqr/Desktop/Dbropbox/Multimedia and Web Databases/Projects/Phase 1/sampledata/X/1.csv");
		for(int i=0;i<MultiVariateData.size();i++)
		{
			System.out.println(MultiVariateData.get(i));
			System.out.println("Univariate Size: "+MultiVariateData.size());
		}
		System.out.println("Multivariate Size: "+MultiVariateData.size());
	}
	void testDefineGuassianLevels(int n)
	{
		defineGuassianLevels(n,0.0,0.25);
		for(int i=0;i<Levels.length;i++)
		{
			System.out.print(Levels[i]+" ");
		}
	}
	void testGetLevel() throws IOException
	{
		defineGuassianLevels(4,0.0,0.25);
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV("C:/Users/fg4mqr/Desktop/Dropbox/Multimedia and Web Databases/Projects/Phase 1/sampledata/X/1.csv");
		ArrayList<Double> univariateNormlized=normalize(MultiVariateData.get(0));
		System.out.println(univariateNormlized);
		for(int i=0;i<univariateNormlized.size();i++)
		{
			System.out.print(getLevel(univariateNormlized.get(i))+" ");
		}
		System.out.println(discretize(univariateNormlized));
	}
	void testGetWords() throws IOException
	{
		defineGuassianLevels(4,0.0,0.25);
		ArrayList<ArrayList<Double>> MultiVariateData=readCSV("C:/Users/fg4mqr/Desktop/Dropbox/Multimedia and Web Databases/Projects/Phase 1/sampledata/X/1.csv");
		ArrayList<Double> univariateNormlized=normalize(MultiVariateData.get(0));
		System.out.println(univariateNormlized);
		ArrayList<Integer>DiscretizedUnivariate=discretize(univariateNormlized);
		System.out.println(DiscretizedUnivariate);
		System.out.println(getWords(DiscretizedUnivariate, 4, 2));
	}
	public static void main(String[] args) throws IOException 
	{
		Wassup w=new Wassup();
		w.testNormalize1(0);
	}
	 */
}
