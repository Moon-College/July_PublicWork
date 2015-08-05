package com.tz.filesbrowser.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.filesbrowser.bean.MyFile;
import com.tz.filesbrowser.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 屈发 on 2015/8/3.
 */
public class MyFileAdapter extends BaseAdapter {
    private final Context context;
    private List<MyFile> list = new ArrayList<>();

    public MyFileAdapter(List<MyFile> list, Context context) {
        this.list = list;
        this.context = context;
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
        MyFile myFile = list.get(position);
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_file, null);
            holder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (myFile.getIcon() == null) {

            ImageLoadAsyncTask task = new ImageLoadAsyncTask();
            task.execute(myFile.getPath(), position + "");
        } else {
            holder.iv_icon.setImageBitmap(myFile.getIcon());
        }

        holder.tv_name.setText(myFile.getName());

        return view;
    }

    public class ViewHolder {
        public ImageView iv_icon;
        private TextView tv_name;
    }


    class ImageLoadAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String path = params[0];
            String position = params[1];
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                list.get(Integer.parseInt(position)).setIcon(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notifyDataSetChanged();
        }
    }
}
