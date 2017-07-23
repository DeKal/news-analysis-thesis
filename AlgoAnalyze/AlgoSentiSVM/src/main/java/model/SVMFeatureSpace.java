package model;

/**
 * Created by Phuong Huynh on 6/22/2017.
 */
public class SVMFeatureSpace {
    private static SVMFeatureSpace instance = null;
    private SVMFeature featureSpace = null;


    private SVMFeatureSpace(){
        //featureSpace = new SVMFeature();
    }

    public static SVMFeatureSpace getInstance(){
        if (instance == null){
            instance = new SVMFeatureSpace();
        }

        return instance;
    }

    public void setFeatureSpace(SVMFeature featureSpace){
        this.featureSpace = featureSpace;
    }

    public SVMFeature getFeatureSpace(){
        return this.featureSpace;
    }

    public static void destroy(){
        if (instance != null) {
            instance = null;
        }
    }
}
