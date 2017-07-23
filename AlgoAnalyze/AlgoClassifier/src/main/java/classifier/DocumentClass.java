package classifier;

import model.DocumentVector;

import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 5/27/2017.
 */
public class DocumentClass {
    private String name;
    private ArrayList<DocumentVector> items;

    public DocumentClass(String name){
        this.name = name;
        this.items = new ArrayList<DocumentVector>();
    }

    public DocumentClass(String name, ArrayList<DocumentVector> items){
        this.name = name;
        this.items = items;
    }

    public boolean addItem(DocumentVector doc){
        if (this.items == null)
            return false;

        this.items.add(doc);
        return true;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<DocumentVector> getItems(){
        return this.items;
    }

    public void printName(){
        System.out.println(this.name);
    }

}
