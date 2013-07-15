package com.azhe.testFileExplorer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileViewActivity extends Fragment implements IFileInteractionListener, IBackPressedListener{

    private View mRootView;
    private Activity mActivity;

    private FileViewInteractionHub mFileViewInteractionHub;

    private FileCategoryHelper mFileCategoryHelper;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        mRootView = inflater.inflate(R.layout.file_explorer_list, container, false);
        ActivitiesManager.getInstance().registerActivity(ActivitiesManager.ACTIVITY_FILE_VIEW, mActivity);

        mFileCategoryHelper = new FileCategoryHelper(mActivity);
        mFileViewInteractionHub = new FileViewInteractionHub(this);
        return mRootView;
    }
}
