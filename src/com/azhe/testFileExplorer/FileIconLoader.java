package com.azhe.testFileExplorer;

import android.os.Handler.Callback;
import android.os.Message;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileIconLoader implements Callback {

    public abstract static interface IconLoadFinishListener {
        void onIconLoadFinished(ImageView view);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
