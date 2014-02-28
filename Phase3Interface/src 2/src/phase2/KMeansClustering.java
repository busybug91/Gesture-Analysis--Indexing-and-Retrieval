package phase2;

import java.util.ArrayList;
import java.util.Random;

public class KMeansClustering 
{
	private static int NUM_CLUSTERS=3;    
	private static int TOTAL_DATA=60;     
	private static double DATAPOINTS[][];
	private static ArrayList<Data> dataSet = new ArrayList<Data>();
	private static ArrayList<Centroid> centroids = new ArrayList<Centroid>();
	public static void initialize(double [][] DATA, int numberOfClusters)
	{
		DATAPOINTS=DATA;
		NUM_CLUSTERS=numberOfClusters;
		TOTAL_DATA=DATA.length;
		System.out.println("Centroids initialized at:");
		for(int i=0;i<numberOfClusters;i++)
		{
			centroids.add(new Centroid(DATA[i][0],DATA[i][1],DATA[i][2])); 
			System.out.println("     (" + centroids.get(i).X() + ", " + centroids.get(i).Y() +", " + centroids.get(i).Z() + ")");
		}
		System.out.print("\n");
	}
	public static void initialize1(double [][] DATA, int numberOfClusters,int c1,int c2,int c3)
	{
		DATAPOINTS=DATA;
		NUM_CLUSTERS=numberOfClusters;
		
		//System.out.println("Centroids initialized at:");
		centroids.add(new Centroid(DATA[c1][0],DATA[c1][1],DATA[c1][2])); 
		//System.out.println("     (" + centroids.get(0).X() + ", " + centroids.get(0).Y() +", " + centroids.get(0).Z() + ")");
		centroids.add(new Centroid(DATA[c2][0],DATA[c2][1],DATA[c2][2])); 
		//System.out.println("     (" + centroids.get(1).X() + ", " + centroids.get(1).Y() +", " + centroids.get(1).Z() + ")");
		centroids.add(new Centroid(DATA[c3][0],DATA[c3][1],DATA[c3][2])); 
		//System.out.println("     (" + centroids.get(2).X() + ", " + centroids.get(2).Y() +", " + centroids.get(2).Z() + ")");
		//System.out.print("\n");
	}

	public static void kMeanCluster()
	{
		final double bigNumber =Double.POSITIVE_INFINITY;    // some big number that's sure to be larger than our data range.
		double minimum = bigNumber;                   // The minimum value to beat. 
		double distance = 0.0;                        // The current minimum value.
		int sampleNumber = 0;
		int cluster = 0;
		boolean isStillMoving = true;
		Data newData = null;
		while(dataSet.size() < TOTAL_DATA)
		{
			newData = new Data(DATAPOINTS[sampleNumber][0], DATAPOINTS[sampleNumber][1],DATAPOINTS[sampleNumber][2],sampleNumber);
			dataSet.add(newData);
			minimum = bigNumber;
			for(int i = 0; i < NUM_CLUSTERS; i++)
			{
				distance = euclideanDist(newData, centroids.get(i));
				if(distance < minimum)
				{
					minimum = distance;
					cluster = i;
				}
			}
			newData.cluster(cluster);
			for(int i = 0; i < NUM_CLUSTERS; i++)
			{
				double totalX = 0.0;
				double totalY = 0.0;
				double totalZ = 0.0;
				double totalInCluster = 0;
				for(int j = 0; j < dataSet.size(); j++)
				{
					if(dataSet.get(j).cluster() == i){
						totalX += dataSet.get(j).X();
						totalY += dataSet.get(j).Y();
						totalZ += dataSet.get(j).Z();
						totalInCluster++;
					}
				}
				if(totalInCluster > 0){
					centroids.get(i).X(totalX / totalInCluster);
					centroids.get(i).Y(totalY / totalInCluster);
					centroids.get(i).Z(totalZ / totalInCluster);
				}
			}
			sampleNumber++;
		}
		while(isStillMoving)
		{
			// calculate new centroids.
			for(int i = 0; i < NUM_CLUSTERS; i++)
			{
				double totalX = 0.0;
				double totalY = 0.0;
				double totalZ = 0.0;
				double totalInCluster = 0;
				for(int j = 0; j < dataSet.size(); j++)
				{
					if(dataSet.get(j).cluster() == i){
						totalX += dataSet.get(j).X();
						totalY += dataSet.get(j).Y();
						totalZ += dataSet.get(j).Z();
						totalInCluster++;
					}
				}
				if(totalInCluster > 0){
					centroids.get(i).X(totalX / totalInCluster);
					centroids.get(i).Y(totalY / totalInCluster);
					centroids.get(i).Z(totalZ / totalInCluster);
				}
			}

			// Assign all data to the new centroids
			isStillMoving = false;

			for(int i = 0; i < dataSet.size(); i++)
			{
				Data tempData = dataSet.get(i);
				minimum = bigNumber;
				for(int j = 0; j < NUM_CLUSTERS; j++)
				{
					distance = euclideanDist(tempData, centroids.get(j));
					if(distance < minimum){
						minimum = distance;
						cluster = j;
					}
				}
				tempData.cluster(cluster);
				if(tempData.cluster() != cluster){
					tempData.cluster(cluster);
					isStillMoving = true;
				}
			}
		}
		return;
	}
	private static double euclideanDist(Data d, Centroid c)
	{
		return Math.sqrt(Math.pow((c.Y() - d.Y()), 2) + Math.pow((c.X() - d.X()), 2) + Math.pow((c.Z() - d.Z()), 2));
	}

