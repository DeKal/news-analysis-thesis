package model;

import java.util.LinkedList;

/**
 * Created by Phuong Huynh on 7/4/2017.
 */
public class CommentCollection {
    private static CommentCollection instance = null;
    private LinkedList<Comment> commentList;

    private CommentCollection(){

    }

    public static CommentCollection getInstance(){
        if (instance == null){
            instance = new CommentCollection();
        }

        return instance;
    }

    public void setCommentList(LinkedList<Comment> commentList){
        this.commentList = commentList;
    }

    public LinkedList<Comment> getCommentList(){
        return this.commentList;
    }

    public void destroy(){
        if (instance != null)
            return;

        instance.commentList = null;
        instance = null;
    }
}
