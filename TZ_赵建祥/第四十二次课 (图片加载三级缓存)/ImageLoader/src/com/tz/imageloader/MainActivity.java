package com.tz.imageloader;

import com.tz.imageloader.util.ImageLoader;
import com.tz.imageloader.util.ImageLoaderCallBack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView iv;
	public static final String IMAGE_URL="http://192.168.136.5:8080/httpserver/jh.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		//º”‘ÿÕº∆¨
		loadImage();
	}

	/**
	 * º”‘ÿÕº∆¨
	 */
	private void loadImage() {
		
		ImageLoader.getInstance().getDrawableFromUrl(IMAGE_URL, new ImageLoaderCallBack() {
			@Override
			public void loadedImage(int id,Drawable drawable) {
				Toast.makeText(MainActivity.this, id+"", 1).show();
				iv.setImageDrawable(drawable);
			}
		});
	}

	private void initView() {
		iv = (ImageView) findViewById(R.id.iv_content);
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent=new Intent(MainActivity.this,ImageActivity.class);
				startActivity(intent);
			}
		});
	}


}
