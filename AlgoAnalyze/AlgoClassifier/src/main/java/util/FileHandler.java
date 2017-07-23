package util;

import io.FileIO;
import model.Collection;
import model.Dictionary;
import model.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Phuong Huynh on 5/23/2017.
 */
public class FileHandler {
    public static boolean DEBUG = false;

    private FileHandler(){
    }

    public static HashMap collectDoc(String inputFolder, Collection mCollection, Dictionary mDict) throws IOException {
        TextFileFilter filter = new TextFileFilter();
        File inputPath = new File(inputFolder);
        HashMap docTable = new HashMap();
        TreeMap<String,ArrayList<String>> invertedIndex = new TreeMap<String, ArrayList<String>>();
        ArrayList<Document> documents = new ArrayList<Document>();

        File[] inputFiles = FileIterator.getListFiles(inputPath,filter);
        int numberOfInputFiles = inputFiles.length;

        for (int i = 0; i < numberOfInputFiles; ++i){
            File file = inputFiles[i];
            String fileName = file.getName();
            String fileID = "ID" + i;

            docTable.put(fileName,fileID);
            Document document = new Document(fileName,fileID);
            TreeMap<String, Integer> listTerm = new TreeMap<String, Integer>();

            /* Close these codes for new methods
            closeReader();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            */

            FileIO.createReader(file.getAbsolutePath());
            String line;
            while ((line = FileIO.readLine()) != null) {
                String words[] = line.split(" ");
                for (String word : words){
                    word = word.toLowerCase();

                    mDict.addTerm(word,document);

                    if (listTerm.containsKey(word)){
                        int count = listTerm.get(word);
                        ++count;
                        listTerm.replace(word,count);
                    }
                    else
                        listTerm.put(word,1);
                }
            }

            document.setListTerm(listTerm);
            documents.add(document);
        }

        mCollection.setItems(documents);
        FileIO.closeReader();

        return docTable;
    }

    public static void createInvertedIndex(String inputFolder, HashMap<String,String> docTable){
        TextFileFilter filter = new TextFileFilter();
        File inputPath = new File(inputFolder);
        String currentPath = (new File("")).getAbsolutePath();

        /*
        // Test path of directory
        */
        String inFullPath = currentPath + File.separator + inputFolder;
        String outFullPath = currentPath+ File.separator;
        if(DEBUG) {
            System.out.println("currentDir = " + currentPath);
            System.out.println("inputDirPath = " + inFullPath);
            System.out.println("outputDirPath = " + outFullPath);
        }

        File[] inputFiles = FileIterator.getListFiles(inputPath,filter);
        int numberOfInputFiles = inputFiles.length;
    }

}
