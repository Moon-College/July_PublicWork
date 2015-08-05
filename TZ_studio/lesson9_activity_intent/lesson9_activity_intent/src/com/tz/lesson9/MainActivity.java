package com.tz.lesson9;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends BaseActivity implements OnClickListener {

	private Button btn_take_photo;
	private Button btn_Implicit;

	private static final String IMAGE_FILE_LOCATION =Environment.getExternalStorageDirectory()+"/temp.jpg";// temp
																				// file
	Uri imageUri = Uri.fromFile(new File(IMAGE_FILE_LOCATION));// The Uri to store the big
													// bitmap
	private static final int TAKE_PHOTO_CODE = 0;
	private static final int CROP_PICTURE_CODE = 0x1;



	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentLayout(R.layout.activity_main);
		btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
		btn_Implicit = (Button) this.findViewById(R.id.btn_Implicit);
		btn_take_photo.setOnClickListener(this);
		btn_Implicit.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_take_photo:// 拍照
			execTakePhoto();
			break;

		case R.id.btn_Implicit:// 隐式意图
			Intent intent=new Intent();
			intent.setAction("com.action.implicit");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			startActivity(intent);
			break;
		}

	}

	private void execTakePhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// action is
																	// capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, TAKE_PHOTO_CODE);// or TAKE_SMALL_PICTU
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PHOTO_CODE://拍照
			cropImageUri(imageUri, 800, 400, CROP_PICTURE_CODE);
			break;

		case CROP_PICTURE_CODE://裁剪图片
			if (imageUri != null) {
				Bitmap bitmap = decodeUriAsBitmap(imageUri);
				imageView.setImageBitmap(bitmap);
			}
			break;

		}
	}

	/**
	 * 
	 * 此方法描述的是： 裁剪图片
	 * cropImageUri
	 * @param uri
	 * @param outputX
	 * @param outputY
	 * @param requestCode void
	 */
	private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 2);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		startActivityForResult(intent, requestCode);

	}
	/**
	 * 
	 * 此方法描述的是：根据uri加载图片
	 * cropImageUri
	 * @param uri
	 * @param outputX
	 * @param outputY
	 * @param requestCode void
	 */
	private Bitmap decodeUriAsBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;

	}
}
