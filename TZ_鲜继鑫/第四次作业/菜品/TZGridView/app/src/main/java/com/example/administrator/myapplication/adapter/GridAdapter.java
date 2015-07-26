package com.example.administrator.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.GridActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.SourceBean;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class GridAdapter extends BaseAdapter{
    private List<SourceBean> list;
    private final LayoutInflater inflater;

    public GridAdapter(List<SourceBean> data, GridActivity gridActivity) {
        this.list = data;
        inflater = LayoutInflater.from(gridActivity);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SourceBean bean = list.get(position);
        Log.i("adapter",position+"");
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_grid,parent,false);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.food = (ImageView) convertView.findViewById(R.id.food);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.num = (TextView) convertView.findViewById(R.id.num);
            holder.header = (ImageView) convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.content.setText(bean.getContent());
        holder.num.setText(bean.getNum()+"道菜");
        holder.food.setImageResource(bean.getFood());
        holder.name.setText(bean.getName());
        holder.header.setImageResource(bean.getHeader());


        return convertView;
    }

    private class ViewHolder {
        public ImageView food;
        public ImageView header;
        public TextView name;
        public TextView content;
        public TextView num;
    }


}
