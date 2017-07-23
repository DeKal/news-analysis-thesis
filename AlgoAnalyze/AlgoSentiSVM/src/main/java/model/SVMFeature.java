package model;

import common.helper.TokenHelper;

import java.util.LinkedHashMap;

/**
 * Created by Phuong Huynh on 6/24/2017.
 */
public class SVMFeature {
    private int label = 1; // feature "label" = index of 1
    private LinkedHashMap<String,Integer> featureList = null;

    public SVMFeature(){
        featureList = new LinkedHashMap<String, Integer>();
    }

    public void addFeatureFromComment(Comment comment){
        if (comment == null) {
            return;
        }

        String[] sentences = TokenHelper.tokenize(comment.getContent());
        for (String sentence : sentences) {
            String terms[] = sentence.split(" ");
            for (String term : terms) {
                addFeature(term);
            }
        }
    }

    public  void addFeature(String f){
        String t = f.toLowerCase();
        if (!featureList.containsKey(t)) {
            int index = featureList.size() - 1;
            featureList.put(t, index);
        }
    }

    public int getIndexOfFeature(String f){
        String t = f.toLowerCase();
        if (featureList.containsKey(t))
            return featureList.get(t);

        return -1;
    }
}
