package phase2;

public class TestKMeans {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//System.out.println("wdwedwdwdwd");
		 double DATA[][] = new double[][] {{1.0, 1.0,1.0}, 
            {1.5, 2.0,3.0}, 
            {3.0, 4.0,4.0}, 
            {5.0, 7.0,4.0}, 
            {3.5, 5.0,4.0}, 
            {4.5, 5.0,4.0}, 
            {3.5, 4.5,4.0}
            };
		 KMeansClustering.getCentroids(DATA, 3);
		//KMeansClustering.initialize(DATA, 3);
		//KMeansClustering.kMeanCluster();
		KMeansClustering.printClusters();
		//KMeansClustering.getCentroids();
		System.out.println(KMeansClustering.findStress());

	}

}
