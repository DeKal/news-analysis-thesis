package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Phuong Huynh on 5/23/2017.
 */
public class FileIterator {
    private FileIterator(){
    }

    public static File[] getListFiles(File folder, TextFileFilter filter){
        ArrayList listOfFiles = new ArrayList();
        if (folder.isDirectory()){
            File[] listOfItems = folder.listFiles();

            int numberOfItems = listOfItems.length;

            for (int i = 0; i < numberOfItems; ++i){
                File item = listOfItems[i];
                if (item.isDirectory()){
                    listOfFiles.addAll(Arrays.asList(getListFiles(item,filter)));
                }
                else if (filter.accept(item)){
                    listOfFiles.add(item);
                }
            }
        }

        return (File[]) listOfFiles.toArray(new File[listOfFiles.size()]);
    }





}
