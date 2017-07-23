package utils;

import config.PathConfigurationRoot;
import model.Comment;
import model.CommentCollection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Phuong Huynh on 7/4/2017.
 */
public class CommentExtractorSentiWord {
    private static CommentCollection mCommentCollection = CommentCollection.getInstance();

    public CommentExtractorSentiWord(){

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
            //path = PathConfigurationRoot.training_comment;
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

}
