package jat.algo.api;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataContentHandler;

import org.springframework.stereotype.Component;

import algoutils.storage.DataSetController;
import main.AlgoSentiSVMInterface;
import main.AlgoSentiWord;

@Component
public class AlgoAnalyzeAPI {
	public static final  DataSetController DataSetController = new DataSetController();
	
	public int getCommentSentiSVM(String content) throws Exception{
		Class<?> algoSentiSVMClass = Class.forName("AlgoSentiSVM");
		AlgoSentiSVMInterface api = (AlgoSentiSVMInterface) algoSentiSVMClass.newInstance();
	
		return api.predict(content, DataSetController.getSentiSVMFeatureSet(),
							DataSetController.getSentiSVMInputSet(), DataSetController.getSentiSVMTrainSet(),
							DataSetController.getSentiSVMOutPutSet(), DataSetController.getProperty()
							);			
	}
	
	public int getCommentSentiVNWord(String content) throws IOException{
		return AlgoSentiWord.predict(content, DataSetController.getSentiWordDictionary(), DataSetController.getProperty());
	}
	
	public static void main(String args[]) throws Exception{
		AlgoAnalyzeAPI a = new AlgoAnalyzeAPI();
		
		/*
		int c = a.getCommentSentiVNWord("mình thích chương trình này lắm");
		System.out.println(c);
		
		a.getCommentSentiSVM("vui thật");
		*/
	
	}
}
