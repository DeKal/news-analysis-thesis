package common.helper;

import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 * Created by Phuong Huynh on 6/22/2017.
 */
public class TokenHelper {
    static VietTokenizer vietTokenizer = null;

    public static String[] tokenize(String sentence){
        if (vietTokenizer == null)
            vietTokenizer = new VietTokenizer();

        return vietTokenizer.tokenize(sentence);
    }
}
