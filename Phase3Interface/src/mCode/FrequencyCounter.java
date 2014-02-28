package mCode;
import java.util.ArrayList;
import java.util.List;


public class FrequencyCounter {

	private List<String> dimensions;
	
	public List<String> getDimensions() { return dimensions;}
	public void setDimensions(List<String> value) { dimensions = value;}
	
	
	public FrequencyCounter()
	{
		dimensions = new ArrayList<String>();
	}

	public double[] compose (double[] tf, double[] idf)
	{
		double[] results = new double[tf.length];
		for(int i = 0; i < tf.length; i++)
		{
			results[i] = tf[i] * idf[i];
		}
		return results;	
	}
	
	public double[] CalculateIDF(String[][][] database)
	{
		
		double[] vector = new double[dimensions.size()];
		int total = 0;
		String[][] current_file;
		String[] current_row;
		String current_check;
		int count;
		for(int k = 0; k < dimensions.size(); k++)
		{
			current_check = dimensions.get(k);
			count=0;
			total=0;
			for(int i = 0; i < database.length; i++)
			{	//for each file
				current_file = database[i];
				for(int r = 0; r < current_file.length; r++)
				{	//for each row...
					current_row = current_file[r];
					for(int t = 0; t < current_row.length; t++)
					{	//find if the value exists.
						if(current_row[t].equals(current_check))
						{
							count++;
							break;
						}
					}
					total++;	//count the total number of rows.
				
				}
			}
			
			//Back in the dictionary loop...
			if(count != 0)
				vector[k] = Math.log10((double)total /(double) count);
			else
				vector[k] = 0;
		}
		
		return vector;
	}
	
	public double[] CalculateIDF2(String[][] dataset)
	{
		List<String> dictionary = dimensions;
		double[] vector = new double[dictionary.size()];
		double total = 0;
		String[] current_row;
		String current_check;
		double count;
		for(int k = 0; k < dictionary.size(); k++)
		{
			current_check = dictionary.get(k);
			count=0;
			total=0;
				//we only care for each file individually
				for(int r = 0; r < dataset.length; r++)
				{	//for each row...
					current_row = dataset[r];
					for(int t = 0; t < current_row.length; t++)
					{	//find if the value exists.
						if(current_row[t].equals(current_check))
						{
							count++;
							break;
						}
					}
					total++;	//count the total number of rows.
			}
			
			//Back in the dictionary loop...
				if(count != 0)
					vector[k] = Math.log10((double)total /(double) count);
				else
					vector[k] = 0;
		}
		
		return vector;
	}
	
	public double[] CalculateTF(String[] words)
	{
		List<String> dictionary = dimensions;
		int index;
		int count = words.length;  //there are this many terms in the row.
		double[] vector = new double[dictionary.size()];
		for(int i = 0; i < words.length; i++)
		{
			index = dictionary.indexOf(words[i]);
			if(index != -1)
			{
				vector[index] = vector[index] + 1;
			}
			else
			{
				System.out.println("ERROR:  Term was not in dictionary." + words[i]);
			//	break;
			}
		}
		
		for(int i = 0; i < vector.length; i++)
		{
			vector[i] = (double)vector[i] / (double)count;
		}
		return vector;
	}

	public FrequencyWrapper VectorizeUnivariate(String[] words, double[] idf, double [] idf2)

	{
		FrequencyWrapper vector = new FrequencyWrapper();
		double[] tf = this.CalculateTF(words);
		vector.setIDF(idf);
		vector.setIDF2(idf2);
		vector.setTF(tf);
		vector.setTFIDF(this.compose(tf, idf));
		vector.setTFIDF2(this.compose(tf, idf2));
		return vector;
	}

	public FrequencyWrapper[] VectorizeDataset(String[][] dataset, double[] idf)
	{
		FrequencyWrapper[] results = new FrequencyWrapper[dataset.length];
		double[] idf2 = CalculateIDF2(dataset);
		
		for(int i = 0; i < dataset.length; i++)
		{
			results[i] = this.VectorizeUnivariate(dataset[i], idf, idf2);		
		}
		
		return results;
	}
	
	public FrequencyWrapper[][] VectorizeDatabase(String[][][] database)
	{
		FrequencyWrapper[][] results = new FrequencyWrapper[database.length][];
		double[] idf = this.CalculateIDF(database);
		for(int i = 0; i < results.length; i++)
			results[i] = this.VectorizeDataset(database[i], idf);
		
		return results;
	}
	
	public FrequencyWrapper[][] VectorizeDatabaseWithOverlap(String[][][] database)
	{
		FrequencyWrapper[][] results = new FrequencyWrapper[database.length][];
		double[] idf = this.CalculateIDFWithOverlap(database);
		for(int i = 0; i < results.length; i++)
			results[i] = this.VectorizeDatasetWithOverlap(database[i], idf);
		
		return results;
	}
	
