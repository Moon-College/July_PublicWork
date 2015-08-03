package com.ws.intent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Notification.Action;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn;
	private ImageView img;
	private String root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.btn);
		img = (ImageView) findViewById(R.id.img);
		btn.setText("�������");

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// �ж�SD���Ƿ����
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					root = Environment.getExternalStorageDirectory()
							.getAbsolutePath();
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
				} else {
					Toast.makeText(MainActivity.this, "SD��������", 1).show();
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
//		Bundle bundle =data.getExtras();
		String path = root+"/�������/";
		FileOutputStream b=null;
		//��ȡ������ص����ݣ���תΪBitmap��ʽ
		Bitmap bitmap = (Bitmap) data.getExtras().get("data");
		File file = new File(path);
			//�����ļ���
			file.mkdirs();
			String fileName = path+"111.jpg";
			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				try {
					b.flush();
					b.close();  
				} catch (IOException e) {
					e.printStackTrace();
				}  
				img.setImageBitmap(bitmap);

			}
		}
	}

}
