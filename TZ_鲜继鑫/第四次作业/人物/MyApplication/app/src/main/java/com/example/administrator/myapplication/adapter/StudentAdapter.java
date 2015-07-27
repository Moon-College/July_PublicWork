package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.com.example.administrator.myapplication.bean.StudentBean;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class StudentAdapter extends BaseAdapter{
    private List<StudentBean> list ;
    private final LayoutInflater inflater;

    public StudentAdapter(List<StudentBean> list,Context context) {
        this.list = list ;
        inflater = LayoutInflater.from(context);
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
        ViewHolder holder =null;
        StudentBean bean = list.get(position);
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_student,parent,false);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.hobby = (TextView)convertView.findViewById(R.id.hobby);
            holder.yan = (TextView)convertView.findViewById(R.id.yan);
            holder.sex = (TextView)convertView.findViewById(R.id.sex);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(bean.getHeader());
        holder.hobby.setText("爱好:"+bean.getHobby());
        holder.name.setText("网名:"+bean.getName());
        holder.sex.setText("性别:"+bean.getSex());
        holder.yan.setText("颜值:"+bean.getYanzhi());
        convertView.setBackgroundColor(bean.getColor());   //设置背景色
        return convertView;
    }
    class ViewHolder {
        private ImageView iv;
        private TextView name;
        private TextView hobby;
        private TextView yan;
        private TextView sex;
    }
}
