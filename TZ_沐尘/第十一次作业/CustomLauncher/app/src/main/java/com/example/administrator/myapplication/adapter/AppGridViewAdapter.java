package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10 0010.
 */
public class AppGridViewAdapter extends BaseAdapter{
    private Context mContext;
//    private List<ResolveInfo> mInfos ;
    private List<PackageInfo> mInfos ;
    private final LayoutInflater inflater;

    public AppGridViewAdapter(MainActivity mainActivity, List<PackageInfo> infos) {
        this.mContext = mainActivity;
        this.mInfos = infos;
        inflater = LayoutInflater.from(mainActivity);
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ResolveInfo info = mInfos.get(position);
        PackageInfo info = mInfos.get(position);
        View view = inflater.inflate(R.layout.item_app, parent, false);

        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        Drawable loadIcon = info.applicationInfo.loadIcon(mContext.getPackageManager());
        iv.setImageDrawable(loadIcon);
        tv.setText(info.applicationInfo.loadLabel(mContext.getPackageManager()));
        return view;
    }

}
