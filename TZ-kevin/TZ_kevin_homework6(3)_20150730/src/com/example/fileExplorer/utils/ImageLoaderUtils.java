package com.example.fileExplorer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * @author kevin.li
 * @version 1.0.1
 * @create 20150728
 * @function 图片加载工具类 圖片緩存核心類 注意使用了LRU算法 将最近不怎么使用的图片回收 linkedHashMap 使用强引用保存
 */
public class ImageLoaderUtils {
	private static ImageLoaderUtils instance;
	private static LruCache<String, Bitmap> cache;

	/**
	 * 构造方法
	 */
	private ImageLoaderUtils() {
		// 获取最大内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		// 缓存内存
		int cacheMemory = maxMemory / 8;
		cache = new LruCache<String, Bitmap>(cacheMemory) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// 重写此方法来衡量每一张图片的大小 保存图片数量
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};

	}

	/**
	 * 使用单例模式创建
	 * 
	 * @return
	 */
	public static ImageLoaderUtils getInstance() {
		if (null == instance) {
			instance = new ImageLoaderUtils();
		}
		return instance;

	}

	/**
	 * 将图片缓存到LruCache中 图片的路径 是 key 图片是value
	 */
	public void addBitmapToMemoryCache(String key, Bitmap value) {

		if (null != getBitmapFromMemoryCache(key)) {
			cache.put(key, value);

		}
	}

	/**
	 * 从LruCache里面获取一张图片 读取图片的路径作为key
	 */
	public Bitmap getBitmapFromMemoryCache(String path) {
		return cache.get(path);

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
