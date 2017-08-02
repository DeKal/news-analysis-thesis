package main;

public interface AlgoSentiSVMInterface {
	//public int predict(String content) throws Exception;

	//public int predict(String content, String featureSpacePath, String inputSetPath, String trainingPath, String outputSetPath)
	//		throws Exception;

	int predict(String content, String featureSpacePath, String inputSetPath, String trainingPath, String outputSetPath,
			String PropertyPath) throws Exception;
}
