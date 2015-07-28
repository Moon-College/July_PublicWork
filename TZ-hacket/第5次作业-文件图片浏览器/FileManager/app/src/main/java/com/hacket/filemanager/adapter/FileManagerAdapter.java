package com.hacket.filemanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hacket.filemanager.bean.FileInfo;
import com.hacket.filemanager.holder.BaseViewHolder;
import com.hacket.filemanager.holder.FileManagerViewHolder;

import java.util.List;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public class FileManagerAdapter extends DefaultAdapter<FileInfo> {

    public FileManagerAdapter(Context mContext, List<FileInfo> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder<FileInfo> holder = null;
        if (convertView == null) {
            holder = new FileManagerViewHolder(mContext);
        } else {
            if (holder instanceof BaseViewHolder) {
                holder = (BaseViewHolder<FileInfo>) convertView.getTagwrap_content();
            } else {
                holder = new FileManagerViewHolder(mContext);
            }

        }

        holder.bindView(mDatas.get(position), position);

        return holder.getmRootView();
    }

}
