package phase3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import phase1.GesturesLibrary;


public class LSHNew 
{
	private static ArrayList<String> gestureDimensions;
	private static double[][] gestureData;
	private static Random rand=new Random(System.nanoTime());
	public static ArrayList<ArrayList<Double[]>> randomVectors;

	public static void createRandomVectors()
	{
		
	}
	public static void generateData(GesturesLibrary gesturesLibrary) throws IOException
	{
		String []gestureDimensions1=Functions.generateGestureDimensions(gesturesLibrary)[0];
		gestureDimensions=new ArrayList<String>();

		for(int i=0;i<gestureDimensions1.length;i++)
		{
			gestureDimensions.add(gestureDimensions1[i]);
		}
		System.out.println(gestureDimensions);
		gestureData=phase2.Functions.convertDoubleArraySmall(Functions.generateGestureData(gesturesLibrary, gestureDimensions));
		String path=System.getProperty("user.dir");
		if(path.contains("/"))
		{ 
			path=path+"/gestureVectors/";
		}
		if(path.contains("\\"))
		{
			path=path+"\\gestureVectors\\";
		}	
		phase2.Functions.saveFileWithNumStringArray(path, "gestureVectors.csv",Functions.converDouble2DtoString2D(gestureData));
	}

	public static double[] randomVector()
	{
		double randomVector[]=new double[gestureDimensions.size()];
		for(int i=0;i<randomVector.length;i++)
		{
			randomVector[i]=rand.nextGaussian();
		}
		return randomVector;
	}
	public static double projectGesture(double[] randomVector,double[] gesturedata)
	{
		double hashValue=phase2.Functions.dot_product(gesturedata, randomVector);
		return hashValue;
	}
	public static double[]  getProjectedGestureData()
	{
		double[] randomVector=randomVector();
		double [] projectedData=new double[gestureData.length];
		for(int i=0;i<gestureData.length;i++)
		{
			projectedData[i]=projectGesture(gestureData[i], randomVector);
		}
		return projectedData;
	}

//	public static void printRandomVectors()
//	{
//		for(int i =0;i<index.size();i++)
//		{
//			System.out.println("Random vectors for layer "+i);
//			ArrayList<double[]> randomVectors=index.get(i).randomVectors;
//			for(int j=0;j<randomVectors.size();j++)
//			{
//				print1Ddouble(randomVectors.get(j));
//			}
//			System.out.println();
//		}
//	}
	public static void print1Ddouble(double []data)
	{
		for(int i=0;i<data.length;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}


	public static int[] hashedGestures(double [] projectedGestureData)
	{
		int ret[] =new int [projectedGestureData.length];
		double median=getMedian(projectedGestureData);
		for(int i=0;i<projectedGestureData.length;i++)
		{
			if(projectedGestureData[i]>median)
			{
				ret[i]=1;
			}
		}
		return ret;
	}
	public static double getMedian(double[] projectedData)
	{
		double max=projectedData[0];
		double min=projectedData[0];
		for(int i=0;i<projectedData.length;i++)
		{
			if(min>projectedData[i])
				min=projectedData[i];
			if(max<projectedData[i])
				max=projectedData[i];
		}
		return max/min;
	}
}