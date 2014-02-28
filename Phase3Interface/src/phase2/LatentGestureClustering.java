package phase2;

import java.io.IOException;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

public class LatentGestureClustering 
{
	public static void findClusters(double[][] DATA) throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		//LatentGestureDiscovery.createGestureSimillarityOnSVD();
	//	double[][] DATA=LatentGestureDiscovery.getPCASimilarityOnSVD();
//		KMeansClustering.initialize(DATA, 3);
//		KMeansClustering.kMeanCluster();
//		KMeansClustering.printClusters();
		 KMeansClustering.getCentroids(DATA, 3);
			//KMeansClustering.initialize(DATA, 3);
			//KMeansClustering.kMeanCluster();
			KMeansClustering.printClusters();
			//KMeansClustering.getCentroids();
			//System.out.println(KMeansClustering.findStress());
	}

}
