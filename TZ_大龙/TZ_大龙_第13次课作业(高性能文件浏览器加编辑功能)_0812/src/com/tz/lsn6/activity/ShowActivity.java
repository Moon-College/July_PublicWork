package com.tz.lsn6.activity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.lsn6.MainActivity;
import com.tz.lsn6.fileexplorer.R;

public class ShowActivity extends Activity {
	
	private ImageView iv_picture;
	private TextView tv_txt;
	private StringBuffer buffer;
	public final static int REQUEST_CODE = 100;
	public final static int RESULT_CODE = 101;
	private String path;
	private Button bt_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		iv_picture = (ImageView)findViewById(R.id.iv_picture);
		tv_txt = (TextView)findViewById(R.id.tv_txt);
		bt_edit = (Button)findViewById(R.id.bt_edit);
		
		Intent intent = getIntent();
		path = intent.getExtras().getString("path");
		
		if(MainActivity.FILE_TYPE_IMG.equals(intent.getExtras().getString("type"))) {
			showPicture();
			tv_txt.setVisibility(View.GONE);
			iv_picture.setVisibility(View.VISIBLE);
			bt_edit.setVisibility(View.GONE);
		} else if(MainActivity.FILE_TYPE_TXT.equals(intent.getExtras().getString("type"))){
			showTxt();
			iv_picture.setVisibility(View.GONE);
			tv_txt.setVisibility(View.VISIBLE);
			bt_edit.setVisibility(View.VISIBLE);
		} else {
			Toast.makeText(this, "error", 0).show();
		}
		

	}
	
	private void showPicture() {
		
		iv_picture = (ImageView)findViewById(R.id.iv_picture);
		Bitmap bm = BitmapFactory.decodeFile(path);
		iv_picture.setImageBitmap(bm);
	}
	
	private void showTxt() {
		buffer = new StringBuffer();
	      try {
	  	      FileInputStream fis = new FileInputStream(path);
	  	      //文件编码Unicode,UTF-8,ASCII,GB2312,Big5
	  	      InputStreamReader isr = new InputStreamReader(fis, "GB2312");
	  	      Reader in = new BufferedReader(isr);
	  	      int ch;
	  	      while ((ch = in.read()) > -1) {
	  	          buffer.append((char)ch);
	  	      }
	  	      in.close();
	  	      tv_txt.setText(buffer.toString());  //buffer.toString())就是读出的内容字符 搜索
	      } catch (IOException e) {
	    	  tv_txt.setText("文件不存在!");
	      }
	      
//	      System.out.println("buffer:" + buffer.toString());
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_edit:
			Intent intent = new Intent();
			intent.putExtra("content", buffer.toString());
			intent.setClass(this, EditTextActivity.class);
			startActivityForResult(intent, REQUEST_CODE);
			break;
		default:
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
			String newContent = data.getExtras().getString("content");
			try {
				FileOutputStream fos = new FileOutputStream(path);
				byte [] bytes = newContent.getBytes(); 
				fos.write(bytes);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			showTxt();
		}
		
		
	}
}
