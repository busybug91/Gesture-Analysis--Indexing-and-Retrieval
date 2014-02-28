package phase2;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;

public class MatlabInterface 
{
	static MatlabProxy proxy;
	static MatlabProxyFactory factory;
	public static void MatlabInitialize() throws MatlabConnectionException, MatlabInvocationException
	{
		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).setHidden(true).setMatlabLocation(null).build(); 
		MatlabProxyFactory factory = new MatlabProxyFactory(options);
		factory = new MatlabProxyFactory();
		proxy = factory.getProxy();
		proxy.eval(Constants.pathMatlab);
	}
	public static MatlabProxy getMatlabProxy()
	{
		return proxy;
	}
	public static void disconnect() throws MatlabInvocationException
	{
//		proxy.eval("exit");
		proxy.disconnect();		
	}
}
