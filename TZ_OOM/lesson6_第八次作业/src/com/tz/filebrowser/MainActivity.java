package com.tz.filebrowser;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.filebrowser.adapter.FileAdapter;
import com.tz.filebrowser.bean.SDFile;
import com.tz.filebrowser.utils.FileUtil;

public class MainActivity extends FragmentActivity implements OnItemClickListener, OnItemLongClickListener {
	
	// SD卡根目录
	private File mRootFile = Environment.getExternalStorageDirectory();
	
	private ListView mListView;
	private FileAdapter adapter;

	private List<SDFile> sdFileList;
	private DecimalFormat  format;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		format = new DecimalFormat("###.00");
		sdFileList = new ArrayList<SDFile>();
		adapter = new FileAdapter(this, sdFileList);
		
		mListView = (ListView) findViewById(R.id.lv);
		mListView.setOnItemClickListener(this);
		mListView.setAdapter(adapter);
		mListView.setOnItemLongClickListener(this);
		if (FileUtil.isExistSDCard()) {
			new LoadSDCardFileAsyncTask().execute(mRootFile);
			
		}else {
			Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	/**
	 * 加载SD卡
	 * @param path
	 */
	private List<SDFile> getSDCardFile(File file) {
		List<SDFile> sdFileList = new ArrayList<SDFile>();
		
		if(file.isDirectory()) {
			// 判断是否返回到SD卡根目录，如果没有到SD卡根目录，就添加返回项
			if(!file.getAbsolutePath().equals(mRootFile.getAbsolutePath())) {
				SDFile back = new SDFile();
				back.setName("返回");
				back.setFile(file.getParentFile());
				back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
				sdFileList.add(back);
				
			}
			
			File[] files = file.listFiles();
			if(files != null) {
				for(File f : files) {
					SDFile sdFile = new SDFile();
					sdFile.setName(f.getName());
					sdFile.setFile(f);
					
					if(f.isDirectory()) {
						sdFile.setCount(f.listFiles() == null ? 0 : f.listFiles().length);
						sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
						sdFile.setPicture(false);
					}
					else if(f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith("jpg") || 
							f.getName().toLowerCase().endsWith(".jpeg")) {
						sdFile.setBitmap(null);
						sdFile.setFileSize(getFileSize(f));
						sdFile.setPicture(true);
					}
					
					else {
						sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
						sdFile.setFileSize(getFileSize(f));
						sdFile.setPicture(false);
					}
					sdFileList.add(sdFile);
				}
			}
			
			
		}
		return sdFileList;
	}
	
	
	private String getFileSize(File file) {
		if(file == null){
			return "0B";
		}
		else {
			long size = file.length();
			return sizeToStr(size);
		}
		
	}

	/**
	 * 将文件大小转换字符串，如： xxM xxKB
	 * @param size
	 * @return
	 */
	private String sizeToStr(long size) {
		if(size / 1024 < 1) {
			return size + "B";
		}else if(size / (1024 * 1024) < 1) {
			return format.format((size / (1024 * 1.0))) + "KB";
		}else {
			
			return format.format(size / (1024 * 1024 * 1.0)) + "M";
		}
	}
	
	// Item单击事件 
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SDFile sdFile = (SDFile) adapter.getItem(position);
		if(sdFile.getFile().isDirectory()) {
			new LoadSDCardFileAsyncTask().execute(sdFile.getFile());
		}else if(sdFile.isPicture()) {
			openPickture(sdFile);
		}
	}
	
