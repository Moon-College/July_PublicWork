package com.example.administrator.myapplication.adapter.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/7/26 0026.
 */
public class ImageLoader {
    private static ImageLoader instance = new ImageLoader();
    private String imagePath;
    private ImageLoader(){};
    //获取系统运行时内存
    private int size = (int) (Runtime.getRuntime().maxMemory()/8);
    //创建个lrucache
    private LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(size){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
    public static ImageLoader getInstance(){
        return instance;
    }

    public void displayImage(ImageView iv, String path) {
        Bitmap bitmap = cache.get(path);
        if (bitmap == null){
            //说明内存中没有缓存图片,去本地获取
            ImageLoaderTask task = new ImageLoaderTask(iv);
            Log.i("MEPAI","路径:"+path);
            task.execute(path);
        }else{
            //说明从内存中获取到图片了,直接加载到图片上
            iv.setImageBitmap(bitmap);
        }
    }

    class ImageLoaderTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView iv;
        public ImageLoaderTask(ImageView iv) {
            this.iv = iv ;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = getBitMapByZoom(params);

            return bitmap;
        }

        private Bitmap getBitMapByZoom(String[] params) {
            //对图片进行缩放处理
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(params[0],options);
            //获取图片的宽高属性
            int mapWidth = options.outWidth;
            int mapHeight = options.outHeight;
            //获取控件的宽高
            int ivWidth = iv.getWidth();
            int ivHeight = iv.getHeight();
            //定义一个比例
            int scale = 1;
            int scaleX = 1;
            int scaleY = 1;
            //对2者进行比较
            if (mapWidth>=ivWidth &&ivWidth!=0){
                scaleX = Math.round(mapWidth/ivWidth);
            }
            if (mapHeight>=ivHeight&&ivHeight!=0){
                scaleY = Math.round(mapHeight/ivHeight);
            }
            if (scaleX>1||scaleY>1){
            scale = scaleX>scaleY?scaleX:scaleY;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            //生成bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(params[0], options);
            //将生成的bitmap图片缓存到内存里面去
            Log.i("MEPAI","路径...:"+params[0]);
            cache.put(params[0],bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv.setImageBitmap(bitmap);
        }
    }
}
