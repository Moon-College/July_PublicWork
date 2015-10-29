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

	// �����ڴ������û���
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
	 * ��url��Ӧ��ͼƬ���浽������
	 * @param url
	 * @param drawable
	 */
	public void cacheImageToSoftReference(String url,Drawable drawable){
		cacheSoftReferenceMap.put(url, new SoftReference<Drawable>(drawable));
	}

	/**
	 * ��URL��ȡͼƬ ��������������ʵ�֣� 1�� ������ 2 SD�� 3���������ȡ��
	 * 
	 * @param url
	 *            ͼƬURL
	 * @param callBack
	 *            ������ͼƬ��ͼƬ���ػص�
	 * @return
	 */
	public Drawable getDrawableFromUrl(final String url, final ImageLoaderCallBack callBack) {
		//urlΪ�շ���null
		if(url==null||"".equals(url)){
			return null;
		}
		drawable = null;
		//1���ȴ��ڴ���������ȡ
		SoftReference<Drawable> sfDrawable=cacheSoftReferenceMap.get(url);
		if(sfDrawable!=null&&sfDrawable.get()!=null){
			Log.i(TAG, "�ӻ����л�ȡ");
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
			//�����̴߳��ڳ��ж�ȡͼƬ
			new Thread(){
				public void run(){
					//2��������û�У���SD��ȡ
					drawable=SdCardImageLoader.getInstance().getImage(url);
					Log.i(TAG, "��SD���л�ȡ");
					Message message=handler.obtainMessage(1, drawable);
					if(drawable==null){
						Log.i(TAG, "�������ȡ");
						//3��SD��Ҳû�У��������ȡ
						Bitmap bitmap=HttpUtil.doImageGetByHttpURLConnection(url);
						if(bitmap!=null){
							drawable=new BitmapDrawable(bitmap);
							message=handler.obtainMessage(2, drawable);
							//���浽���������á�SD��
							cacheImageToSoftReference(url, drawable);
							SdCardImageLoader.getInstance().putImage(url, bitmap);
						}
					}
					//��������Ƿ��ȡ��ͼƬ������callback
					handler.sendMessage(message);
					Log.i(TAG, "getDrawableFromUrl Thread END...");
				}
			}.start();
		}
		Log.i(TAG, "getDrawableFromUrl END...");
		return drawable;
	}
}
