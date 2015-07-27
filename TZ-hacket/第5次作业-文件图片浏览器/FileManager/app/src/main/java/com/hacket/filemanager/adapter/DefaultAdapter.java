package com.hacket.filemanager.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;

    public DefaultAdapter() {
    }

    public DefaultAdapter(Context mContext, List<T> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }
}
