package phase2;

import java.io.IOException;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

public class SVD
{
	public static double[][] getRightFactor(Double[][] ud) throws MatlabInvocationException, IOException
	{
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[U,S,Vt]=calSVD(input,3);");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("Vt");
		double[][] Vt = array.getRealArray2D();	
		return Vt;
	}
	public static double[][] getSVDData(Double[][] ud) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		double[][] Vt = getRightFactor(ud);
		return Functions.matrixTranspose(Functions.matrixMultiplication(Vt,Functions.matrixTranspose(Functions.convertDoubleArraySmall(ud))));
	}
	public double[][] getLatent(Double[][] ud) throws MatlabInvocationException, IOException, MatlabConnectionException
	{
		MatlabProxy proxy= MatlabInterface.getMatlabProxy();
		proxy.eval("input="+phase1.Functions.getDoubleString(ud)+";");
		proxy.eval("[U,S,Vt]=calSVD(input,3);");
		MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
		MatlabNumericArray array = processor.getNumericArray("S");
		double[][] S = array.getRealArray2D();	
		return S;
	}
}


