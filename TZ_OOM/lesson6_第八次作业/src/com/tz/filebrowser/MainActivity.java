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
	
	// SD����Ŀ¼
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
			Toast.makeText(this, "SD��������", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	/**
	 * ����SD��
	 * @param path
	 */
	private List<SDFile> getSDCardFile(File file) {
		List<SDFile> sdFileList = new ArrayList<SDFile>();
		
		if(file.isDirectory()) {
			// �ж��Ƿ񷵻ص�SD����Ŀ¼�����û�е�SD����Ŀ¼������ӷ�����
			if(!file.getAbsolutePath().equals(mRootFile.getAbsolutePath())) {
				SDFile back = new SDFile();
				back.setName("����");
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
	 * ���ļ���Сת���ַ������磺 xxM xxKB
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
	
	// Item�����¼� 
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
	 * ��ͼƬ
	 * @param sdFile
	 */
	private void openPickture(SDFile sdFile) {
		// ͨ����ʽ��ͼ��ͼƬ�ļ� 
		Uri uri = Uri.fromFile(sdFile.getFile());
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "image/*");
		startActivity(intent);
	}
	
	// Item �����¼� 
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		//
		SDFile sdFile =  (SDFile) adapter.getItem(position);
		if(sdFile.getName().equals("����")) {
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
	 * ����һ��PopupWindow
	 * @param height PopupWindow �߶�
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
			tvOpen.setText("��");
			tvOpen.setVisibility(View.VISIBLE);
		}else{
			tvOpen.setVisibility(View.INVISIBLE);
		}
		
		// ��ϸ
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
						// ��ȡ���ļ������ж��ٸ����ļ�
						count = files.length;
						// �����ļ����������ļ����ܴ�С
						for (File f : files) {
							if(f != null) {
								totalSize += f.length();
							}
						}
					}
					// ת��Ϊ����KB�����M 
					size = sizeToStr(totalSize);
					
				}else {
					// ת��Ϊ����KB�����M 
					size = sizeToStr(sdFile.getFile().length());
				}
				
				AlertDialog dialog = createAlertDialog("��ϸ",size, count,sdFile,false);
				dialog.show();
			}
		});
		
		// ɾ��
		tvDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				AlertDialog dialog = createAlertDialog("ɾ��", "", 0, sdFile, true);
				dialog.show();
			}
		});
		
		//��������� 
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
	 * ����һ�������Ի��� 
	 * @param size �ļ��д�С
	 * @param count ���ļ�����
	 * @return ����һ�������Ի��� 
	 */
	private AlertDialog createAlertDialog(String title, String size, int count,final SDFile sdFile, boolean isDelete) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if(isDelete) {
			String message =  sdFile.getFile().isFile() ? ("��ȷ�� Ҫɾ��" + sdFile.getName() + "�ļ��� ") : "��ȷ�� Ҫɾ��" + sdFile.getName() + "�ļ�����";
			builder.setMessage(message);
			builder.setNegativeButton("ȡ��", null);
			builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					boolean success = false;
					if(sdFile.getFile().isFile()) {
						success = sdFile.getFile().delete();
					}else {
						success = deleteFile(sdFile.getFile());
					}
					//�����ɾ���ɹ� ��֪ͨ���ݸ���
					if(success) {
						if (sdFileList.contains(sdFile)) {
							sdFileList.remove(sdFile);
							adapter.notifyDataSetChanged();
						}
					}
				}
				
				/**
				 * �ݹ�ɾ���ļ����µ������ļ� 
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
			builder.setNegativeButton("ȷ��", null);
		}
		builder.setTitle(title);
		
		return builder.create();
	}
	
	/**
	 * ����һ��View
	 * @param size �ļ������ܴ�С
	 * @param count �ļ����µ����ļ���
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
		tvSize.setText("�ļ��ܴ�СΪ�� " + size);
		tvSize.setTextColor(Color.WHITE);
		linearLayout.addView(tvSize);
		
		if(sdFile.getFile().isDirectory()) {
			TextView tvCount = new TextView(this);
			LinearLayout.LayoutParams tvCountParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			tvCount.setLayoutParams(tvCountParams);
			tvCount.setPadding(10, 8, 10, 8);
			tvCount.setText("�ܹ��ļ������� " + count);
			tvCount.setTextColor(Color.WHITE);
			linearLayout.addView(tvCount);
		}else {
			CheckBox cb = new CheckBox(this);
			LinearLayout.LayoutParams cbParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			cb.setLayoutParams(cbParams);
			cb.setPadding(5, 8, 10, 8);
			cb.setText("�Ƿ����ļ�");
			cb.setChecked(sdFile.getFile().isFile());
			cb.setEnabled(false);
			cb.setTextColor(Color.WHITE);
			linearLayout.addView(cb);
		}
		
		TextView tvModify = new TextView(this);
		LinearLayout.LayoutParams tvModifyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		tvModify.setLayoutParams(tvModifyParams);
		tvModify.setPadding(10, 8, 10, 8);
		tvModify.setText("����޸�ʱ�䣺" + dateTimeToStr(sdFile.getFile().lastModified()));
		tvModify.setTextColor(Color.WHITE);
		linearLayout.addView(tvModify);
		
		return linearLayout;
	}
	
	/**
	 * ��ʱ���ת�����ַ���
	 * @param dateTime ʱ���
	 * @return �����ַ���
	 */
	private String dateTimeToStr(long dateTime) {
		Date date = new Date(dateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
		return format.format(date);
	}
	
	
	class LoadSDCardFileAsyncTask extends AsyncTask<File, Void, List<SDFile>> {
		private ProgressDialog dialog;
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this,R.style.loading_dialog);
			dialog.setMessage("����ƴ������SD������....");
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
