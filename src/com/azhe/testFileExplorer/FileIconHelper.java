package com.azhe.testFileExplorer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.azhe.testFileExplorer.FileIconLoader.IconLoadFinishListener;
import com.azhe.testFileExplorer.FileCategoryHelper.FileCategory;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhe
 * Date: 7/15/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileIconHelper implements IconLoadFinishListener {

    private static HashMap<String, Integer> fileExtToIcons = new HashMap<String, Integer>();

    private static HashMap<ImageView, ImageView> imageFrames = new HashMap<ImageView, ImageView>();

    private FileIconLoader mIconLoader;

    public FileIconHelper(Context context) {
        mIconLoader = new  FileIconLoader(context, this);
    }

    @Override
    public void onIconLoadFinished(ImageView view) {

    }

    public void setIcon(FileInfo fileInfo, ImageView fileImage, ImageView fileImageFrame) {
        String filePath = fileInfo.filePath;
        long fileId = fileInfo.dbId;
        String extFromFilename = Util.getExtFromFilename(filePath);
        FileCategory fc = FileCategoryHelper.getCategoryFromPath(filePath);
        fileImageFrame.setVisibility(View.GONE);
        boolean set =false;
        int id = getFileIcon(extFromFilename);
        fileImage.setImageResource(id);

        mIconLoader.cancelRequest(fileImage);
        switch (fc) {
            case Apk:
                set = mIconLoader.loadIcon(fileImage, filePath, fileId, fc);
                break;
            case Picture:
            case Video:
                set = mIconLoader.loadIcon(fileImage, filePath, fileId, fc);
                if (set)
                    fileImageFrame.setVisibility(View.VISIBLE);
                else {
                    fileImage.setImageResource(fc == FileCategory.Picture ? R.drawable.file_icon_picture
                            : R.drawable.file_icon_video);
                    imageFrames.put(fileImage, fileImageFrame);
                    set = true;
                }
                break;
            default:
                set = true;
                break;
        }
    }

    private static int getFileIcon(String ext) {
        Integer i = fileExtToIcons.get(ext.toLowerCase());
        if (i != null) {
            return i.intValue();
        } else {
            return R.drawable.file_icon_default;
        }
    }
}
