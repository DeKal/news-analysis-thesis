package test;

import utils.CommentExtractor;

/**
 * Created by Phuong Huynh on 6/25/2017.
 */
public class TestCommentExtractor {
    public void test() throws Exception {
        CommentExtractor.TextToComment(1);
        CommentExtractor.CommentToCommentSVMandFeature();
        CommentExtractor.CommentSVMToTrainingSet();
    }
}
