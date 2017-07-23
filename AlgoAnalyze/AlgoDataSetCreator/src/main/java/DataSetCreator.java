import common.io.FileIO;
import config.PathConfigurationRoot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Phuong Huynh on 7/15/2017.
 */
public class DataSetCreator {

    static private ArrayList<String> data = new ArrayList<String>();

    static public void createDataSet() throws IOException {
        resetData();

        String data_path = PathConfigurationRoot.data;
        String original_path = PathConfigurationRoot.original;

        /* Read original data file*/
        String line = "";
        FileIO.createReader(data_path);
        while ((line = FileIO.readLine()) != null){
            if (line.trim().startsWith("#"))
                data.add(line);
        }
        FileIO.closeReader();

        /* computing name of training files */
        int numberTrainFile = 0;
        File original = new File(original_path);
        File[] files = original.listFiles();
        for (File f : files){
            if (f.isFile())
                if (f.getName().endsWith(".train"))
                    ++ numberTrainFile;
        }

        ++ numberTrainFile;
        String temp = "";
        if (numberTrainFile > 0 && numberTrainFile < 10)
            temp = "00" + numberTrainFile;
        else if (numberTrainFile >= 10 && numberTrainFile < 100)
            temp = "0" + numberTrainFile;
        else if (numberTrainFile >= 100)
            temp += numberTrainFile;
        String pathTrainFile = PathConfigurationRoot.original + temp + ".train";
        String pathTestFile = PathConfigurationRoot.original + temp + ".test";

        /*Generate testing set*/
        FileIO.createWriter(pathTestFile);
        for (int i = 0; i < 100; ++i) {
            line = randomLine();
            FileIO.writeln(line);
        }
        FileIO.closeWriter();

        /*Generate training set*/
        FileIO.createWriter(pathTrainFile);
        for (String s : data){
            FileIO.writeln(s);
        }
        FileIO.closeWriter();

        exportTrainingSet(pathTrainFile);
        exportTestingSet(pathTestFile);
    }

    static public void exportListDataSet(){


    }

    static public void exportTrainingSet(String pathName){
        File from = new File(pathName);
        //File to = new File(PathConfigurationRoot.folder + "\\try.txt");
        File to = new File(PathConfigurationRoot.training_comment);
        try {
            FileUtils.copyFile(from,to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void exportTestingSet(String pathName){
        File from = new File(pathName);
        //File to = new File(PathConfigurationRoot.folder + "\\try.txt");
        File to = new File(PathConfigurationRoot.testing_comment);
        try {
            FileUtils.copyFile(from,to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private String randomLine(){
        int index = new Random().nextInt(data.size());
        String line = data.get(index);
        data.remove(index);
        return line;
    }

    static private void resetData(){
        if (data != null && data.size() > 0)
            data.clear();
    }

    static public void main(String arg[]){
        try {
            for (int i = 0; i < 50; ++i) {
                createDataSet();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
