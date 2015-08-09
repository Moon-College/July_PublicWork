package com.tz.fileexplorer;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.fileexplorer.util.FileUtil;
import com.tz.fileexplorer.util.MyLog;

public class TextEditActivity extends Activity {

	private EditText et_edittext;

	private Button bt_back;
	private Button bt_save;

	private File file;// 被编辑的文件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_edit);
		// 初始化控件
		initView();

		// 从bundle中获取保存的文件内容
		boolean isSave = false;
		if (savedInstanceState != null) {
			String textcontent = savedInstanceState.getString("textcontent");
			String path = savedInstanceState.getString("path");
			if (textcontent != null && !textcontent.trim().equals("")) {
				file=new File(path);
				et_edittext.setText(textcontent);
				isSave = true;
			}
		}
		if (!isSave) {
			// 从意图中获取文件，并将其内容显示
			Intent intent = getIntent();
			Uri uri = intent.getData();
			String path = uri.getPath();
			file = new File(path);
			String result = FileUtil.getFileContent(file);
			et_edittext.setText(result);
		}

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		et_edittext = (EditText) findViewById(R.id.et_textedit);
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_save = (Button) findViewById(R.id.bt_save);

		bt_save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String textcontent = et_edittext.getText().toString();
				boolean isSuccess = FileUtil.saveFileContent(file, textcontent);
				Toast.makeText(TextEditActivity.this,
						isSuccess ? "保存成功" : "保存失败", Toast.LENGTH_LONG).show();
			}
		});

		bt_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				TextEditActivity.this.finish();
			}
		});
	}

	/**
	 * 被系统杀死时调用，保存资源
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		MyLog.i("jzhao", "onSaveInstanceState begin...");
		outState.putString("textcontent", et_edittext.getText().toString());
		outState.putString("path", file.getAbsolutePath());

		super.onSaveInstanceState(outState);
	}

}
