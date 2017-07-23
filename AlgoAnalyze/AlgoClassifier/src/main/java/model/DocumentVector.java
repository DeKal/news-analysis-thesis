package model;

/**
 * Created by Phuong Huynh on 5/27/2017.
 */
public class DocumentVector {
    private Document document = null;
    private Double[] vector = null;

    private DocumentVector(){
    }

    public DocumentVector(Document document, int vectorSize){
        this.document = document;
        vector = new Double[vectorSize];
    }

    public DocumentVector(Document document, Double vector[]){
        this.document = document;
        this.vector = vector;
    }

    public Double[] getVector(){
        return this.vector;
    }

    public Document getDocument(){
        return this.document;
    }

    public void printName(){
        System.out.println(document.getName());
    }

}
