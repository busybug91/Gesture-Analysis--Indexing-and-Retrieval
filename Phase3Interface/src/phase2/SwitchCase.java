package phase2;

import java.io.IOException;
import java.util.Scanner; 

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;

import phase1.Consatnts;
import phase1.Gesture;
import phase1.GesturesLibrary;

public class SwitchCase 
{ 

	public static Gesture queryGesture;

	static int qgn=0;
	static GesturesLibrary gestureLibrary;
	public static void main(String args[]) throws MatlabInvocationException, IOException, MatlabConnectionException

	{ 
		Constants.pathMatlab="cd(\'/Users/nitin/workspace/mwdb2/resources/')";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter path for database files.");
		System.out.println(">>>");
		String path=sc.nextLine()+"/";
		Consatnts.PATH1=path;
		gestureLibrary=new GesturesLibrary();
		System.out.println("Values for parameters:".toUpperCase());
		System.out.println("");
		System.out.println("Path for database files: "+ Consatnts.PATH1);
		System.out.println("Resolution: "+ Consatnts.NUMBER_OF_LEVELS);
		System.out.println("Standard Deviation: "+Consatnts.STANDARD_DEVIATION_VALUE);
		System.out.println("Mean value: "+Consatnts.MEAN_VALUE);
		System.out.println("Window length: "+Consatnts.WINDOW_SIZE);
		System.out.println("Step size: "+Consatnts.STEP_SIZE);
		System.out.println();
		System.out.println("Enter 1 if you want to make changes.");
		System.out.println(">>>");

		int c1=sc.nextInt();
		if(c1==1)
		{
			System.out.println("Enter option to make changes>>");
			System.out.println("1: Path for database file");
			System.out.println("2: Resolution");
			System.out.println("3: Standard Deviation");
			System.out.println("4: Mean value");
			System.out.println("5: Window length");
			System.out.println("6: Step size");
			System.out.println("0: To go back to main menu");
			System.out.println(">>>");
			int c2=sc.nextInt();
			switch(c2)
			{
			case 0:
				break;
			case 1:
				System.out.println("Enter new path.");
				System.out.println(">>>");
				String npath=sc.next();
				Consatnts.PATH1=npath;
				gestureLibrary=new GesturesLibrary();
				break;
			case 2:
				System.out.println("Enter new Resolution.");
				System.out.println(">>>");
				int r=sc.nextInt();
				Consatnts.NUMBER_OF_LEVELS=r;
				gestureLibrary=new GesturesLibrary();
				break;
			case 3:
				System.out.println("Enter new Standard Deviation.");
				System.out.println(">>>");
				int d=sc.nextInt();
				Consatnts.STANDARD_DEVIATION_VALUE=d;
				gestureLibrary=new GesturesLibrary();
				break;
			case 4:
				System.out.println("Enter new Mean value.");
				System.out.println(">>>");
				int m=sc.nextInt();
				Consatnts.MEAN_VALUE=m;
				gestureLibrary=new GesturesLibrary();
				break;
			case 5:
				System.out.println("Enter new window length.");
				System.out.println(">>>");
				int wl=sc.nextInt();
				Consatnts.WINDOW_SIZE=wl;
				gestureLibrary=new GesturesLibrary();
				break;
			case 6:
				System.out.println("Enter new step size.");
				System.out.println(">>>");
				int ss=sc.nextInt();
				Consatnts.STEP_SIZE=ss;
				gestureLibrary=new GesturesLibrary();
				break;
			}
		}

		try
		{
			System.out.println("Initializing...");
			MatlabInterface.MatlabInitialize();
			Query.generatePCAData(gestureLibrary);
			QuerySVD.generateSVDData(gestureLibrary);
//			QueryLDA.generateLDAData(gestureLibrary);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		boolean pointTwo=true;
		while (pointTwo)
		{
		
			try{
				int flag; 
				do 
				{ 	
					pointTwo=false;
					System.out.println("Please select the task that you want to perform:");
					System.out.println("enter 0 for setting parameters");
					System.out.println("enter 1 for Task 1: Sensor Analysis and Sensor-based Search Tasks ");
					System.out.println("enter 2 for Task 2: Latent Gesture Discovery Tasks");
					System.out.println("enter 3 for Task 3: Latent Gesture Clustering and Analysis Tasks");
					System.out.println("enter 4 for exit.");
					//String s=null;
					
					
					int op=sc.nextInt();
					flag=0;
					switch(op)
					{
					case 0:
						System.out.println("Values for parameters:".toUpperCase());
						System.out.println("");
						System.out.println("Path for database files: "+ Consatnts.PATH1);
						System.out.println("Resolution: "+ Consatnts.NUMBER_OF_LEVELS);
						System.out.println("Standard Deviation: "+Consatnts.STANDARD_DEVIATION_VALUE);
						System.out.println("Mean value: "+Consatnts.MEAN_VALUE);
						System.out.println("Window length: "+Consatnts.WINDOW_SIZE);
						System.out.println("Step size: "+Consatnts.STEP_SIZE);
						System.out.println();
						System.out.println("Enter 1 if you want to make changes.");
						System.out.println(">>>");
						int c11=sc.nextInt();
						if(c11==1)
						{
							System.out.println("Enter option to make changes>>");
							System.out.println("1: Path for database file");
							System.out.println("2: Resolution");
							System.out.println("3: Standard Deviation");
							System.out.println("4: Mean value");
							System.out.println("5: Window length");
							System.out.println("6: Step size");
							System.out.println("0: To go back to main menu");
							System.out.println(">>>");
							int c22=sc.nextInt();
							switch(c22)
							{
							case 0:
								break;
							case 1:
								System.out.println("Enter new path.");
								System.out.println(">>>");
								String npath1=sc.next();
								Consatnts.PATH1=npath1;
								
								break;
							case 2:
								System.out.println("Enter new Resolution.");
								System.out.println(">>>");
								int r1=sc.nextInt();
								Consatnts.NUMBER_OF_LEVELS=r1;
								
								break;
							case 3:
								System.out.println("Enter new Standard Deviation.");
								System.out.println(">>>");
								int d1=sc.nextInt();
								Consatnts.STANDARD_DEVIATION_VALUE=d1;
								
								break;
							case 4:
								System.out.println("Enter new Mean value.");
								System.out.println(">>>");
								int m1=sc.nextInt();
								Consatnts.MEAN_VALUE=m1;
								
								break;
							case 5:
								System.out.println("Enter new window length.");
								System.out.println(">>>");
								int wl1=sc.nextInt();
								Consatnts.WINDOW_SIZE=wl1;
								
								break;
							case 6:
								System.out.println("Enter new step size.");
								System.out.println(">>>");
								int ss1=sc.nextInt();
								Consatnts.STEP_SIZE=ss1;
								
								break;
							}
							gestureLibrary=new GesturesLibrary();
							//MatlabInterface.MatlabInitialize();
							Query.generatePCAData(gestureLibrary);
							QuerySVD.generateSVDData(gestureLibrary);
							QueryLDA.generateLDAData(gestureLibrary);
						}
						break;
					case 1:
						System.out.println("you have chosen task 1.");
						task1();
						flag=1;
						break;
					case 2:
						System.out.println("you have chosen task 2.");
						task2();
						flag=1;
						break;
					case 3:
						System.out.println("you have chosen task 3.");
						task3();
						flag=1;
						break;
					case 4:
						break;
					default:
						System.out.println("please chose between 1 and 4");
						flag=1;
					}	
				}
				while(flag==1);

			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error enter correct option");
				pointTwo=true;	
			}
			System.out.println("Reached here");
		}
		
     	sc.close();
		MatlabInterface.disconnect();
	}

	public static void task1() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		int op_1_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task1 that you want to perform:");
			System.out.println("enter 1 for Print Features");
			System.out.println("enter 2 for 1b- Find and rank the 5 most similar gestures relying on component");
			System.out.println("enter 3 for 1c- Find and rank the 5 most similar gestures ");
			System.out.println("enter 4 for return");
			Scanner kbr_1_1 = new Scanner(System.in); 
			op_1_1 = kbr_1_1.nextInt(); 
		} while(op_1_1<1 || op_1_1>4);
		switch(op_1_1)
		{
		case 1:
			System.out.println("you have chosen task 1a.");
			task1a();
			break;
		case 2:
			System.out.println("you have chosen task 1b.");
			task1b();
			break;
		case 3:
			System.out.println("you have chosen task 1c.");
			task1c();
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4");	
		}		
	}


	public static void task1a() throws java.io.IOException
	{

		Scanner sc=new Scanner(System.in);
		int op_1a_1,op_1a_2; 
		do 
		{ 	
			dispComponentOptions();
			op_1a_1 = sc.nextInt(); 

		} 
		while(op_1a_1<1 || op_1a_1>5); 
		switch(op_1a_1) 
		{ 
		case 1: 
			System.out.println("You have chosen X(task1a).");
			do 
			{
				dispOptionsPCASVDLDA();
				op_1a_2 = sc.nextInt();
				//kbr_1a_2.close();
			}while(op_1a_2<1 || op_1a_2>4);

			switch(op_1a_2)
			{
			case 1:

				System.out.println("you have chosen PCA(task1a---X).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s=sc.nextInt();
				Query.printXRightFactorSensor(s);
				break;
			case 2:

				System.out.println("you have chosen SVD(task1a---X).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s1=sc.nextInt();
				QuerySVD.printXRightFactorSensor(s1);
				break;
			case 3:
				System.out.println("you have chosen LDA(task1a---X).");
				Functions.printStrings3D(QueryLDA.XLDAData);
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1a---X)");
			}	

			break;

		case 2: 
			System.out.println("you have chosen Y(task1a)."); 
			do 
			{
				dispOptionsPCASVDLDA();
				op_1a_2 = sc.nextInt();
			}while(op_1a_2<1 || op_1a_2>4);	

			switch(op_1a_2)
			{
			case 1:

				System.out.println("you have chosen PCA(task1a---Y).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s=sc.nextInt();
				Query.printYRightFactorSensor(s);
				break;
			case 2:
				System.out.println("you have chosen SVD(task1a---Y).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s1=sc.nextInt();
				QuerySVD.printYRightFactorSensor(s1);
				break;
			case 3:
				System.out.println("you have chosen LDA(task1a---Y).");
				Functions.printStrings3D(QueryLDA.YLDAData);
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1a---Y)");
			}


			break;

		case 3: 
			System.out.println("You have chosen Z(task1a)."); 
			do 
			{
				dispOptionsPCASVDLDA();
				op_1a_2 = sc.nextInt();
			}while(op_1a_2<1 || op_1a_2>4);

			switch(op_1a_2)
			{

			case 1:

				System.out.println("you have chosen PCA(task1a---Z).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s1=sc.nextInt();
				Query.printZRightFactorSensor(s1);
				break;
			case 2:
				System.out.println("you have chosen SVD(task1a---Z).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s=sc.nextInt();
				QuerySVD.printZRightFactorSensor(s);
				break;
			case 3:
				System.out.println("you have chosen LDA(task1a---Z).");
				Functions.printStrings3D(QueryLDA.ZLDAData);
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1a---Z)");
			}


			break; 
		case 4: 
			System.out.println("you have chosen W(task1a)."); 

			do 
			{
				dispOptionsPCASVDLDA();
				op_1a_2 = sc.nextInt();
			}while(op_1a_2<1 || op_1a_2>4);	
			switch(op_1a_2)
			{
			case 1:

				System.out.println("you have chosen PCA(task1a---W).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s1=sc.nextInt();
				Query.printWRightFactorSensor(s1);
				break;
			case 2:
				System.out.println("you have chosen SVD(task1a---W).");
				System.out.println("Enter the sensor number for which you want to display top three principal component");
				int s=sc.nextInt();
				QuerySVD.printWRightFactorSensor(s);
				break;
			case 3:
				System.out.println("you have chosen LDA(task1a---W).");
				Functions.printStrings3D(QueryLDA.WLDAData);
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1a---W)");
			}

			break;
		case 5:
			break;
		default:
			System.out.println("pls chose between 1 and 5(task1a)");
		}




	}

	public static void task1b() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter parth for X component for query gesture");
		String xPath=sc.nextLine();
		System.out.println("Enter parth for Y component for query gesture");
		String yPath=sc.nextLine();
		System.out.println("Enter parth for Z component for query gesture");
		String zPath=sc.nextLine();
		System.out.println("Enter parth for W component for query gesture");
		String wPath=sc.nextLine();
		System.out.println("Enter Gesture Number");
		qgn=sc.nextInt();

		queryGesture=Query.initializeQuery(gestureLibrary, xPath, yPath, zPath, wPath);


		int op_1b_1,op_1b_2,op_1b_3; 
		do 
		{ 	
			dispComponentOptions();

			op_1b_1= sc.nextInt(); 
		} while(op_1b_1<1 ||op_1b_1>5);
		switch(op_1b_1) 
		{ 
		case 1: 
			System.out.println("You have chosen X(task1b).");
			do 
			{
				dispOptionsTFIDFTFIDF2top3LS();
				op_1b_2 = sc.nextInt();
			}while(op_1b_2 <1 || op_1b_2>4);
			switch(op_1b_2)
			{
			case 1:
				System.out.println("you have chosen TF-IDF(task1b---X).");
				gestureLibrary.queryDocument(xPath, 'X', 2);
				break;
			case 2:
				System.out.println("you have chosen TF-IDF2(task1b---X).");
				gestureLibrary.queryDocument(xPath, 'X', 3);
				break;
			case 3:
				System.out.println("you have chosen top-3 Latent sensor semantics(task1b---X).");
				do 
				{
					dispOptionsPCASVDLDA();
					op_1b_3 = sc.nextInt();
				}while(op_1b_3 <1 || op_1b_3>4);
				switch(op_1b_3)
				{
				case 1:
					System.out.println("you have chosen PCA(task1b---X---top3LS).");
					Query.printResulDocumentsOnXAxis(gestureLibrary, queryGesture);
					break;
				case 2:
					System.out.println("you have chosen SVD(task1b---X---top3LS).");
					QuerySVD.printResulDocumentsOnXAxis(gestureLibrary, queryGesture);
					break;
				case 3:
					System.out.println("you have chosen LDA(task1b---X---top3LS).");
					QueryLDA.printResulDocumentsOnXAxis(gestureLibrary, gestureLibrary.getGestureIndex(qgn));
					break;
				case 4:
					break;
				default:
					System.out.println("please chose between 1 and 4(task1b---X---top3LS)");	
				}
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1b---X)");	
			}	
			break;

		case 2: 
			System.out.println("you have chosen Y(task1b)."); 
			do 
			{
				dispOptionsTFIDFTFIDF2top3LS();
				op_1b_2 = sc.nextInt();
			}while(op_1b_2 <1 || op_1b_2>4);	
			switch(op_1b_2)
			{
			case 1:
				System.out.println("you have chosen TF-IDF(task1b---Y).");
				gestureLibrary.queryDocument(yPath, 'Y', 2);

				break;
			case 2:
				System.out.println("you have chosen TF-IDF2(task1b---Y).");
				gestureLibrary.queryDocument(yPath, 'Y', 3);
				break;
			case 3:
				System.out.println("you have chosen top-3 Latent sensor semantics(task1b---Y).");

				do 
				{
					dispOptionsPCASVDLDA();
					op_1b_3 = sc.nextInt();
				}while(op_1b_3 <1 || op_1b_3>4);
				switch(op_1b_3)
				{
				case 1:
					System.out.println("you have chosen PCA(task1b---Y---top3LS).");
					Query.printResulDocumentsOnYAxis(gestureLibrary, queryGesture);
					break;
				case 2:
					System.out.println("you have chosen SVD(task1b---Y---top3LS).");
					QuerySVD.printResulDocumentsOnYAxis(gestureLibrary, queryGesture);
					break;
				case 3:
					System.out.println("you have chosen LDA(task1b---Y---top3LS).");
					QueryLDA.printResulDocumentsOnYAxis(gestureLibrary, gestureLibrary.getGestureIndex(qgn));
					break;
				case 4:
					break;
				default:
					System.out.println("please chose between 1 and 4(task1b---Y---top3LS)");	
				}	
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1b---Y---top3LS)");	
			}		
			break;

		case 3: 
			System.out.println("You have chosen Z.(task1b)"); 
			do 
			{
				dispOptionsTFIDFTFIDF2top3LS();
				op_1b_2 = sc.nextInt();
			}while(op_1b_2 <1 || op_1b_2>4);	
			switch(op_1b_2)
			{
			case 1:
				System.out.println("you have chosen TF-IDF(task1b---Z).");
				gestureLibrary.queryDocument(zPath, 'Z', 2);

				break;
			case 2:
				System.out.println("you have chosen TF-IDF2(task1b---Z).");
				gestureLibrary.queryDocument(zPath, 'Z', 3);

				break;
			case 3:
				System.out.println("you have chosen top-3 Latent sensor semantics(task1b---Z).");
				do 
				{
					dispOptionsPCASVDLDA();
					op_1b_3 = sc.nextInt();
				}while(op_1b_3 <1 || op_1b_3>4);	

				switch(op_1b_3)
				{
				case 1:
					System.out.println("you have chosen PCA(task1b---Z---top3LS).");
					Query.printResulDocumentsOnZAxis(gestureLibrary, queryGesture);
					break;
				case 2:
					System.out.println("you have chosen SVD(task1b---Z---top3LS).");
					QuerySVD.printResulDocumentsOnZAxis(gestureLibrary, queryGesture);
					break;
				case 3:
					System.out.println("you have chosen LDA(task1b---Z---top3LS).");
					QueryLDA.printResulDocumentsOnZAxis(gestureLibrary, gestureLibrary.getGestureIndex(qgn));
					break;
				case 4:
					break;
				default:
					System.out.println("please chose between 1 and 4(task1b---Z---top3LS)");	
				}	
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1b---Z---top3LS)");	
			}		
			break; 
		case 4: 
			System.out.println("you have chosen W(task1b)."); 
			do 
			{
				dispOptionsTFIDFTFIDF2top3LS();
				op_1b_2 = sc.nextInt();
			}while(op_1b_2<1 || op_1b_2>4);	
			switch(op_1b_2)
			{
			case 1:
				System.out.println("you have chosen TF-IDF(task1b---W).");
				gestureLibrary.queryDocument(wPath, 'W', 2);
				break;
			case 2:
				System.out.println("you have chosen TF-IDF2(task1b---W).");
				gestureLibrary.queryDocument(wPath, 'W', 3);
				break;
			case 3:
				System.out.println("you have chosen top-3 Latent sensor semantics(task1b---W).");

				do 
				{
					dispOptionsPCASVDLDA();
					op_1b_3 = sc.nextInt();
				}while(op_1b_3 <1 || op_1b_3>4);
				switch(op_1b_3)
				{
				case 1:
					System.out.println("you have chosen PCA(task1b---W---top3LS).");
					Query.printResulDocumentsOnWAxis(gestureLibrary, queryGesture);
					break;
				case 2:
					System.out.println("you have chosen SVD(task1b---W---top3LS).");
					QuerySVD.printResulDocumentsOnWAxis(gestureLibrary, queryGesture);
					break;
				case 3:
					System.out.println("you have chosen LDA(task1b---W---top3LS).");
					QueryLDA.printResulDocumentsOnWAxis(gestureLibrary, gestureLibrary.getGestureIndex(qgn));
					break;
				case 4:
					break;
				default:
					System.out.println("please chose between 1 and 4(task1b---W---top3LS)");

				}	
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4(task1b---W)");	

			}		
			break;
		case 5:
			break;
		default:
			System.out.println("pls chose between 1 and 5(task1b)"); 
		}
	}

	public static void task1c() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter parth for X component for query gesture");
		String xPath=sc.nextLine();
		System.out.println("Enter parth for Y component for query gesture");
		String yPath=sc.nextLine();
		System.out.println("Enter parth for Z component for query gesture");
		String zPath=sc.nextLine();
		System.out.println("Enter parth for W component for query gesture");
		String wPath=sc.nextLine();
		System.out.println("Enter Gesture Number");
		qgn=sc.nextInt();

		queryGesture=Query.initializeQuery(gestureLibrary, xPath, yPath, zPath, wPath);


		int op_1c_1,op_1c_2; 
		do 
		{
			dispOptionsTFIDFTFIDF2top3LS();
			op_1c_1 = sc.nextInt();
		}while(op_1c_1 <1 || op_1c_1>4);

		switch(op_1c_1)
		{
		case 1:
			System.out.println("you have chosen TF-IDF(task1c).");
			gestureLibrary.queryGestureONTFIDF(queryGesture);
			break;
		case 2:
			System.out.println("you have chosen TF-IDF2(task1c).");
			gestureLibrary.queryGestureONTFIDF2(queryGesture);
			break;
		case 3:
			System.out.println("you have chosen top-3 Latent sensor semantics(task1c).");
			do 
			{
				dispOptionsPCASVDLDA();
				op_1c_2 = sc.nextInt();
			}while(op_1c_2 <1 || op_1c_2>4);

			switch(op_1c_2)
			{
			case 1:
				System.out.println("you have chosen PCA(task1c---top3LS).");
				Query.printResultGesture(gestureLibrary, queryGesture);
				break;
			case 2:
				System.out.println("you have chosen SVD(task1c---top3LS).");
				QuerySVD.printResultGesture(gestureLibrary, queryGesture);
				break;
			case 3:
				System.out.println("you have chosen LDA(task1c---top3LS).");
				QueryLDA.printResulGESTURE(gestureLibrary, gestureLibrary.getGestureIndex(qgn));
				break;
			case 4:
				break;
			default:
				System.out.println("please chosen between 1 and 4(task1c---top3LS)");	
			}
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(task1c)");	
		}	

	}

	public static void task2() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		int op_2_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task2 that you want to perform:");
			System.out.println("enter 1 for 2b PCA Similarity");
			System.out.println("enter 2 for 2c SVD Similarity");
			System.out.println("enter 3 for return");
			op_2_1 = sc.nextInt(); 
		} while(op_2_1 < 1 || op_2_1 > 3); 

		switch(op_2_1)
		{
		case 1:
			System.out.println("you have chosen task 2b.");
			task2b();
			break;
		case 2:
			System.out.println("you have chosen task 2c.");
			task2c();
			break;
		case 3:
			break;
		default:
			System.out.println("please chose between 1 and 3");	
		}	

	}
	public static void task2b() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		int op_2b_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task2b that you want to perform:");
			System.out.println("enter 1 for GestureGestureSimilarityMatrix ");
			System.out.println("enter 2 for PCA on GestureGestureSimilarityMatrix ");
			System.out.println("enter 3 for Report top3 Principal Component ");
			System.out.println("enter 4 for return");
			op_2b_1=sc.nextInt(); 
		} while(op_2b_1 < 1 || op_2b_1 > 4); 

		switch(op_2b_1)
		{
		case 1:
			System.out.println("you have chosen GestureGestureSimilarityMatrix(task2b).");
			gestureGestureSimilarityTFIDFTFIDF2top3LS();
			break;
		case 2:
			System.out.println("you have chosen PCA on GestureGestureSimilarityMatrix(task2b).");
			gestureGestureSimilarityTFIDFTFIDF2top3LS_PCA();
			break;
		case 3:
			System.out.println("you have chosen Report top3 Principal Component(task2b).");
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(task2b)");
		}
	}
	public static void task2c() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		int op_2c_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task2b that you want to perform:");
			System.out.println("enter 1 for GestureGestureSimilarityMatrix ");
			System.out.println("enter 2 for SVD on GestureGestureSimilarityMatrix ");
			System.out.println("enter 3 for Report top3 Latent Semantics ");
			System.out.println("enter 4 for return");
			op_2c_1 = sc.nextInt(); 
		} while(op_2c_1 < 1 || op_2c_1 > 4); 

		switch(op_2c_1)
		{
		case 1:
			System.out.println("you have chosen GestureGestureSimilarityMatrix(task2c).");
			gestureGestureSimilarityTFIDFTFIDF2top3LS();
			break;
		case 2:
			System.out.println("you have chosen SVD on GestureGestureSimilarityMatrix(task2c).");
			gestureGestureSimilarityTFIDFTFIDF2top3LS_SVD();
			break;
		case 3:
			System.out.println("you have chosen Report top3 LatentSemantics(task2c).");
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(task2c)");
		}
	}

	public static void task3() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		int op_3_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task3 that you want to perform:");
			System.out.println("enter 1 for 3a ");
			System.out.println("enter 2 for return");
			op_3_1 = sc.nextInt(); 
		} while(op_3_1<1 || op_3_1>2);
		switch(op_3_1)
		{
		case 1:
			//			task3a();
			break;
		case 2:
			break;
		default:
			System.out.println("please select again");
		}
	}
	public static void task3a() throws java.io.IOException, MatlabConnectionException, MatlabInvocationException
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("you have chosen partitioning the gestures into 3 groups.");
		int op_3a_1; 
		do 
		{ 	
			System.out.println("Please select the sub tasks of task3a that you want to perform:");
			System.out.println("enter 1 for PCA ");
			System.out.println("enter 2 for SVD ");
			System.out.println("enter 3 for return");
			op_3a_1 = sc.nextInt(); 
		} while(op_3a_1<1 || op_3a_1>3);
		switch(op_3a_1)
		{
		case 1:

			break;
		case 2:

			break;
		case 3:
			break;
		default:
			System.out.println("please select again");
		}		
	}

	public static void dispComponentOptions()
	{
		System.out.println("Please select one component from the below:");
		System.out.println("enter 1 for X ");
		System.out.println("enter 2 for Y ");
		System.out.println("enter 3 for Z ");
		System.out.println("enter 4 for W ");
		System.out.println("enter 5 for return");
	}

	public static void dispOptionsPCASVDLDA()
	{
		System.out.println("Please select one option from the below:");
		System.out.println("enter 1 for PCA ");
		System.out.println("enter 2 for SVD ");
		System.out.println("enter 3 for LDA ");
		System.out.println("enter 4 for return");
	}

	public static void dispOptionsTFIDFTFIDF2top3LS()
	{
		System.out.println("Please select one option from the below:");
		System.out.println("enter 1 for TF-IDF sensor vectors");
		System.out.println("enter 2 for TF-IDF2 sensor vectors");
		System.out.println("enter 3 for top3 Latent Semantic sensors");
		System.out.println("enter 4 for return");
	}

	public static void gestureGestureSimilarityTFIDFTFIDF2top3LS() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		Scanner sc= new Scanner(System.in);
		int op_ggs_1,op_ggs_2; 
		do 
		{
			System.out.println("For creating GestureGestureSimilarity Matrix--> ");
			dispOptionsTFIDFTFIDF2top3LS();
			op_ggs_1 = sc.nextInt();
		}while(op_ggs_1<1 || op_ggs_1>4);
		switch(op_ggs_1)
		{
		case 1:
			System.out.println("you have chosen TF-IDF(Gesture Gesture Similarity).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF(gestureLibrary);
			LatentGestureDiscovery.printGestureSimilarityOnTFIDF();
			break;
		case 2:
			System.out.println("you have chosen TF-IDF2(Gesture Gesture Similarity).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF1(gestureLibrary);
			LatentGestureDiscovery.printGestureSimilarityOnTFIDF1();
			break;
		case 3:
			System.out.println("you have chosen top-3 Latent sensor semantics(Gesture Gesture Similarity).");
			do 
			{
				dispOptionsPCASVDLDA();
				op_ggs_2 = sc.nextInt();
			}while(op_ggs_2<1 || op_ggs_2>4);
			switch(op_ggs_2)
			{
			case 1:
				System.out.println("you have chosen PCA(Gesture Gesture Similarity--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnPCA();
				LatentGestureDiscovery.printGestureSimilarityOnPCA();
				break;
			case 2:
				System.out.println("asdfasdfasdfadsfadsfds");
				System.out.println("you have chosen SVD(Gesture Gesture Similarity--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnSVD();
				LatentGestureDiscovery.printGestureSimilarityOnSVD();
				break;
			case 3:
				System.out.println("you have chosen LDA(Gesture Gesture Similarity--top3LS).");
				Functions.printDoubleArray1(QueryLDA.GestureSimilarityMatrix);
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4((Gesture Gesture Similarity--top3LS))");	
			}	
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(Gesture Gesture Similarity)");
		}	
	}


	public static void gestureGestureSimilarityTFIDFTFIDF2top3LS_PCA() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		Scanner sc=new Scanner(System.in);
		int op_ggs_1,op_ggs_2; 
		do 
		{
			System.out.println("For applying PCA on GestureGestureSimilarity Matrix--> ");
			dispOptionsTFIDFTFIDF2top3LS();

			op_ggs_1 = sc.nextInt();
		}while(op_ggs_1<1 || op_ggs_1>4);
		switch(op_ggs_1)
		{
		case 1:
			System.out.println("you have chosen TF-IDF(ggs).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF(gestureLibrary);
			LatentGestureDiscovery.createPCAGestureSimilarityOnTFIDF();
			LatentGestureDiscovery.printPCASimilarityOnTFIDF();
			System.out.println("Right Factor Matrix");
			LatentGestureDiscovery.printRFPCASimilarityOnTFIDF();
			double data[][]=LatentGestureDiscovery.PCASimilarityOnTFIDF;
			System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
			int op=sc.nextInt();
			if(op==1)
				LatentGestureClustering.findClusters(data);
			break;
		case 2:
			System.out.println("you have chosen TF-IDF2(Gesture Gesture Similarity).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF1(gestureLibrary);
			LatentGestureDiscovery.createPCAGestureSimilarityOnTFIDF1();
			LatentGestureDiscovery.printPCASimilarityOnTFIDF1();
			System.out.println("Right Factor Matrix");
			LatentGestureDiscovery.printRFPCASimilarityOnTFIDF1();
			double data2[][]=LatentGestureDiscovery.PCASimilarityOnTFIDF1;
			System.out.println("Do you wnat to print clusters with this data ?");
			int op1=sc.nextInt();
			if(op1==1)
				LatentGestureClustering.findClusters(data2);
			break;
		case 3:
			System.out.println("you have chosen top-3 Latent sensor semantics(Gesture Gesture Similarity).");
			do 
			{
				dispOptionsPCASVDLDA();

				op_ggs_2 = sc.nextInt();
			}while(op_ggs_2<1 || op_ggs_2>4);
			switch(op_ggs_2)
			{
			case 1:
				System.out.println("you have chosen PCA(ggs--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnPCA();
				LatentGestureDiscovery.createPCAGestureSimilarityOnPCA();
				LatentGestureDiscovery.printPCASimilarityOnPCA();
				System.out.println("Right Factor Matrix");
				LatentGestureDiscovery.printRFPCASimilarityOnPCA();
				double data3[][]=LatentGestureDiscovery.PCASimilarityOnPCA;
				System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
				int op3=sc.nextInt();
				if(op3==1)
					LatentGestureClustering.findClusters(data3);
				break;
			case 2:
				System.out.println("you have chosen SVD(ggs--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnSVD();
				LatentGestureDiscovery.createPCAGestureSimilarityOnSVD();
				LatentGestureDiscovery.printPCASimilarityOnSVD();
				System.out.println("Right Factor Matrix");
				LatentGestureDiscovery.printRFPCASimilarityOnSVD();
				double data4[][]=LatentGestureDiscovery.PCASimilarityOnSVD;
				System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
				int op4=sc.nextInt();
				if(op4==1)
					LatentGestureClustering.findClusters(data4);
				break;
			case 3:
				System.out.println("you have chosen LDA(ggs--top3LS).");
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4((ggs--top3LS))");	
			}	
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(ggs)");
		}	
	}

	public static void gestureGestureSimilarityTFIDFTFIDF2top3LS_SVD() throws MatlabConnectionException, MatlabInvocationException, IOException
	{
		Scanner sc=new Scanner(System.in);

		int op_ggs_1,op_ggs_2; 
		do 
		{
			System.out.println("For applying SVD on GestureGestureSimilarity Matrix--> ");
			dispOptionsTFIDFTFIDF2top3LS();

			op_ggs_1 = sc.nextInt();
		}while(op_ggs_1<1 || op_ggs_1>4);
		switch(op_ggs_1)
		{
		case 1:
			System.out.println("you have chosen TF-IDF(ggs).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF(gestureLibrary);
			LatentGestureDiscovery.createSVDGestureSimilarityOnTFIDF();
			LatentGestureDiscovery.printSVDSimilarityOnTFIDF();
			System.out.println("Right Factor Matrix");
			LatentGestureDiscovery.printRFSVDSimilarityOnTFIDF();
			double data[][]=LatentGestureDiscovery.SVDSimilarityOnTFIDF;
			System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
			int op=sc.nextInt();
			if(op==1)
				LatentGestureClustering.findClusters(data);
			break;
		case 2:
			System.out.println("you have chosen TF-IDF2(ggs).");
			LatentGestureDiscovery.createGestureSimillarityOnTFIDF1(gestureLibrary);
			LatentGestureDiscovery.createSVDGestureSimilarityOnTFIDF1();
			LatentGestureDiscovery.printSVDSimilarityOnTFIDF1();
			System.out.println("Right Factor Matrix");
			LatentGestureDiscovery.printRFSVDSimilarityOnTFIDF1();
			double data1[][]=LatentGestureDiscovery.SVDSimilarityOnTFIDF1;
			System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
			int op1=sc.nextInt();
			if(op1==1)
				LatentGestureClustering.findClusters(data1);
			break;
		case 3:
			System.out.println("you have chosen top-3 Latent sensor semantics(ggs).");
			do 
			{
				dispOptionsPCASVDLDA();

				op_ggs_2 = sc.nextInt();
			}while(op_ggs_2<1 || op_ggs_2>4);
			switch(op_ggs_2)
			{
			case 1:
				System.out.println("you have chosen PCA(ggs--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnPCA();
				LatentGestureDiscovery.createSVDGestureSimilarityOnPCA();
				LatentGestureDiscovery.printSVDSimilarityOnPCA();
				System.out.println("Right Factor Matrix");
				LatentGestureDiscovery.printRFSVDSimilarityOnPCA();
				double data2[][]=LatentGestureDiscovery.SVDSimilarityOnPCA;
				System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
				int op2=sc.nextInt();
				if(op2==1)
					LatentGestureClustering.findClusters(data2);
				break;
			case 2:
				System.out.println("you have chosen SVD(ggs--top3LS).");
				LatentGestureDiscovery.createGestureSimillarityOnSVD();
				LatentGestureDiscovery.createSVDGestureSimilarityOnSVD();
				LatentGestureDiscovery.printSVDSimilarityOnSVD();
				System.out.println("Right Factor Matrix");
				LatentGestureDiscovery.printRFSVDSimilarityOnSVD();
				double data3[][]=LatentGestureDiscovery.SVDSimilarityOnSVD;
				System.out.println("Do you wnat to print clusters with this data ? (1 or 0)");
				int op3=sc.nextInt();
				if(op3==1)
					LatentGestureClustering.findClusters(data3);
				break;
			case 3:
				System.out.println("you have chosen LDA(ggs--top3LS).");
				break;
			case 4:
				break;
			default:
				System.out.println("please chose between 1 and 4((ggs--top3LS))");	
			}	
			break;
		case 4:
			break;
		default:
			System.out.println("please chose between 1 and 4(ggs)");
		}	
	}
}