package com.tz.lsn6.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * 图片加载工具类 图片缓存核心类 注意使用了LRU算法 将最近不怎么使用的图片回收 linkedHashMap 使用强引用保存
 * @author dallon2
 *
 */
public class ImageLoader {
	
	private static ImageLoader mImageLoader;
	private static LruCache<String, Bitmap> mMemoryCache; //需要V4兼容包
	
	//单例模式，防止MemoryCache出现多个
	public static ImageLoader getInstance() {
		if(mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	
	/**
	 * 图片缓存核心(最近不怎么使用的图片回收，尽量保持最近使用比较多的)
	 */
	public ImageLoader() {
		
		//maxSize 最多缓存多大内存（获得应用程序最大可用内在的8分之一）
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory/8;
		mMemoryCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				//此方法来衡量每一张图片的大小，默认返回图片数量大小
				return bitmap.getRowBytes() * bitmap.getHeight()/1024;
			}
			
		};
	}
	
	/**
	 * 将一张图片缓存到LruCache中
	 * 将图片路径作为图片缓存的key
	 */
	public void addBitmapToMemoryCache(String path, Bitmap bitmap) {
		if(getBitmapFromMemoryCache(path) != null) {
			mMemoryCache.put(path, bitmap);
		}
	}
	
	/**
	 * 从缓存LruCache中取一张图片
	 * 需要图片的路径作为key来取图片
	 */
	public Bitmap getBitmapFromMemoryCache(String path) {
		return mMemoryCache.get(path);
	}
	
	/**
	 * 图片按宽度压缩
	 * @param iconFile
	 * @param reqWdith
	 * @return 返回压缩好的图片
	 */
	public static Bitmap decodeBitmapFromResource(String iconFile, int reqWdith) {
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		
		//不读取图片本身，而是只解析图片的宽高
		options.inJustDecodeBounds = true; 
		BitmapFactory.decodeFile(iconFile, options);
		// 得到原始图片的宽高  
		int width = options.outWidth;
		int height = options.outHeight;
		//默认压缩比例为1 即不 保持原来尺寸大小
		int inSampleSize = 1;
		int widthRate = 1;
		
		//如果实际像素比需要压缩的像素大才进行压缩  否则保持原来尺寸大小
		if(width > reqWdith) {
			//计算出实际宽度与目标宽度的比率
			widthRate = Math.round(width/reqWdith);
			inSampleSize = widthRate;
		} 
		
		if(height/inSampleSize > reqWdith * 1.5) {
			inSampleSize = Math.round((float)(height * 1.5/reqWdith));
		}
		
		//读取图片本身
		options.inJustDecodeBounds = false;
		//设置压缩比例 （ 为2 宽和高一起压缩 0.5 丢了3/4）
		options.inSampleSize = inSampleSize;
		//得到压缩后的图片
		Bitmap bitmap = BitmapFactory.decodeFile(iconFile, options);
		
		return bitmap;
	}
	
	/**
	 * 图片压缩  将图片文件  经过一定的压缩处理 返回一个bitmap
	 */
	public static Bitmap decodeBitmapFromResoure(String path,int reWidth){
		BitmapFactory.Options options = new BitmapFactory.Options();
		//获取宽高  
		options.inJustDecodeBounds = true;
		
		BitmapFactory.decodeFile(path, options);
		int width = options.outWidth;
		int inSampleSize = 1;
		if (width >reWidth) {
			//计算出实际宽度和目标宽度的比率
			int widthRatio = Math.round((float)width/(float)reWidth);
			inSampleSize = widthRatio;
		}
		
		//正式压缩
		options.inJustDecodeBounds =false;	
		options.inSampleSize =inSampleSize;

		return BitmapFactory.decodeFile(path, options);
		
	}
}
