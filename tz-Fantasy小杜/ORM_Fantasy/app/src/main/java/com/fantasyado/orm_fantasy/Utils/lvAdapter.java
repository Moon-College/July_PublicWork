package com.fantasyado.orm_fantasy.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fantasyado.orm_fantasy.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/3 0003.
 */
public class lvAdapter extends BaseAdapter {
    private ArrayList<String> mDatas;

    private LayoutInflater mInflater;
    private Context mContext;
    public lvAdapter(Context context,ArrayList<String> datas){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
         if (convertView==null){
             convertView = mInflater.inflate(R.layout.lv_item,parent,false);
             holder = new ViewHolder();
             holder.tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
             convertView.setTag(holder);

         }else{
             holder = (ViewHolder) convertView.getTag();
         }

        holder.tvInfo.setText(mDatas.get(position));

        return convertView;
    }

    class ViewHolder{
        private TextView tvInfo;
    }
}
