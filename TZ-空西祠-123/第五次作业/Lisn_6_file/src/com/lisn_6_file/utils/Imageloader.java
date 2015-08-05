package com.lisn_6_file.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class Imageloader {
	
	//使用v4兼容包，会兼容低版本
	private static LruCache<String, Bitmap> mMoryCache;
	private static Imageloader mImageLoadder;
	private Imageloader(){
		//maxSize最多缓存多大内存（获得应用程序最大内存/8）
		int maxMemory=(int) Runtime.getRuntime().maxMemory();
	    int maxSize=maxMemory/8;
	    mMoryCache=new LruCache<String, Bitmap>(maxSize){
	    	@Override
	    	protected int sizeOf(String key, Bitmap bitmap) {
	    		// 重新此方法来衡量每张图片的大小，默认返回图片的数量大小
	    		return bitmap.getRowBytes()*bitmap.getHeight()/1024;
	    	}
	    };
	}
	//单例模式，防止mMoryCache出现多次
	public static Imageloader getIntance(){
		if(mImageLoadder==null){
			mImageLoadder=new Imageloader();
		}
		return mImageLoadder;
		
	}
	/**
	 * 将一张图片缓存到Lrucache
	 */
	public void addBitmapCache(String path,Bitmap bitmap){
		if(getBitmapFromMemoryCache(path)==null){
			mMoryCache.put(path, bitmap);
		}
	}
	/**
	 * 从LrcCache里面取一张图片
	 * 读需要图片的路径作为key
	 */
	public Bitmap getBitmapFromMemoryCache(String path){
		return mMoryCache.get(path);
	}
	/**
	 * 
	 */
/**
 * 图片压缩
 * 读取一个图片文件，通过压缩算法，生成BitMap返回
 * 
 */
	public static Bitmap decodeitmapForm(String path,int reqwidth){
		BitmapFactory.Options option=new BitmapFactory.Options();
		//得到图片高度
		option.inJustDecodeBounds=true;//不读取图片本身，而是只解析图片宽高
		BitmapFactory.decodeFile(path,option);//inJustDecodeBounds=true时返回值为空
		//int hight=option.outHeight;
		int width=option.outWidth;
		int inSampleSize=1;
		if(width>reqwidth){
			//计算压缩比例
			int widthRitio=Math.round((float)width/(float)reqwidth);
			inSampleSize=widthRitio;
		}
		option.inJustDecodeBounds=false;
		//设置压缩比例
		option.inSampleSize=inSampleSize;
		
		return BitmapFactory.decodeFile(path,option);
		
	}
}
