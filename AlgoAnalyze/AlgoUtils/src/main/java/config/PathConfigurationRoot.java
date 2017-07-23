package config;

/**
 * Created by Phuong Huynh on 7/13/2017.
 */
public class PathConfigurationRoot {
    public static String root = System.getProperty("user.dir");
    public static String resultSVM = root + "\\senti_predict\\" + "predictSentiSVM.txt";
    public static String resultSentiWord = root + "\\senti_predict\\" + "predictSentiWord.txt";

    public static String original = root + "\\data\\" + "\\original\\";
    public static String data = root + "\\data\\" + "\\original\\" + "data_comment.txt";

    public static String training_comment = root + "\\data\\" + "training_comment.txt";
    public static String testing_comment = root + "\\data\\" + "testing_comment.txt";

    public static String folder = root + "\\data\\";
    public final static String SENTIWORD_RES = "data/SentiWord/";
}
