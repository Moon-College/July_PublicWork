package com.example.tz_724_filelistview.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {

	private static ImageLoader imageLoader;
	private LruCache<String, Bitmap> lruCache;
	
	private ImageLoader()
	{
    //得到当前app运行时占用的最大内存
	long maxMemory= Runtime.getRuntime().maxMemory();	
	 lruCache = new LruCache<String, Bitmap>((int) (maxMemory/8)){
		 @Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// 重写此方法来衡量每一张图片的大小，默认返回图片的数量大小
				//或者getByteCount() =getRowBytes*getHeight()
				
				return bitmap.getRowBytes()*bitmap.getHeight()/1024;
			}
		 
	    };
	}
	
	public static ImageLoader getInstance()
	{
		if(imageLoader == null)
		{
			imageLoader = new ImageLoader();
		}
		return imageLoader;
	}
	/**
	 * 添加图片到内存
	 * @param bitmap
	 */
	public void addBitmapToMemory(String path,Bitmap bitmap)	
	{
		if(getBitmapFromMemory(path))
		{
			return;
		}
		lruCache.put(path, bitmap);
	}
	/**
	 * 从内存中拿到Bitmap
	 * @param path
	 * @return
	 */
	public Bitmap getBitmapFromMemory(String path)
	{
		return lruCache.get(path);
	}
	
	
	
	
	/**
	 * 根据传入的参数返回压缩图片
	 * 
	 * @return
	 */
	public Bitmap getCompressionBitmapForRequest(String imgpath,float requset)
	{
	BitmapFactory.Options options = new BitmapFactory.Options();	
	//设置只读取图片宽高不需要解析图片
	options.inJustDecodeBounds =true;
	BitmapFactory.decodeFile(imgpath, options);
	//得到图片宽高
	int width = options.outWidth;
	int height = options.outHeight;
	
	//解析得到真实的图片不仅仅是宽高等数据
	options.inJustDecodeBounds = false;
	//压缩比例
	int inSampleSize =0;
	if(requset>0)
	{
	 inSampleSize = Math.max(width/(int)requset, height/(int)requset);
	}
	options.inSampleSize = inSampleSize;
	
	return BitmapFactory.decodeFile(imgpath, options);
	}

}
