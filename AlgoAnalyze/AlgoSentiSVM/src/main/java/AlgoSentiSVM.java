import common.console.ConsoleOutputCapturer;
import common.io.FileIO;
import config.PathConfigurationRoot;
import config.PathConfigurationSentiSVM;
import org.apache.commons.io.FilenameUtils;
import utils.CommentExtractor;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 6/18/2017.
 */
public class AlgoSentiSVM {
    public static void main(String[] args) throws Exception {

        /*
        TestCommentExtractor testCommentExtractor = new TestCommentExtractor();
        try {
            testCommentExtractor.test();
        } catch (Exception e) {
            e.printStackTrace();
        }


        SVMAnalyzer.train();
        SVMAnalyzer.test();
        */

        //random();
        //normal();
        allDataSet();
        //oneDataSet("0887");
    }

    public static void random() throws Exception {
        DataSetCreator.createDataSet();

        ProcessRunner processRunner = new ProcessRunner();
        processRunner.testTrain();
        processRunner.testTest();
    }

    public static void normal() throws Exception {
        ProcessRunner processRunner = new ProcessRunner();
        processRunner.testTrain();
        processRunner.testTest();
    }

    public static void allDataSet() throws Exception {
        ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
        String data_path = PathConfigurationRoot.data;
        String original_path = PathConfigurationRoot.original;

        ArrayList<String> trainingFileNames = new ArrayList<String>();
        ArrayList<String> testingFileNames = new ArrayList<String>();

        File original = new File(original_path);
        File[] files = original.listFiles();
        for (File f : files){
            if (f.isFile()) {
                if (f.getName().endsWith(".train"))
                    trainingFileNames.add(f.getName());

                if (f.getName().endsWith(".test"))
                    testingFileNames.add(f.getName());
            }
        }

        ArrayList<String> records = new ArrayList<String>();
        for (String name_train : trainingFileNames){
            String name_test = FilenameUtils.removeExtension(name_train) + ".test";
            if (testingFileNames.contains(name_test)){
                System.out.println("#######" + name_train);

                /*#####################################################*/
                /*
                * This function must be called for clearing SVM Features Space for every new data set.
                **/
                CommentExtractor.destroySVMFeatureSpace();
                /*#####################################################*/

                String trainPath = PathConfigurationRoot.original + name_train;
                String testPath = PathConfigurationRoot.original + name_test;
                DataSetCreator.exportTrainingSet(trainPath);
                DataSetCreator.exportTestingSet(testPath);

                String record = "";
                String s = "---------------------------------------------------\n";
                s += name_train + "\t" +name_test + "\n";
                s += "---------------------------------------------------\n";

                consoleOutputCapturer.start();
                normal();
                s += consoleOutputCapturer.stop();
                record = s;
                records.add(record);
            }
        }

        FileIO.createWriter(PathConfigurationSentiSVM.sumary);
        for (String r : records){
            FileIO.writeln(r);
        }
        FileIO.closeWriter();
    }

    public static void oneDataSet(String name) throws Exception {
        ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();
        String data_path = PathConfigurationRoot.data;
        String original_path = PathConfigurationRoot.original;

        ArrayList<String> trainingFileNames = new ArrayList<String>();
        ArrayList<String> testingFileNames = new ArrayList<String>();

        File original = new File(original_path);
        File[] files = original.listFiles();
        for (File f : files){
            if (f.isFile()) {
                if (f.getName().endsWith(".train"))
                    trainingFileNames.add(f.getName());

                if (f.getName().endsWith(".test"))
                    testingFileNames.add(f.getName());
            }
        }

        ArrayList<String> records = new ArrayList<String>();
        if (trainingFileNames.contains(name + ".train")){
            String name_train = name + ".train";
            String name_test = FilenameUtils.removeExtension(name_train) + ".test";
            if (testingFileNames.contains(name_test)){
                System.out.println("#######" + name_train);

                String trainPath = PathConfigurationRoot.original + name_train;
                String testPath = PathConfigurationRoot.original + name_test;
                DataSetCreator.exportTrainingSet(trainPath);
                DataSetCreator.exportTestingSet(testPath);

                String record = "";
                String s = "---------------------------------------------------\n";
                s += name_train + "\t" +name_test + "\n";
                s += "---------------------------------------------------\n";

                consoleOutputCapturer.start();
                normal();
                s += consoleOutputCapturer.stop();
                record = s;
                records.add(record);
            }
        }

        FileIO.createWriter(PathConfigurationSentiSVM.sumary);
        for (String r : records){
            FileIO.writeln(r);
        }
        FileIO.closeWriter();
    }
}
