package phase3;

import java.util.Scanner;

public class ScannerInterface 
{
	private static Scanner sc;
	public static void scannerInitialize()
	{
		sc=new Scanner(System.in);
	}
	public static Scanner getScanner()
	{
		return sc;
	}
	public static void close() 
	{
		sc.close();
	}

}
