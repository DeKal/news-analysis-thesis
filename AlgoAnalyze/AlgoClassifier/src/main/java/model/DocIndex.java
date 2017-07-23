package model;

import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 5/24/2017.
 */
public class DocIndex {
    private ArrayList<Document> docList = null;

    public DocIndex(){
        docList = new ArrayList<Document>();
    }

    public DocIndex(Document doc){
        docList = new ArrayList<Document>();
        docList.add(doc);
    }

    public void addDoc(Document doc){
        removeDoc(doc);
        this.docList.add(doc);
    }

    public void removeDoc(Document doc){
        if (this.docList.contains(doc))
            this.docList.remove(doc);
    }

    public ArrayList<Document> getDocList(){
        if (!this.docList.isEmpty())
            return this.docList;

        return null;
    }

    public String[] getDocIDList(){
        ArrayList<Document> docList = getDocList();
        String[] docs = new String[docList.size()];
        int i = 0;
        for (Document d : docList){
            String doc = d.getID();
            docs[i] = doc;
            ++i;
        }

        return docs;
    }

    public Document get(int index){
        if (this.docList != null && index < docList.size())
            return this.docList.get(index);

        return null;
    }

}
