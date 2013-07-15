package com.azhe.testFileExplorer;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Settings {
    // whether show system and cache images, default not
    private boolean mShowDotAndHiddenFiles;
    private static Settings mInstance;

    private Settings() {

    }

    public static Settings instance() {
        if(mInstance == null) {
            mInstance = new Settings();
        }
        return mInstance;
    }

    public boolean getShowDotAndHiddenFiles() {
        return mShowDotAndHiddenFiles;
    }

    public void setShowDotAndHiddenFiles(boolean s) {
        mShowDotAndHiddenFiles = s;
    }
}
