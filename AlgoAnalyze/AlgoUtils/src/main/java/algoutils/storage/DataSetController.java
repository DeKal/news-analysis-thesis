package algoutils.storage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import config.PathConfigurationRoot;

public class DataSetController {
	
	public InputStream getSentiWordTrainingSet(){
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream lStream = classLoader.getResourceAsStream(PathConfigurationRoot.SENTIWORD_RES + "input.txt");
		
    	return lStream;
	}
	
	public File getSentiSVMTrainingSet(){
		ClassLoader classLoader = DataSetController.class.getClassLoader();
    	File file = new File(classLoader.getResource("data/input.txt").getFile());
    	return file;
	}
}
