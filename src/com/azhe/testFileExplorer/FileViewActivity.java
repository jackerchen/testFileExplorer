package com.azhe.testFileExplorer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.azhe.testFileExplorer.FileViewInteractionHub.Mode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileViewActivity extends Fragment implements IFileInteractionListener, IBackPressedListener {

    public static final String EXT_FILETER_KEY = "ext_filter";

    private static final String LOG_TAG = "FileViewActivity";

    public static final String EXT_FILE_FIRST_KEY = "ext_file_first";

    public static final String ROOT_DIRECTORY = "root_directory";

    public static final String PICK_FOLDER = "pick_folder";

    private ListView mFileListView;

    private View mRootView;

    private Activity mActivity;

    private FileViewInteractionHub mFileViewInteractionHub;

    private FileCategoryHelper mFileCategoryHelper;

    private FileIconHelper mFileIconHelper;

    private ArrayAdapter<FileInfo> mAdapter;

    private ArrayList<FileInfo> mFileNameList = new ArrayList<FileInfo>();


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        mRootView = inflater.inflate(R.layout.file_explorer_list, container, false);
        ActivitiesManager.getInstance().registerActivity(ActivitiesManager.ACTIVITY_FILE_VIEW, mActivity);

        mFileCategoryHelper = new FileCategoryHelper(mActivity);
        mFileViewInteractionHub = new FileViewInteractionHub(this);
        Intent intent = mActivity.getIntent();
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && (action.equals(Intent.ACTION_PICK) || action.equals(Intent.ACTION_GET_CONTENT))) {
            mFileViewInteractionHub.setMode(Mode.Pick);

            boolean pickFolder = intent.getBooleanExtra(PICK_FOLDER, false);
            if (!pickFolder) {
                String[] exts = intent.getStringArrayExtra(EXT_FILETER_KEY);
                if (exts != null) {
                    mFileCategoryHelper.setCustomCategory(exts);
                }
            }
        } else {
            mFileViewInteractionHub.setMode(Mode.View);
        }

        mFileListView = (ListView) mRootView.findViewById(R.id.file_path_list);
        mFileIconHelper =  new FileIconHelper(mActivity);
        mAdapter = new FileListAdapter(mActivity, R.layout.file_browse_item, mFileNameList, mFileViewInteractionHub, mFileIconHelper);
        return mRootView;
    }

    @Override
    public View getViewById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Context getContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDataChanged() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPick(FileInfo f) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean shouldShowOperationPane() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onOperation(int id) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDisplayPath(String path) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRealPath(String displayPath) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void runOnUiThread(Runnable r) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onNavigation(String path) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean shouldHideMenu(int menu) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FileIconHelper getFileIconHelper() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FileInfo getItem(int pos) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sortCurrentList(FileSortHelper sort) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<FileInfo> getAllFiles() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addSingleFile(FileInfo file) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onRefreshFileList(String path, FileSortHelper sort) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getItemCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
