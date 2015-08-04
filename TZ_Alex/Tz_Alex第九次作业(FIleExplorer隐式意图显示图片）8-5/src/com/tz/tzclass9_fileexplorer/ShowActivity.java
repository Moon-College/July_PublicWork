package com.tz.tzclass9_fileexplorer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends Activity {

	private ImageView iv_show;
	private TextView tv_show;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		iv_show = (ImageView) findViewById(R.id.iv_PicGallery);
		tv_show = (TextView) findViewById(R.id.tv_fileInfo);
		Intent intent = getIntent();
		Uri data = intent.getData();
		String path = data.getPath();
		Log.i("++++++++++++++", path);
		String str = path.toLowerCase();
		if(str.endsWith("gif") || str.endsWith("png")
				|| str.endsWith("jpeg") || str.endsWith("jpg")){
			Bitmap bm = BitmapFactory.decodeFile(path);
			iv_show.setImageBitmap(bm);
		}else{
			try {
				File file = new File(path);
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer))!=-1) {
					baos.write(buffer, 0, len);				
				}
				tv_show.setVisibility(View.VISIBLE);
				iv_show.setVisibility(View.INVISIBLE);
				tv_show.setText(baos.toString());
				fis.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
