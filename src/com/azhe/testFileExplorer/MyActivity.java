package com.azhe.testFileExplorer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MyActivity extends Activity {

    private Fragment fragment;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        initComponent();
    }

    private void initComponent() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment = new FileViewActivity();
        ft.remove(fragment);
        ft.replace(R.id.file_explorer_frame_layout, fragment);
        ft.commit();
    }
}
