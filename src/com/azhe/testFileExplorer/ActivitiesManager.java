package com.azhe.testFileExplorer;

import android.app.Activity;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivitiesManager {
    public static final String ACTIVITY_FILE_VIEW = "FileView";

    public static final String ACTIVITY_FILE_CATEGORY = "FileCategory";

    public static final String ACTIVITY_TAB = "FileExplorerTab";

    private static ActivitiesManager instance;

    private HashMap<String, Activity> activities = new HashMap<String, Activity>();

    private ActivitiesManager() {

    }

    // return true indicates successful, false indicates the name exists
    public void registerActivity(String name, Activity a) {
        activities.put(name, a);
    }

    public Activity getActivity(String name) {
        return activities.get(name);
    }

    public static ActivitiesManager getInstance() {
        if (instance == null)
            instance = new ActivitiesManager();
        return instance;
    }
}
