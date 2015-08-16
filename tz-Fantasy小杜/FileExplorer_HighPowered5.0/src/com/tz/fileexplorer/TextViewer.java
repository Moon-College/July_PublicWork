package com.tz.fileexplorer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * created at:2015年8月5日 上午8:25:46 project name:FileExplorer_HighPowered
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:TextViewer.java description:
 */

public class TextViewer extends Activity implements OnClickListener {
	private Button save;
	private Button clear;
	private Button cancel;
	private TextView et_content;
	private File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_layout);
		initializeView();

		Uri uri = getIntent().getData();

		String path = uri.getPath();

		parseFile(path);

	}

	private void initializeView() {
		et_content = (TextView) findViewById(R.id.et_content);
		save = (Button) findViewById(R.id.btn_save);
		cancel = (Button) findViewById(R.id.btn_cancel);
		clear = (Button) findViewById(R.id.btn_clear);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
		clear.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		getMenuInflater().inflate(R.menu.text_menu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.save:
			try {

				// PrintWriter pw = new PrintWriter(file);
				// pw.print(content);
				// pw.close();
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter writer = new BufferedWriter(osw);
				String content = et_content.getText().toString();

				writer.write(content);
				writer.flush();

				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(getApplicationContext(), "saved successfully!",
					Toast.LENGTH_SHORT).show();

			break;
		case R.id.clear:

			et_content.setText(null);

			break;
		case R.id.cancel:
			finish();
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("content", et_content.getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			String content = savedInstanceState.getString("content");
			et_content.setText(content);
		}
	}

	private void parseFile(String path) {
		// TODO Auto-generated method stub

		file = new File(path);
		// tv_title.setText(file.getName());
		setTitle(file.getName());

		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "gb2312");
			BufferedReader br = new BufferedReader(isr);
			String buffer = null;
			StringBuilder sb = new StringBuilder();
			while ((buffer = br.readLine()) != null) {
				sb.append(buffer);
				sb.append("\n");

			}
			et_content.setText(sb);
			br.close();
			isr.close();
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {

	}
}
