package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
	private static ImageLoader imageLoader;
	
	public static ImageLoader getInstance(){
		if(imageLoader==null){
			imageLoader=new ImageLoader();
		}
		return imageLoader;
	}
	
	public LruCache<String, Bitmap> lru=null;//new LruCache<String, Bitmap>(maxSize)
	
	private ImageLoader(){
		//拿出可以内存的1/8做为缓存
		int maxSize=Math.round(Runtime.getRuntime().freeMemory()/8);
		lru=new LruCache<String, Bitmap>(maxSize);
	}
	
	/**
	 * 从LRU内存缓存中获取图片
	 * @param path
	 * @return
	 */
	public Bitmap getImageFromLruCache(String path){
		return lru.get(path);
	}

	/**
	 * 将图片加入LRU内存缓存
	 * @param path
	 * @param image
	 */
	public void addImageToLruCache(String path,Bitmap image){
		if(getImageFromLruCache(path)==null){
			lru.put(path, image);
		}
	}
	
			
			
			
	/**
	 * 将图片按一定宽度，进行成比例压缩图片
	 * 
	 * @param path
	 * @param toWidth
	 * @return
	 */
	public static Bitmap compressedImageFromResource(String path, int toWidth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 只解析图片宽高，不加载图片
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int outWidth = options.outWidth;
		int outHeight= options.outHeight;
		int inSampleSize = 1;// 压缩比例
		if (outWidth > toWidth||outHeight>toWidth) {
			//宽比高长，按宽度计算压缩比例
			if(outWidth>outHeight){
				inSampleSize = Math.round((float) outWidth / toWidth);
			}
			//高比宽长，按高度计算压缩比例
			else{
				inSampleSize = Math.round((float) outHeight / toWidth);
			}
		}
		// 设置压缩比例，并加载图片
		options.inSampleSize = inSampleSize;
		options.inJustDecodeBounds = false;
		Bitmap image = BitmapFactory.decodeFile(path, options);
		return image;

	}

}
