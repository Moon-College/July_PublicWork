package com.tz.fileexplorer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class TextViewActivtity extends Activity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tv = new TextView(this);
		ScrollView scrollView = new ScrollView(this);
		tv.setTextColor(Color.WHITE);
		tv.setBackgroundColor(Color.BLACK);
		scrollView.addView(tv);
		setContentView(scrollView);
		String data = getIntent().getData().getPath();
		File file = new File(data);
		new ShowViewTask().execute(file);
	}

	class ShowViewTask extends AsyncTask<File, Void, Void> {

		private ByteArrayOutputStream os;

		@Override
		protected Void doInBackground(File... params) {
			InputStream is = null;
			BufferedInputStream isB = null;
			try {
				is = new FileInputStream(params[0]);
				isB = new BufferedInputStream(is);
				os = new ByteArrayOutputStream();
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = isB.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null)
						is.close();
					if (isB != null)
						is.close();
					if (os != null)
						os.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			try {
				tv.setText(new String(os.toByteArray(), "GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}

	}
}
