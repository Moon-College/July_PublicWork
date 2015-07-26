package com.hacket.listviewhomework1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hacket.listviewhomework1.bean.Person;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;

    private MyAdapter mAdapter;

    private int[] resIds = {
            R.drawable.face1, R.drawable.face2,
            R.drawable.face3, R.drawable.face4,
            R.drawable.face5, R.drawable.face6,
            R.drawable.face7, R.drawable.face8, R.drawable.face1, R.drawable.face2,
            R.drawable.face3, R.drawable.face4,
            R.drawable.face5, R.drawable.face6,
            R.drawable.face7, R.drawable.face8, R.drawable.face1, R.drawable.face2,
            R.drawable.face3, R.drawable.face4,
            R.drawable.face5, R.drawable.face6,
            R.drawable.face7, R.drawable.face8, R.drawable.face1, R.drawable.face2,
            R.drawable.face3, R.drawable.face4,
            R.drawable.face5, R.drawable.face6,
    };
    private List<Person> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initData();
    }

    private void setListener() {
        mListView.setOnItemClickListener(this);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            Person person = new Person("nick_" + i, resIds[i], i % 2 == 1 ? "male" : "female", i + "", "backetball,socker");
            datas.add(person);
        }
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv);

    }

    public void loadData(View view) {
        mAdapter = new MyAdapter(datas);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.getDatas().remove(position);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "delete position:" + position, 0).show();
    }


    private class MyAdapter extends BaseAdapter {

        private List<Person> mDatas;

        public MyAdapter(List<Person> datas) {
            this.mDatas = datas;
        }

        public List<Person> getDatas() {
            return mDatas;
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

            BaseViewHolder holder;
            if (convertView != null && convertView instanceof LinearLayout) {
                holder = (BaseViewHolder) convertView.getTag();
            } else {
                holder = new MyViewHolder(getApplicationContext());
            }

            holder.bindView(mDatas.get(position));

            return holder.getRootView();
        }

    }

    private class MyViewHolder extends BaseViewHolder<Person> {

        private View root;
        private ImageView ivIcon;
        private TextView tvNick;
        private TextView tvGender;
        private TextView tvBeauty;
        private TextView tvHobby;

        public MyViewHolder(Context context) {
            super(context);
        }

        @Override
        public View newView(Context context) {
            View view = View.inflate(context, R.layout.layout_item, null);
            root = view.findViewById(R.id.root);
            ivIcon = (ImageView) view.findViewById(R.id.icon);
            tvNick = (TextView) view.findViewById(R.id.nick);
            tvGender = (TextView) view.findViewById(R.id.gender);
            tvBeauty = (TextView) view.findViewById(R.id.beauty);
            tvHobby = (TextView) view.findViewById(R.id.hobby);
            view.setTag(this);
            return view;
        }

        @Override
        public void bindView(Person data) {
            String gender = data.getGender();
            if ("male".equals(gender)) {
                root.setBackgroundColor(Color.BLUE);
            } else {
                root.setBackgroundColor(Color.RED);
            }
            ivIcon.setImageDrawable(mContext.getResources().getDrawable(data.getIcon()));
            tvNick.setText(data.getNick());
            tvGender.setText(data.getGender());
            tvBeauty.setText(data.getBeauty());
            tvHobby.setText(data.getHobby());
        }
    }

    private abstract class BaseViewHolder<T> {

        protected Context mContext;
        private View rootView;

        public BaseViewHolder(Context context) {
            mContext = context;
            rootView = newView(context);
        }

        abstract View newView(Context context);

        abstract void bindView(T datas);

        public View getRootView() {
            return rootView;
        }
    }

}