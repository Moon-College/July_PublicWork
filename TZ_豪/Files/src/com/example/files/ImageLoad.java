package com.example.files;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoad {
   private static ImageLoad load;
   private static LruCache<String,Bitmap> cache;
	private ImageLoad(){
		int maxMemory=(int) Runtime.getRuntime().maxMemory();//获取应用程序最大给用内存
		int maxSize=maxMemory/5;
		cache=new LruCache<String, Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getRowBytes()*bitmap.getHeight()/1024;
			}
		};
	}
	
	public static ImageLoad getImaeLoad(){
		if(load==null){
			load=new ImageLoad();
		}
		return load;
		
	}
	
	public void addBitmapToMemoryCache(String path,Bitmap bitmap){
		if(getBitmapFromMemoryCache(path)==null){
			cache.put(path, bitmap);
		}
		
	}
	
	public Bitmap getBitmapFromMemoryCache(String path){
		return cache.get(path);
	}
	public static Bitmap getImgae(String path,int compressWidth){
		BitmapFactory.Options options=new BitmapFactory.Options();
		//不读取图片本身 只解析图片宽高
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		int width=options.outWidth;
		int height=options.outHeight;
		float scale=1;
		if(width>compressWidth){
			scale=width/compressWidth;
		}
		options.inJustDecodeBounds=false;
		options.inSampleSize=Math.round(scale);//压缩比例
		return BitmapFactory.decodeFile(path, options);
		
	}
}
