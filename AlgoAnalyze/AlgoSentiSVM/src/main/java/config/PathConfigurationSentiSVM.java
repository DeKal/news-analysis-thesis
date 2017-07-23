package config;

/**
 * Created by Phuong Huynh on 6/27/2017.
 */
public final class PathConfigurationSentiSVM {
    public static String root = System.getProperty("user.dir");
    public static String training_comment = root + "\\data\\" + "training_comment.txt";
    public static String testing_comment = root + "\\data\\" + "testing_comment.txt";

    public static String training_set = root + "\\save\\" + "training_set.txt";
    public static String testing_set = root + "\\save\\" + "testing_set.txt";

    public static String training_model = root + "\\save\\" + "training_set.txt.model";
    public static String testing_model = root + "\\save\\" + "testing_set.txt.model";

    public static String predict = root + "\\senti_predict\\" + "predictSentiSVM.txt";

    public static String sumary = root + "\\senti_predict\\" + "sumarySentiSVM.txt";
}
