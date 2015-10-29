package com.tz.imageloader;

import com.tz.imageloader.util.ImageLoader;
import com.tz.imageloader.util.ImageLoaderCallBack;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends Activity {

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
				Toast.makeText(ImageActivity.this, id+"", 1).show();
				iv.setImageDrawable(drawable);
			}
		});
	}

	private void initView() {
		iv = (ImageView) findViewById(R.id.iv_content);
	}


}
