package model;

import java.util.LinkedList;

/**
 * Created by Phuong Huynh on 6/22/2017.
 */
public class CommentSVMCollection {
    private static CommentSVMCollection instance = null;
    private LinkedList<CommentSVM> commentSVMList;

    private CommentSVMCollection(){

    }

    public static CommentSVMCollection getInstance(){
        if (instance == null){
            instance = new CommentSVMCollection();
        }

        return instance;
    }

    public void setCommentSVMList(LinkedList<CommentSVM> commentSVMList){
        this.commentSVMList = commentSVMList;
    }

    public LinkedList<CommentSVM> getCommentSVMList(){
        return this.commentSVMList;
    }


}
