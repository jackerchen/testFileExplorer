package com.azhe.testFileExplorer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.azhe.testFileExplorer.FileViewInteractionHub.Mode;

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
    private OnClickListener checkClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            ImageView img = (ImageView) v.findViewById(R.id.file_checkbox);
            assert (img != null && img.getTag() != null);

            FileInfo tag = (FileInfo) img.getTag();
            tag.Selected = !tag.Selected;
//            ActionMode actionMode = ((FileExplorerTabActivity) mContext).getActionMode();
            ActionMode actionMode = null;
            if (actionMode == null) {
                actionMode = startActionMode(new ModeCallback());
//                ((FileExplorerTabActivity) mContext).setActionMode(actionMode);
            } else {
                actionMode.invalidate();
            }
        }
    };

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

        ImageView checkbox = (ImageView) findViewById(R.id.file_checkbox);
        if (mFileViewInteractionHub.getMode() == Mode.Pick) {
            checkbox.setVisibility(View.GONE);
        } else {
            checkbox.setVisibility(mFileViewInteractionHub.canShowCheckBox() ? View.VISIBLE : View.GONE);
            checkbox.setImageResource(lFileInfo.Selected ? R.drawable.btn_check_on_holo_light : R.drawable.btn_check_off_holo_light);
            checkbox.setTag(lFileInfo);
            View checkArea = findViewById(R.id.file_checkbox_area);
            checkArea.setOnClickListener(checkClick);
            setSelected(lFileInfo.Selected);
        }

        Util.setText(this, R.id.file_name, lFileInfo.fileName);
        Util.setText(this, R.id.file_count, lFileInfo.IsDir ? "(" + lFileInfo.Count + ")" : "");
        Util.setText(this, R.id.modified_time, Util.formatDateString(mContext, lFileInfo.ModifiedDate));
        Util.setText(this, R.id.file_size, (lFileInfo.IsDir ? "" : Util.convertStorage(lFileInfo.fileSize)));

        ImageView lFileImage = (ImageView) findViewById(R.id.file_image);
        ImageView lFileImageFrame = (ImageView) findViewById(R.id.file_image_frame);

        if (lFileInfo.IsDir) {
            lFileImageFrame.setVisibility(View.GONE);
            lFileImage.setImageResource(R.drawable.folder);
        } else {
            mFileIcon.setIcon(lFileInfo, lFileImage, lFileImageFrame);
        }
    }

    private class ModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mFileViewInteractionHub.clearSelection();
        }
    }
}
