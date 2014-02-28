package phase3;

import java.io.IOException;
import phase1.Consatnts;
import phase1.GesturesLibrary;

public class Ctest 
{
	public static void main(String[] args) throws IOException 
	{
		Consatnts.PATH1="C:/Users/SONY/Desktop/Dropbox/Multimedia and Web Databases/Projects/Phase 3/3classdata/";
		GesturesLibrary gesturesLibrary=new GesturesLibrary();
		System.out.println(gesturesLibrary.getXwordDicforSensors(0));
		System.out.println(gesturesLibrary.getMultivariateData('X',1));
		System.out.println(Consatnts.TOTAL_NUMBER_OF_GESTURES);
				
	}
}
