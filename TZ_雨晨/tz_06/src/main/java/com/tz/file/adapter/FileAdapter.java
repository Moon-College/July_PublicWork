package com.tz.file.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.file.R;
import com.tz.file.bean.AFile;

import java.util.List;

/**
 * Created by Tokey on 2015/7/25.
 */
public class FileAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<AFile> mDatas;


    public FileAdapter(Context mContext, List<AFile> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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


        AFile mFile = mDatas.get(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_listview, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mFile.getName());
        if (mFile.getIcon() == null) {//当前文件是一个图片

            //异步线程处理图片加载
            LoadImgTask loadImg = new LoadImgTask();
            //传入图片的路径，postion
            loadImg.execute(mFile.getPath(), position + "");

        } else {//当前文件是一个文件夹或文件
            viewHolder.icon.setImageBitmap(mFile.getIcon());
        }


        return convertView;
    }


    class ViewHolder {
        ImageView icon;
        TextView name;
    }


    class LoadImgTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            String imgPath = params[0];
            String position = params[1];

            try {
                Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
                mDatas.get(Integer.parseInt(position)).setIcon(bitmap);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //刷新
            notifyDataSetChanged();

        }
    }

}
