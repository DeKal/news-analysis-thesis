package classifier;

import model.Collection;
import model.Dictionary;
import model.Document;
import model.DocumentVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Phuong Huynh on 5/27/2017.
 */
public class BaseVectorBuilder {
    Dictionary mDictionary = null;
    Collection mCollection = null;

    public BaseVectorBuilder(){
        mDictionary = Dictionary.getInstance();
        mCollection = Collection.getInstance();
    }

    public ArrayList<DocumentVector> buildDataVector(){
        ArrayList<DocumentVector> dataVector = new ArrayList<DocumentVector>();
        ArrayList<Document> documents = mCollection.getItems();
        Set<String> termsSpace = mDictionary.buildVectorSpace();

        for (Document doc : documents) {
            Double[] vector = new Double[termsSpace.size()];
            Arrays.fill(vector,0.0);
            int index = 0;

            for (String term: termsSpace){
                if (doc.getTermList().containsKey(term)){
                    int value = doc.getTermList().get(term);
                    int pos = index;

                    vector[pos] = (double) value;
                }

                ++index;
            }

            DocumentVector documentVector = new DocumentVector(doc,vector);
            dataVector.add(documentVector);
        }

        return dataVector;
    }


}
