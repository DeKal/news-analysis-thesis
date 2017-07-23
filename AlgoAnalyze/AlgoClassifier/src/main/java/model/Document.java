package model;

import java.util.TreeMap;

/**
 * Created by Phuong Huynh on 5/24/2017.
 */
public class Document {
    private String name = null;
    private String url = null;
    private String id = null;
    private TreeMap<String, Integer> termList = null;

    public Document(){
    }

    public Document(String name, String id){
        this.name = name;
        this.id = id;
        this.termList = new TreeMap<String, Integer>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setID(String ID){
        this.id = id;
    }

    public void setURL(String url){
        this.url = url;
    }

    public void setListTerm(TreeMap listTerm){
        this.termList = listTerm;
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.id;
    }

    public String getURL(){
        return this.url;
    }

    public TreeMap<String, Integer> getTermList(){
        return this.termList;
    }
}
