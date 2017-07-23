package classifier;

import model.DocumentVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Phuong Huynh on 5/27/2017.
 */
public final class kNNClassifier {
    private final double acceptScore = 0.40;
    private ArrayList<DocumentVector> data = null;
    private ArrayList<DocumentClass> classList = null;

    public void performClassify(){
        BaseVectorBuilder builder = new BaseVectorBuilder();
        data = builder.buildDataVector();
        classList = new ArrayList<DocumentClass>();

        int classID = 1;
        for (DocumentVector docVector : data){
            if (classList.isEmpty()){
                String className = "Class" + classID;
                DocumentClass docClass = new DocumentClass(className);
                docClass.addItem(docVector);

                classList.add(docClass);

                ++classID;
            }
            else{
                double majorityScore = 0.0;
                DocumentClass majorityClass = null;

                for (DocumentClass c : classList){
                    double score = calculateClassScore(docVector,c);
                    if (score > majorityScore){
                        majorityScore = score;
                        majorityClass = c;
                    }
                }

                if (majorityScore >= acceptScore){
                    majorityClass.addItem(docVector);
                }
                else{
                    String className = "Class" + classID;
                    DocumentClass docClass = new DocumentClass(className);
                    docClass.addItem(docVector);

                    classList.add(docClass);
                    ++classID;
                }
            }
        }



    }

    private double calculateClassScore(DocumentVector docVector, DocumentClass docClass){
        ArrayList<DocumentResult> resultList = new ArrayList<DocumentResult>();
        for (DocumentVector item : docClass.getItems()){
            double score = cosineSim(docVector,item);
            DocumentResult result = new DocumentResult(item,score);
            resultList.add(result);
        }

        Collections.sort(resultList,new DocumentResultComparator());
        return resultList.get(resultList.size()- 1).cosineSim;
    }

    private double cosineSim(DocumentVector a, DocumentVector b){
        Double[] vectorA = a.getVector();
        Double[] vectorB = b.getVector();
        int len;

        if ((len = vectorA.length) != vectorB.length){

            return 0.0;
        }

        double numerator = 0.0;
        double denominator = 1.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;
        for (int i = 0; i < len; ++i){
            numerator += vectorA[i]*vectorB[i];
            magnitudeA += Math.pow(vectorA[i],2);
            magnitudeB += Math.pow(vectorB[i],2);
        }

        denominator *= Math.sqrt(magnitudeA)*Math.sqrt(magnitudeB);

        return (numerator/denominator);
    }

    public ArrayList<DocumentClass> getClasses(){
        return this.classList;
    }

    private class DocumentResult {
        public DocumentVector document;
        public double cosineSim;

        public DocumentResult(DocumentVector document, double score){
            this.document = document;
            this.cosineSim = score;
        }
    }

    // Comparator for DocumentResult in ascending order
    private class DocumentResultComparator implements Comparator<DocumentResult> {

        public int compare(DocumentResult d1, DocumentResult d2) {
            return d1.cosineSim < d2.cosineSim ? -1 : d1.cosineSim == d2.cosineSim ? 0 : 1;
        }
    }
}