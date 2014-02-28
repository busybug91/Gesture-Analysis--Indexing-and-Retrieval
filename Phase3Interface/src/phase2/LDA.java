package phase2;
import mCode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phase1.GesturesLibrary;


import LDAGibb.*;
public class LDA
{
	private static List<String>  newdic; 
	public static TopicWrapper[][][] performLDA(GesturesLibrary gestureLibrary, char documentAxis, int k)
	{  
		String [][][] componentWords=gestureLibrary.getComponentWordsArray(documentAxis);
		String [][] sensorData= new String[componentWords.length][]; 
		TopicWrapper[][][] hello = new TopicWrapper[componentWords[0].length][][];
		for(int s = 0; s < componentWords[0].length; s++)
		{	
			for(int i = 0; i < componentWords.length; i++)
			{
				sensorData[i] = componentWords[i][s];
			}
			hello[s] = solve(documentAxis,(s+1), sensorData,"input-sensor-" + s, k);
		}
		return hello;
		//		// result contains new words/// now calculate tf/idf for these words
	}
	@SuppressWarnings("unused")
	public static double[][] VectorizeQuery(String[][] queryData1, FrequencyWrapper[][] ldaVectors)
	{
		String queryData[][][]=Functions.convert2Dto3DString(queryData1);
		FrequencyCounter fc = new FrequencyCounter();
		List<String> newdic2 = Extractor.BuildUniqueDictionary(queryData);
	//	System.out.println(newdic2);
		//System.out.println(newdic);
		fc.setDimensions(newdic);
		double[] _tf =null;
		double[] _idf = null;
		double[][] _TFIDF=null;
		FrequencyWrapper[][] vectors =fc.VectorizeDatabaseWithOverlap(queryData);
		for(int i = 0; i < vectors.length; i++)
		{
			_TFIDF=new double[vectors[i].length][];
			for(int j = 0; j < vectors[i].length; j++)
			{
				
				//System.out.println("file " + i + " sensor " + j + ":" + newdic);
				_idf=ldaVectors[i][j].getIDF();
				_tf=vectors[i][j].getTF();
				double[][] temp = new double[1][];
				temp[0] = _idf;
				//System.out.println("6325 " + j + ": " + Functions.printDoubleArray1(temp));
				double[][] temp2 = new double[1][];
				temp2[0] = _tf;
				//System.out.println("6421 " + j + ": " + Functions.printDoubleArray1(temp2));
				_TFIDF[j]= fc.compose(_tf, _idf);
			}
		}

		
		return _TFIDF;	
		
		
	}

	public static FrequencyWrapper[][] vectorizeNewData(String[][][] newData, List<String> dictionary)
	{
		FrequencyCounter fc = new FrequencyCounter();
		newdic = Extractor.BuildUniqueDictionary(newData);
		//System.out.println(newdic);
		fc.setDimensions(dictionary);
		FrequencyWrapper[][] vectors =fc.VectorizeDatabaseWithOverlap(newData);
		//
		//		for(int i = 0; i < vectors.length; i++)
		//		{
		//			for(int j = 0; j < vectors[i].length; j++)
		//			{
		//				//System.out.println("file " + i + " sensor " + j + ":" + newdic);
		//				vectors[i][j].print();
		//			}
		//		}
		return vectors;
	}
	@SuppressWarnings("unused")
	public static String[][] transformQuery(TopicWrapper[][][] topic_dist, String[][] data)
	{
		//We need to mess with the values!
		//Okay, so first mode of topic_dist is for each sensor; second is topics; third is the words themselves.
		double THRESHOLD = 0.05;
		TopicWrapper[] local_t;
		double total = 0.0;
		List<Map<String,String>> replacements_list = new ArrayList<Map<String,String>>();	
		for(int s = 0; s < topic_dist.length; s++)
		{
			Map<String,String> replacements = new HashMap<String,String>();
			for(int t = 0; t < topic_dist[s].length; t++)
				for(int w = 0; w < topic_dist[s][t].length; w++)
				{
					if(topic_dist[s][t][w].getProbablity() >= THRESHOLD)
					{
						if(replacements.containsKey(topic_dist[s][t][w].getWord()))
						{
							String value = replacements.get(topic_dist[s][t][w].getWord());
							value += "|" + topic_dist[s][t][w].getTopic();
							replacements.put(topic_dist[s][t][w].getWord(), value);
						}
						else
						{
							replacements.put(topic_dist[s][t][w].getWord(), topic_dist[s][t][w].getTopic());
						}
					}
				}
			replacements_list.add(replacements);
		}
		
		//System.out.println("Monika123" + replacements_list);		
		String newQueryData[][]=data.clone();
		//System.out.println(newQueryData.length);
		//System.out.println(newQueryData[0].length);
		for(int s = 0; s < newQueryData.length; s++)
		{
			Map<String,String> cur_repl = replacements_list.get(s);
			for(int w = 0; w < newQueryData[s].length; w++)
			{
				//System.out.println(newQueryData[s][w] + " = " + data[s][w]);
				if(cur_repl.containsKey(newQueryData[s][w]))
				{
					newQueryData[s][w] = cur_repl.get(newQueryData[s][w]);
				}
				else
				{
					newQueryData[s][w] = "....";
				}
			}
		}	
		FileUtility.SaveFile("./", "lda-replace-query.csv", newQueryData);
		return newQueryData;
	}

