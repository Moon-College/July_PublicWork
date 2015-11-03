package com.tz.volleylrucache.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.tz.volleylrucache.MyApplication;
import com.tz.volleylrucache.cache.libcore.io.DiskLruCache;
import com.tz.volleylrucache.cache.libcore.io.DiskLruCache.Editor;
import com.tz.volleylrucache.cache.libcore.io.DiskLruCache.Snapshot;

@SuppressLint("NewApi")
public class LruImageCache extends LruCache<String, Bitmap> implements
		ImageCache {

	private DiskLruCache dislru;

	@Override
	protected int sizeOf(String key, Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// SDK>=3.1使用的方法不一样
			/**
			 * SDK的兼容性开发： 我们经常碰到高版本使用的方法和低版本使用的方法不一样。
			 * 比如：bitmap.getByteCount()在12的api才能调用得到 就应该判断版本号选用不同的方法
			 * 如果检测编译都不能通过，则：
			 * 
			 * @SuppressLint("NewApi")
			 * @disable
			 */
			return bitmap.getByteCount();
		} else {
			return bitmap.getRowBytes() * bitmap.getHeight();
		}
	}

	/**
	 * 
	 * @param directory
	 *            磁盘缓存目录
	 * @param diskMaxSize
	 *            磁盘缓存最大存储
	 * @param memoryMaxSize
	 *            内存缓存最大储
	 */
	public LruImageCache(File directory, int diskMaxSize, int memoryMaxSize) {
		super(memoryMaxSize);
		System.out.println("memoryMaxSize:" + memoryMaxSize);
		try {
			dislru = DiskLruCache.open(directory,
					getAppVersion(MyApplication.application), 1, diskMaxSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getAppVersion(Context context) {
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 从URL获取图片
	 */
	@Override
	public Bitmap getBitmap(String url) {
		System.out.println("getBitmap--url:" + url);
		Bitmap bitmap = null;
		try {
			// 从内存lru获取图片
			Log.i("jzhao", "从内存lru获取图片");
			bitmap = get(url);
			String key = generateKey(url);
			if (bitmap == null) {
				// 从sd卡获取图片
				Log.i("jzhao", "从sd卡获取图片");
				Snapshot snapshot = dislru.get(key);
				if (snapshot != null) {
					InputStream is = snapshot.getInputStream(0);
					bitmap = BitmapFactory.decodeStream(is);
					// 放到内存
					if (bitmap != null) {
						put(url, bitmap);
					}
				} else {
					Log.i("jzhao", "从网络获取图片");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 缓存URL图片
	 */
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		System.out.println("putBitmap--str:" + url);
		String key = generateKey(url);
		// 缓存到内存
		Log.i("jzhao", "缓存到内存");
		put(url, bitmap);
		// 缓存到sd卡
		try {
			Log.i("jzhao", "缓存到sd卡");
			if (dislru.get(key) == null) {
				Editor editor = dislru.edit(key);

				OutputStream ops = editor.newOutputStream(0);

				boolean compress = bitmap.compress(CompressFormat.JPEG, 100,
						ops);
				if (compress) {
					editor.commit();
				} else {
					editor.abort();
				}
				dislru.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String generateKey(String url) {
		String cacheKey = null;
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(url.getBytes());
			byte[] digest = mDigest.digest();
			// byte转成16进制的字符串(效果：根据url，加密，后生成一串固定长度的字符串)
			cacheKey = bytesToHexString(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheKey;

	}

	// byte转成16进制的字符串
	private static String bytesToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hexString = Integer.toHexString(0xFF & bytes[i]);
			if (hexString.length() == 1) {
				sb.append('0');
			}
			sb.append(hexString);
		}
		return sb.toString();
	}

}
