import common.io.FileIO;
import config.PathConfigurationSentiWord;
import model.Comment;
import model.CommentCollection;
import utils.CommentExtractorSentiWord;

/**
 * Created by Phuong Huynh on 7/4/2017.
 */
public class ProcessRunner {
    public String predict() throws Exception {
        //String pathDict = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\BizSentiWord\\data\\VietSentiWordnet_Test.txt";
        String pathDict = PathConfigurationSentiWord.dictionary_source;
        String pathData = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\BizSentiWord\\data\\input.txt";

        DictHandler dictHandler = new DictHandler(pathDict);
        dictHandler.exportDict();

        CommentExtractorSentiWord.TextToComment(2);

        CommentCollection commentCollection = CommentCollection.getInstance();

        String fullPath = PathConfigurationSentiWord.predict;
        FileIO.createWriter(fullPath);

        SentiAnalyzer analyzer = new SentiAnalyzer(dictHandler.getDict());
        int correct = 0;
        int total = commentCollection.getCommentList().size();
        for (Comment c : commentCollection.getCommentList()) {
            double score = analyzer.analyze(c.getContent());
            String label = "";
            /*
            if (score >= -0.3 && score <= 0.3) {
                label = "#neu";
                FileIO.write("0.0");
            }
            else if (score < -0.3 && score >= -1.0) {
                label = "#neg";
                FileIO.write("-1.0");
            }
            else if (score > 0.3 && score <= 1) {
                label = "#pos";
                FileIO.write("1.0");
            }
            else if (Double.isNaN(score)){
                label = "#neu";
                FileIO.write("0.0");
            }
            */
            if (score < 0.25 && score > -0.25) {
                label = "#neu";
                FileIO.write("0.0");
            }
            else if (score <= -0.25 && score >= -1.0) {
                label = "#neg";
                FileIO.write("-1.0");
            }
            else if (score > 0.25 && score <= 1) {
                label = "#pos";
                FileIO.write("1.0");
            }
            else if (Double.isNaN(score)){
                label = "#neu";
                FileIO.write("0.0");
            }
            else {
                FileIO.write(String.valueOf(score));
            }

            if (label.equals(c.getLabel())){
                ++correct;
            }

            FileIO.writeln("");
        }

        double p = ( (double) correct /(double) total) *100;
        String stringP = p + "%";
        FileIO.write("### p = " + stringP);
        FileIO.closeWriter();

        return stringP;
    }

}
