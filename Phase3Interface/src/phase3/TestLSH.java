package phase3;
import phase2.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase2.*;
import phase1.*;

public class TestLSH {

	public static void main(String args[]) throws IOException, InterruptedException, MatlabConnectionException, MatlabInvocationException
	{
		ScannerInterface.scannerInitialize();
		Scanner sc=ScannerInterface.getScanner();

		System.out.println("Enter path for matlab resources: ");
		String mpath=sc.nextLine();
		//mpath=mpath.replace("\\", "\\\\");
		
		mpath="cd('"+mpath+"')";
		System.out.println(mpath);
		Consatnts.pathMatlab="cd(\'C:\\Users\\asahu3\\Desktop\\resources\')";
		System.err.println(Consatnts.pathMatlab);
		Consatnts.pathMatlab=mpath;
		Consatnts.PATH1="C:\\Users\\asahu3\\Desktop\\3classdata\\";
		MatlabInterface.MatlabInitialize();
		
		String path=Consatnts.PATH1+"labels.csv";
		TrainingData.setTrainingData(path);
		//TrainingData.getTrainingDataNumbers();
		System.out.println(TrainingData.getTrainingDataNumbers());
	
		
		Data.setData();
		Data.KNNClassify(8,589);
		//Data.SVMClassify();

		ScannerInterface.close();
		MatlabInterface.disconnect();
	}
}
