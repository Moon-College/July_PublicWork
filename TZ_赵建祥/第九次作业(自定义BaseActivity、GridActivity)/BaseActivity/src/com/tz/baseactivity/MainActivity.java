package com.tz.baseactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * 自己的BaseActivity
 * @author 赵建祥
 *
 */
public class MainActivity extends BaseActivity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("自定义Activity");
		// 设置内容
		setBaseContentView(R.layout.main);
		// 设置背景色
		setBaseContentBackGroundColor(Color.RED);

		// 获取返回按钮，设置点击事件
		Button back = getBt_back();
		Button more = getBt_more();

		back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("提醒");
				builder.setMessage("确定要退出吗？");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		});

		more.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "正在获取更多信息...",
						Toast.LENGTH_LONG).show();
			}
		});
		
		//获取网络状态
		int netState=this.netState;
		Toast.makeText(this, NET_STATE[netState], Toast.LENGTH_LONG).show();
	}
}