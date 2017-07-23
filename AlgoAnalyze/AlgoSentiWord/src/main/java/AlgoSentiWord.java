import common.io.FileIO;
import config.PathConfigurationRoot;
import config.PathConfigurationSentiWord;

import java.io.File;
import java.util.ArrayList;

public class AlgoSentiWord {

    public static void main(String[] args) throws Exception {
        /* Process runner
        String pathDict = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\BizSentiWord\\data\\VietSentiWordnet_Test.txt";
        String pathData = "C:\\Users\\Phuong Huynh\\Desktop\\JAVA WORKSPACE\\BizSentiWord\\data\\input.txt";

        DictHandler dictHandler = new DictHandler(pathDict);
        dictHandler.exportDict();

        SentiAnalyzer analyzer = new SentiAnalyzer(dictHandler.getDict());
        */

        /*
        String value = "Cô ấy xấu";
        String s = new String(value.getBytes("UTF-8"));

        if (s.contains("xấu")){
            System.out.println("true");
        }
        else System.out.println("false");
        */

        /*
        BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(pathData), "UTF-8")));
        String s = br.readLine();
        //String s = "Ngày đẹp trời \n ngày mới. Có bao nhiêu niềm vui.";
        //String s = "đẹp \n hay";
        analyzer.analysis(s);
        System.out.println("this ---- " + analyzer.getResult());
        */


        predictAllDataSet();
    }

    public static String predictOneFile() throws Exception {
        ProcessRunner processRunner = new ProcessRunner();
        return processRunner.predict();
    }

    public static void predictAllDataSet() throws Exception {
        String original_path = PathConfigurationRoot.original;

        ArrayList<String> testingFileNames = new ArrayList<String>();

        File original = new File(original_path);
        File[] files = original.listFiles();
        for (File f : files){
            if (f.isFile()) {
                if (f.getName().endsWith(".test"))
                    testingFileNames.add(f.getName());
            }
        }

        ArrayList<String> records = new ArrayList<String>();
        for (String name_test : testingFileNames){
                System.out.println("#######" + name_test);

                String testPath = PathConfigurationRoot.original + name_test;
                DataSetCreator.exportTestingSet(testPath);

                String record = "";
                String s = "---------------------------------------------------\n";
                s += name_test + "\n";
                s += "---------------------------------------------------\n";

                String result = predictOneFile();
                s += result + "\n";

                record = s;
                records.add(record);
        }


        FileIO.createWriter(PathConfigurationSentiWord.sumary);
        for (String r : records){
            FileIO.writeln(r);
        }
        FileIO.closeWriter();
    }

}
