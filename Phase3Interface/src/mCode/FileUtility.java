package mCode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class FileUtility {

	public static boolean SaveFileWithNumber(String path, String name, String[][] data)
	{
		try{
		File file = new File(path + name);
		if(!file.exists())
		{
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(String.valueOf(data.length));
		bw.newLine();
		System.out.println(data.length);
		// for each document
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length;j++)
			{
				if(j != 0)
					bw.write(" ");
				bw.write(data[i][j]);

				//System.out.print(data[i][j] + " ");
			}
			if(i != (data.length - 1))
				bw.newLine();
		}
		
		bw.close();
		return true;
		}
		catch(Exception ex)
		{

			System.out.println("ERROR: " + ex.getMessage());
		}
		return false;
	}
	public static boolean SaveFile(String path, String name, double[][] data)
	{
		try{
			File file = new File(path + name);
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(data.length);
			// for each document
			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length;j++)
				{
					if(j != 0)
						bw.write(" ");
					bw.write(String.valueOf(data[i][j]));

					//System.out.print(data[i][j] + " ");
				}
				if(i != (data.length - 1))
					bw.newLine();
			}
			
			bw.close();
			return true;
			}
			catch(Exception ex)
			{

				System.out.println("ERROR: " + ex.getMessage());
			}
			return false;
	}
	
	public static boolean SaveFile(String path, String name, char[][] data)
	{
		try{
			File file = new File(path + name);
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(data.length);
			// for each document
			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length;j++)
				{
					if(j != 0)
						bw.write(" ");
					bw.write(String.valueOf(data[i][j]));

					//System.out.print(data[i][j] + " ");
				}
				if(i != (data.length - 1))
					bw.newLine();
			}
			
			bw.close();
			return true;
			}
			catch(Exception ex)
			{

				System.out.println("ERROR: " + ex.getMessage());
			}
			return false;
	}
	
	public static boolean SaveFile(String path, String name, String[][] data)
	{
		try{
			File file = new File(path + name);
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(data.length);
			// for each document
			for(int i = 0; i < data.length; i++)
			{
				for(int j = 0; j < data[i].length;j++)
				{
					if(j != 0)
						bw.write(" ");
					bw.write(String.valueOf(data[i][j]));

					//System.out.print(data[i][j] + " ");
				}
				if(i != (data.length - 1))
					bw.newLine();
			}
			
			bw.close();
			return true;
			}
			catch(Exception ex)
			{

				System.out.println("ERROR: " + ex.getMessage());
			}
			return false;
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static double[][] ReadFileToDoubleArray(String path)
	{
		List<double[]> results = new ArrayList<double[]>();
		BufferedReader br = null;
		try{
			String current_line;
			String[] parts;
			br = new BufferedReader(new FileReader(path));
			int count = 0;
			while ((current_line = br.readLine()) != null)
			{
				parts = current_line.split(",");
				double[] array = new double[parts.length];
				for(int i = 0; i < parts.length; i++)
				{
					array[i] = Double.parseDouble(parts[i]);
				}
				results.add(array);
				count++;
			}
		}
		catch(Exception ex){
			System.out.println("ERROR: " + ex.getMessage());
		}
		//Split into pieces, delimited by comma.
		
		double[][] results_arr = new double[results.size()][];
		for(int i = 0; i < results.size(); i++)
		{
			results_arr[i] = results.get(i);
		}
		return results_arr;
	}
	
	public static String getFileName(File file)
	{
		return file.getName();
	}
	
	public static File[] getFiles(String directory, String filter)
	{
		File file = new File(directory);
		File[] files = file.listFiles();
		List<File> files_new = new ArrayList<File>();
		
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].getName().endsWith(filter))
			{
				files_new.add(files[i]);
			}
		}
		
		files = new File[files_new.size()];
		for(int i = 0; i < files_new.size(); i++)
		{
			files[i] = files_new.get(i);
		}
		
		return files;
	}
	
}