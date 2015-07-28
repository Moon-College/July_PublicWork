package com.tz.www.fourth;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tz.www.fourth.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {
    private ListView lvPerson;
    private List<Person> mPersonList = new ArrayList<Person>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_list_view);
        lvPerson = (ListView) findViewById(R.id.lv_person);
        for (int i = 0; i < 11; i++) {

            mPersonList.add(new Person("shang" + i, "男", "爱好：女", "男神", BitmapFactory.decodeResource(getResources(), R.mipmap.face2)));
        }
        PersonAdapter adapter = new PersonAdapter();
        lvPerson.setAdapter(adapter);
    }

    class PersonAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mPersonList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder pholder = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),R.layout.personitem,  null);
                pholder = new ViewHolder();
                pholder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image);
                pholder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                pholder.tvSex = (TextView) convertView.findViewById(R.id.tv_sex);
                pholder.tvYanzhi = (TextView) convertView.findViewById(R.id.tv_yanzhi);
                pholder.tvHobby = (TextView) convertView.findViewById(R.id.tv_hobby);
                convertView.setTag(pholder);
            } else {
                pholder = (ViewHolder) convertView.getTag();
            }
            Person person = mPersonList.get(position);
            person.setBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.face2));
            person.setName(pholder.tvName.getText().toString());
            person.setSex(pholder.tvSex.getText().toString());
            person.setYanZhi(pholder.tvYanzhi.getText().toString());
            person.setHobby(pholder.tvHobby.getText().toString());
            return convertView;
        }

    }

    class ViewHolder {
        private ImageView ivImage;
        private TextView tvName;
        private TextView tvSex;
        private TextView tvYanzhi;
        private TextView tvHobby;
    }


}