	@SuppressWarnings("unused")
	public static TopicWrapper[][] solve(char documentAxis, int sensorNumber,String [][] data, String modelName, int k)
	{
		String path=System.getProperty("user.dir");
		//		System.out.println(path);
		if(path.contains("/"))
		{ 
			documentAxis=Character.toUpperCase(documentAxis);
			path=path+"/ldaOutput/"+documentAxis+"/"+sensorNumber+"/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\ldaOutput\\"+documentAxis+"\\"+sensorNumber+"\\";
		}
		Functions.SaveFileWithNumber(path, modelName+".dat", data);
		LDACmdOption ldaOption = new LDACmdOption();
		ldaOption.est = true;
		ldaOption.dir = path;
		ldaOption.K = 3;
		ldaOption.modelName = "model-final";
		ldaOption.savestep = 1000;
		ldaOption.niters = 1000; 
		ldaOption.dfile = modelName+".dat";
		Estimator est = new Estimator();
		est.init(ldaOption);
		est.estimate(); 
		Inferencer inf = new Inferencer();
		inf.init(ldaOption);
		Model newModel = inf.inference();
		List<TopicWrapper[]> tw = Functions.LoadTopicDistributions(path,modelName + ".dat.model-final.twords", k,100);
		//		System.out.println("Value of ************************ topic wrapper size"+tw.size());
		TopicWrapper[][] tws = new TopicWrapper[tw.size()][];
		TopicWrapper[] tw_arr;
		for(int j = 0; j < tw.size(); j++)
		{
			//			System.out.println("======= " + tw.get(j).length);
			tw_arr = tw.get(j);
//			for(int i = 0; i < tw_arr.length; i++)
//			{	
//				System.out.println(j + " " + i);
//				System.out.println(tw_arr[i].getTopic() + "---" + tw_arr[i].getWord() + "--" + tw_arr[i].getProbablity()); 
//			}
		}
		tw.toArray(tws);
		return tws;
	}
	@SuppressWarnings("unused")
	public static String[][][] transform(TopicWrapper[][][] topic_dist, String[][][] data)
	{ 
		//We need to mess with the values!
		//Okay, so first mode of topic_dist is for each sensor; second is topics; third is the words themselves.
		double THRESHOLD = 0.05;
		TopicWrapper[] local_t;
		double total = 0.0;
		List<Map<String,String>> replacements_list = new ArrayList<Map<String,String>>();	
		for(int s = 0; s < topic_dist.length; s++)
		{
			Map<String,String> replacements = new HashMap<String,String>();
			for(int t = 0; t < topic_dist[s].length; t++)
				for(int w = 0; w < topic_dist[s][t].length; w++)
				{
					if(topic_dist[s][t][w].getProbablity() >= THRESHOLD)
					{
						if(replacements.containsKey(topic_dist[s][t][w].getWord()))
						{
							String value = replacements.get(topic_dist[s][t][w].getWord());
							value += "|" + topic_dist[s][t][w].getTopic();
							replacements.put(topic_dist[s][t][w].getWord(), value);
						}
						else
						{
							replacements.put(topic_dist[s][t][w].getWord(), topic_dist[s][t][w].getTopic());
						}
					}
				}
			replacements_list.add(replacements);
		}
		//System.out.println(replacements_list);		
		for(int f = 0; f < data.length; f++)
		{
			for(int s = 0; s < data[f].length; s++)
			{
				Map<String,String> cur_repl = replacements_list.get(s);
				for(int w = 0; w < data[f][s].length; w++)
				{
					if(cur_repl.containsKey(data[f][s][w]))
					{
						data[f][s][w] = cur_repl.get(data[f][s][w]);
					}
					else
					{
						data[f][s][w] = "....";
					}
				}
			}	
			FileUtility.SaveFile("./", "lda-replace-" + f + ".csv", data[f]);
		}
		return data;
	}
	public static List<String>  getNewDic()
	{
		return newdic;
	}
}	
