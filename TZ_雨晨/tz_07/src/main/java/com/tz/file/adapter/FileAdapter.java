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
import com.tz.file.util.ImageUtil;

import java.util.List;

/**
 * Created by Tokey on 2015/7/25.
 */
public class FileAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<AFile> mDatas;
    private ImageUtil imageUtil;


    public FileAdapter(Context mContext, List<AFile> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageUtil = ImageUtil.getInstance();
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
            //设置图片加载时显示的图片
            viewHolder.icon.setImageResource(R.mipmap.ic_launcher);
            //异步线程处理图片加载
            LoadImgTask loadImg = new LoadImgTask(viewHolder.icon);
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


    class LoadImgTask extends AsyncTask<String, Void, Bitmap> {

        ImageView iv;

        public LoadImgTask(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String imgPath = params[0];
            String position = params[1];
            Bitmap bitmap = null;
            try {

                //判断缓存中是否有图片
                if(imageUtil.getBitmapFromMemoryCahce(imgPath)==null){
                    //缓存中没有图片
                    bitmap = ImageUtil.getBitmapByCompress(imgPath, 120);
                    //添加图片到缓存
                    imageUtil.setBitmapToMemoryCache(imgPath,bitmap);
                }else {
                    bitmap = imageUtil.getBitmapFromMemoryCahce(imgPath);
                }

                mDatas.get(Integer.parseInt(position)).setIcon(bitmap);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
//            //刷新
//            notifyDataSetChanged();
            iv.setImageBitmap(bitmap);

        }
    }

}
