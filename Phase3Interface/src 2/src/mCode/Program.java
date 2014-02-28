package mCode;

import java.io.*;

public class Program {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unused", "static-access" })
	public static void main(String[] args) {


			String directory = "C:\\Documents and Settings\\Moniika\\Desktop\\Fall 2013\\Phase1\\sampledata\\sampledata\\X\\";
			int lowerbound = -1, upperbound = 1;
			File[] files = FileUtility.getFiles(directory, ".csv");
			Extractor ex = new Extractor();
			
			ex.setR(10);
			ex.setW(4);
			ex.setS(5);
			
			double[][] data;
			char[][] letters;
			String[][] words;
			String[][][] database = new String[files.length][][];
			FrequencyWrapper[][] vectors = new FrequencyWrapper[files.length][];
			String[] filenames = new String[files.length];
			Band[] bands = Band.BuildBands(ex.getR(), 0.25, 0, lowerbound, upperbound);
			

			for(int i = 0; i < files.length; i++)
			{
				System.out.println(files[i].getName());
				//for each life
				data = ex.NormalizeFile(files[i], lowerbound, upperbound);
				//for(int j = 0; j < data.length; j++)
				//	for(int k = 0; k < data[j].length; k++)
				//		System.out.print(data[j][k]);
				letters = ex.DiscretizeData(bands, data);
				words = ex.BuildNGrams(letters);
				database[i] = words;
				filenames[i] = files[i].getName();
			}
			
			//need to get idf now.
			FrequencyCounter fc = new FrequencyCounter();
			fc.setDimensions(ex.BuildUniqueDictionary(database));
			vectors = fc.VectorizeDatabase(database);
			
			String[][] sensor1data = new String[database.length][];
			
			for(int i = 0; i < database.length; i++)
			{
				sensor1data[i] = database[i][1];
			}
			
			LDA lda = new LDA();
			lda.solve(sensor1data, 3);
		
	}
}