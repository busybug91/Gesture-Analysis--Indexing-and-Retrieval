package mCode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


//This class is in charge of getting data from files.
public class Extractor {

	private final String OUTPUT_PATH = "./Outputs/";
	private final String NORM_FILE_NAME = "{0}-{1}-{2}.csv";
	private int r,w,s;
	
	public int getR() { return r;}
	public int getW() { return w;}
	public int getS() {	return s;}
	
	public void setR(int value) { r = value;}
	public void setW(int value) { w = value;}
	public void setS(int value) { s = value;}

	public double[][] NormalizeFile(File file, double lowerbound, double upperbound)
	{
		String filename = FileUtility.getFileName(file);
		double[][] original_data = FileUtility.ReadFileToDoubleArray(file.getAbsolutePath());
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		
		//Find the global max and min for the conversion
		for(int i = 0; i < original_data.length; i++)
		{	for(int j = 0; j < original_data[i].length; j++)
			{	if(original_data[i][j] < min)
					min = original_data[i][j];
				if(original_data[i][j] > max)
					max = original_data[i][j];
			}
		}

		//need to normalize all values between the bounds
		for(int i = 0; i < original_data.length; i++)
		{
			for(int j = 0; j < original_data[i].length; j++)
			{
				original_data[i][j] = lowerbound + (original_data[i][j] - min) * (upperbound - lowerbound) / (max - min);					
			}
		}
		
		FileUtility.SaveFile(OUTPUT_PATH + "Norm/", String.format(NORM_FILE_NAME, filename, lowerbound, upperbound), original_data);
		return original_data;
	}

	public char[][] DiscretizeData(Band[] bands, double[][] data)
	{
		char[][] chars = new char[data.length][data[0].length];
		for(int i = 0; i < data.length; i ++)
		{	for(int j = 0; j < data[i].length; j++)
			{
				chars[i][j] = DiscretizeValue(bands,data[i][j]);
			}
		}
		return chars;
	}
	
	private char DiscretizeValue(Band[] bands, double value )
	{
		char result = '.';
		//find where the value fits.
		for(int i = 0; i < bands.length; i++)
		{
			if(value >= bands[i].getStart() && value < bands[i].getStop())
			{
				//use the symbol once we find it
				result = bands[i].getSymbol();
				break;
			}
		}
		
		return result;
	}

	public String[][] BuildNGrams(char[][] data)
	{
		String[][] words = new String[data.length][];
		for(int i=0; i < data.length; i++)
		{
			words[i] = this.BuildNGramsArray(data[i]);
			//System.out.println();
		}
		
		return words;
	}
	
	public String[]	BuildNGramsArray(char[] data)
	{
		int offset = this.getS();
		int window = this.getW();
		
		int strings = (int)(data.length/offset);
		if(data.length % offset > 0)
			strings++;	//account for remainder
		String[] dictionary = new String[strings];
		int count = 0;
		char default_char = '.'; //this will be appended to the end of remainders.
		@SuppressWarnings("unused")
		int end;
		String current;
		for(int i = 0; i < data.length; i = i + offset)
		{
			current = "";	//reset string
			//need to check if window will be in bounds
			for(int w = 0; w < window; w++)
			{
				if(i + w >= data.length)	//we ran off the array! use default
					current += default_char;
				else
					current += data[i+w];	//otherwise add to word
			}
			dictionary[count]=(current);	//add word to collection
			count++;
		//	System.out.print(current + "   ");
		}
		return dictionary;
	}

	public static List<String> BuildUniqueDictionary(String[][][] database)
	{
		List<String> dictionary = new ArrayList<String>();
		String[][] current_file;
		String[] current_row;
		for(int i = 0; i < database.length; i++)
		{
			current_file = database[i];
			for(int r = 0; r < current_file.length; r++)
			{
				current_row = current_file[r];
				for(int t = 0; t < current_row.length; t++)
				{
					if(current_row[t].contains("|"))
					{
						String[] parts = current_row[t].split("\\|");
					
						for(int o = 0; o < parts.length; o++)
						{
							if(!dictionary.contains(parts[o]))
							{
								dictionary.add(parts[o]);
							}
						}
					}
					else{
					if(!dictionary.contains(current_row[t]))
					{
						dictionary.add(current_row[t]);
					//	System.out.println(current_row[t]);
					}}
					
				}
			}
		}
		
		return dictionary;
	}
}
