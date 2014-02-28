package phase3;

import java.io.IOException;
import java.util.Scanner;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.Consatnts;
import phase2.MatlabInterface;

public class ConsoleApp 
{

	public static void main(String args[]) throws IOException, InterruptedException, MatlabConnectionException, MatlabInvocationException
	{
		//ScannerInterface.scannerInitialize();
		//Scanner sc=ScannerInterface.getScanner();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Path for Data as weel as for labels.csv: ");
		Consatnts.PATH1 = sc.nextLine()+"\\";
		System.out.println("Enter Mean Value: ");
		Consatnts.MEAN_VALUE = Double.parseDouble(sc.nextLine());
		System.out.println("Enter Standard Deviation: ");
		Consatnts.STANDARD_DEVIATION_VALUE = Double.parseDouble(sc.nextLine());
		System.out.println("Enter Step Size: ");
		Consatnts.STEP_SIZE = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Window Size: ");
		Consatnts.WINDOW_SIZE = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Number Of levels: ");
		Consatnts.NUMBER_OF_LEVELS = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter path for matlab resources: ");
		String mpath=sc.nextLine();
		mpath="cd('"+mpath+"')";
		Consatnts.pathMatlab=mpath;
		MatlabInterface.MatlabInitialize();
		
		String path=Consatnts.PATH1+"labels.csv";
		TrainingData.setTrainingData(path);

		Data.setData();

		boolean exit=false;
		while(!exit)
		{
			System.out.println();
			System.out.println();
			System.out.println("Press 0 to exit.");
			System.out.println("Press 1 classify data using SVM.");
			System.out.println("Press 2 classify data using KNN.");
			System.out.println();
			int ch=sc.nextInt();
			Scanner sc1=new Scanner(System.in);
			switch(ch)
			{
			case 0:
				System.out.println("Pragram treminated.");
				exit=true;
				break;
			case 1:
				Data.SVMClassify();
				break;
			case 2:
				System.out.println("Enter number of gesture to classify: ");
				int gn = Integer.parseInt(sc1.nextLine());
				System.out.println("Enter number of nearest neighbours to consider: ");
				int kn = Integer.parseInt(sc1.nextLine());
				Data.KNNClassify(kn,gn);
				break;

			}
		}

		//ScannerInterface.close();
		MatlabInterface.disconnect();
	}

}
