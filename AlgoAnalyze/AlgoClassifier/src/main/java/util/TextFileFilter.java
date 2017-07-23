package util;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Phuong Huynh on 5/23/2017.
 */
public class TextFileFilter implements FileFilter{
    private String extension = ".txt";

    public TextFileFilter(){
    }

    public TextFileFilter(String extension){
        this.extension = extension;
    }

    public boolean accept(File pathname) {
        if (!pathname.isFile())
            return false;
        if (pathname.getName().endsWith(this.extension))
            return true;

        return false;
    }
}
