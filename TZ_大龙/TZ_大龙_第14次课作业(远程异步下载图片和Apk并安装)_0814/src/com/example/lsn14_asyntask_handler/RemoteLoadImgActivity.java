package com.example.lsn14_asyntask_handler;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yolanda.nohttp.base.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadManager;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.download.StatusCode;

public class RemoteLoadImgActivity extends Activity {
	private TextView tv_notice;
	private ProgressBar pb;
	private TextView tv_progress;
	private int pb_MaxValue = 0;
	int index = 0;
	
	private String[] imageUrl = {
			"http://image6.tuku.cn/pic/wallpaper/meinv/guoziMMdushiyoujizhutisheyingdatu/019.jpg",
			"http://c.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f98db59264272dd42a2834a419.jpg"
			};

	private final int DOWNLOAD_BRFORE = 0;
	private final int DOWNLOAD_DURING = 1;
	private final int DOWNLOAD_AFTER = 2;
	
	private String imgPath = SDCardUtils.getSDCardPath();
	private ImageView iv_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_loadimg);
		tv_notice = (TextView)findViewById(R.id.tv_notice);
		pb = (ProgressBar)findViewById(R.id.pb);
		pb.setMax(100);
		tv_progress = (TextView)findViewById(R.id.tv_progress);
		
		iv_img = (ImageView)findViewById(R.id.iv_img);
		
	}
	
	
	public void btnClick(View view) {
		System.out.println("》》》index:" + index + " imageUrl.length：" + imageUrl.length);
		if(index < imageUrl.length) {
			LoadImgAsynTask task = new LoadImgAsynTask();
			task.execute(imageUrl);
		} else {
			Toast.makeText(this, "没有更多图片", 0).show();
		}
	}
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch (msg.what) {
				case DOWNLOAD_BRFORE:
					tv_notice.setText("准备下载图片");
					break;
				case DOWNLOAD_DURING:
					tv_notice.setText("正在下载图片");
					if(msg.arg1 > 0) {
						pb.setProgress(msg.arg1);
						tv_progress.setText(msg.arg1 + "%");
					}
					break;
				case DOWNLOAD_AFTER:
					System.out.println(">>>>>>>>>>>>>>>>>>DOWNLOAD_AFTER");
					tv_notice.setText("下载完成");
					Bitmap bm = BitmapFactory.decodeFile((String)msg.obj);
					iv_img.setImageBitmap(bm);
					break;
	
				default:
					break;
			}
			
		}
	};

	
	private DownloadListener downloadListener = new DownloadListener() {
		
		@Override
		public void onStart(int what) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgress(int what, int progress) {
			// TODO Auto-generated method stub
			System.out.println(">>>>>>>progress:" + progress);
			Message msg = handler.obtainMessage();
			msg.what = DOWNLOAD_DURING;
			msg.arg1 = progress;
			handler.sendMessage(msg);
		}
		
		@Override
		public void onFinish(int what, String filePath) {
			// TODO Auto-generated method stub
			System.out.println(">>>>>>>onFinish>>>>>>>>>>filePath:" + filePath);
			Message msg = handler.obtainMessage();
			msg.what = DOWNLOAD_AFTER;
			msg.obj = filePath;
			handler.sendMessage(msg);
		}
		
		@Override
		public void onDownloadError(int what, StatusCode statusCode) {
			// TODO Auto-generated method stub
		System.out.println(">>>error-->statusCode:" + statusCode);	
		}
	};
	
	class LoadImgAsynTask extends AsyncTask<String, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			handler.sendEmptyMessage(DOWNLOAD_BRFORE);
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			DownloadRequest downloadRequest = new DownloadRequest(params[index], RequestMethod.GET);
			
			if(index < imageUrl.length) {
				++ index;
			}

			downloadRequest.setFileDir(imgPath);
			downloadRequest.setFileName(index + "_tz.jpg");
			DownloadManager.getInstance(RemoteLoadImgActivity.this).download(downloadRequest, 0, downloadListener);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
		
	}
	
}
