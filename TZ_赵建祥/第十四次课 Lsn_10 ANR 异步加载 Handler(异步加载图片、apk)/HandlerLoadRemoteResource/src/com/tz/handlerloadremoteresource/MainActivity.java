package com.tz.handlerloadremoteresource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 作业 1 利用异步任务实现远程图片加载需要进度条
 * http://wifi.189.cn/service/unionpay/images/unionpay_intro1.jpg 2
 * 利用异步任务实现远程apk下载并且安装，需要进度条
 * http://filelx.liqucn.com/upload/2015/anquan/360MobileSafe.ptada
 * 
 * @author 赵建祥
 * 
 */
public class MainActivity extends Activity {
	public static final String image_url = "http://wifi.189.cn/service/unionpay/images/unionpay_intro1.jpg";
	public static final String apk_url = "http://filelx.liqucn.com/upload/2015/anquan/360MobileSafe.ptada";
	public static final String savepath = MyConstant.INNER_SD_ABSOLUTE_PATH;

	private ProgressBar pb_imagedownload;
	private Button bt_imagedownload;

	private ProgressBar pb_apkdownload;
	private Button bt_apkdownload;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0) {
				// 设置图片原始大小
				pb_imagedownload.setMax(msg.arg1);
				// 设置图片下载大小
				pb_imagedownload.setProgress(msg.arg2);
				bt_imagedownload.setText(MathUtil.scale(
						pb_imagedownload.getProgress()
								/ ((double) pb_imagedownload.getMax()) * 100, 2)
						+ "%");
				if (pb_imagedownload.getProgress() == pb_imagedownload.getMax()) {
					Toast.makeText(MainActivity.this, "图片下载完成",
							Toast.LENGTH_SHORT).show();
				}
			}
			if (msg.what == 1) {
				// 设置图片原始大小
				pb_apkdownload.setMax(msg.arg1);
				// 设置图片下载大小
				pb_apkdownload.setProgress(msg.arg2);
				bt_apkdownload.setText(MathUtil.scale(
						pb_apkdownload.getProgress()
								/ ((double) pb_apkdownload.getMax()) * 100, 2)
						+ "%");
				if (pb_apkdownload.getProgress() == pb_apkdownload.getMax()) {
					Toast.makeText(MainActivity.this, "apk下载完成",
							Toast.LENGTH_SHORT).show();
					String path = (String) msg.obj;
					FileUtil.openFile(MainActivity.this, new File(path));

				}
			}

		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG = true;
		setContentView(R.layout.main);

		initView();
	}

	private void initView() {
		pb_imagedownload = (ProgressBar) findViewById(R.id.pb_imagedownload);
		bt_imagedownload = (Button) findViewById(R.id.bt_imagedownload);

		bt_imagedownload.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 异步下载图片
				new Thread() {
					public void run() {
						try {
							// what==0表示是图片
							down_file(image_url, savepath, 0);
						} catch (IOException e) {
							e.printStackTrace();
							// TODO Auto-generated catch block
							Toast.makeText(MainActivity.this, "下载失败",
									Toast.LENGTH_SHORT).show();
						}
					}
				}.start();

			}
		});

		pb_apkdownload = (ProgressBar) findViewById(R.id.pb_apkdownload);
		bt_apkdownload = (Button) findViewById(R.id.bt_apkdownload);

		bt_apkdownload.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 异步下载apk
				new Thread() {
					public void run() {
						try {
							// what==1表示是图片apk
							down_file(apk_url, savepath, 1);
						} catch (IOException e) {
							e.printStackTrace();
							// TODO Auto-generated catch block
							Toast.makeText(MainActivity.this, "下载失败",
									Toast.LENGTH_SHORT).show();
						}
					}
				}.start();
			}
		});
	}

	public void down_file(String url, String path, int what) throws IOException {
		// 获取文件名
		URL myURL = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
		int fileSize = conn.getContentLength();// 根据响应获取文件大小
		// 获取文件名
		String file = "";
		if (conn.getResponseCode() == 200) {
			file=FileUtil.getFileNameFromHttpURLConnection(conn);
		}

		if (fileSize <= 0)
			throw new RuntimeException("无法获知文件大小 ");
		if (is == null)
			throw new RuntimeException("stream is null");
		FileOutputStream fos = new FileOutputStream(path + "/" + file);
		// 把数据存入路径+文件名
		byte buf[] = new byte[1024];
		int downLoadFileSize = 0;
		do {
			// 循环读取
			int numread = is.read(buf);
			if (numread == -1) {
				break;
			}
			fos.write(buf, 0, numread);
			downLoadFileSize += numread;

			Message msg = handler.obtainMessage();
			msg.what = what;
			msg.arg1 = fileSize;
			msg.arg2 = downLoadFileSize;
			msg.obj = path + "/" + file;
			msg.sendToTarget();// 更新进度条
		} while (true);
		try {
			is.close();
			fos.close();
		} catch (Exception ex) {
			MyLog.i("jzhao", "error1: " + ex.getMessage());
		}
	}
}