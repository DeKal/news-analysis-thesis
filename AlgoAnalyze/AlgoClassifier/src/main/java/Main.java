import model.Collection;
import model.Dictionary;
import test.function.TestClassifier;
import test.function.TestFileHandler;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class Main {
    private Collection mCollection = Collection.getInstance();
    private Dictionary mDic = Dictionary.getInstance();

    public void runTest(){
        String inputDirectory = "C:\\Users\\Phuong Huynh\\Desktop\\Thesis_Token\\in";
        String outputDirectory = "out_token";

        VietTokenizer viTokenizer = new VietTokenizer();
        viTokenizer.tokenizeDirectory(inputDirectory,outputDirectory);

        String inputFolder = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\Thesis_DocumentProcessing\\out_token";
        TestFileHandler testFileHandler = new TestFileHandler();
        testFileHandler.runFileHandler(inputFolder,mCollection,mDic);

        TestClassifier testClassifier = new TestClassifier();
        testClassifier.testClassify();
    }

    public static void main(String[] args) {
        System.out.println("Test");

        Main main = new Main();
        main.runTest();

        //FileHandler.createInvertedIndex(inputDirectory);

    }
}
