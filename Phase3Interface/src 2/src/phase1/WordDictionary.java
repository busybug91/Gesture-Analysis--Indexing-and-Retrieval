package phase1;

import java.util.ArrayList;

public class WordDictionary 
{
	private ArrayList<String> words;
	private ArrayList<Integer> count;
	private ArrayList<Double> TF;
	private ArrayList<Double> IDF;
	private ArrayList<Double> IDF1;
	private ArrayList<Double> TFIDF;
	private ArrayList<Double> TFIDF1;
	private ArrayList<Double> TFIDF3;

	private int totalNumberOfWordsInUnivariate;
	WordDictionary()
	{
		words=new ArrayList<String>();
		count=new ArrayList<Integer>();
		TF=new ArrayList<Double>();
		IDF=new ArrayList<Double>();
		IDF1=new ArrayList<Double>();
		TFIDF=new ArrayList<Double>();
		TFIDF1=new ArrayList<Double>();
		TFIDF3=new ArrayList<Double>();
		totalNumberOfWordsInUnivariate=0;
	}
	public void setTotalNumberOfWordsInUnivariate(int totalNumberOfWordsInUnivariate)
	{
		this.totalNumberOfWordsInUnivariate=totalNumberOfWordsInUnivariate;
	}
	public int getoTalNumberOfWordsInUnivariate()
	{
		return totalNumberOfWordsInUnivariate;
	}
	public String getWord(int index) 
	{
		return words.get(index);
	}
	public ArrayList<String> getAllWords() 
	{
		return words;
	}
	public ArrayList<Double> getAllTFIDF() 
	{
		return TFIDF;
	}
	public void addWord(String word)
	{
		words.add(word);
		count.add(1);
		TF.add(null);
		IDF.add(null);
		IDF1.add(null);
		TFIDF.add(null);
		TFIDF1.add(null);
		TFIDF3.add(null);
	}
	public boolean findWord(String word)
	{
		return words.contains(word);
	}
	public Integer getCount(String word)
	{
		return count.get(words.indexOf(word));
	}
	public void incrementCount(String word)
	{
		int index=words.indexOf(word);
		Integer wcount=count.get(index);
		wcount++;
		count.remove(index);
		count.add(index, wcount);
	}
	public void setCount(String word, Integer newCount)
	{
		int index=words.indexOf(word);
		count.remove(index);
		count.add(index, newCount);
	}
	public int size()
	{
		return words.size();
	}
	public void setTF(String word, Double newTF)
	{
		int index=words.indexOf(word);
		TF.add(index, newTF);
	}
	public Double getTF(String word)
	{
		int index=words.indexOf(word);
		return TF.get(index);
	}
	public void setIDF(String word,Double newIDF)
	{
		int index=words.indexOf(word);
		IDF.add(index, newIDF);
	}
	public Double getIDF(String word)
	{
		int index=words.indexOf(word);
		return IDF.get(index);
	}
	public void setIDF1(String word,Double newIDF)
	{
		int index=words.indexOf(word);
		IDF1.add(index, newIDF);
	}
	public Double getIDF1(String word)
	{
		int index=words.indexOf(word);
		return IDF1.get(index);
	}
	public void setTFIDF(String word,Double newTFIDF)
	{
		int index=words.indexOf(word);
		TFIDF.add(index, newTFIDF);
	}
	public Double getTFIDF(String word)
	{
		int index=words.indexOf(word);
		return TFIDF.get(index);
	}
	public void setTFIDF3(String word,Double newTFIDF3)
	{
		int index=words.indexOf(word);
		TFIDF3.add(index, newTFIDF3);
	}
	public Double getTFIDF3(String word)
	{
		int index=words.indexOf(word);
		return TFIDF3.get(index);
	}
	public void setTFIDF1(String word,Double newTFIDF)
	{
		int index=words.indexOf(word);
		TFIDF1.add(index, newTFIDF);
	}
	public Double getTFIDF1(String word)
	{
		int index=words.indexOf(word);
		return TFIDF1.get(index);
	}
	public Double maxFrequency()
	{
		Double maxFreq=0.0;
		for(int i=0;i<count.size();i++)
		{
			if(maxFreq<new Double(count.get(i)))maxFreq=new Double(count.get(i));
		}
		return maxFreq;
	}
	public String toString()
	{
		String op="";
		//op+="TotalNumberOfWordsInUnivariate: "+totalNumberOfWordsInUnivariate+"\n";
		op+=String.format("%20s%10s%20s%20s%20s%20s%20s%20s","Word","Count","TF","IDF","IDF2","TFIDF","TFIDF2","TFIDF3");
		op+="\n_________________________________________________________________________________________________________________________________________________________\n";
		for(int i=0;i<words.size();i++)
		{
			op+=String.format("%20s%10d%20.8f%20.8f%20.8f%20.8f%20.8f%20.8f",words.get(i),count.get(i),TF.get(i),IDF.get(i),IDF1.get(i),TFIDF.get(i),TFIDF1.get(i),TFIDF3.get(i));
			op+="\n";		
		}
		return op;
	}
}
