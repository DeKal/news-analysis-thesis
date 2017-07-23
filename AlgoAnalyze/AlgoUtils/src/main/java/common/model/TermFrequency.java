package common.model;

import java.util.Objects;

/**
 * Created by Phuong Huynh on 5/24/2017.
 */
public class TermFrequency {
    private String term = null;
    private int frequency = 0;

    public TermFrequency(){
    }

    public TermFrequency(String term){
        this.term = term;
        this.frequency = 1;
    }

    public TermFrequency(String term, int frequency){
        this.term = term;
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof TermFrequency)){
            return false;
        }

        TermFrequency object = (TermFrequency) o;

        return this.term.equals(object.term);
    }

    @Override
    public int hashCode(){
        return Objects.hash(term);
    }

    public void increaseFreq(){
        ++this.frequency;
    }

    public void setTerm(String t){
        this.term = t;
        this.frequency = 1;
    }

    public void setFrequency(int f){
        this.frequency = f;
    }

    public String getTerm(){
        return this.term;
    }

    public int getFrequency(){
        return this.frequency;
    }
}
