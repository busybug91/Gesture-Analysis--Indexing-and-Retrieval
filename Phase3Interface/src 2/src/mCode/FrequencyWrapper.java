package mCode;
import java.text.DecimalFormat;


public class FrequencyWrapper {

	private double[] _tf;
	private double[] _idf;
	private double[] _idf2;
	private double[] _tfidf;
	private double[] _tfidf2;
	
	public double[] getTF() { return _tf; }
	public double[] getIDF() { return _idf; }
	public double[] getIDF2() { return _idf2;}
	public double[] getTFIDF2() { return _tfidf2;}
	public double[] getTFIDF() { return _tfidf;}
	
	public void setTF(double[] value) { _tf = value;}
	public void setIDF(double[] value) { _idf = value;}
	public void setIDF2(double[] value) { _idf2 = value;}
	public void setTFIDF(double[] value) { _tfidf = value;}
	public void setTFIDF2(double[] value) { _tfidf2 = value;}
	
	public void print()
	{
		if(_tf.length == 0)
			System.out.println("NOTHING SET");
		for(int i = 0; i < _tf.length; i++)
		{
	//		System.out.print(_tf[i] + "   ");
		}
	//	System.out.println();
		for(int i = 0; i < _tf.length; i++)
		{
	//		System.out.print(_idf[i] + "  ");
		}
	//	System.out.println();
		for(int i = 0; i < _tf.length; i++)
		{
			System.out.println(this.printline("i "+ i , i));
		}
		System.out.println("\n");
		
	}
	public String printline(String name, int index)
	{
		DecimalFormat df = new DecimalFormat("0.000000");
		String result = String.format("%1$-10s", name);
		result += df.format(_tf[index]) + "     ";
		result += df.format(_tfidf[index]) + "     ";
		result += df.format(_tfidf2[index]);
		return result;
		
	}
}
