package com.tz.imageloader.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ImageLoader {
	
	public static final String TAG="ImageLoader";

	// 定义内存软引用缓存
	private HashMap<String, SoftReference<Drawable>> cacheSoftReferenceMap;

	private static ImageLoader imageLoader;

	private Drawable drawable;

	private ImageLoader() {
		cacheSoftReferenceMap= new HashMap<String, SoftReference<Drawable>>();
	}

	public static ImageLoader getInstance() {
		if(imageLoader==null)
			imageLoader=new ImageLoader();
		return imageLoader;
	}
	
	/**
	 * 将url对应的图片缓存到软引用
	 * @param url
	 * @param drawable
	 */
	public void cacheImageToSoftReference(String url,Drawable drawable){
		cacheSoftReferenceMap.put(url, new SoftReference<Drawable>(drawable));
	}

	/**
	 * 从URL获取图片 （采用三级缓存实现： 1级 软引用 2 SD卡 3、从网络获取）
	 * 
	 * @param url
	 *            图片URL
	 * @param callBack
	 *            加载完图片把图片返回回调
	 * @return
	 */
	public Drawable getDrawableFromUrl(final String url, final ImageLoaderCallBack callBack) {
		//url为空返回null
		if(url==null||"".equals(url)){
			return null;
		}
		drawable = null;
		//1、先从内存软引用中取
		SoftReference<Drawable> sfDrawable=cacheSoftReferenceMap.get(url);
		if(sfDrawable!=null&&sfDrawable.get()!=null){
			Log.i(TAG, "从缓存中获取");
			drawable=sfDrawable.get();
			callBack.loadedImage(0,drawable);
		} else {
			final Handler handler=new Handler(){
				@Override
				public void handleMessage(Message msg) {
					int what=msg.what;
					callBack.loadedImage(what,drawable);
				}
			};
			//启用线程从内程中读取图片
			new Thread(){
				public void run(){
					//2、软引用没有，从SD卡取
					drawable=SdCardImageLoader.getInstance().getImage(url);
					Log.i(TAG, "从SD卡中获取");
					Message message=handler.obtainMessage(1, drawable);
					if(drawable==null){
						Log.i(TAG, "从网络获取");
						//3、SD卡也没有，从网络获取
						Bitmap bitmap=HttpUtil.doImageGetByHttpURLConnection(url);
						if(bitmap!=null){
							drawable=new BitmapDrawable(bitmap);
							message=handler.obtainMessage(2, drawable);
							//缓存到劳务软引用、SD卡
							cacheImageToSoftReference(url, drawable);
							SdCardImageLoader.getInstance().putImage(url, bitmap);
						}
					}
					//最后无论是否获取到图片，调用callback
					handler.sendMessage(message);
					Log.i(TAG, "getDrawableFromUrl Thread END...");
				}
			}.start();
		}
		Log.i(TAG, "getDrawableFromUrl END...");
		return drawable;
	}
}
