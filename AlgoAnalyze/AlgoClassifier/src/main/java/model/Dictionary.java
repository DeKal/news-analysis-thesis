package model;

import io.FileIO;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Phuong Huynh on 5/24/2017.
 */
public final class Dictionary {
    private static Dictionary instance = null;
    private ArrayList<TermFrequency> termList = null;
    private TreeMap<String, DocIndex> dictionary = null;

    private Dictionary(){
        termList = new ArrayList<TermFrequency>();
        dictionary = new TreeMap<String, DocIndex>();
    }

    public static Dictionary getInstance(){
        if (instance == null){
            instance = new Dictionary();
        }

        return instance;
    }

    public void setDictionary(TreeMap<String, DocIndex> dictionary){
        this.dictionary = dictionary;
    }

    public TreeMap<String, DocIndex> getDictionary(){
        return this.dictionary;
    }

    public void addTerm(String term, Document doc){
        TermFrequency mTerm = new TermFrequency(term);
        if (termList.contains(mTerm)) {
            int index = termList.indexOf(mTerm);
            TermFrequency modifiedTerm = termList.get(index);
            modifiedTerm.increaseFreq();

            termList.remove(index);
            termList.add(modifiedTerm);

            DocIndex modifiedDoc = dictionary.get(term);
            modifiedDoc.addDoc(doc);
            dictionary.put(term,modifiedDoc);
        }
        else {
            termList.add(mTerm);
            DocIndex modifiedDoc = new DocIndex(doc);
            dictionary.put(term,modifiedDoc);

        }
    }

    public void exportDict(){
        String path = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\Thesis_DocumentProcessing\\out_dict\\dict.txt";
        FileIO.createWriter(path);

        for (Map.Entry<String,DocIndex> entry : dictionary.entrySet()){
            String term = entry.getKey().toString();
            FileIO.write(term + '\t');

            DocIndex index = entry.getValue();
            String[] docs = (String[]) index.getDocIDList();
            FileIO.write(docs);
            FileIO.write("\n");
        }

        FileIO.closeWriter();
    }

    public TreeSet<String> buildVectorSpace(){
        TreeSet<String> vectorSpace = new TreeSet<String>();
        for (TermFrequency t : termList){
            String term = t.getTerm();
            vectorSpace.add(term);
        }

        //String[] vectorSpaceArray = (String[]) vectorSpace.toArray(new String[vectorSpace.size()]);
        return vectorSpace;
    }

    public boolean searchTerm(TermFrequency t){
        return false;
    }

    /*
    //Case 1
    */
    /*
    public void addTerm(String term, Document doc){
        for (TermFrequency mTerm : termList){
            if (mTerm.getTerm().equals(term)){
                mTerm.increaseFreq();

            }

        }
    }
    */

}
