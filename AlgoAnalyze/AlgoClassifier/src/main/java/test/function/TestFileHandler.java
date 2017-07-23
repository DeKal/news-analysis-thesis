package test.function;

import model.Collection;
import model.Dictionary;
import util.FileHandler;

import java.io.IOException;

/**
 * Created by Phuong Huynh on 5/25/2017.
 */
public class TestFileHandler {

    public void runFileHandler(String folder, Collection mCollection, Dictionary mDict){
        try {

            FileHandler.collectDoc(folder,mCollection,mDict);
            System.out.print("Collecting docs complete! \n");

            mDict.exportDict();
            System.out.print("Exporting dict complete! \n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
