package com.azhe.testFileExplorer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileListItem extends LinearLayout {
    private static Context mContext;
    private static FileViewInteractionHub mFileViewInteractionHub;

    public FileListItem(Context context) {
        super(context);
        mContext = context;
    }

    public FileListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public final void bind(FileInfo file, FileViewInteractionHub f, FileIconHelper mFileIcon) {
        mFileViewInteractionHub = f;
        FileInfo lFileInfo = file;

        if (mFileViewInteractionHub.isMoveState()) {
            lFileInfo.Selected = mFileViewInteractionHub.isFileSelected(lFileInfo.filePath);
        }

        ImageView checkbox = (IamgeView) findViewById(R.id.file_checkbox);
    }
}
