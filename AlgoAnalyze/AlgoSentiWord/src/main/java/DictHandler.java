import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phuong Huynh on 3/16/2017.
 */
public class DictHandler {
    private static HashMap<String,Double> dictionary = new HashMap();
    public HashMap<String,Double> getDict(){
        return dictionary;
    }

    public DictHandler(String path) throws IOException {
        HashMap<String, HashMap<Integer,Double>> tempDict = new HashMap<String, HashMap<Integer,Double>>();

        BufferedReader br = null;
        try {
            br = new BufferedReader (new InputStreamReader(new FileInputStream(path), "UTF-8"));
            int lineNum = 0;

            String line;
            while ((line = br.readLine()) != null){
                ++lineNum;

                if (!line.trim().startsWith("#")){
                    String data[] = line.split("\t");
                    String wordType = data[0];

                    // check if the line is valid?
                    if (data.length != 6)
                        throw new Exception("Invalid line: " + lineNum);

                    // calculate score = pos - neg
                    Double synsetScore = Double.parseDouble(data[2]) - Double.parseDouble(data[3]);

                    /* Modified: change from splitting by " " to adding "_" by htaphuong
                    * String synTerms[] = data[4].split(" ");
                    */
                    //data[4] = data[4].replace(" ","_");
                    String synTerms[] = data[4].split(" ");
                    int count_merge_term = 0;
                    if (synTerms.length > 1) {
                        for (int i = 0; i < synTerms.length; ++i) {
                            if (!synTerms[i].contains("#") && (i + 1) < synTerms.length) {
                                synTerms[i + 1] = synTerms[i] + "_" + synTerms[i + 1];
                                ++count_merge_term;
                            }
                        }
                    }
                    String[] modifiedSynTerms = new String[synTerms.length - count_merge_term];
                    int modifiedIndex = 0;
                    for (int i = 0; i < synTerms.length; ++i){
                        if (synTerms[i].contains("#") && modifiedIndex < modifiedSynTerms.length){
                            modifiedSynTerms[modifiedIndex] = synTerms[i];
                            ++modifiedIndex;
                        }
                    }
                    /*end modified by htaphuong*/

                    for (String synTerm : modifiedSynTerms){
                        // Term#Rarnk -> Term, Rank
                        String synTermWithRank[] = synTerm.split("#");

                        String term = synTermWithRank[0];
                        byte strByte[] = term.getBytes("UTF-8");
                        term = new String(strByte,"UTF-8");
                        term = term;

                        if (term.equals(""))
                            continue;

                        int rank = 1;
                        if (synTermWithRank.length > 1) {
                            rank = Integer.parseInt(synTermWithRank[1]);
                        }
                        // map to temp-dictionary
                        if (!tempDict.containsKey(term)){
                            tempDict.put(term, new HashMap<Integer,Double>());
                        }

                        //String finalTerm = new String(term.getBytes("UTF-8"));
                        tempDict.get(term).put(rank,synsetScore);
                    }

                }
            }

            // complete main dictionary
            for (Map.Entry<String,HashMap<Integer,Double>> entry : tempDict.entrySet()){
                String word = entry.getKey();
                Map<Integer,Double> synsetScore = entry.getValue();

                // calculate weight average of synset according the rank
                // Score = 1/2*first + 1/3*second + 1/4*third ... (Score )
                // Sum = 1/2 + 1/3 + 1/4 ...
                double score = 0.0;
                double sum = 0.0;
                for (Map.Entry<Integer,Double> setScore : synsetScore.entrySet()){
                    score += setScore.getValue() / (double)setScore.getKey();
                    sum += 1.0/ (double)setScore.getKey();
                }

                score = score / sum;
                dictionary.put(word,score);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public double extract(String word){
        return dictionary.get(word);
    }

    public void exportDict() throws FileNotFoundException, UnsupportedEncodingException {
        String dict = "";

        for (Map.Entry<String,Double> entry : dictionary.entrySet()){
            dict += entry.getKey() + "\t" + entry.getValue() + "\n";
        }
        PrintWriter writer = new PrintWriter("dict.text", "UTF-8");
        writer.print(dict);
        writer.close();
        
    }
}









