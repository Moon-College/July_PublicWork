package com.house.fileexplorer.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片缓存
 * 
 * @author House
 *
 */
public class ImageLoader {

	/**
	 * 图片缓存核心类（将最近使用少的图片回收，尽量保持最近使用次数多的图片）
	 */

	private static LruCache<String, Bitmap> mMemoryCache;
	private static ImageLoader mImageLoader;

	private ImageLoader() {
		//maxSize最多缓存多大内存(获得应用程序最大可用内存/8)
		// 获取应用可用的最大内存数
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int maxSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(maxSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// 重写此方法来衡量每张图片的方法，默认返回图片的数量大小  返回kb
				return bitmap.getRowBytes() / bitmap.getHeight() / 1024;
			}
		};
	}
	
	/**
	 * 将图片缓存到LruCache中
	 * 将图片的路径作为图片缓存的key
	 * @param path 图片的路径
	 * @param bitmap 图片
	 */
	public void addBitmapAddMemoryCache(String path, Bitmap bitmap){
		if (getBitmapFromMemoryCache(path) == null) { // 如果缓存中不存在这张图片，则缓存；
			mMemoryCache.put(path, bitmap);
		}
	}

	/**
	 * 根据key从缓存中取图片
	 * @param path
	 * @return
	 */
	public Bitmap getBitmapFromMemoryCache(String path){
		return mMemoryCache.get(path);
	}
	
	/**
	 * 使用单利模式，避免没MemoryCache重复创建
	 * @return
	 */
	public static ImageLoader getInstance() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
}
