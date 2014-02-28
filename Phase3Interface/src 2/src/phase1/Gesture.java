package phase1;

import java.io.IOException;
import java.util.ArrayList;

public class Gesture 
{
	private int GestureNumber;
	private MultiVriateDataDocument XMultivariateData;
	private MultiVriateDataDocument YMultivariateData;
	private MultiVriateDataDocument ZMultivariateData;
	private MultiVriateDataDocument WMultivariateData;
	public Gesture(int gestureNumber,MultiVriateDataDocument xMultiVriateDataDocument,MultiVriateDataDocument yMultiVriateDataDocument,MultiVriateDataDocument zMultiVriateDataDocument,MultiVriateDataDocument wMultiVriateDataDocument)
	{
		XMultivariateData=xMultiVriateDataDocument;
		YMultivariateData=yMultiVriateDataDocument;
		ZMultivariateData=zMultiVriateDataDocument;
		WMultivariateData=wMultiVriateDataDocument;
		this.GestureNumber=gestureNumber;
	}
	public Gesture(int GestureNumber,ArrayList<String> directories) throws IOException
	{
		this.GestureNumber=GestureNumber;
		XMultivariateData=null;
		YMultivariateData=null;
		ZMultivariateData=null;
		WMultivariateData=null;
		if(directories.contains("X"))
			XMultivariateData=new MultiVriateDataDocument('X', GestureNumber);
		if(directories.contains("Y"))
			YMultivariateData=new MultiVriateDataDocument('Y', GestureNumber);
		if(directories.contains("Z"))
			ZMultivariateData=new MultiVriateDataDocument('Z', GestureNumber);
		if(directories.contains("W"))
			WMultivariateData=new MultiVriateDataDocument('W', GestureNumber);
	}
	public int getGestureNumber()
	{
		return GestureNumber;
	}
	public MultiVriateDataDocument getXMultivariateData() 
	{
		return XMultivariateData;
	}
	public MultiVriateDataDocument getYMultivariateData() 
	{
		return YMultivariateData;
	}
	public MultiVriateDataDocument getZMultivariateData() 
	{
		return ZMultivariateData;
	}
	public MultiVriateDataDocument getWMultivariateData() 
	{
		return WMultivariateData;
	}
	public String toString()
	{
		String op="";
		op=XMultivariateData.toString()+"\n"+YMultivariateData+"\n"+ZMultivariateData+"\n"+WMultivariateData;
		return op;
	}
}
