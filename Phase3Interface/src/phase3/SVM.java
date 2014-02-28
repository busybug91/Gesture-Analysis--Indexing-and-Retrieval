package phase3;

import java.io.IOException;
import java.util.ArrayList;
import phase2.MatlabInterface;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;


public class SVM 
{
	private static ArrayList<SVMData> svmDatas;
	public static SVMData findW(String a,String b) throws IOException, MatlabInvocationException
	{
		
		ArrayList<String> lab=new ArrayList<>();
		lab.add(a);
		double[][] data1=TrainingData.getLabledData(lab);
		lab.clear();
		lab.add(b);
		double[][] data2=TrainingData.getLabledData(lab);
		double[][] data3=new double[data1.length+data2.length][];
		System.out.println("Before fors");

		for(int i=0;i<data1.length;i++)
		{
			data3[i]=data1[i];
		}
		for(int i=0;i<data2.length;i++)
		{
			data3[i+data1.length]=data2[i];
		}
		System.out.println("after fors");

		double[][] y=new double[data3.length][];
		for(int i=0;i<y.length;i++)
		{
			y[i]=new double[1];
			if(i<data1.length)
				y[i][0]=1.0;
			else
				y[i][0]=-1.0;
		}
		System.out.println("after last for");

		String X=phase1.Functions.getDoubleString(data3);
		System.out.println("One done");

		String Y=phase1.Functions.getDoubleString(y);
		System.out.println("calling matlab");
		final long startTime = System.nanoTime();
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("X="+X+";");
		proxy.eval("Y="+Y+";");
		proxy.eval("[w,bias]=svmCal(X,Y);");
		final long duration = System.nanoTime() - startTime;
		System.out.println("time taken in miliseconds: "+duration/1000000);
		double [] w=(double[]) proxy.getVariable("w");
		double bias[]=(double[]) proxy.getVariable("bias");
		System.out.println("w: ");
		LSH.print1Ddouble(w);
		System.out.println("bias "+bias[0]);
		//		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		//		MatlabNumericArray array = processor.getNumericArray("w");
		//	
		return null;
	}
	public static SVMData findWPCAandBias(String a,String b) throws IOException, MatlabInvocationException
	{	
		SVMData ret;
		ArrayList<String> lab=new ArrayList<>();
		lab.add(a);
		double[][] data1=TrainingData.getLabledPCAData(lab);
		lab.clear();
		lab.add(b);
		double[][] data2=TrainingData.getLabledPCAData(lab);
		double[][] data3=new double[data1.length+data2.length][];
		//System.out.println("Before fors");

		for(int i=0;i<data1.length;i++)
		{
			data3[i]=data1[i];
		}
		for(int i=0;i<data2.length;i++)
		{
			data3[i+data1.length]=data2[i];
		}
		//System.out.println("after fors");

		double[][] y=new double[data3.length][];
		for(int i=0;i<y.length;i++)
		{
			y[i]=new double[1];
			if(i<data1.length)
				y[i][0]=1.0;
			else
				y[i][0]=-1.0;
		}
		//System.out.println("after last for");

		String X=phase1.Functions.getDoubleString(data3);
		//System.out.println("One done");
		String Y=phase1.Functions.getDoubleString(y);
		//
		//final long startTime = System.nanoTime();
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("X="+X+";");
		proxy.eval("Y="+Y+";");
		proxy.eval("[w,bias]=svmCal(X,Y);");
		//final long duration = System.nanoTime() - startTime;
		//System.out.println("time taken in miliseconds: "+duration/1000000);
		double [] w=(double[]) proxy.getVariable("w");
		double bias[]= (double[]) proxy.getVariable("bias");
		//System.out.println("w: ");
		//LSH.print1Ddouble(w);
		//System.out.println("bias "+bias[0]);
		ret=new SVMData(a,b,w, bias[0]);
		return ret;
	}
	public static void nArySVMTrainer() throws IOException, MatlabInvocationException
	{
		svmDatas=new ArrayList<SVMData>();
		ArrayList<String>allLables=TrainingData.getAllLables();
		for(int i=0;i<allLables.size()-1;i++)
			for(int j=i+1;j<allLables.size();j++)
			{
				String a=allLables.get(i);
				String b=allLables.get(j);
				svmDatas.add(findWPCAandBias(a, b));
			}
	}
	public static String getClass(SVMData svd,double[] queryData) throws MatlabInvocationException, IOException
	{
		String clas="";
		double[] w =svd.getW();
		double bias=svd.getBias();
		double result=0.0;///Matlab COde
		if(w.length==queryData.length)
		{
			double [][]W=new double[1][];
			W[0]=w;
			MatlabProxy proxy=MatlabInterface.getMatlabProxy();
			proxy.eval("W="+phase1.Functions.getDoubleString(W)+";");
			//System.out.println(phase1.Functions.getDoubleString(W));
			double [][]X=new double[1][];
			X[0]=queryData;
			proxy.eval("X="+phase1.Functions.getDoubleString(X)+";");
			//System.out.println(phase1.Functions.getDoubleString(X));
			proxy.eval("bias="+Double.toString(bias)+";");
			proxy.eval("result=getResult(W,X,bias);");
			double []r=	(double[])proxy.getVariable("result");
			//System.out.println("Result is: "+r[0]);
			result=r[0];

		}

		if(result>0.0)
		{
			clas= svd.getA();
		}
		else
		{
			if(result<0.0)
				clas=svd.getB();
		}
		return clas;
	}
	public static String classify(double[] queryData) throws MatlabInvocationException, IOException
	{
		String[] res=new String[svmDatas.size()];
		String[] set=new String[svmDatas.size()];
		boolean[] checked =new boolean[svmDatas.size()];
		ArrayList<Integer> needToBeChecked=new ArrayList<Integer>();
		for(int i=0;i<svmDatas.size();i++)
		{
			res[i]=getClass(svmDatas.get(i), queryData);
			set[i]=svmDatas.get(i).getA()+"|"+svmDatas.get(i).getA();
			checked[i]=false;
			needToBeChecked.add(i);
		}
		String initialable=svmDatas.get(0).getA();
		while(needToBeChecked.size()!=0)
		{
			needToBeChecked.clear();
			for(int i=0;i<svmDatas.size();i++)
			{
				if(!checked[i]&&set[i].contains(initialable))
				{
					needToBeChecked.add(i);
				}
			}
			if(needToBeChecked.size()!=0)
			{
				initialable=res[needToBeChecked.get(0)];
				checked[needToBeChecked.get(0)]=true;
			}
		}

		return initialable;
	}

}
