package com.tz.lesson5.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tz.lesson5.R;
import com.tz.lesson5.bean.Person;

/**
 * Created by huangnn on 2015/7/23.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Person> mPersons;
    private LayoutInflater mInflater;
    public ListViewAdapter(Context context, List<Person> persons) {
        this.mContext = context;
        this.mPersons = persons;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mPersons.size();
    }

    @Override
    public Person getItem(int position) {
        return mPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        // 如果view为空，加载布局文件
        if (convertView == null) {
            convertView = this.mInflater.inflate(R.layout.list_view_item,parent,false);
            holder = new ViewHolder();
            holder.ivFace = (ImageView) convertView.findViewById(R.id.iv_face);
            holder.tvNickName = (TextView) convertView.findViewById(R.id.tv_nickname);
            holder.tvSex = (TextView) convertView.findViewById(R.id.tv_sex);
            holder.rbFaceScore = (RatingBar) convertView.findViewById(R.id.rb_facescore);
            holder.tvHobby = (TextView) convertView.findViewById(R.id.tv_hobby);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 获取对象
        Person person = getItem(position);
        // 根据性别设置背景色
        convertView.setBackgroundColor(person.getSex() == 0 ? Color.BLUE : Color.RED);
        // 绑定数据
        holder.ivFace.setImageResource(person.getFace());
        holder.tvNickName.setText("网名：" + person.getNickName());
        holder.tvSex.setText("性别：" + (person.getSex() == 0 ? "男" : "女"));
        holder.rbFaceScore.setRating(person.getFaceScore());
        holder.tvHobby.setText("爱好：" + person.getHobby());

        return convertView;
    }

    class ViewHolder {
        public ImageView ivFace;
        public TextView tvNickName;
        public TextView tvSex;
        public RatingBar rbFaceScore;
        public TextView tvHobby;
    }

}
