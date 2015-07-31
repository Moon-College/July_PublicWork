package com.hac.tz_homework5;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hac.tz_homework5.model.Item;
import com.hac.tz_homework5.util.ImageLoader;

import java.util.List;

/**
 * Created by hp on 2015/7/25.
 */
public class MyAdapter extends BaseAdapter {

    List<Item> itemList;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    //int mPosition;

    public MyAdapter(Context context, List<Item> itemList) {
        inflater = LayoutInflater.from(context);
        this.itemList = itemList;
        //获得ImageLoader对象
        imageLoader = ImageLoader.getInstance();
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
        //mPosition = position;
        //重用convertView
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_lv_file, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_dir = (TextView) convertView.findViewById(R.id.tv_dir);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = itemList.get(position);
        //若该项为图片
        if(item.isPic()) {
            holder.img.setImageResource(R.drawable.file);
            //为ImageView设置tag为需要加载的路径，防止错位
            holder.img.setTag(itemList.get(position).getDir());
            //启动异步任务加载图片
            LoadImgTask task = new LoadImgTask();
            task.execute(item.getDir(), holder.img);
        }
        //若该项为文件
        else if(item.isFile()){
            holder.img.setImageResource(R.drawable.file);
        }
        //若该项为目录
        else if(item.isDir()) {
            holder.img.setImageResource(R.drawable.dirs);
        }
        //设置文件名
        holder.tv_name.setText(item.getName());
        //设置路径
        holder.tv_dir.setText(item.getDir());

        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView tv_name;
        TextView tv_dir;
    }

    //加载图片的异步任务
    class LoadImgTask extends AsyncTask<Object, Void, Bitmap> {
        ImageView img;
        String path;
        //int postion;

        /**
         * @param params 传入图片的路径和ImageView
         * @return 加载的bitmap图像，同时作为onPostExecute的传入参数
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            path = (String) params[0];
            img = (ImageView)params[1];
           // postion = (Integer)params[2];

            Bitmap bitmap;
            //如果缓存中不存在改图片
            if((bitmap=imageLoader.getBitmapFromCache(path))==null) {
                Log.i("cache", path+" not in cache");
                //从文件中加载图片，并将其存入缓存中
                bitmap = imageLoader.decodeBitmapFromFile(path, 80, 80);
                imageLoader.addBitmapToCache(path, bitmap);
            }

            return bitmap;
        }

        /**
         * @param result 加载的bitmap
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //对ImageView的tag进行判断，若tag等于加载中的路径，则显示图片。
            //若不相等，则说明在上个图片加载期间，item已经划出屏幕，tag发生了变化，不需要显示
            if(img.getTag()!=null && img.getTag().equals(path))
                //设置img的图像为result
                img.setImageBitmap(result);
        }
    }

}
