package model;

import common.io.FileIO;
import config.PathConfigurationSentiSVM;

import java.io.*;
import java.util.Map;

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

    public void loadFeatureSpace() throws IOException {
        SVMFeature feature = new SVMFeature();

        File input = new File("src/main/resources/" + PathConfigurationSentiSVM.featureSpace);
        String path =  input.getAbsolutePath();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            int lineNum = 0;

            String line;
            while ((line = br.readLine()) != null) {
                ++lineNum;

                // check if the line is valid?
                String data[] = line.split(" ");
                if (data.length != 1)
                    throw new Exception("Invalid line: " + lineNum);

                String f = data[0];
                feature.addFeature(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }

        setFeatureSpace(feature);
    }

    public void saveFeatureSpace() throws IOException {
        SVMFeature feature = getFeatureSpace();

        File input = new File("src/main/resources/" + PathConfigurationSentiSVM.featureSpace);
        String path =  input.getAbsolutePath();

        if (feature != null) {
            FileIO.createWriter(path);

            for (Map.Entry<String, Integer> entry : feature.getFeatureList().entrySet()) {
                FileIO.writeln(entry.getKey());
            }

            FileIO.closeWriter();
        }
    }
}
