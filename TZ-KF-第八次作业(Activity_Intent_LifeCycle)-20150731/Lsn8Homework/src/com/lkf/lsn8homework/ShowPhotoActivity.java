package com.lkf.lsn8homework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShowPhotoActivity extends Activity {
	public static final String TAG = "ShowPhotoActivity";
	public static final String ACTION = "com.lkf.lsn8homework.ShowPhotoActivity";
	Button btn_open_carmara;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_photo_act);
		btn_open_carmara = (Button) findViewById(R.id.btn_open_carmara);
		btn_open_carmara.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1);
			}
		});

		iv = (ImageView) findViewById(R.id.iv);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult");
		try {
			Bitmap bmp = (Bitmap) data.getExtras().get("data");
			iv.setImageBitmap(bmp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
