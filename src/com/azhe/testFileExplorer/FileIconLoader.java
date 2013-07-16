package com.azhe.testFileExplorer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import com.azhe.testFileExplorer.FileCategoryHelper.FileCategory;

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

    private final ConcurrentHashMap<ImageView, FileId> mPendingRequests = new ConcurrentHashMap<ImageView, FileId>();

    private final static ConcurrentHashMap<String, ImageHolder> mImageCache = new ConcurrentHashMap<String, ImageHolder>();

    private boolean mPaused;

    private boolean mLoadingRequested;

    private final Handler mMainThreadHandler = new Handler(this);

    private static final int MESSAGE_REQUEST_LOADING = 1;

    public FileIconLoader(Context context, IconLoadFinishListener l){
        mContext = context;
        iconLoadListener = l;
    }

    public void cancelRequest(ImageView view) {
        mPendingRequests.remove(view);
    }

    public boolean loadIcon(ImageView view, String path, long id, FileCategory cate) {
        boolean loaded = loadCachedIcon(view, path, cate);
        if (loaded) {
            mPendingRequests.remove(view);
        } else {
            FileId p = new FileId(path, id, cate);
            mPendingRequests.put(view, p);
            if (!mPaused) {
                // Send a request to start loading photos
                requestLoading();
            }
        }
        return loaded;
    }

    private void requestLoading() {
        if (!mLoadingRequested) {
            mLoadingRequested = true;
            mMainThreadHandler.sendEmptyMessage(MESSAGE_REQUEST_LOADING);
        }
    }

    private boolean loadCachedIcon(ImageView view, String path, FileCategory cate) {
        ImageHolder holder = mImageCache.get(path);
        if (holder == null) {
            holder = ImageHolder.create(cate);
            if (holder == null)
                return false;

            mImageCache.put(path, holder);
        } else if (holder.state == ImageHolder.LOADED) {
            if (holder.isNull()) {
                return true;
            }

            // failing to set imageview means that the soft reference was
            // released by the GC, we need to reload the photo.
            if (holder.setImageView(view)) {
                return true;
            }
        }

        holder.state = ImageHolder.NEEDED;
        return false;
    }

    public abstract static interface IconLoadFinishListener {
        void onIconLoadFinished(ImageView view);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }

    private static class FileId {
        public String mPath;

        public long mId;

        public FileCategory mCategory;

        public FileId(String path, long id, FileCategory cate) {
            mPath = path;
            mId = id;
            mCategory = cate;
        }
    }

    private static abstract class ImageHolder {
        public static final int NEEDED = 0;

        public static final int LOADING = 1;

        public static final int LOADED = 2;

        int state;

        public static ImageHolder create(FileCategory cate) {
            switch (cate) {
                case Apk:
                    return new DrawableHolder();
                case Picture:
                case Video:
                    return new BitmapHolder();
            }

            return null;
        };

        public abstract boolean setImageView(ImageView v);

        public abstract boolean isNull();

        public abstract void setImage(Object image);

    }

    private static class BitmapHolder extends ImageHolder {
        SoftReference<Bitmap> bitmapRef;

        @Override
        public boolean setImageView(ImageView v) {
            if (bitmapRef.get() == null)
                return false;
            v.setImageBitmap(bitmapRef.get());
            return true;
        }

        @Override
        public boolean isNull() {
            return bitmapRef == null;
        }

        @Override
        public void setImage(Object image) {
            bitmapRef = image == null ? null : new SoftReference<Bitmap>((Bitmap) image);
        }
    }

    private static class DrawableHolder extends ImageHolder {
        SoftReference<Drawable> drawableRef;

        @Override
        public boolean setImageView(ImageView v) {
            if (drawableRef.get() == null)
                return false;

            v.setImageDrawable(drawableRef.get());
            return true;
        }

        @Override
        public boolean isNull() {
            return drawableRef == null;
        }

        @Override
        public void setImage(Object image) {
            drawableRef = image == null ? null : new SoftReference<Drawable>((Drawable) image);
        }
    }
}
