package com.lisn_6_file.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class Imageloader {
	
	//ʹ��v4���ݰ�������ݵͰ汾
	private static LruCache<String, Bitmap> mMoryCache;
	private static Imageloader mImageLoadder;
	private Imageloader(){
		//maxSize��໺�����ڴ棨���Ӧ�ó�������ڴ�/8��
		int maxMemory=(int) Runtime.getRuntime().maxMemory();
	    int maxSize=maxMemory/8;
	    mMoryCache=new LruCache<String, Bitmap>(maxSize){
	    	@Override
	    	protected int sizeOf(String key, Bitmap bitmap) {
	    		// ���´˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ��������С
	    		return bitmap.getRowBytes()*bitmap.getHeight()/1024;
	    	}
	    };
	}
	//����ģʽ����ֹmMoryCache���ֶ��
	public static Imageloader getIntance(){
		if(mImageLoadder==null){
			mImageLoadder=new Imageloader();
		}
		return mImageLoadder;
		
	}
	/**
	 * ��һ��ͼƬ���浽Lrucache
	 */
	public void addBitmapCache(String path,Bitmap bitmap){
		if(getBitmapFromMemoryCache(path)==null){
			mMoryCache.put(path, bitmap);
		}
	}
	/**
	 * ��LrcCache����ȡһ��ͼƬ
	 * ����ҪͼƬ��·����Ϊkey
	 */
	public Bitmap getBitmapFromMemoryCache(String path){
		return mMoryCache.get(path);
	}
	/**
	 * 
	 */
/**
 * ͼƬѹ��
 * ��ȡһ��ͼƬ�ļ���ͨ��ѹ���㷨������BitMap����
 * 
 */
	public static Bitmap decodeitmapForm(String path,int reqwidth){
		BitmapFactory.Options option=new BitmapFactory.Options();
		//�õ�ͼƬ�߶�
		option.inJustDecodeBounds=true;//����ȡͼƬ��������ֻ����ͼƬ���
		BitmapFactory.decodeFile(path,option);//inJustDecodeBounds=trueʱ����ֵΪ��
		//int hight=option.outHeight;
		int width=option.outWidth;
		int inSampleSize=1;
		if(width>reqwidth){
			//����ѹ������
			int widthRitio=Math.round((float)width/(float)reqwidth);
			inSampleSize=widthRitio;
		}
		option.inJustDecodeBounds=false;
		//����ѹ������
		option.inSampleSize=inSampleSize;
		
		return BitmapFactory.decodeFile(path,option);
		
	}
}
