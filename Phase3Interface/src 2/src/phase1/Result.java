package phase1;

public class Result
{
	private MultiVriateDataDocument mvd;
	private Gesture g;
	private Double distance;
	public Result(MultiVriateDataDocument mvd, Double distance)
	{
		this.mvd=mvd;
		this.distance=distance;
	}
	public Result(Gesture gesture,Double distance)
	{
		this.g=gesture;
		this.distance=distance;
	}
	public MultiVriateDataDocument getMultiVariateDataDocument()
	{
		return mvd;
	}
	public Gesture getGesture()
	{
		return g;
	}
	public Double getDistance()
	{
		return distance;
	}
	public String toString()
	{
		String op="NULL";
		if(mvd!=null)
		{
			op="Gesture Data: "+mvd.getDocumentAxis()+":"+mvd.getGestureNumber()+"  Similarity with the query: "+distance;
			op="Gesture Data: "+mvd.getDocumentAxis()+":"+mvd.getGestureNumber();
		}
		if(g!=null)
		{
			op="Gesture Data: "+g.getGestureNumber()+"  Similarity with the query: "+distance;
			op="Gesture Data: "+g.getGestureNumber();
		}
		return op;
	}
	public int getGestureNumber()
	{
		return g.getGestureNumber();
	}
}
