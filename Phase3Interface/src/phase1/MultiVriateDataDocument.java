package phase1;

import java.io.IOException;
import java.util.ArrayList;

public class MultiVriateDataDocument 
{
	private char DocumentAxis;
	private int GestureNumber;
	private ArrayList<UnivariateDataDocument> UnivariateDatas;
	private WordDictionary WordDocumentFrequency;
	public MultiVriateDataDocument(String QueryPath,char DocumentAxis) throws IOException 
	{
		//Consatnts.WINDOW_SIZE=4;
		this.GestureNumber=99999;
		this.DocumentAxis=DocumentAxis;
		UnivariateDatas=new ArrayList<UnivariateDataDocument>();
		WordDocumentFrequency=new WordDictionary();
		Functions.defineGuassianLevels(Consatnts.NUMBER_OF_LEVELS,Consatnts.MEAN_VALUE,Consatnts.STANDARD_DEVIATION_VALUE);
		ArrayList<ArrayList<Double>> MultiVariateData=Functions.readCSV(QueryPath);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			UnivariateDataDocument udd=new UnivariateDataDocument(i);
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			udd.addWords(ipWords);
			udd.setIPWords(ipWords);
			UnivariateDatas.add(udd);
			WordDictionary uddWordDictionary=udd.getWords();
			for(int j=0;j<uddWordDictionary.size();j++)
			{
				if(WordDocumentFrequency.findWord(uddWordDictionary.getWord(j)))
					WordDocumentFrequency.incrementCount(uddWordDictionary.getWord(j));
				else
					WordDocumentFrequency.addWord(uddWordDictionary.getWord(j));
			}
		}
	}
	public MultiVriateDataDocument(char DocumentAxis, int GestureNumber) throws IOException 
	{
		String path=Consatnts.PATH1+DocumentAxis+"/"+GestureNumber+".csv";
		this.GestureNumber=GestureNumber;
		this.DocumentAxis=DocumentAxis;
		UnivariateDatas=new ArrayList<UnivariateDataDocument>();
		WordDocumentFrequency=new WordDictionary();
		Functions.defineGuassianLevels(Consatnts.NUMBER_OF_LEVELS,Consatnts.MEAN_VALUE,Consatnts.STANDARD_DEVIATION_VALUE);
		ArrayList<ArrayList<Double>> MultiVariateData=Functions.readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
	//**********For Saving Normalized Data**************	
		String pathToSave=System.getProperty("user.dir");
		//		System.out.println(path);
		if(pathToSave.contains("/"))
		{ 
			DocumentAxis=Character.toUpperCase(DocumentAxis);
			pathToSave+="/normalizedData/"+DocumentAxis+"/";
		}
		if(pathToSave.contains("\\"))
		{
			pathToSave+="\\normalizedData\\"+DocumentAxis+"\\";
		}
		phase2.Functions.saveFileWithNumStringArray(pathToSave, Integer.toString(GestureNumber)+"_norm.csv", phase3.Functions.convertDoubleArrayListToStringArray(NormalizedMultiVariateData));

		ArrayList<ArrayList<Integer>> DiscretizedMultivariateData= new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			DiscretizedMultivariateData.add(DiscretizedUnivariate);
			UnivariateDataDocument udd=new UnivariateDataDocument(i);
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			//System.out.println(DocumentAxis+" "+GestureNumber+"  "+i+" "+ipWords);
			udd.setIPWords(ipWords);
			udd.addWords(ipWords);
			UnivariateDatas.add(udd);
			WordDictionary uddWordDictionary=udd.getWords();
			for(int j=0;j<uddWordDictionary.size();j++)
			{
				if(WordDocumentFrequency.findWord(uddWordDictionary.getWord(j)))
					WordDocumentFrequency.incrementCount(uddWordDictionary.getWord(j));
				else
					WordDocumentFrequency.addWord(uddWordDictionary.getWord(j));
			}
		}
		pathToSave=System.getProperty("user.dir");
		if(pathToSave.contains("/"))
		{ 
			DocumentAxis=Character.toUpperCase(DocumentAxis);
			pathToSave+="/discretizedData/"+DocumentAxis+"/";
		}
		if(pathToSave.contains("\\"))
		{
			pathToSave+="\\discretizedData\\"+DocumentAxis+"\\";
		}
		phase2.Functions.saveFileWithNumStringArray(pathToSave, Integer.toString(GestureNumber)+"_dis.csv", phase3.Functions.convertIntegerArrayListToStringArray(DiscretizedMultivariateData));
		
		pathToSave=System.getProperty("user.dir");
		if(pathToSave.contains("/"))
		{ 
			DocumentAxis=Character.toUpperCase(DocumentAxis);
			pathToSave+="/words/"+DocumentAxis+"/";
		}
		if(pathToSave.contains("\\"))
		{
			pathToSave+="\\words\\"+DocumentAxis+"\\";
		}
		phase2.Functions.saveFileWithNumStringArray(pathToSave, Integer.toString(GestureNumber)+"_words.csv", getMulWords(UnivariateDatas));
	}
	public UnivariateDataDocument getUnivariateDataDocument(int index)
	{
		return UnivariateDatas.get(index);
	}
	public ArrayList<UnivariateDataDocument> getAllUnivariateDataDocument()
	{		
		return UnivariateDatas;
	}
	public int getMultiVariateDataDocumentSize()
	{
		return UnivariateDatas.size();
	}
	public WordDictionary getWordDocumentFrequency()
	{
		return WordDocumentFrequency;
	}
	public int getGestureNumber()
	{
		return GestureNumber;
	}
	public char getDocumentAxis()
	{
		return DocumentAxis;
	}
	@Deprecated
	public void readMultiVariate(String path) throws IOException
	{
		Functions.defineGuassianLevels(Consatnts.NUMBER_OF_LEVELS,Consatnts.MEAN_VALUE,Consatnts.STANDARD_DEVIATION_VALUE);
		ArrayList<ArrayList<Double>> MultiVariateData=Functions.readCSV(path);
		ArrayList<ArrayList<Double>> NormalizedMultiVariateData=Functions.normalize(MultiVariateData);
		for(int i=0;i<NormalizedMultiVariateData.size();i++)
		{
			ArrayList<Integer>DiscretizedUnivariate=Functions.discretize(NormalizedMultiVariateData.get(i));
			UnivariateDataDocument udd=new UnivariateDataDocument(i);
			ArrayList<String> ipWords=Functions.getWords(DiscretizedUnivariate, Consatnts.WINDOW_SIZE, Consatnts.STEP_SIZE);
			udd.addWords(ipWords);
			UnivariateDatas.add(udd);
			WordDictionary uddWordDictionary=udd.getWords();
			for(int j=0;j<uddWordDictionary.size();j++)
			{
				if(WordDocumentFrequency.findWord(uddWordDictionary.getWord(j)))
					WordDocumentFrequency.incrementCount(uddWordDictionary.getWord(j));
				else
					WordDocumentFrequency.addWord(uddWordDictionary.getWord(j));
			}
		}
	}
	public void printWordDocumentFrequencies()
	{
		for(int i=0;i<WordDocumentFrequency.size();i++)
		{
			System.out.println("Word: "+WordDocumentFrequency.getWord(i)+" Number of Documents: "+WordDocumentFrequency.getCount(WordDocumentFrequency.getWord(i)));
		}
	}
	@Deprecated
	public void printWholeDocument()
	{
		System.out.println("MULTIVARIATE DOCUMENT INFO: "+DocumentAxis+":"+GestureNumber);
		System.out.println("********************************************************Univariate Documents**************************************************************");
		for(int i=0;i<UnivariateDatas.size();i++)
		{
			System.out.println("Univariate Document Number: "+UnivariateDatas.get(i).getDocumnetNumber());
			System.out.println(UnivariateDatas.get(i));
		}
	}
	public String toString()
	{
		String op="";
		op+="MULTIVARIATE DOCUMENT INFO: "+DocumentAxis+":"+GestureNumber+"\n"+"*************************************************Univariate Documents*********************************************************";
		op+="\n";
		for(int i=0;i<UnivariateDatas.size();i++)
		{
			op+="Univariate Document Number: "+UnivariateDatas.get(i).getDocumnetNumber()+"\n";
			op+=UnivariateDatas.get(i)+"\n\n";
		}
		return op;
	}
	public String[][] getMulWords()
	{
		ArrayList<UnivariateDataDocument> unis= this.getAllUnivariateDataDocument();
		String[][] words= new String[unis.size()][];
		for(int i=0;i<unis.size();i++)
		{
			UnivariateDataDocument curDataDocument= unis.get(i);
			words[i]=new String[curDataDocument.getIPWords().size()];
			ArrayList<String> ipwords= curDataDocument.getIPWords();
			words[i]=ipwords.toArray(new String[curDataDocument.getIPWords().size()]);
		}
		return words;
	}
	public String[][] getMulWords(ArrayList<UnivariateDataDocument> unis)
	{
		String[][] words= new String[unis.size()][];
		for(int i=0;i<unis.size();i++)
		{
			UnivariateDataDocument curDataDocument= unis.get(i);
			words[i]=new String[curDataDocument.getIPWords().size()];
			ArrayList<String> ipwords= curDataDocument.getIPWords();
			words[i]=ipwords.toArray(new String[curDataDocument.getIPWords().size()]);
		}
		return words;
	}
	
	public ArrayList<String> getMulWordsInOneList()
	{
		ArrayList<UnivariateDataDocument> unis= this.getAllUnivariateDataDocument();
		ArrayList<String> allMulWords= new ArrayList<String>();
		
		for(int i=0;i<unis.size();i++)
		{
			UnivariateDataDocument curDataDocument= unis.get(i);
			allMulWords.addAll(curDataDocument.getIPWords());
		}
		return phase3.Functions.getUniqueWords(allMulWords);
	}
	//	public void hetaMap(String dataChoice) throws IOException, MatlabConnectionException, MatlabInvocationException
	//	{
	//		dataChoice=dataChoice.toLowerCase();
	//		int dc=3;
	//		if(dataChoice.equals("tf"))dc=0;
	//		else if(dataChoice.equals("tfidf1"))dc=2;
	//		else if(dataChoice.equals("tfidf"))dc=1;
	//		else if(dataChoice.equals("idf1"))dc=4;
	//		else if(dataChoice.equals("idf"))dc=5;
	//		switch(dc)
	//		{
	//		case 0:
	//			Functions.craetetopTFHeatMap(this, DocumentAxis, GestureNumber);
	//			break;
	//		case 1:
	//			Functions.craetetopTFIFDHeatMap(this, DocumentAxis, GestureNumber);
	//			break;
	//		case 2:
	//			Functions.craetetopTFIFD1HeatMap(this, DocumentAxis, GestureNumber);
	//			break;
	//		case 4:
	//			Functions.craetetopIFD1HeatMap(this, DocumentAxis, GestureNumber);
	//			break;
	//		case 5: 
	//			Functions.craetetopIFDHeatMap(this, DocumentAxis, GestureNumber);
	//			break;
	//		default :
	//			Functions.craeteHeatMap(DocumentAxis, GestureNumber);
	//		}
	//	}
}
