package phase3;

public class SVMData 
{
	private  double []w;
	private  double bias;
	private String a ;
	private String b;
	public SVMData(String a,String b,double[] w, double bias)
	{
		this.w=w;
		this.bias=bias;
		this.a=a;
		this.b=b;
	}
	public double[] getW()
	{
		return w;
	}
	public double getBias()
	{
		return bias;
	}
	public String getA()
	{
		return a;
	}
	public String getB() 
	{
		return b;
	}

}
