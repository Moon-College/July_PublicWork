package com.mumu.intenttest;

import com.mumu.intenttest.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	
	private final int REQUEST_CODE_IMAGE_CAPTURE = 1000;

	private Button btn_caputre;
	private Button btn_intent;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		iv = (ImageView) findViewById(R.id.iv);
		btn_caputre = (Button) findViewById(R.id.btn_caputre);
		btn_caputre.setOnClickListener(this);
		btn_intent = (Button) findViewById(R.id.btn_intent);
		btn_intent.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_caputre:
			startCamera();
			break;
		case R.id.btn_intent:
			startActivityByImplicitIntent();
			break;
		default:
			break;
		}
	}

	private void startActivityByImplicitIntent() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		//隐式调用系统应用
		//intent.setAction(Intent.ACTION_DIAL);   
		//intent.setData(Uri.parse("tel:110"));
		//隐式调用自定义第二个Activity
		intent.setAction(".SecondActivity"); 
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE_IMAGE_CAPTURE){
			Bitmap bitmap = data.getParcelableExtra("data");
			if(bitmap != null){
				iv.setImageBitmap(bitmap);
			}
		}
	}	
	
	//隐式请求相机服务
	private void startCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE);
	}

}
