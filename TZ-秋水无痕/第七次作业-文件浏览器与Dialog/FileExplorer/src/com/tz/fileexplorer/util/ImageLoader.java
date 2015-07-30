package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
    /**
     * ͼƬ���������(���������ôʹ�õ�ͼƬ����;�����������ʹ�ñȽ϶��)
     */
    private static LruCache<String,Bitmap> mMemoryCache;
    private static ImageLoader mImageLoader;
    
    private ImageLoader(){
        //maxSize��໺�����ڴ�(���Ӧ�ó����������ڴ�/8)
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxSize = maxMemory/8;
        mMemoryCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // ��д�˷���������ÿһ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ��������С
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }
    
    //����ģʽ����ֹmMemoryCache���ֶ��
    public static ImageLoader getIntance(){
       if (mImageLoader == null) {
           mImageLoader = new ImageLoader();
       }
       return mImageLoader;
    }
    
    /**
     * ��һ��ͼƬ���浽LruCache��
     * ��ͼƬ��·����ΪͼƬ�����key
     */
    public void addBitmapToMemoryCache(String path,Bitmap bitmap){
        if (getBitmapFromMemoryCache(path) == null) {
            mMemoryCache.put(path, bitmap);
        }
    }
    
    /**
     * ��LruCache����ȡһ��ͼƬ
     * ����ҪͼƬ��·����Ϊkey
     */
    public Bitmap getBitmapFromMemoryCache(String path) {
        // TODO Auto-generated method stub
        return mMemoryCache.get(path);
    }
    
    /**
     * ͼƬѹ��
     * ��ȡһ��ͼƬ�ļ���ͨ��ѹ���㷨ѹ��������һ��Bitmap����
     */
    public static Bitmap decodeBitmapFromResource(String iconPath,int reqWidth){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //1.�õ�ԭʼͼƬ�Ŀ��
        //����ȡͼƬ�������ǽ���ͼƬ�Ŀ��
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(iconPath, options);
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth) {
            //�����ʵ�ʿ�Ⱥ�Ŀ���ȵı���
            int widthRatio = Math.round((float)width/(float)reqWidth);
            inSampleSize = widthRatio;
        }
        //2.ѹ�����ͼƬ���reqWidth
        //��ʼѹ��ͼƬ
        //��ͼƬ����
        options.inJustDecodeBounds = false;
        //����ѹ������(���磺2����͸�һ��ѹ��1/2)
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(iconPath, options);
    }
}
