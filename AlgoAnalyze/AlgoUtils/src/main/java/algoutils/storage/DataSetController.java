package algoutils.storage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import config.PathConfigurationRoot;

public class DataSetController {
	
	public boolean getSentiWordTrainingSet(){
		ClassLoader classLoader = DataSetController.class.getClass().getClassLoader();
		File file = new File(classLoader.getResource("input.txt").getFile());
		String path = file.getAbsolutePath();
		
		System.out.println(path);
		
    	return true;
	}
	
	public File getSentiSVMTrainingSet(){
		ClassLoader classLoader = DataSetController.class.getClassLoader();
    	File file = new File(classLoader.getResource("data/input.txt").getFile());
    	return file;
	}
}
