package mCode;
public class Band {

	private double start, stop;
	private char symbol;
	
	public Band(char symbol, double start, double stop)
	{	this.start = start;
		this.stop = stop;
		this.symbol = symbol;
	}
	
	public char getSymbol()	{return symbol;}
	
	public double getStart(){return start;}
	
	public double getStop()	{return stop;}

	public static Band[] BuildBands(int r, double variance, double mean, double lowerbound, double upperbound)
	{
		Band[] bands = new Band[2*r];  //Create 2r bands
		
		double midpoint = ( upperbound + lowerbound ) / 2; //Should be 0 each time when dealing between -1 and 1.
		double length = (upperbound - midpoint) / r;
		double size, end, start = lowerbound;
		char c;
		double normalValue = SimpsonRule((lowerbound + upperbound)/2, upperbound, 100, mean, variance);
		for(int i = 0; i < 2*r; i++)
		{
			//calculate the area under the curve between the limits
			size = SimpsonRule(lowerbound + length * i, lowerbound + length * (i + 1), 100, mean, variance) / normalValue;
			end = start + size;
			//assign a symbol
			c = (char)((int)'A' + i);
			//add the band
			bands[i] = new Band(c, start, end);
			start = end;
			System.out.println("Band " + i + "\t" + bands[i].getSymbol() + "\t[" + bands[i].getStart() + "," + bands[i].getStop() + ")" + "\t\t" + size); 
		}
		
		return bands;
	}
	
	private static double SimpsonRule(double a, double b, double n, double mean, double var)
	{
		double h = (b - a)/n;
		double s = Gaussian(a, mean, var) + Gaussian(b,mean,var);
		
		for(int i = 1; i <= n; i=i+2 )
		{
			s+= 4 * Gaussian(a + i*h,mean,var);
		}
		for(int i = 2; i <= n-1; i=i+2)
		{
			s+= 2 * Gaussian(a + i*h,mean,var);
		}
	//	System.out.println("U:" + b + "\tL:" + a + "\t\t" + s*h/3);
		return s * h/3;
	}
	
	private static double Gaussian(double value, double mean, double variance)
	{
		double coeff = 1/(variance * Math.pow(2*Math.PI, .5));
		double exp = -(Math.pow(value - mean, 2)/(2*Math.pow(variance, 2)));
		return coeff * Math.pow(Math.E, exp);
	}      
	
}
