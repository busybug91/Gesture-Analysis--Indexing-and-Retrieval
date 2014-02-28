package phase1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GesturesLibrary implements Cloneable
{
	public Object clone()
	{  
		try{  
			return super.clone();  
		}catch(Exception e){ 
			return null; 
		}
	}
	private ArrayList<Gesture> Gestures;
	public ArrayList<String> directories;
	private WordDictionary XWordDocumentFrequency;
	private WordDictionary YWordDocumentFrequency;
	private WordDictionary ZWordDocumentFrequency;
	private WordDictionary WWordDocumentFrequency;
	private ArrayList<WordDictionary> xWordDictionaries;
	private ArrayList<WordDictionary> yWordDictionaries;
	private ArrayList<WordDictionary> zWordDictionaries;
	private ArrayList<WordDictionary> wWordDictionaries;
	public ArrayList<Integer>  gestureNumbers;
	
	public ArrayList<String> getDirectories()
	{
		return directories;
	}
	public WordDictionary getXwordDicforSensors(int SensorNumber)
	{
		return xWordDictionaries.get(SensorNumber);
	}
	public WordDictionary getYwordDicforSensors(int SensorNumber)
	{
		return yWordDictionaries.get(SensorNumber);
	}
	public WordDictionary getZwordDicforSensors(int SensorNumber)
	{
		return zWordDictionaries.get(SensorNumber);
	}
	public WordDictionary getWwordDicforSensors(int SensorNumber)
	{
		return wWordDictionaries.get(SensorNumber);
	}
	public void addGesture(Gesture gesture)
	{
		Gestures.add(gesture);
	}
	public int getGestureIndex(int GestureNumber)
	{
		return gestureNumbers.indexOf((int)GestureNumber);
	}
	public int getGestureFileNumber(int index)
	{
		return gestureNumbers.get(index);
	}
	public GesturesLibrary() throws IOException 
	{
		Gestures=new ArrayList<Gesture>();
		XWordDocumentFrequency=new WordDictionary();
		YWordDocumentFrequency=new WordDictionary();
		ZWordDocumentFrequency=new WordDictionary();
		WWordDocumentFrequency=new WordDictionary();
		xWordDictionaries=new ArrayList<WordDictionary>();
		yWordDictionaries=new ArrayList<WordDictionary>();
		zWordDictionaries=new ArrayList<WordDictionary>();
		wWordDictionaries=new ArrayList<WordDictionary>();
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			xWordDictionaries.add(new WordDictionary());
			yWordDictionaries.add(new WordDictionary());
			zWordDictionaries.add(new WordDictionary());
			wWordDictionaries.add(new WordDictionary());
		}
		
		File dir=new File(Consatnts.PATH1);
		
		directories=new ArrayList<String>();
		if (dir.isDirectory()) 
		{
			for (File temp : dir.listFiles()) 
			{
				if (temp.isDirectory()) 
				{
					String path=temp.getAbsolutePath();
					if(path.contains("/"))
						path=path.substring(path.lastIndexOf("/")+1).trim();
					else
						path=path.substring(path.lastIndexOf("\\")+1).trim();	
					directories.add(path.toUpperCase());
					System.out.println(path);
				}
			}
		}
		gestureNumbers= Functions.searchDirectory(new File(Consatnts.PATH1+directories.get(0)));
		Collections.sort(gestureNumbers);
		Consatnts.TOTAL_NUMBER_OF_DOCUMENTS=(gestureNumbers.size()*20);
		Consatnts.TOTAL_NUMBER_OF_GESTURES=gestureNumbers.size();
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g =new Gesture(gestureNumbers.get(i),directories);
			Gestures.add(g);
		}
		if(directories.contains("X"))
			createXWordFrequencies();
		if(directories.contains("Y"))
			createYWordFrequencies();
		if(directories.contains("Z"))
			createZWordFrequencies();
		if(directories.contains("W"))
			createWWordFrequencies();
		Functions.calculateTFIDF(this);
	}
	public GesturesLibrary(ArrayList<Integer> gestureNumbers) throws IOException 
	{
		Gestures=new ArrayList<Gesture>();
		XWordDocumentFrequency=new WordDictionary();
		YWordDocumentFrequency=new WordDictionary();
		ZWordDocumentFrequency=new WordDictionary();
		WWordDocumentFrequency=new WordDictionary();
		xWordDictionaries=new ArrayList<WordDictionary>();
		yWordDictionaries=new ArrayList<WordDictionary>();
		zWordDictionaries=new ArrayList<WordDictionary>();
		wWordDictionaries=new ArrayList<WordDictionary>();
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;i++)
		{
			xWordDictionaries.add(new WordDictionary());
			yWordDictionaries.add(new WordDictionary());
			zWordDictionaries.add(new WordDictionary());
			wWordDictionaries.add(new WordDictionary());
		}
		
		File dir=new File(Consatnts.PATH1);
		
		directories=new ArrayList<String>();
		if (dir.isDirectory()) 
		{
			for (File temp : dir.listFiles()) 
			{
				if (temp.isDirectory()) 
				{
					String path=temp.getAbsolutePath();
					if(path.contains("/"))
						path=path.substring(path.lastIndexOf("/")+1).trim();
					else
						path=path.substring(path.lastIndexOf("\\")+1).trim();	
					directories.add(path.toUpperCase());
					System.out.println(path);
				}
			}
		}
		this.gestureNumbers=gestureNumbers;
		Collections.sort(gestureNumbers);
		Consatnts.TOTAL_NUMBER_OF_DOCUMENTS=(gestureNumbers.size()*20);
		Consatnts.TOTAL_NUMBER_OF_GESTURES=gestureNumbers.size();
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			Gesture g =new Gesture(gestureNumbers.get(i),directories);
			Gestures.add(g);
		}
		if(directories.contains("X"))
			createXWordFrequencies();
		if(directories.contains("Y"))
			createYWordFrequencies();
		if(directories.contains("Z"))
			createZWordFrequencies();
		if(directories.contains("W"))
			createWWordFrequencies();
		Functions.calculateTFIDF(this);
	}
	public int findWordDocumentFrequency(String word, char DocumentAxis)
	{
		int documentFreq=0;
		switch(DocumentAxis)
		{
		case 'X' :
			if(XWordDocumentFrequency.findWord(word))
			{
				documentFreq=XWordDocumentFrequency.getCount(word);
			}
			break;
		case 'Y' :
			if(YWordDocumentFrequency.findWord(word))
			{
				documentFreq=YWordDocumentFrequency.getCount(word);
			}
			break;
		case 'Z' :
			if(ZWordDocumentFrequency.findWord(word))
			{
				documentFreq=ZWordDocumentFrequency.getCount(word);
			}
			break;
		case 'W' :
			if(WWordDocumentFrequency.findWord(word))
			{
				documentFreq=WWordDocumentFrequency.getCount(word);
			}
			break;
		default :documentFreq=0;
		}
		return documentFreq;
	}
	private void createXWordFrequencies()
	{
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			WordDictionary XMultivariateDictionary=Gestures.get(i).getXMultivariateData().getWordDocumentFrequency();
			for(int j=0;j<XMultivariateDictionary.size();j++)
			{
				String curWord=XMultivariateDictionary.getWord(j);
				if(XWordDocumentFrequency.findWord(curWord))
				{
					Integer count1=XWordDocumentFrequency.getCount(curWord);
					//Integer count2=XMultivariateDictionary.getCount(curWord);
					XWordDocumentFrequency.setCount(curWord, (count1++));
				}
				else
				{
					XWordDocumentFrequency.addWord(curWord);
					//Integer count2=XMultivariateDictionary.getCount(curWord);
					XWordDocumentFrequency.setCount(curWord, new Integer(1));
				}
			}
		}
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument curMultiVriateDataDocument=Gestures.get(i).getXMultivariateData();
			for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
			{
				WordDictionary curDictionary=curMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curDictionary.size();k++)
				{
					String curWord=curDictionary.getWord(k);
					if(xWordDictionaries.get(j).findWord(curWord))
					{
						Integer count1=xWordDictionaries.get(j).getCount(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						xWordDictionaries.get(j).setCount(curWord, (count1++));
					}
					else
					{
						xWordDictionaries.get(j).addWord(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						xWordDictionaries.get(j).setCount(curWord, new Integer(1));
					}
				}
			}
		}
	}
	private void createYWordFrequencies()
	{
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			WordDictionary YMultivariateDictionary=Gestures.get(i).getYMultivariateData().getWordDocumentFrequency();
			for(int j=0;j<YMultivariateDictionary.size();j++)
			{
				String curWord=YMultivariateDictionary.getWord(j);
				if(YWordDocumentFrequency.findWord(curWord))
				{
					Integer count1=YWordDocumentFrequency.getCount(curWord);
					//Integer count2=YMultivariateDictionary.getCount(curWord);
					YWordDocumentFrequency.setCount(curWord, (count1++));
				}
				else
				{
					YWordDocumentFrequency.addWord(curWord);
					//Integer count2=YMultivariateDictionary.getCount(curWord);
					YWordDocumentFrequency.setCount(curWord, new Integer(1));
				}
			}
		}
		
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument curMultiVriateDataDocument=Gestures.get(i).getYMultivariateData();
			for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
			{
				WordDictionary curDictionary=curMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curDictionary.size();k++)
				{
					String curWord=curDictionary.getWord(k);
					if(yWordDictionaries.get(j).findWord(curWord))
					{
						Integer count1=yWordDictionaries.get(j).getCount(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						yWordDictionaries.get(j).setCount(curWord, (count1++));
					}
					else
					{
						yWordDictionaries.get(j).addWord(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						yWordDictionaries.get(j).setCount(curWord, new Integer(1));
					}
				}
			}
		}
	}
	private void createZWordFrequencies()
	{
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			WordDictionary ZMultivariateDictionary=Gestures.get(i).getZMultivariateData().getWordDocumentFrequency();
			for(int j=0;j<ZMultivariateDictionary.size();j++)
			{
				String curWord=ZMultivariateDictionary.getWord(j);
				if(ZWordDocumentFrequency.findWord(curWord))
				{
					Integer count1=ZWordDocumentFrequency.getCount(curWord);
					//Integer count2=ZMultivariateDictionary.getCount(curWord);
					ZWordDocumentFrequency.setCount(curWord, (count1++));
				}
				else
				{
					ZWordDocumentFrequency.addWord(curWord);
					//Integer count2=ZMultivariateDictionary.getCount(curWord);
					ZWordDocumentFrequency.setCount(curWord, new Integer(1));
				}
			}
		}
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument curMultiVriateDataDocument=Gestures.get(i).getZMultivariateData();
			for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
			{
				WordDictionary curDictionary=curMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curDictionary.size();k++)
				{
					String curWord=curDictionary.getWord(k);
					if(zWordDictionaries.get(j).findWord(curWord))
					{
						Integer count1=zWordDictionaries.get(j).getCount(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						zWordDictionaries.get(j).setCount(curWord, (count1++));
					}
					else
					{
						zWordDictionaries.get(j).addWord(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						zWordDictionaries.get(j).setCount(curWord, new Integer(1));
					}
				}
			}
		}
	}
	private void createWWordFrequencies()
	{
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			WordDictionary WMultivariateDictionary=Gestures.get(i).getWMultivariateData().getWordDocumentFrequency();
			for(int j=0;j<WMultivariateDictionary.size();j++)
			{
				String curWord=WMultivariateDictionary.getWord(j);
				if(WWordDocumentFrequency.findWord(curWord))
				{
					Integer count1=WWordDocumentFrequency.getCount(curWord);
					//Integer count2=WMultivariateDictionary.getCount(curWord);
					WWordDocumentFrequency.setCount(curWord, (count1++));
				}
				else
				{
					WWordDocumentFrequency.addWord(curWord);
					//Integer count2=WMultivariateDictionary.getCount(curWord);
					WWordDocumentFrequency.setCount(curWord, new Integer(1));
				}
			}
		}
		
		for(int i=0;i<Consatnts.TOTAL_NUMBER_OF_GESTURES;i++)
		{
			MultiVriateDataDocument curMultiVriateDataDocument=Gestures.get(i).getWMultivariateData();
			for(int j=0;j<Consatnts.TOTAL_NUMBER_OF_UNIVARIATEDATAS;j++)
			{
				WordDictionary curDictionary=curMultiVriateDataDocument.getUnivariateDataDocument(j).getWords();
				for(int k=0;k<curDictionary.size();k++)
				{
					String curWord=curDictionary.getWord(k);
					if(wWordDictionaries.get(j).findWord(curWord))
					{
						Integer count1=wWordDictionaries.get(j).getCount(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						wWordDictionaries.get(j).setCount(curWord, (count1++));
					}
					else
					{
						wWordDictionaries.get(j).addWord(curWord);
						//Integer count2=curDictionary.getCount(curWord);
						wWordDictionaries.get(j).setCount(curWord, new Integer(1));
					}
				}
			}
		}
	}
	public ArrayList<MultiVriateDataDocument> getAllMultiVariateDataDocument(char DocumentAxis)
	{

		ArrayList<MultiVriateDataDocument> allMul=new ArrayList<MultiVriateDataDocument>();
		MultiVriateDataDocument curMultivariateDataDocument;
		Gesture curGesture=null;
		for(int i=0;i<Gestures.size();i++)
		{
			curGesture=Gestures.get(i);

			switch(DocumentAxis)
			{
			case 'X' :curMultivariateDataDocument=curGesture.getXMultivariateData();
			break;
			case 'Y' :curMultivariateDataDocument=curGesture.getYMultivariateData();
			break;
			case 'Z' :curMultivariateDataDocument=curGesture.getZMultivariateData();
			break;
			case 'W' :curMultivariateDataDocument=curGesture.getWMultivariateData();
			break;
			default :curMultivariateDataDocument=null;
			}
			allMul.add(curMultivariateDataDocument);
		}
		return allMul;	
	}


	public MultiVriateDataDocument getMultivariateData(char DocumentAxis, int GestureNumber)
	{
		MultiVriateDataDocument curMultivariateDataDocument;
		Gesture curGesture=null;
		for(int i=0;i<Gestures.size();i++)
		{
			if(Gestures.get(i).getGestureNumber()==GestureNumber)
				curGesture=Gestures.get(i);
		}
		switch(DocumentAxis)
		{
		case 'X' :curMultivariateDataDocument=curGesture.getXMultivariateData();
		break;
		case 'Y' :curMultivariateDataDocument=curGesture.getYMultivariateData();
		break;
		case 'Z' :curMultivariateDataDocument=curGesture.getZMultivariateData();
		break;
		case 'W' :curMultivariateDataDocument=curGesture.getWMultivariateData();
		break;
		default :curMultivariateDataDocument=null;
		}
		return curMultivariateDataDocument;
	}
	public WordDictionary getXWordDocumentFrequency()
	{
		return XWordDocumentFrequency;
	}
	public WordDictionary getYWordDocumentFrequency()
	{
		return YWordDocumentFrequency;
	}
	public WordDictionary getZWordDocumentFrequency()
	{
		return ZWordDocumentFrequency;
	}
	public WordDictionary getWWordDocumentFrequency()
	{
		return WWordDocumentFrequency;
	}
	public int size()
	{
		return Gestures.size();
	}
	public Gesture getGestureByFileNumber(int gestureFileNumber)
	{
		return Gestures.get(getGestureIndex(gestureFileNumber));
	}
	public Gesture getGesture(int i)
	{
		return Gestures.get(i);
	}
	public void queryDocument(String QueryPath, char DocumentAxis, int option) throws IOException
	{
		//String QueryPath=Consatnts.PATH1+"/"+QueryName+".csv";
		MultiVriateDataDocument queryMultiVariateDataDocument=new MultiVriateDataDocument(QueryPath,DocumentAxis);
		ResultSet resultSet = new ResultSet();
		for(int i=0;i<Gestures.size();i++)
		{
			MultiVriateDataDocument multiVariateDataDocument=getMultivariateData(DocumentAxis, gestureNumbers.get(i));
			Functions.calculateQueryTFIDF(queryMultiVariateDataDocument, this, multiVariateDataDocument, DocumentAxis);
			Result r =new Result(multiVariateDataDocument,Functions.compareMultiVariate(option,queryMultiVariateDataDocument, multiVariateDataDocument));
			resultSet.addResult(r);
		}
		resultSet.printTop101();
	}
	public void queryGestureONTFIDF(Gesture queryGesture) throws IOException
	{
		ResultSet resultSet = new ResultSet();
		for(int i=0;i<Gestures.size();i++)
		{
			Gesture g=getGesture(i);
			Result r =new Result(g,Functions.compareGestureOnTFIDF(g, queryGesture));
			resultSet.addResult(r);
		}
		resultSet.printTop101();
	}
	public void queryGestureONTFIDF2(Gesture queryGesture) throws IOException
	{
		ResultSet resultSet = new ResultSet();
		for(int i=0;i<Gestures.size();i++)
		{
			Gesture g=getGesture(i);
			Result r =new Result(g,Functions.compareGestureOnTFIDF2(g, queryGesture));
			resultSet.addResult(r);
		}
		resultSet.printTop101();
	}
	public String[][][] getComponentWordsArray(char documentAxis)
	{
		ArrayList<MultiVriateDataDocument> allMul=getAllMultiVariateDataDocument(documentAxis);
		String array[][][]=new String[allMul.size()][][];
		for(int i=0;i<allMul.size();i++)
		{
			MultiVriateDataDocument curMultiVriateDataDocument=allMul.get(i);
			array[i]=new String[curMultiVriateDataDocument.getMultiVariateDataDocumentSize()][];
			for(int j=0; j<curMultiVriateDataDocument.getMultiVariateDataDocumentSize();j++)
			{
				ArrayList<UnivariateDataDocument> allUni=curMultiVriateDataDocument.getAllUnivariateDataDocument();
				for(int k=0; k<allUni.size();k++)
				{
					UnivariateDataDocument curDataDocument=allUni.get(k);
					array[i][j]=new String[curDataDocument.getIPWords().size()];
					ArrayList<String> allWords=curDataDocument.getIPWords();
					array[i][j]=allWords.toArray(new String[allWords.size()]);
				}
			}
		}
		return array;
	}
}
