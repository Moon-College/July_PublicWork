package com.tz.photo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	
	private static final int PICK_CODE = 0X110;
	private String currentPhotoStr;
	private Button mImageButton;
	private ImageView mPhoto;
	private Bitmap photoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageButton = (Button) findViewById(R.id.getImage);
        mPhoto = (ImageView) findViewById(R.id.photo);
    }
    public void click(View view){
    	Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, PICK_CODE);
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_CODE) {
			if (data != null) {
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, null, null,
						null, null);
				cursor.moveToFirst();

				int idx = cursor
						.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
				currentPhotoStr = cursor.getString(idx);
				cursor.close();
				// 压缩图片
				resizePhoto();
				mPhoto.setImageBitmap(photoImage);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 *********************************************** 
	 * 功能描述：压缩图片<br>
	 * 创建人：商文通<br>
	 * 创建日期：2015-5-18<br>
	 * 修改记录：<br>
	 * 
	 *********************************************** 
	 */
	private void resizePhoto() {
		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inJustDecodeBounds = true;
		// 图片路径和尺寸(宽、高)
		BitmapFactory.decodeFile(currentPhotoStr, option);

		double ration = Math.max(option.outWidth * 1.0d / 1024f,
				option.outHeight * 1.0d / 1024f);

		option.inSampleSize = (int) Math.ceil(ration);
		option.inJustDecodeBounds = false;
		// 压缩后
		photoImage = BitmapFactory.decodeFile(currentPhotoStr, option);

	}


   
}
