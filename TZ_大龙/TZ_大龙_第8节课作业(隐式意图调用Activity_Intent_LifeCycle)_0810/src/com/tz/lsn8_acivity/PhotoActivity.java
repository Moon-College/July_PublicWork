package com.tz.lsn8_acivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends Activity  implements OnClickListener{
	private Button bt_openSysPhoto;
	private final int PHOTO_REQUEST_CODE = 810;
	private ImageView iv_photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		
		bt_openSysPhoto = (Button)findViewById(R.id.bt_openSysPhoto);
		bt_openSysPhoto.setOnClickListener(this);
		
		iv_photo = (ImageView)findViewById(R.id.iv_photo);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, PHOTO_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(PHOTO_REQUEST_CODE == requestCode) {
			Bitmap bm = (Bitmap) data.getExtras().get("data");
			if(bm != null) {
				iv_photo.setImageBitmap(bm);
			}
		}
	}
	
}
