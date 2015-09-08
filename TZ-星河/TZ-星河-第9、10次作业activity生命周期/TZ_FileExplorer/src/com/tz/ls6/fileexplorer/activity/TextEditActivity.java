package com.tz.ls6.fileexplorer.activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tz_fileexplorer.R;

public class TextEditActivity extends Activity {
	private EditText editor;
	private Button btnCancel, btnSave;
	private String orignContent;
	private String path;
	private boolean isSaved;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_edit_activity);
		System.out.println(isSaved);
		initView();
		//如果不为空，说明是意外销毁了activity，再次创建了activity
		if (savedInstanceState != null) {
			String content = savedInstanceState.getString("content");
			String path=savedInstanceState.getString("path");
			String orignContent=savedInstanceState.getString("orignContent");
			boolean isSaved=savedInstanceState.getBoolean("isSaved");
			if (content != null) {
				editor.setText(content);
				this.path=path;
				this.orignContent=orignContent;
				this.isSaved=isSaved;
			}
		}else{
			isSaved=true;
			Intent intent = getIntent();
			if (intent != null) {
				System.out.println("getIntent");
				path = intent.getStringExtra("path");
				final File file = new File(path);
				new AsyncTask<String, Void, String>() {
					@Override
					protected String doInBackground(String... params) {
						StringBuilder sb = new StringBuilder();
						BufferedReader reader = null;
						try {
							reader = new BufferedReader(new FileReader(file));
							String line = null;
							while ((line = reader.readLine()) != null) {
								sb.append(line);
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (reader != null) {
								try {
									reader.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						return sb.toString();
					}

					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						orignContent = result;
						editor.setText(result);
					}

				}.execute("");
			}
		}
		System.out.println(isSaved);
	}

	private void initView() {
		editor = (EditText) findViewById(R.id.et_editor);
		btnCancel = (Button) findViewById(R.id.bt_cancel);
		btnSave = (Button) findViewById(R.id.bt_save);
		// 监听文本是否改变
		editor.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if(!s.toString().equals(orignContent)){
					isSaved = false;
				}
				
			}
		});
		OnClickListener listener = new MyOnclickListener();
		btnCancel.setOnClickListener(listener);
		btnSave.setOnClickListener(listener);
	}

	private class MyOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bt_cancel:
				isSaved = true;
				if (orignContent != null)
					editor.setText(orignContent);
				break;
			case R.id.bt_save:
				final String text = editor.getText().toString().trim();
				new AsyncTask<String, Void, Boolean>() {
					@Override
					protected Boolean doInBackground(String... params) {
						BufferedWriter writer = null;
						try {
							writer = new BufferedWriter(new FileWriter(path));
							writer.write(text);
							writer.flush();
							isSaved = true;
							return true;
						} catch (IOException e) {
							e.printStackTrace();
							return false;
						} finally {
							if (writer != null) {
								try {
									writer.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					protected void onPostExecute(Boolean result) {
						if(result)
							Toast.makeText(TextEditActivity.this, "保存成功",
									Toast.LENGTH_SHORT).show();
						else
							Toast.makeText(TextEditActivity.this, "保存失败，请重试",
									Toast.LENGTH_SHORT).show();
					}
				}.execute("");
				break;
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			if (!isSaved) {
				AlertDialog.Builder builder = new Builder(this);
				AlertDialog dialog = builder.setTitle("警告").setMessage("你确定放弃修改吗？")
						.setPositiveButton("确定", new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								isSaved=true;
								dialog.dismiss();
								TextEditActivity.this.finish();
							}
	
						})
						.setNegativeButton("取消", new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).create();
				dialog.show();
			} else {
				finish();
			}
			break;
		}
		
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		System.out.println("onSaveInstanceState");
		String text = editor.getText().toString().trim();
		System.out.println(text);
		outState.putString("content", text);
		outState.putString("orignContent", orignContent);
		outState.putBoolean("isSaved", isSaved);
		outState.putString("path", path);
	}
}
