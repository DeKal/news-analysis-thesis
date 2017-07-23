package common.io;

import java.io.*;

/**
 * Created by Phuong Huynh on 5/25/2017.
 */
public class FileIO {
    public static BufferedReader reader = null;
    public static BufferedWriter writer = null;

    public static void createReader(String filePath) {
        if (reader != null)
            try {
                closeReader();
            } catch (IOException e) {
                e.printStackTrace();
            }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeReader() throws IOException {
        if (reader != null) {
            reader.close();
            reader = null;
        }
    }

    public static void createWriter(String filePath) {
        closeWriter();
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void closeWriter() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void write(String s){
        try {
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeln(String s){
        try {
            writer.write(s);
            writer.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String[] strings){
        for (int i = 0; i < strings.length; ++i){
            write(strings[i] + " ");
        }
    }
}