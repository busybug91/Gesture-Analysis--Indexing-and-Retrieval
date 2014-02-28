package phase2;

import java.io.IOException;
import java.util.ArrayList;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import phase1.*;

public class TestPCA
{
	public static void main(String []args) throws IOException, MatlabConnectionException, MatlabInvocationException
	{
		Constants.pathMatlab="cd(\'C:\\Users\\jkshirsa\\Desktop\\New Workspace\\workspace\\mwdb2\\resources\')";
		
		Constants.pathSampleData="C:\\Users\\jkshirsa\\Desktop\\sampleData\\";
		Consatnts.PATH1=Constants.pathSampleData;
		//C:\Users\jkshirsa\Desktop\New Workspace\workspace\mwdb2\resources
		MatlabInterface.MatlabInitialize();
		GesturesLibrary gestureLibrary=new GesturesLibrary();
		ArrayList<UnivariateDataDocument> sensorDatas=Functions.getSensorData(gestureLibrary, 0, 'X');
		System.out.println("Covariance");
		System.out.println(Functions.printDoubleArray1(PCA.getCovariance(Functions.getSensorArray(sensorDatas))));
		System.out.println("left factor by cov");
		System.out.println(Functions.printDoubleArray1(PCA.getLeftFactorCov(Functions.convertDoubleArray(PCA.getCovariance(Functions.getSensorArray(sensorDatas))))));
		System.out.println("left factor by NP");	
		System.out.println(Functions.printDoubleArray1(PCA.getLeftFactorNbyP(Functions.getSensorArray(sensorDatas))));	
		System.out.println("Right factor by cov");
		System.out.println(Functions.printDoubleArray1(PCA.getRightFactorCov(Functions.convertDoubleArray(PCA.getCovariance(Functions.getSensorArray(sensorDatas))))));
		System.out.println("Right factor by NbyP");
		System.out.println(Functions.printDoubleArray1(PCA.getRightFactorNbyP(Functions.getSensorArray(sensorDatas))));
		System.out.println("PCA Data by COV");
		System.out.println(Functions.printDoubleArray1(PCA.getPCADataCOV(Functions.convertDoubleArray(PCA.getCovariance(Functions.getSensorArray(sensorDatas))),Functions.getSensorArray(sensorDatas))));
		System.out.println("PCA Data by NbyP");
		System.out.println(Functions.printDoubleArray1(PCA.getPCADataNbyp((Functions.getSensorArray(sensorDatas)))));
		System.out.println("Latent by Cov");
//		System.out.println(Functions.printDoubleArray1D1(PCA.getLatentCov(Functions.convertDoubleArray(PCA.getCovariance(Functions.getSensorArray(sensorDatas))))));
//		System.out.println("Latent by NbyP");
//		System.out.println(Functions.printDoubleArray1D1(PCA.getLatentNbyP(Functions.getSensorArray(sensorDatas))));
//		System.out.println("Percentage by Cov");
//		System.out.println(Functions.printDoubleArray1D1(PCA.getPercentageCovarianceCov(Functions.convertDoubleArray(PCA.getCovariance(Functions.getSensorArray(sensorDatas))))));
//		System.out.println("Percentage by NbyP");
//		System.out.println(Functions.printDoubleArray1D1(PCA.getPercentageCovarianceNbyP(Functions.getSensorArray(sensorDatas))));
		MatlabInterface.disconnect();
	}
}