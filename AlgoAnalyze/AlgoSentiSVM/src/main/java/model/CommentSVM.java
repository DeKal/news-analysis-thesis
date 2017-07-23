package model;

import common.model.TermFrequency;
import vn.hus.nlp.tokenizer.VietTokenizer;

import java.util.LinkedList;

/**
 * Created by Phuong Huynh on 6/18/2017.
 */
public class CommentSVM {
    private int labelCode = -100;
    private Comment comment = null;
    private LinkedList<TermFrequency> termList = null;

    private CommentSVM() {
    }

    public CommentSVM(Comment comment){
        this.comment = comment;
        termList = new LinkedList<TermFrequency>();
        generateTermListFromComment();
        generateLabelCode();;
    }

    public CommentSVM(int label, Comment comment, LinkedList<TermFrequency> termList) {
        this.labelCode = label;
        this.comment = comment;
        this.termList = termList;
    }

    public void setLabelCode(int label) {
        this.labelCode = label;
    }

    public int getLabelCode(){
        return this.labelCode;
    }

    public void setTermList(LinkedList<TermFrequency> termList) {
        this.termList = termList;
    }

    public LinkedList<TermFrequency> getTermList(){
        return this.termList;
    }

    public void addTerm(TermFrequency t) {
        if (termList.contains(t)) {
            int index = termList.indexOf(t);
            termList.get(index).increaseFreq();
            return;
        }

        termList.add(t);
    }

    public void generateTermListFromComment() {
        if (comment == null) {
            return;
        }

        //String[] sentences = TokenHelper.tokenize(comment.getContent());
        String[] sentences = tokenize(comment.getContent());
        for (String sentence : sentences) {
            String terms[] = sentence.split(" ");
            for (String term : terms) {
                TermFrequency termFrequency = new TermFrequency(term.toLowerCase());
                addTerm(termFrequency);
            }
        }
    }

    public void generateLabelCode(){
        if (comment.getLabel().equals("#neg"))
            setLabelCode(-1);
        else if (comment.getLabel().equals("#neu"))
            setLabelCode(0);
        else if (comment.getLabel().equals("#pos"))
            setLabelCode(1);
        else
            setLabelCode(-100);

    }

    /*******COULD BE REMOVED *******/
    private static String[] tokenize(String sentence){
        VietTokenizer vietTokenizer = new VietTokenizer();
        return vietTokenizer.tokenize(sentence);
    }
}
