package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class LoadFileAsyncTask extends AsyncTask<String, Void, Bitmap> {
	private long maxBitmapSize = 0;
	private String path;

	public LoadFileAsyncTask() {
		//最大不超过3M一张
		maxBitmapSize = 1048576 * 3;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// 后台(线程)里面处理得事情
		// 加载图片
		Bitmap bmp = null;
		try {
			bmp = BitmapUtil.shrinkBitmap(path = params[0], maxBitmapSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null && !result.isRecycled()) {
			BitmapCache.getInstance().addBitmap(path, result);
		}
	}
}
