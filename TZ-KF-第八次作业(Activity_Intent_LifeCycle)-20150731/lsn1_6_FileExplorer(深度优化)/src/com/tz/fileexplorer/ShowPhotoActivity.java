package com.tz.fileexplorer;

import com.tz.fileexplorer.util.BitmapUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShowPhotoActivity extends Activity {
	public static final String TAG = "ShowPhotoActivity";
	public static final String ACTION = "com.tz.fileexplorer.ShowPhotoActivity";
	public static final String EXTRA_PATH = "extra.path";
	ImageView iv;
	private Dialog dlg;
	private Bitmap bmp;

	void showProgress() {
		cancelProgress();
		dlg = ProgressDialog.show(this, "", "reading");
	}

	void cancelProgress() {
		if (dlg != null && dlg.isShowing()) {
			dlg.cancel();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_photo_act);
		iv = (ImageView) findViewById(R.id.iv);
		final String path = getIntent().getStringExtra(EXTRA_PATH);
		if (path != null) {
			new AsyncTask<String, Void, Bitmap>() {

				@Override
				protected void onPreExecute() {
					showProgress();
				}

				@Override
				protected Bitmap doInBackground(String... params) {
					return BitmapUtil.shrinkBitmap(path, 1080 * 1920 * 4);
				}

				@Override
				protected void onPostExecute(Bitmap result) {
					iv.setImageBitmap(bmp = result);
					cancelProgress();
				}
			}.execute(path);

		}
	}

	@Override
	protected void onDestroy() {
		if (bmp != null && !bmp.isRecycled()) {
			bmp.recycle();
		}
		super.onDestroy();
	}
}
