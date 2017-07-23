package test.function;

import classifier.DocumentClass;
import classifier.kNNClassifier;
import model.DocumentVector;

import java.util.ArrayList;

/**
 * Created by Phuong Huynh on 5/28/2017.
 */
public class TestClassifier {

    public void testClassify(){
        kNNClassifier classifier = new kNNClassifier();
        classifier.performClassify();

        ArrayList<DocumentClass> documentClasses = classifier.getClasses();

        for (DocumentClass c : documentClasses){
            c.printName();
            for (DocumentVector d : c.getItems()){
                System.out.print("---");
                d.printName();
            }
        }

    }
}
