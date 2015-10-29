package com.tz.imageloader.util;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

/**
 * 从sd卡中加载缓存的网络图片
 * 
 * @author zhao_jx
 * 
 */
public class SdCardImageLoader {
	
	private static final String TAG="SdCardImageLoader";
	
	// 定义图片保存路径
	private static final String IMAGE_PATH = "/mnt/sdcard"
			+ File.separator
			+ "myapp";
	
	private static SdCardImageLoader sdCardImageLoader;
	
	private SdCardImageLoader(){
		
	}
	public static SdCardImageLoader getInstance(){
		if(sdCardImageLoader==null)
			sdCardImageLoader=new SdCardImageLoader();
		return sdCardImageLoader;
	}
	/**
	 * 根据url获取缓存在SD卡中的图片
	 * @param url
	 * @return
	 */
	public Drawable getImage(String url) {
		Drawable drawable = null;
		String key=generateKeyByUrl(url);
		//判断文件夹存不存在，不存在就创建
		File parentfile =new File(IMAGE_PATH);
		if(!parentfile.exists()){
			parentfile.mkdirs();
		}
		File file =new File(IMAGE_PATH+File.separator+key);
		//如果文件不存在，返回null
		if(!file.exists()){
			return null;
		}
		//文件存在，构建Drawable对象
		Bitmap bitmap=BitmapFactory.decodeFile(file.getAbsolutePath()); 
		drawable=new BitmapDrawable(bitmap);
		//将图片缓存到软引用
		if (drawable != null) {
			ImageLoader.getInstance().cacheImageToSoftReference(url, drawable);
		}
		return drawable;
	}

	/**
	 * 将drawable缓存到SD卡
	 * @param url
	 * @param drawable
	 */
	public void putImage(String url,Bitmap bitmap) {
		try {
			if(url==null||"".equals(url)||bitmap==null){
				return ;
			}
			String key=generateKeyByUrl(url);
			Log.i(TAG,""+IMAGE_PATH+File.separator+key);
			File file =new File(IMAGE_PATH+File.separator+key);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据url生成key值(用MD5进行加密)
	 * 
	 * @param url
	 * @return
	 */
	public String generateKeyByUrl(String url) {
		if (url == null || "".equals(url)) {
			return null;
		}
		String key = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bs = url.getBytes();
			StringBuilder sb = new StringBuilder(40);
			for (byte x : bs) {
				if ((x & 0xff) >> 4 == 0) {
					sb.append("0").append(Integer.toHexString(x & 0xff));
				} else {
					sb.append(Integer.toHexString(x & 0xff));
				}
			}
			key = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
}
