package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
    /**
     * 图片缓存核心类(将最近不怎么使用的图片回收;尽量保持最近使用比较多的)
     */
    private static LruCache<String,Bitmap> mMemoryCache;
    private static ImageLoader mImageLoader;
    
    private ImageLoader(){
        //maxSize最多缓存多大内存(获得应用程序最大可用内存/8)
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxSize = maxMemory/8;
        mMemoryCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每一张图片的大小，默认返回图片的数量大小
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }
    
    //单例模式，防止mMemoryCache出现多个
    public static ImageLoader getIntance(){
       if (mImageLoader == null) {
           mImageLoader = new ImageLoader();
       }
       return mImageLoader;
    }
    
    /**
     * 将一张图片缓存到LruCache中
     * 将图片的路径作为图片缓存的key
     */
    public void addBitmapToMemoryCache(String path,Bitmap bitmap){
        if (getBitmapFromMemoryCache(path) == null) {
            mMemoryCache.put(path, bitmap);
        }
    }
    
    /**
     * 从LruCache里面取一张图片
     * 读需要图片的路径作为key
     */
    public Bitmap getBitmapFromMemoryCache(String path) {
        // TODO Auto-generated method stub
        return mMemoryCache.get(path);
    }
    
    /**
     * 图片压缩
     * 读取一个图片文件，通过压缩算法压缩后，生成一个Bitmap返回
     */
    public static Bitmap decodeBitmapFromResource(String iconPath,int reqWidth){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //1.得到原始图片的宽度
        //不读取图片本身，而是解析图片的宽高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(iconPath, options);
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth) {
            //计算出实际宽度和目标宽度的比率
            int widthRatio = Math.round((float)width/(float)reqWidth);
            inSampleSize = widthRatio;
        }
        //2.压缩后的图片宽度reqWidth
        //开始压缩图片
        //读图片本身
        options.inJustDecodeBounds = false;
        //设置压缩比例(比如：2，宽和高一起压缩1/2)
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(iconPath, options);
    }
}