	public double[] CalculateIDFWithOverlap(String[][][] database)
	{	
		double[] vector = new double[dimensions.size()];
		int total = 0;
		String[][] current_file;
		String[] current_row;
		String current_check;
		int count;
		for(int k = 0; k < dimensions.size(); k++)
		{
			current_check = dimensions.get(k);
			count=0;
			total=0;
			for(int i = 0; i < database.length; i++)
			{	//for each file
				current_file = database[i];
				for(int r = 0; r < current_file.length; r++)
				{	//for each row...
					current_row = current_file[r];
					for(int t = 0; t < current_row.length; t++)
					{	//find if the value exists.
						//With overlap we need to extract the different values and compare
						String[] parts = current_row[t].split("\\|");
						System.out.println("IDF Over " + t + " " + r + " " + i + "|" + current_row[t] + "|" + parts.length + "|" + current_check);
						boolean found = false;
						for(int o = 0; o < parts.length; o++)
							if(parts[o].equals(current_check))
							{
								count++;
								System.out.println("HERE +" + count + "!!" + current_check + " = " + parts[o]);
								found = true;
							}
						if(found)
						{
							System.out.println("FOUND!");
							break;
						}
					}
					System.out.println("Inc total");
					total++;	//count the total number of rows.
				
				}
			}
			
			//Back in the dictionary loop...
			
			if(count != 0)
			{
				
				vector[k] = Math.log10((double)total /(double) count);
							}
			else
				vector[k] = 0;
			System.out.println(String.valueOf(vector[k]) + " | " + total + " | " + count);
		}
		
		return vector;
	}
	
	public double[] CalculateIDF2WithOverlap(String[][] dataset)
	{
		List<String> dictionary = dimensions;
		double[] vector = new double[dictionary.size()];
		double total = 0;
		String[] current_row;
		String current_check;
		double count;
		for(int k = 0; k < dictionary.size(); k++)
		{
			current_check = dictionary.get(k);
			count=0;
			total=0;
				//we only care for each file individually
				for(int r = 0; r < dataset.length; r++)
				{	//for each row...
					current_row = dataset[r];
					for(int t = 0; t < current_row.length; t++)
					{	//find if the value exists.
						String[] parts = current_row[t].split("\\|");
						boolean found = false;
						for(int o = 0; o < parts.length; o++)
							if(parts[o].equals(current_check))
							{
								count++;
								found = true;
							}
						if(found)
						{
							break;
						}
					}
					total++;	//count the total number of rows.
			}
			
			//Back in the dictionary loop...
				if(count != 0)
					vector[k] = Math.log10((double)total /(double) count);
				else
					vector[k] = 0;
		}
		
		return vector;
	}
	
	public FrequencyWrapper VectorizeUnivariateWithOverlap(String[] words, double[] idf, double [] idf2)

	{
		FrequencyWrapper vector = new FrequencyWrapper();
		double[] tf = this.CalculateTFWithOverlap(words);
		vector.setIDF(idf);
		vector.setIDF2(idf2);
		vector.setTF(tf);
		vector.setTFIDF(this.compose(tf, idf));
		vector.setTFIDF2(this.compose(tf, idf2));
		return vector;
	}

	public FrequencyWrapper[] VectorizeDatasetWithOverlap(String[][] dataset, double[] idf)
	{
		FrequencyWrapper[] results = new FrequencyWrapper[dataset.length];
		double[] idf2 = CalculateIDF2WithOverlap(dataset);
		
		for(int i = 0; i < dataset.length; i++)
		{
			results[i] = this.VectorizeUnivariateWithOverlap(dataset[i], idf, idf2);		
		}
		
		return results;
	}
	
	public double[] CalculateTFWithOverlap(String[] words)
	{
		List<String> dictionary = dimensions;
		int index;
		int count = words.length;  //there are this many terms in the row.
		double[] vector = new double[dictionary.size()];
		for(int i = 0; i < words.length; i++)
		{
			String[] parts = words[i].split("\\|");
			//System.out.println(parts.length);
			for(int x = 0; x < parts.length; x++)
			{
				index = dictionary.indexOf(parts[x]);
				if(index != -1)
				{
					vector[index] = vector[index] + 1;
				}
				else
				{
					System.out.println("ERROR TF:  Term was not in dictionary." + words[i]);
					break;
				}
			}
		}
		
		for(int i = 0; i < vector.length; i++)
		{
			vector[i] = (double)vector[i] / (double)count;
		}
		return vector;
	}
	
}