package model;

/**
 * Created by Phuong Huynh on 7/4/2017.
 */
public class Comment {
    private String label;
    private String content;

    private Comment(){};

    public Comment(String label, String content){
        this.label = label;
        this.content = content;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getLabel(){
        return this.label;
    }

    public String getContent(){
        return this.content;
    }

}
