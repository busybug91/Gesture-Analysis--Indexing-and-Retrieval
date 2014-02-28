package phase1;

import java.util.ArrayList;

public class UnivariateDataDocument 
{
	private int DocumentNumber;
	private WordDictionary words;
	private ArrayList<String> ipWords;
	public UnivariateDataDocument(int DocumentNumber)
	{
		this.DocumentNumber=DocumentNumber;
		words=new WordDictionary();
		ipWords=new ArrayList<String>();
	}
	public int getDocumnetNumber()
	{
		return DocumentNumber;
	}
	public WordDictionary getWords()
	{
		return words;
	}
	public void setIPWords(ArrayList<String> ipWords)
	{
		this.ipWords=ipWords;
	}
	public ArrayList<String> getIPWords()
	{
		return ipWords;
	}
	public void addWords(ArrayList<String> ipWords)
	{
		words.setTotalNumberOfWordsInUnivariate(ipWords.size());
		for(int i=0;i<ipWords.size();i++)
		{
			if(words.findWord(ipWords.get(i)))
				words.incrementCount(ipWords.get(i));
			else
				words.addWord(ipWords.get(i));
		}
	}
	@Deprecated
	public String toString1()
	{
		String op="[";
		for(int i=0;i<words.size();i++)
		{
			op+="["+words.getWord(i)+"] Count:["+words.getCount(words.getWord(i))+"], ";
		}
		op+="]";
		return op;
	}
	public String toString()
	{
		String op="";
		op+=words.toString();
		return op;
	}
	public int totalNumberOfWords()
	{
		return words.size();
	}
	public ArrayList<Double> calculateTFVector()
	{
		ArrayList<Double> op= new ArrayList<Double>();
		for(int i=0;i<ipWords.size();i++)
		{
			op.add(words.getTF(ipWords.get(i)));
		}
		return op;
	}
	public double[] calculateTFIDFVector()
	{
		double[] op= new double[ipWords.size()];
		for(int i=0;i<ipWords.size();i++)
		{
			op[i]=words.getTFIDF(ipWords.get(i));
		}
		return op;
	}
	public ArrayList<Double> calculateTFIDF1Vector()
	{
		ArrayList<Double> op= new ArrayList<Double>();
		for(int i=0;i<ipWords.size();i++)
		{
			op.add(words.getTFIDF1(ipWords.get(i)));
		}
		return op;
	}
	public ArrayList<Double> calculateIDFVector()
	{
		ArrayList<Double> op= new ArrayList<Double>();
		for(int i=0;i<ipWords.size();i++)
		{
			op.add(words.getIDF(ipWords.get(i)));
		}
		return op;
	}
	public ArrayList<Double> calculateIDF1Vector()
	{
		ArrayList<Double> op= new ArrayList<Double>();
		for(int i=0;i<ipWords.size();i++)
		{
			op.add(words.getIDF1(ipWords.get(i)));
		}
		return op;
	}
}