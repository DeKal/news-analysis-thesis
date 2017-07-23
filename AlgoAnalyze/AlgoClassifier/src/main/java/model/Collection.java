package model;

import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 5/24/2017.
 */
public final class Collection {
    private static Collection instance = null;
    private ArrayList<Document> items;

    private Collection(){

    }

    public static Collection getInstance(){
        if (instance == null){
            instance = new Collection();
        }

        return instance;
    }

    public void setItems(ArrayList<Document> items){
        this.items = items;
    }

    public ArrayList<Document> getItems(){
        return this.items;
    }
}
