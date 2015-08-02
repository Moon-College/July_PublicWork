package com.tz.fileexplorer.util;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class LoadFileAsyncTask extends AsyncTask<String, Void, Bitmap> {
	private long maxBitmapSize = 0;
	private String path;

	public LoadFileAsyncTask() {
		//��󲻳���3Mһ��
		maxBitmapSize = 1048576 * 3;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// ��̨(�߳�)���洦�������
		// ����ͼƬ
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
