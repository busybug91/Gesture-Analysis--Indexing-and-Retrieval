package phase1;

//import java.io.IOException;
//import java.util.Scanner;
//
//import phase2.Constants;
//import phase2.PCA;
//import matlabcontrol.MatlabConnectionException;
//import matlabcontrol.MatlabInvocationException;

public class Start 
{
//	public static void main(String[] args) throws IOException, MatlabConnectionException, MatlabInvocationException 
//	{
//		System.out.println("welcome");
//		System.out.println();
//		Scanner scanner=new Scanner(System.in);
//		System.out.println("Enter path for database files.");
//		System.out.println(">>>");
//		
//		String path=scanner.nextLine();
//		Consatnts.PATH1=Constants.pathSampleData;
//		GesturesLibrary gesturesLibrary=new GesturesLibrary();
//		PCA pca= new PCA(gesturesLibrary);
//		pca.findPrincipalComponents('X', 1);
//		boolean exit=false;
//		while(!exit)
//		{
//			try
//			{
//				System.out.println();
//				System.out.println();
//				System.out.println("Press 1 to set parameters.");
//				System.out.println("Press 2 to generate heatmap.");
//				System.out.println("Press 3 to query database.");
//				System.out.println("Press 4 to print words in multivariate data.");
//				System.out.println("Press 0 to exit program.");
//				System.out.println();
//				System.out.println(">>>");
//				int ch=scanner.nextInt();
//				gesturesLibrary=new GesturesLibrary();
//				switch(ch)
//				{
//				case 0:
//					System.out.println("Pragram treminated.");
//					exit=true;
//					break;
//				case 1:
//					System.out.println("Values for parameters:".toUpperCase());
//					System.out.println("");
//					System.out.println("Path for database files: "+ Consatnts.PATH1);
//					System.out.println("Resolution: "+ Consatnts.NUMBER_OF_LEVELS);
//					System.out.println("Standard Deviation: "+Consatnts.STANDARD_DEVIATION_VALUE);
//					System.out.println("Mean value: "+Consatnts.MEAN_VALUE);
//					System.out.println("Window length: "+Consatnts.WINDOW_SIZE);
//					System.out.println("Step size: "+Consatnts.STEP_SIZE);
//					System.out.println();
//					System.out.println("Enter 1 if you want to make changes.");
//					System.out.println(">>>");
//					int c1=scanner.nextInt();
//					if(c1==1)
//					{
//						System.out.println("Enter option to make changes>>");
//						System.out.println("1: Path for database file");
//						System.out.println("2: Resolution");
//						System.out.println("3: Standard Deviation");
//						System.out.println("4: Mean value");
//						System.out.println("5: Window length");
//						System.out.println("6: Step size");
//						System.out.println("0: To go back to main menu");
//						System.out.println(">>>");
//						int c2=scanner.nextInt();
//						switch(c2)
//						{
//						case 0:
//							break;
//						case 1:
//							System.out.println("Enter new path.");
//							System.out.println(">>>");
//							String npath=scanner.next();
//							Consatnts.PATH1=npath;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						case 2:
//							System.out.println("Enter new Resolution.");
//							System.out.println(">>>");
//							int r=scanner.nextInt();
//							Consatnts.NUMBER_OF_LEVELS=r;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						case 3:
//							System.out.println("Enter new Standard Deviation.");
//							System.out.println(">>>");
//							int d=scanner.nextInt();
//							Consatnts.STANDARD_DEVIATION_VALUE=d;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						case 4:
//							System.out.println("Enter new Mean value.");
//							System.out.println(">>>");
//							int m=scanner.nextInt();
//							Consatnts.MEAN_VALUE=m;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						case 5:
//							System.out.println("Enter new window length.");
//							System.out.println(">>>");
//							int wl=scanner.nextInt();
//							Consatnts.WINDOW_SIZE=wl;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						case 6:
//							System.out.println("Enter new step size.");
//							System.out.println(">>>");
//							int ss=scanner.nextInt();
//							Consatnts.STEP_SIZE=ss;
//							gesturesLibrary=new GesturesLibrary();
//							break;
//						}
//					}
//					break;
//				case 2:
////					System.out.println("Enter multivariate file number.");
////					System.out.println(">>>");
////					int dn1=scanner.nextInt();
////					System.out.println("Enter document axis.");
////					System.out.println(">>>");
////					String docAxis11=scanner.next();
////					docAxis11=docAxis11.toUpperCase();
////					char DAxis11=docAxis11.charAt(0);
////					System.out.println("Enter vector for generating heatmap.");
////					String dc=scanner.next();
////					System.out.println("Generating heat map......");
////					//gesturesLibrary.getMultivariateData(DAxis11, dn1).hetaMap(dc);
////					System.out.println("heat map generated.");
//					break;
//				case 3:
//					System.out.println("Enter name of query object.");
//					System.out.println(">>>");
//					String qpath=scanner.next();
//					System.out.println("Enter axis of thw query object.");
//					String docAxis=scanner.next();
//					docAxis=docAxis.toUpperCase();
//					char DAxis=docAxis.charAt(0);
//					System.out.println("Eneter option.");
//					System.out.println("1: query on TF");
//					System.out.println("2: query on TFIDF");
//					System.out.println("3: query on TFIDF1");
//					int option=scanner.nextInt();
//					if(option==1||option==2||option==3)
//					{
//						gesturesLibrary.query(qpath,DAxis , option);
//					}
//					else
//						System.out.println("Not a valid option.");
//					break;
//				case 4:
//					System.out.println("Enter multivariate file number.");
//					System.out.println(">>>");
//					int dn=scanner.nextInt();
//					System.out.println("Enter document axis.");
//					System.out.println(">>>");
//					String docAxis1=scanner.next();
//					docAxis1=docAxis1.toUpperCase();
//					char DAxis1=docAxis1.charAt(0);
//					System.out.println(gesturesLibrary.getMultivariateData(DAxis1, dn));
//					break;
//				default:
//					System.out.println("Enter valid option.");
//				}
//			}
//			catch(Exception e)
//			{
//				System.err.println(e.toString());
//			}
//		}
//	}
}
