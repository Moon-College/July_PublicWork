package com.example.administrator.myapplication.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.utils.ImageLoader;
import com.example.administrator.myapplication.bean.FilesBean;

import java.util.List;

/**
 * Created by Administrator on 2015/7/26 0026.
 */
public class MyAdapter extends RecyclerView.Adapter<FilesHolder> {

    private OnItemClickListener listener;

    //回调接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    private final List<FilesBean> mDatas;

    public MyAdapter(List<FilesBean> list) {

        this.mDatas = list;
    }

    @Override
    public FilesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_files, parent, false);
        FilesHolder holder = new FilesHolder(view,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FilesHolder holder, final int position) {
        FilesBean bean = mDatas.get(position);
        holder.tv.setText(bean.name);
        if (bean.bitmap!=null){
            holder.iv.setImageBitmap(bean.bitmap);
        }else{
            ImageLoader.getInstance().displayImage(holder.iv,bean.path.getAbsolutePath());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener!=null){
                    listener.onItemClick(holder.itemView,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class FilesHolder extends RecyclerView.ViewHolder {
    public ImageView iv;
    public TextView tv;
    public View itemView;

    public FilesHolder(View itemView, MyAdapter.OnItemClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        iv = (ImageView) itemView.findViewById(R.id.iv);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
