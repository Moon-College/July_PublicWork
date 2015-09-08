package com.tz.fileexplorer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.tz.fileexplorer.view.ScalableImageView;

public class ImageActivity extends Activity {

	
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		iv = (ScalableImageView) findViewById(R.id.iv);
		//获取传过来的uri路径
		String path = getIntent().getData().getPath();
		new ImageLoaderTask().execute(path);
	}
	
	class ImageLoaderTask extends AsyncTask<String, Void, Void>{

		private Bitmap bm;

		@Override
		protected Void doInBackground(String... params) {
			//有些图片可能太多，解码不出来，有待解决
			bm = BitmapFactory.decodeFile(params[0]);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			iv.setImageBitmap(bm);
		}
	}
	
}