	private static class Data
	{
		private double mX = 0;
		private double mY = 0;
		private double mZ = 0;
		private int dataNumber;
		private int mCluster = 0;
		public Data(double x, double y, double z,int dataNumber)
		{
			this.dataNumber=dataNumber;
			this.X(x);
			this.Y(y);
			this.Z(z);
			return;
		}
		public void X(double x)
		{
			this.mX = x;
			return;
		}
		public double X()
		{
			return this.mX;
		}
		public void Z(double z)
		{
			this.mZ = z;
			return;
		}
		public double Z()
		{
			return this.mZ;
		}
		public void Y(double y)
		{
			this.mY = y;
			return;
		}
		public double Y()
		{
			return this.mY;
		}
		public void cluster(int clusterNumber)
		{
			this.mCluster = clusterNumber;
			return;
		}
		public int cluster()
		{
			return this.mCluster;
		}
	}
	private static class Centroid
	{
		private double mX = 0.0;
		private double mY = 0.0;
		private double mZ = 0.0;
		public Centroid(double newX, double newY, double newZ)
		{
			this.mX = newX;
			this.mY = newY;
			this.mZ = newZ;
			return;
		}
		public void X(double newX)
		{
			this.mX = newX;
			return;
		}
		public double X()
		{
			return this.mX;
		}
		public void Y(double newY)
		{
			this.mY = newY;
			return;
		}
		public double Y()
		{
			return this.mY;
		}
		public void Z(double newZ)
		{
			this.mZ = newZ;
			return;
		}
		public double Z()
		{
			return this.mZ;
		}
	}
	public static void printClusters()
	{
		for(int i = 0; i < NUM_CLUSTERS; i++)
		{
			System.out.println("Cluster " + i + " includes:");
			for(int j = 0; j < TOTAL_DATA; j++)
			{
				if(dataSet.get(j).cluster() == i){
					System.out.println("     ( Gesture "+dataSet.get(j).dataNumber+"   " + dataSet.get(j).X() + ", " + dataSet.get(j).Y()  + ", " + dataSet.get(j).Z() + ")");
				}
			} 
			System.out.println();
		} 
//		System.out.println("Centroids finalized at:");
//		for(int i = 0; i < NUM_CLUSTERS; i++)
//		{
//			System.out.println("     (" + centroids.get(i).X() + ", " + centroids.get(i).Y()+" , "+ centroids.get(i).Z()+")");
//		}
	}
	public static double findStress()
	{
		double stress=0.0;
		for(int i = 0; i < NUM_CLUSTERS; i++)
		{
			for(int j = 0; j < TOTAL_DATA; j++)
			{
				if(dataSet.get(j).cluster() == i){
					stress+=euclideanDist(dataSet.get(j), centroids.get(i));
				}
			} 
		}
		return stress;
	}
	public static void getCentroids(double [][] DATA, int numberOfClusters)
	{
		TOTAL_DATA=DATA.length;
		int c11=0;
		int c22=0;
		int c33=0;
		int iterations=0;
		double minStress=Double.MAX_VALUE;
		Random r=new Random();
		while(iterations<10000)
		{
			int c1=r.nextInt(TOTAL_DATA);
			int c2=r.nextInt(TOTAL_DATA);
			int c3=r.nextInt(TOTAL_DATA);
			while(c2==c1||c3==c1||c2==c3)
			{		
				c2=r.nextInt(TOTAL_DATA);
				c3=r.nextInt(TOTAL_DATA);
			}
			
			initialize1(DATA, numberOfClusters, c1, c2, c3);
			KMeansClustering.kMeanCluster();
			if(KMeansClustering.findStress()<minStress)
			{
				c11=c1;
				c22=c2;
				c33=c3;
			}
			iterations++;
		}
		System.out.println(c11);
		System.out.println(c22);
		System.out.println(c33);
		initialize1(DATA, numberOfClusters, c11, c22, c33);
	}
	

}