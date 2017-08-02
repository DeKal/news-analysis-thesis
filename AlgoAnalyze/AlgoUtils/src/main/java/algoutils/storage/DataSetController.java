package algoutils.storage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import config.PathConfigurationRoot;

public class DataSetController {
	private final static String ROOT_DATA_SVM_INPUT = "data/SentiSVM/input.txt";
	private final static String ROOT_DATA_SVM_TRAINMODEL = "data/SentiSVM/training.model";
	private final static String ROOT_DATA_SVM_OUTPUT = "data/SentiSVM/output.txt";
	private final static String ROOT_DATA_SVM_FEATUREMODEL = "data/SentiSVM/feature.model";

	
	private final static String ROOT_DATA_WORD = "/data/SentiWord/VietSentiWordnet_ver1.0.txt";
	
	private final static String ROOT_DATA_PROPERTY = "/data/SentiWord/tokenizer.properties";
	
	public String getSentiWordDictionary(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(ROOT_DATA_WORD).getPath());
		
		String dictPath = file.getAbsolutePath();
		dictPath = dictPath.replace("%20", " ");
		dictPath = dictPath.replace("\\", "/");
				
		return dictPath;
	}
	
	public String getProperty(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(ROOT_DATA_PROPERTY).getPath());
		
		String dictPropertyPath = file.getAbsolutePath();
		dictPropertyPath = dictPropertyPath.replace("%20", " ");
		dictPropertyPath = dictPropertyPath.replace("\\", "/");
		
		System.out.println(dictPropertyPath);
		return dictPropertyPath;
	}
	
	
	public String getSentiSVMInputSet(){
		ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource(ROOT_DATA_SVM_INPUT).getPath());

    	String inputPath = file.getAbsolutePath();
    	inputPath = inputPath.replace("%20", " ");
    	inputPath = inputPath.replace("\\", "/");
    	
		return inputPath;
	}
	
	public String getSentiSVMTrainSet(){
		ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource(ROOT_DATA_SVM_TRAINMODEL).getPath());

    	String trainPath = file.getAbsolutePath();
    	trainPath = trainPath.replace("%20", " ");
    	trainPath = trainPath.replace("\\", "/");
    	
		return trainPath;
	}
	
	public String getSentiSVMOutPutSet(){
		ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource(ROOT_DATA_SVM_OUTPUT).getPath());

    	String outputPath = file.getAbsolutePath();
    	outputPath = outputPath.replace("%20", " ");
    	outputPath = outputPath.replace("\\", "/");
    	
		return outputPath;
	}
	
	public String getSentiSVMFeatureSet(){
		ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource(ROOT_DATA_SVM_FEATUREMODEL).getPath());

    	String fearurePath = file.getAbsolutePath();
    	fearurePath = fearurePath.replace("%20", " ");
    	fearurePath = fearurePath.replace("\\", "/");
    	
		return fearurePath;
	}
	
}
