package com.azhe.testFileExplorer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class FilenameExtFilter implements FilenameFilter {


    private HashSet<String> mExts = new HashSet<String>();

    // using lower case
    public FilenameExtFilter(String[] exts) {
        if (exts != null) {
            mExts.addAll(Arrays.asList(exts));
        }
    }

    public boolean contains(String ext) {
        return mExts.contains(ext.toLowerCase());
    }

    public boolean accept(File dir, String filename) {

        File file = new File(dir + File.separator + filename);
        if (file.isDirectory()) {
            return true;
        }

        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition != -1) {
            String ext = (String) filename.subSequence(dotPosition + 1, filename.length());
            return contains(ext.toLowerCase());
        }

        return false;
    }
}
