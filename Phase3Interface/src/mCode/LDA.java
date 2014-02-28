package mCode;

import LDAGibb.*;
public class LDA implements IDimensionalityReduction {

	@SuppressWarnings("unused")
	public void solve(String [][] data, int k)
	{
		FileUtility.SaveFileWithNumber("./", "input-model.dat", data);
		
		
		LDACmdOption ldaOption = new LDACmdOption();
		ldaOption.est = true;
		ldaOption.dir = ".";
		ldaOption.K = 3;
		ldaOption.modelName = "model-final";
		ldaOption.savestep = 1000;
		ldaOption.niters = 1000; 
		ldaOption.dfile = "input-model.dat";
		Estimator est = new Estimator();
		est.init(ldaOption);
		
		est.estimate(); 
		
		Inferencer inf = new Inferencer();
		inf.init(ldaOption);
		Model newModel = inf.inference();
	}
}
