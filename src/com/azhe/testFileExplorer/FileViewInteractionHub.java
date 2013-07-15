package com.azhe.testFileExplorer;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.azhe.testFileExplorer.FileOperationHelper.IOperationProgressListener;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileViewInteractionHub implements IOperationProgressListener {
    private static final String LOG_TAG = "FileViewInteractionHub";

    private IFileInteractionListener mFileViewListener;

    private ListView mFileListView;

    private FileOperationHelper mFileOperationHelper;

    private Context mContext;

    private Mode mCurrentMode;

    @Override
    public void onFinish() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFileChanged(String path) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMode(Mode m) {
        mCurrentMode = m;
    }

    public boolean isMoveState() {
        return mFileOperationHelper.isMoveState() || mFileOperationHelper.canPaste();
    }

    public boolean isFileSelected(String filePath) {
        return mFileOperationHelper.isFileSelected(filePath);
    }

    public enum Mode {
        View, Pick
    }

    public FileViewInteractionHub(IFileInteractionListener fileViewListener) {
        assert (fileViewListener != null);
        mFileViewListener = fileViewListener;
        setup();
        mFileOperationHelper = new FileOperationHelper(this);
    }

    private void setup() {
        setupFileListView();
    }

    private void setupFileListView() {
        mFileListView = (ListView) mFileViewListener.getViewById(R.id.file_path_list);
        mFileListView.setLongClickable(true);
//        mFileListView.setOnCreateContextMenuListener();
        mFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 onListItemClick(parent, view, position, id);

            }
        });
    }

    private void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        FileInfo lFileInfo = mFileViewListener.getItem(position);

        if (lFileInfo == null) {
            return;
        }


        if (!lFileInfo.IsDir) {
            if(mCurrentMode == Mode.Pick){
                mFileViewListener.onPick(lFileInfo);
            } else {
                viewFile(lFileInfo);
            }

        }
    }

    private void viewFile(FileInfo lFileInfo) {
        IntentBuilder.viewFile(mContext, lFileInfo.filePath);

    }
}
