package com.tz.fileexplorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {

	private static ImageLoader mImageLoader;
	private ImageLoader() {}
	
	public synchronized static ImageLoader getInstance(){
		if (mImageLoader==null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}
	
	public Bitmap loadImage(String path,int widthAndHeight){
		BitmapFactory.Options opts = new BitmapFactory.Options();
		
		opts.inJustDecodeBounds = true;
		Bitmap pre = BitmapFactory.decodeFile(path, opts);
		int width = opts.outWidth;
		int ratio = Math.round((float)width/(float)widthAndHeight);
		opts.inSampleSize = ratio;
		opts.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, opts);
	}
}
