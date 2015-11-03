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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// SDK>=3.1ʹ�õķ�����һ��
			/**
			 * SDK�ļ����Կ����� ���Ǿ��������߰汾ʹ�õķ����͵Ͱ汾ʹ�õķ�����һ����
			 * ���磺bitmap.getByteCount()��12��api���ܵ��õõ� ��Ӧ���жϰ汾��ѡ�ò�ͬ�ķ���
			 * ��������붼����ͨ������
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
	 *            ���̻���Ŀ¼
	 * @param diskMaxSize
	 *            ���̻������洢
	 * @param memoryMaxSize
	 *            �ڴ滺�����
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
	 * ��URL��ȡͼƬ
	 */
	@Override
	public Bitmap getBitmap(String url) {
		System.out.println("getBitmap--url:" + url);
		Bitmap bitmap = null;
		try {
			// ���ڴ�lru��ȡͼƬ
			Log.i("jzhao", "���ڴ�lru��ȡͼƬ");
			bitmap = get(url);
			String key = generateKey(url);
			if (bitmap == null) {
				// ��sd����ȡͼƬ
				Log.i("jzhao", "��sd����ȡͼƬ");
				Snapshot snapshot = dislru.get(key);
				if (snapshot != null) {
					InputStream is = snapshot.getInputStream(0);
					bitmap = BitmapFactory.decodeStream(is);
					// �ŵ��ڴ�
					if (bitmap != null) {
						put(url, bitmap);
					}
				} else {
					Log.i("jzhao", "�������ȡͼƬ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * ����URLͼƬ
	 */
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		System.out.println("putBitmap--str:" + url);
		String key = generateKey(url);
		// ���浽�ڴ�
		Log.i("jzhao", "���浽�ڴ�");
		put(url, bitmap);
		// ���浽sd��
		try {
			Log.i("jzhao", "���浽sd��");
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
			// byteת��16���Ƶ��ַ���(Ч��������url�����ܣ�������һ���̶����ȵ��ַ���)
			cacheKey = bytesToHexString(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheKey;

	}

	// byteת��16���Ƶ��ַ���
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
