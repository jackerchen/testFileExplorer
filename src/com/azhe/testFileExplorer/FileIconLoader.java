package com.azhe.testFileExplorer;

import android.content.Context;
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

    private Context mContext;

    private IconLoadFinishListener iconLoadListener;

    public FileIconLoader(Context context, IconLoadFinishListener l){
        mContext = context;
        iconLoadListener = l;
    }

    public abstract static interface IconLoadFinishListener {
        void onIconLoadFinished(ImageView view);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
