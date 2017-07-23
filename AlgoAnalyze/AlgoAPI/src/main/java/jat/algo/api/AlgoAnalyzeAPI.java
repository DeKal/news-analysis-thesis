package jat.algo.api;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import algoutils.storage.DataSetController;

@Component
public class AlgoAnalyzeAPI {
	private static final  DataSetController DataSetController = new DataSetController();
	
	public InputStream testGetDataStorage(){
		
		return DataSetController.getSentiWordTrainingSet();
	}
	public double getCommentSentiSVM(String content){
		double dc = Math.random();
		if (dc > 0.5)
			dc = 1;
		else
			dc = -1;
		
		return Math.random() * dc;
	}
	public double getCommentSentiVNWord(String content){
		double dc = Math.random();
		if (dc > 0.5)
			dc = 1;
		else
			dc = -1;
		
		return Math.random() * dc;
	}
}