	/**
	 * 打开图片
	 * @param sdFile
	 */
	private void openPickture(SDFile sdFile) {
		// 通过隐式意图打开图片文件 
		Uri uri = Uri.fromFile(sdFile.getFile());
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "image/*");
		startActivity(intent);
	}
	
	// Item 长按事件 
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		//
		SDFile sdFile =  (SDFile) adapter.getItem(position);
		if(sdFile.getName().equals("返回")) {
			return true;
		}
		
		if(view != null) {
			ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
			int[] location = new int[2];
			view.getLocationInWindow(location);
			
			PopupWindow popupWindow = createPopupWindow(view.getHeight(),sdFile);
			
			popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + icon.getRight(), location[1]);
		}
		
		
		return true;
	}
	
	/**
	 * 创建一个PopupWindow
	 * @param height PopupWindow 高度
	 * @param sdFile  
	 * @return
	 */
	private PopupWindow createPopupWindow(int height,SDFile sdFile) {
		PopupWindow popupWindow = new PopupWindow(this);
		View view = loadPopupWindowView(popupWindow,sdFile);
		popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(height);
		popupWindow.setContentView(view);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.local_popup_bg));
		popupWindow.setFocusable(true);
		return popupWindow;
	}
	
	
	private View loadPopupWindowView(final PopupWindow popupWindow, final SDFile sdFile) {
		View view = getLayoutInflater().inflate(R.layout.popup_window, null);
		TextView tvDetail = (TextView) view.findViewById(R.id.tv_detail);
		TextView tvDelete = (TextView) view.findViewById(R.id.tv_delete);
		TextView tvOpen = (TextView) view.findViewById(R.id.tv_open);
		
		 if(sdFile.getFile().isDirectory()){
			tvOpen.setVisibility(View.VISIBLE);
		}else if(sdFile.isPicture()) {
			tvOpen.setText("打开");
			tvOpen.setVisibility(View.VISIBLE);
		}else{
			tvOpen.setVisibility(View.INVISIBLE);
		}
		
		// 详细
		tvDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				
				int count = 0;
				String size = ""; 
				File file = sdFile.getFile();
				
				if(file.isDirectory()) {
					long totalSize = 0;
					File[] files = sdFile.getFile().listFiles();
					if(files != null) {
						// 获取该文件夹下有多少个子文件
						count = files.length;
						// 计算文件夹下所有文件的总大小
						for (File f : files) {
							if(f != null) {
								totalSize += f.length();
							}
						}
					}
					// 转换为多少KB或多少M 
					size = sizeToStr(totalSize);
					
				}else {
					// 转换为多少KB或多少M 
					size = sizeToStr(sdFile.getFile().length());
				}
				
				AlertDialog dialog = createAlertDialog("详细",size, count,sdFile,false);
				dialog.show();
			}
		});
		
		// 删除
		tvDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				AlertDialog dialog = createAlertDialog("删除", "", 0, sdFile, true);
				dialog.show();
			}
		});
		
		//　点击进入 
		tvOpen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				if(sdFile.isPicture()) {
					openPickture(sdFile);
				}else {
					new LoadSDCardFileAsyncTask().execute(sdFile.getFile());
				}
				
			}
		});
		
		
		return view;
	}
	
	/**
	 * 创建一个弹出对话框 
	 * @param size 文件夹大小
	 * @param count 子文件数量
	 * @return 返回一个弹出对话框 
	 */
	private AlertDialog createAlertDialog(String title, String size, int count,final SDFile sdFile, boolean isDelete) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if(isDelete) {
			String message =  sdFile.getFile().isFile() ? ("您确定 要删除" + sdFile.getName() + "文件吗？ ") : "您确定 要删除" + sdFile.getName() + "文件夹吗？";
			builder.setMessage(message);
			builder.setNegativeButton("取消", null);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					boolean success = false;
					if(sdFile.getFile().isFile()) {
						success = sdFile.getFile().delete();
					}else {
						success = deleteFile(sdFile.getFile());
					}
					//　如果删除成功 ，通知数据更新
					if(success) {
						if (sdFileList.contains(sdFile)) {
							sdFileList.remove(sdFile);
							adapter.notifyDataSetChanged();
						}
					}
				}
				
				/**
				 * 递归删除文件夹下的所有文件 
				 * @param file
				 */
				private boolean deleteFile(File file) {
					File[] files = file.listFiles();
					if(files != null) {
						for (File f : files) {
							if(f.isDirectory()) {
								deleteFile(f);
							}else {
								f.delete();
							}
						}
					}
					return file.delete();
				}
			});
		}else {
			LinearLayout linearLayout = createLinearLayout(size, count,sdFile);
			builder.setView(linearLayout);
			builder.setNegativeButton("确定", null);
		}
		builder.setTitle(title);
		
		return builder.create();
	}
	
	/**
	 * 创建一个View
	 * @param size 文件夹下总大小
	 * @param count 文件夹下的子文件数
	 * @return
	 */
	private LinearLayout createLinearLayout(String size, int count,SDFile sdFile) {
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		linearLayout.setLayoutParams(params);
		
		TextView tvSize = new TextView(this);
		LinearLayout.LayoutParams tvSizeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvSize.setLayoutParams(tvSizeParams);
		tvSize.setPadding(10, 8, 10, 8);
		tvSize.setText("文件总大小为： " + size);
		tvSize.setTextColor(Color.WHITE);
		linearLayout.addView(tvSize);
		
		if(sdFile.getFile().isDirectory()) {
			TextView tvCount = new TextView(this);
			LinearLayout.LayoutParams tvCountParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			tvCount.setLayoutParams(tvCountParams);
			tvCount.setPadding(10, 8, 10, 8);
			tvCount.setText("总共文件数量： " + count);
			tvCount.setTextColor(Color.WHITE);
			linearLayout.addView(tvCount);
		}else {
			CheckBox cb = new CheckBox(this);
			LinearLayout.LayoutParams cbParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			cb.setLayoutParams(cbParams);
			cb.setPadding(5, 8, 10, 8);
			cb.setText("是否是文件");
			cb.setChecked(sdFile.getFile().isFile());
			cb.setEnabled(false);
			cb.setTextColor(Color.WHITE);
			linearLayout.addView(cb);
		}
		
		TextView tvModify = new TextView(this);
		LinearLayout.LayoutParams tvModifyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvModify.setLayoutParams(tvModifyParams);
		tvModify.setPadding(10, 8, 10, 8);
		tvModify.setText("最后修改时间：" + dateTimeToStr(sdFile.getFile().lastModified()));
		tvModify.setTextColor(Color.WHITE);
		linearLayout.addView(tvModify);
		
		return linearLayout;
	}
	
	/**
	 * 将时间戳转换成字符串
	 * @param dateTime 时间戳
	 * @return 日期字符串
	 */
	private String dateTimeToStr(long dateTime) {
		Date date = new Date(dateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}
	
	
	class LoadSDCardFileAsyncTask extends AsyncTask<File, Void, List<SDFile>> {
		private ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this,R.style.loading_dialog);
			dialog.setMessage("正在拼命加载SD卡数据....");
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}
		
		@Override
		protected List<SDFile> doInBackground(File... params) {
			return getSDCardFile(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<SDFile> result) {
			if(dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			if(result != null) {
				sdFileList.clear();
				sdFileList.addAll(result);
				adapter.notifyDataSetChanged();
			}
		}
	}

}
