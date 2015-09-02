package com.example.lsn14_asyntask_handler;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yolanda.nohttp.base.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadManager;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.download.StatusCode;

public class RemoteLoadApkActivity extends Activity {
	private TextView tv_notice;
	private ProgressBar pb;
	private TextView tv_progress;
	
	private String apkUrl = "http://wap.eoemarket.com/apps/download/id/745634";

	private final int DOWNLOAD_BRFORE = 0;
	private final int DOWNLOAD_DURING = 1;
	private final int DOWNLOAD_AFTER = 2;
	
	private String apkPath = SDCardUtils.getSDCardPath();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_loadapk);
		tv_notice = (TextView)findViewById(R.id.tv_notice);
		pb = (ProgressBar)findViewById(R.id.pb);
		pb.setMax(100);
		tv_progress = (TextView)findViewById(R.id.tv_progress);
		
	}
	
	
	public void btnClick(View view) {
		String filePathName = apkPath + "ttlgd.apk";
		File apkFile = new  File(filePathName);
		if(apkFile.exists()) {
			installApkFromLocalPath(filePathName);
		} else {
			LoadApkAsynTask task = new LoadApkAsynTask();
			task.execute(apkUrl);
		}
	}
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch (msg.what) {
				case DOWNLOAD_BRFORE:
					tv_notice.setText("准备下载安装包Apk");
					break;
				case DOWNLOAD_DURING:
					tv_notice.setText("正在下载安装包Apk");
					if(msg.arg1 > 0) {
						pb.setProgress(msg.arg1);
						tv_progress.setText(msg.arg1 + "%");
					}
					break;
				case DOWNLOAD_AFTER:
					tv_notice.setText("下载完成, 开始安装");
					tv_progress.setText("100%");
					
					File apkFile = new File((String)msg.obj);
					if(apkFile.exists()) {
						installApkFromLocalPath((String)msg.obj);
					} else {
						Toast.makeText(RemoteLoadApkActivity.this, "apk不存在", 0).show();
					}
					break;
	
				default:
					break;
			}
			
		}
	};
	
	/**
	 * 从本地安装apk
	 * @param apkPathName
	 */
	 private void installApkFromLocalPath(String apkPathName){ 
		   Intent intent = new Intent(); 
		   intent.setAction(Intent.ACTION_VIEW); //向用户显示数据 
		   //第一种方法： 隐式安装
		   intent.setDataAndType(Uri.parse("file://"+apkPathName), "application/vnd.android.package-archive"); 
		   //第二种方法
		   //intent.setDataAndType(Uri.fromFile(new File(apkPathName)), "application/and.android.package-archive"); 
		   startActivity(intent); 
	 } 

	
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
	
	class LoadApkAsynTask extends AsyncTask<String, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			handler.sendEmptyMessage(DOWNLOAD_BRFORE);
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			DownloadRequest downloadRequest = new DownloadRequest(params[0], RequestMethod.GET);
			downloadRequest.setFileDir(apkPath);
			downloadRequest.setFileName("ttlgd.apk");
			DownloadManager.getInstance(RemoteLoadApkActivity.this).download(downloadRequest, 0, downloadListener);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
		
	}
	
}
