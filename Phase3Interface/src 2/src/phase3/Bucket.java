package phase3;

import java.util.ArrayList;
import phase1.UnivariateDataDocument;

public class Bucket 
{

	public ArrayList<UnivariateDataDocument> objects;

	public Bucket()
	{
		objects=new ArrayList<UnivariateDataDocument>();

	}
	public ArrayList<UnivariateDataDocument> getBucket()
	{
		return objects;
	}
	public String toString()
	{
		String op="";
		for(int i=0; i<objects.size();i++)
		{
			op+=Integer.toString(objects.get(i).getDocumnetNumber())+"\t";
		}
		op+="\n";
		return op;
	}

}
