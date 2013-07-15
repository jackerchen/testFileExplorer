package com.azhe.testFileExplorer;

import android.content.Context;
import android.widget.ImageView;
import com.azhe.testFileExplorer.FileIconLoader.IconLoadFinishListener;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileIconHelper implements IconLoadFinishListener {

    private FileIconLoader mIconLoader;

    public FileIconHelper(Context context) {
        mIconLoader = new  FileIconLoader(context, this);
    }

    @Override
    public void onIconLoadFinished(ImageView view) {

    }
}
