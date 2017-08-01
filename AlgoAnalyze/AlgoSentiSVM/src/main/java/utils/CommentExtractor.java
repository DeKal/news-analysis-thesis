package utils;

import common.io.FileIO;
import common.model.TermFrequency;
import config.PathConfigurationRoot;
import config.PathConfigurationSentiSVM;
import model.*;
import vn.hus.nlp.tokenizer.VietTokenizer;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Phuong Huynh on 6/18/2017.
 */
public class CommentExtractor {
    //private static String mPath = System.getProperty("user.dir") + "\\data\\" + "comment_set.txt";
    private static CommentCollection mCommentCollection = CommentCollection.getInstance();
    private static CommentSVMCollection mCommentSVMCollection = CommentSVMCollection.getInstance();

    /*Note: this object is stored and used Globally*/
    private static SVMFeatureSpace mSVMFeatureSpace = SVMFeatureSpace.getInstance();

    public CommentExtractor(){

    }

    public static void TextToComment(int mode) throws Exception {
        /**
         * Each mode has its own input path
         * '1' = training Mode
         * '2' = testing Mode
         * '3' and other = predicting Mode
         * */
        String path = null;

        if (mode == 1){
            path = PathConfigurationRoot.training_comment;
        }
        else if (mode == 2){
            path = PathConfigurationRoot.testing_comment;
        }
        else
            return;

        LinkedList<Comment> commentList = new LinkedList<Comment>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            int lineNum = 0;

            String line;
            while ((line = br.readLine()) != null) {
                ++lineNum;

                if (line.trim().startsWith("#")) {
                    String data[] = line.split("\t");
                    String label = data[0];

                    // check if the line is valid?
                    if (data.length != 2)
                        throw new Exception("Invalid line: " + lineNum);

                    Comment comment = new Comment(data[0],data[1]);
                    commentList.add(comment);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }

        mCommentCollection.setCommentList(commentList);
    }

    public static void TextToComment(String content) throws Exception {
        LinkedList<Comment> commentList = new LinkedList<Comment>();

        Comment comment = new Comment("#neu",content);
        commentList.add(comment);

        mCommentCollection.setCommentList(commentList);
    }

    /** This function is used for creating CommentSVMCollection.
     ** then program will use the collection for building SVM training set.
     **/
    public static void CommentToCommentSVMandFeature() {
        LinkedList<Comment> listOfComment = mCommentCollection.getCommentList();
        LinkedList<CommentSVM> listOfCommentSVM = new LinkedList<CommentSVM>();

        //SVMFeature feature = new SVMFeature();
        SVMFeature feature = null;
        boolean newFeatureSpace = false;
        if (mSVMFeatureSpace.getFeatureSpace() == null) {
            feature = new SVMFeature();
            newFeatureSpace = true;
        }
        else
            feature = mSVMFeatureSpace.getFeatureSpace();

        for (Comment comment : listOfComment){

            CommentSVM commentSVM = new CommentSVM(comment);
            listOfCommentSVM.add(commentSVM);

            feature.addFeatureFromComment(comment);
        }

        mCommentSVMCollection.setCommentSVMList(listOfCommentSVM);
        if (newFeatureSpace)
            mSVMFeatureSpace.setFeatureSpace(feature);
    }

    public static void CommentSVMToTrainingSet(){
        /*
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "\\save";
        String name = "training_data.txt";

        String fullPath = path + "\\" + name;
        */

        String fullPath = PathConfigurationSentiSVM.training_set;

        FileIO.createWriter(fullPath);

        SVMFeature feature = mSVMFeatureSpace.getFeatureSpace();
        for (CommentSVM commentSVM : mCommentSVMCollection.getCommentSVMList()){
            String label = String.valueOf(commentSVM.getLabelCode());
            FileIO.write(label + " ");

            LinkedList<TermFrequency> termList = commentSVM.getTermList();
            for (TermFrequency t : termList){
                // Get index of the feature
                String term = t.getTerm();
                int index = feature.getIndexOfFeature(term);
                // Get value of the feature
                int value = t.getFrequency();
                FileIO.write(String.valueOf(index) + ":" + String.valueOf(value) + " ");
            }

            // At the end of comment, insert newline for another comment
            FileIO.writeln("");
        }

        FileIO.closeWriter();
    }

    public static void CommentSVMToTestingSet(){
        /*
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "\\save";
        String name = "testing_data.txt";

        String fullPath = path + "\\" + name;
        */

        String fullPath = PathConfigurationSentiSVM.testing_set;

        FileIO.createWriter(fullPath);

        SVMFeature feature = mSVMFeatureSpace.getFeatureSpace();
        for (CommentSVM commentSVM : mCommentSVMCollection.getCommentSVMList()){
            String label = String.valueOf(commentSVM.getLabelCode());
            FileIO.write(label + " ");

            LinkedList<TermFrequency> termList = commentSVM.getTermList();
            for (TermFrequency t : termList){
                // Get index of the feature
                String term = t.getTerm();
                int index = feature.getIndexOfFeature(term);
                // Get value of the feature
                int value = t.getFrequency();
                FileIO.write(String.valueOf(index) + ":" + String.valueOf(value) + " ");
            }

            // At the end of comment, insert newline for another comment
            FileIO.writeln("");
        }

        FileIO.closeWriter();
    }

    public static void CommentSVMToPredictSet(){
        /*
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "\\save";
        String name = "testing_data.txt";

        String fullPath = path + "\\" + name;
        */

        File resourcesDirectory = new File("src/main/resources/" + PathConfigurationSentiSVM.input);
        String fullPath =  resourcesDirectory.getAbsolutePath();

        FileIO.createWriter(fullPath);

        SVMFeature feature = mSVMFeatureSpace.getFeatureSpace();
        for (CommentSVM commentSVM : mCommentSVMCollection.getCommentSVMList()){
            String label = String.valueOf(commentSVM.getLabelCode());
            FileIO.write(label + " ");

            LinkedList<TermFrequency> termList = commentSVM.getTermList();
            for (TermFrequency t : termList){
                // Get index of the feature
                String term = t.getTerm();
                int index = feature.getIndexOfFeature(term);
                // Get value of the feature
                int value = t.getFrequency();
                FileIO.write(String.valueOf(index) + ":" + String.valueOf(value) + " ");
            }

            // At the end of comment, insert newline for another comment
            FileIO.writeln("");
        }

        FileIO.closeWriter();
    }

    public static void destroySVMFeatureSpace(){
        SVMFeatureSpace.destroy();
        mSVMFeatureSpace = SVMFeatureSpace.getInstance();
    }

    public static void loadSVMFeatureSpace() throws IOException {
        mSVMFeatureSpace.loadFeatureSpace();
    }

    public static void saveSVMFeatureSpace() throws IOException {
        mSVMFeatureSpace.saveFeatureSpace();
    }

    /*******COULD BE REMOVED *******/
    private static String[] tokenize(String sentence){
        VietTokenizer vietTokenizer = new VietTokenizer();
        return vietTokenizer.tokenize(sentence);
    }
}