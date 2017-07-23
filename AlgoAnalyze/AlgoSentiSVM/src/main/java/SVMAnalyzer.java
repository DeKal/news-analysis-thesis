import config.PathConfigurationSentiSVM;

import java.io.IOException;

/**
 * Created by Phuong Huynh on 6/25/2017.
 */
public class SVMAnalyzer {
/*
    private static String mInputTrain = System.getProperty("user.dir") + "\\save\\training_data.txt";
    private static String mInputModel = System.getProperty("user.dir") + "\\save\\training_data.txt.model";
    private static String mInputAnalyze = System.getProperty("user.dir") + "\\save\\test.txt";
*/
    /*
    private static String mInputTrain = System.getProperty("user.dir") + "\\save\\training_test.txt";
    private static String mInputModel = System.getProperty("user.dir") + "\\save\\training_test.txt.model";
    private static String mInputAnalyze = System.getProperty("user.dir") + "\\save\\test.txt";
*/
    public static void train(){
        String[] arg = new String[5];
        arg[0] = "-s";
        arg[1] = "0";
        arg[2] = "-c";
        arg[3] = "90.0";
        arg[4] = PathConfigurationSentiSVM.training_set;

        try {
            svm_train.main(arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test(){
        String[] arg = new String[3];
        /*
        arg[0] = mInputAnalyze;
        arg[1] = mInputModel;
        arg[2] = "test.predict.txt";
        */

        arg[0] = PathConfigurationSentiSVM.testing_set;
        arg[1] = PathConfigurationSentiSVM.training_model;
        arg[2] = PathConfigurationSentiSVM.predict;

        try {
            svm_predict.main(arg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}