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

        // ���viewΪ�գ����ز����ļ�
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

        // ��ȡ����
        Person person = getItem(position);
        // �����Ա����ñ���ɫ
        convertView.setBackgroundColor(person.getSex() == 0 ? Color.BLUE : Color.RED);
        // ������
        holder.ivFace.setImageResource(person.getFace());
        holder.tvNickName.setText("������" + person.getNickName());
        holder.tvSex.setText("�Ա�" + (person.getSex() == 0 ? "��" : "Ů"));
        holder.rbFaceScore.setRating(person.getFaceScore());
        holder.tvHobby.setText("���ã�" + person.getHobby());

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
