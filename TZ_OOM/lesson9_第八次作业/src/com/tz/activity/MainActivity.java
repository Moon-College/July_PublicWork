package com.tz.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {

	private static final int REQUEST_CODE = 0x000001;
	
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContent(R.layout.activity_main);
		
		setTitleText("MainActivity");
		
		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	public void openGridActivity(View v) {
		Intent intent = new Intent(this, TestGridActivity.class);
		
		startActivity(intent);
	}
	
	public void openActivity(View v){
		Intent intent = new Intent("com.tz.activity.FRIST_ACTIVITY");
		startActivity(intent);
	}
	
	
	public void takePickture(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQUEST_CODE);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resuleCode, Intent data) {
		super.onActivityResult(requestCode, resuleCode, data);
		if(requestCode == REQUEST_CODE) {
			if(resuleCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");
				imageView.setImageBitmap(bitmap);
			}
		}
		
	}
}
