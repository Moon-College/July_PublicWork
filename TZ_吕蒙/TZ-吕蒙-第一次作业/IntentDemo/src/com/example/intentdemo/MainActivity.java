package com.example.intentdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	//设置Intent的Request Code
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE=1;

	
	
	private Button IMG;
	private ImageView image;
	private Uri fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		image=(ImageView) findViewById(R.id.image);
		
		IMG=(Button) findViewById(R.id.IMG);		
		IMG.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				startIntent();	
			}
		});
			
	}
	
	//启动隐式Intent
	void startIntent(){
		Intent intent=new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置存储照片Action
//		
//		fileUri = getOutputFileUri();  // create a file to save the video
//		
//	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name	     
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode==Activity.RESULT_OK){
				
				   Bundle extras = data.getExtras();
				   Bitmap bmp = (Bitmap) extras.get("data");
				   image.setImageBitmap(bmp); 

			}else if(resultCode==Activity.RESULT_CANCELED){
				Log.i("RESULT_CANCELED", "User cancelled the image capture");
			}
			else{
				Log.i("Not OK OR Cancel","image capture failed");
			}		
		}	
	}
	
	
	// Convert a file to Uri to save the IMG OR Video
	private Uri getOutputFileUri(){		
		return Uri.fromFile(getOutputMediaFile(MEDIA_TYPE_IMAGE));
	}
	
	//创建一个媒体文件
	private File getOutputMediaFile(int type){
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "IntentDemo");
		
		if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("IntentDemo", "failed to create directory");
	            return null;
	        }
	    }
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile = null;
		
		if (type == MEDIA_TYPE_IMAGE){
			mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	         timeStamp + ".jpg");
			}
		return mediaFile;
	}
	}

