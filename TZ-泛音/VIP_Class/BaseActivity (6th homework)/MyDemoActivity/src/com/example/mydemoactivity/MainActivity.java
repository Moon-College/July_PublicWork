package com.example.mydemoactivity;

import com.example.template.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 
 * @author   泛音
 * @date     2015-08-01 
 * @describe 构造抽象模板类作业
 */
public class MainActivity extends BaseActivity {
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setCustmoTitleBtnUI();
		setOnBtnListener();
	}

	private void setOnBtnListener() {
		getLeftButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                      Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
			}
		});
		getRightButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void setCustmoTitleBtnUI() {
		setLeftBtnText("修改左边");
		setRightBtnText("修改右边");
		setMidContent("中间被改");
	}

	@Override
	public void setContentView() {
		setMain(R.layout.activity_main);
	}

	@Override
	public void initParams() {
		System.out.println("initParams");
	}

	@Override
	public void initView() {
		System.out.println("initView");
		button1 = (Button) findViewById(R.id.button1);
	}

	@Override
	public void initListener() {
		System.out.println("initListener");
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("OnClickListener");
			}
		});
	}

	@Override
	public void regBroadcastReceiver() {
		System.out.println("regBroadcastReceiver");
	}

	@Override
	public void unregBroadcastReceiver() {
		System.out.println("unregBroadcastReceiver");
	}

	@Override
	public void beforeExit() {
		System.out.println("beforeExit");
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
