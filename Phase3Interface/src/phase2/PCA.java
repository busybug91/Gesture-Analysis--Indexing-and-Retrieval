package phase2;

import java.io.IOException;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

public class PCA 
{
	public static double[][] getCovariance(Double[][] ud) throws MatlabInvocationException, IOException
	{

		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[covInput]=calCOV(input);");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("covInput");
		double[][] covInput = array.getRealArray2D();	
		return covInput;
	}
	public static double[][] getLeftFactorNbyP(Double[][] ud) throws MatlabInvocationException, IOException
	{
		double[][] covInput=getCovariance(ud);
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(Functions.convertDoubleArray(covInput))+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("TopK");
		double[][] coeff = array.getRealArray2D();	
		return coeff;		
	}
	public static double[][] getLeftFactorCov(Double[][] ud) throws MatlabInvocationException, IOException
	{
		//ud id covariance matrix.
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("TopK");
		double[][] coeff = array.getRealArray2D();	
		return coeff;		
	}
	public static double[][] getRightFactorCov(Double[][] ud) throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		return Functions.matrixTranspose((getLeftFactorCov(ud)));
	}
	public static double[][] getRightFactorNbyP(Double[][] ud) throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		return Functions.matrixTranspose((getLeftFactorNbyP(ud)));
	}
	public static double[][] getPCADataNbyp(Double[][] ud) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] rightFactor = getRightFactorNbyP(ud);
		return Functions.matrixTranspose(Functions.matrixMultiplication(rightFactor,Functions.matrixTranspose(Functions.convertDoubleArraySmall(ud)))); 
	}
	public static double[][] getPCADataCOV(Double[][] ud, Double[][] data) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] rightFactor = getRightFactorCov(ud);
		return Functions.matrixTranspose(Functions.matrixMultiplication(rightFactor,Functions.matrixTranspose(Functions.convertDoubleArraySmall(data)))); 
	}
	public static double[] getLatentCov(Double[][] ud) throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		double[] latentSemantics=(double[])proxy.getVariable("latent");
		return latentSemantics;
	}
	public static double[] getLatentNbyP(Double[][] ud) throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		double[][] covInput=getCovariance(ud);
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(Functions.convertDoubleArray(covInput))+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		double[] latentSemantics=(double[])proxy.getVariable("latent");
		return latentSemantics;
	}
	public static double[] getPercentageCovarianceNbyP(Double[][] ud) throws MatlabInvocationException, IOException
	{
		double[][] covInput=getCovariance(ud);
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(Functions.convertDoubleArray(covInput))+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		double[] explained=(double[])proxy.getVariable("explained");
		return explained;
	}
	public static double[] getPercentageCovarianceCov(Double[][] ud) throws MatlabInvocationException, IOException
	{
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[COEFF,latent,explained,TopK]=calPCACOV(input,3);");
		double[] explained=(double[])proxy.getVariable("explained");
		return explained;
	}
}

