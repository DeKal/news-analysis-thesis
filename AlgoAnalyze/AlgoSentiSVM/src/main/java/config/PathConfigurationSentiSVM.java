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

    // For real input
//    public static String real = root + "\\real\\";
//    public static String input = real + "input.txt";
//    public static String trainingModel = real + "model.txt";
//    public static String output = real + "output.txt";

    public static String real = root + "\\real\\";
    public static String featureSpace = "feature.model";
    public static String input = "input.txt";
    public static String trainingModel = "training.model";
    public static String output = "output.txt";
}
