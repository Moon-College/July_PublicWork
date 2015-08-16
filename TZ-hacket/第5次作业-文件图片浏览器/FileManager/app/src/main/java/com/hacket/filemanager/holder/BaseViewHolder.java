package com.hacket.filemanager.holder;

import android.content.Context;
import android.view.View;

import com.hacket.filemanager.utils.ImageLoader;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public abstract class BaseViewHolder<T> {

    protected Context mContext;
    protected View mRootView;
    protected final ImageLoader mImageLoader;

    public BaseViewHolder(Context mContext) {
        this.mContext = mContext;
        mImageLoader = ImageLoader.getInstance(mContext);
        mRootView = newView(mContext);
        mRootView.setTag(this);
    }

    public View getmRootView() {
        return mRootView;
    }

    public abstract View newView(Context mContext);

    public abstract void bindView(T data, int position);

}
