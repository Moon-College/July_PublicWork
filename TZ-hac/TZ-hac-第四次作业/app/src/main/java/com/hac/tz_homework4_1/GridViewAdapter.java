package com.hac.tz_homework4_1;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 2015/7/23.
 */
public class GridViewAdapter extends BaseAdapter {
    List<Item> itemList;
    LayoutInflater inflater;

    public GridViewAdapter(Context context, List<Item> itemList) {
        inflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_gv, null);
            holder = new ViewHolder();
            holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_author = (TextView) convertView.findViewById(R.id.tv_author);
            holder.rl_img = (RelativeLayout)convertView.findViewById(R.id.rl_img);
            holder.img_photo = (ImageView)convertView.findViewById(R.id.img_photo);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rl_img.setBackground(new BitmapDrawable(itemList.get(position).getBackground()));
        holder.img_photo.setImageBitmap(itemList.get(position).getPhoto());
        holder.tv_number.setText(String.valueOf(itemList.get(position).getNumber())+"道菜");
        holder.tv_name.setText(itemList.get(position).getName());
        holder.tv_author.setText(itemList.get(position).getAuthor());

        return convertView;
    }

    class ViewHolder {
        RelativeLayout rl_img;
        ImageView img_photo;
        TextView tv_number;
        TextView tv_author;
        TextView tv_name;
    }
}
