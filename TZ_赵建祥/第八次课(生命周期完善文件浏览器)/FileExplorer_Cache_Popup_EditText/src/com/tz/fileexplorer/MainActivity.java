package com.tz.fileexplorer;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tz.fileexplorer.adapter.FileAdapter;
import com.tz.fileexplorer.bean.MyFile;
import com.tz.fileexplorer.util.DateUtil;
import com.tz.fileexplorer.util.DensityUtil;
import com.tz.fileexplorer.util.FileUtil;
import com.tz.fileexplorer.util.MyLog;
import com.tz.fileexplorer.util.SdCardUtils;

/**
 * 文件浏览器
 * 
 * @author 赵建祥
 * 
 */
public class MainActivity extends Activity {

	// 文件列表
	private ListView fileListLv;
	// 列表文件数据
	private List<MyFile> list = new ArrayList<MyFile>();
	// 外置SD卡路径，如果取不到，则取内置SD卡
	private String sdPath = SdCardUtils.getOneExterSdcardPath();
	// 自定义适配器
	private FileAdapter adapter;

	// 当前选择的文件
	private MyFile selectedFile = null;
	private PopupWindow popUpWindow;
	public static boolean timeUpdateFlag=false;//时间更新标志

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG = true;
		setContentView(R.layout.main);

		initView();
		initData(sdPath);

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		fileListLv = (ListView) findViewById(R.id.lv_filelist);
		// 如果外置SD卡依然为空，则获取内置SD卡
		if (sdPath == null || sdPath.trim().equalsIgnoreCase("")) {
			// 判断SD是否已挂载
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				sdPath = Environment.getExternalStorageDirectory()
						.getAbsolutePath();
			} else {
				Toast.makeText(this, "SD ERROR", Toast.LENGTH_LONG).show();
			}
		}
		//
		if (sdPath == null) {
			Toast.makeText(this, "SD ERROR", Toast.LENGTH_LONG).show();
			// 外置内置SD卡都找不到退出系统
			this.finish();
		}
		MyLog.i("jzhao", "sdPath-->" + sdPath + "fileListLv="
				+ (fileListLv == null));

		// 设置文件点击监听
		fileListLv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				File f = new File(myFile.getPath());
				if (f.isDirectory()) {
					initData(myFile.getPath());
				}
				// 为文件或图片，弹出popwindow文件操作窗口。(进行删除、打开、显示文件详情)调用系统软件打开
				else {
					selectedFile = myFile;
					// 打开pop窗口
					openPopWindow(view);
				}
			}
		});
	}

	/**
	 * 初始化数据
	 */
	public void initData(String path) {
		if (list != null && list.size() > 0) {
			list.clear();
		}

		File file = new File(path);
		if (file.isDirectory()) {
			// 循环文件夹下的所有文件
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {

					// 如果是文件夹
					if (f.isDirectory()) {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.dirs), f.getAbsolutePath(),
								false);
						list.add(myFile);
					}
					// 如果是图片，实现异步加载
					else if (f.getPath().toLowerCase().endsWith(".png")
							|| f.getPath().toLowerCase().endsWith(".jpg")
							|| f.getPath().toLowerCase().endsWith(".jpeg")
							|| f.getPath().toLowerCase().endsWith(".gif")) {
						MyFile myFile = new MyFile(f.getName(), null, // 是图片，先不加载，后台异步加载实现
								f.getAbsolutePath(), true);
						list.add(myFile);
					}
					// 其他文件
					else {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.file), f.getAbsolutePath(),
								false);
						list.add(myFile);
					}
				}
			}
			// 按文件名对文件进行排序
			Collections.sort(list, new MyFile());
			// 首先一个返回
			if (!path.equalsIgnoreCase(sdPath)) {
				MyFile myFile = new MyFile("返回", BitmapFactory.decodeResource(
						getResources(), R.drawable.dirs), file.getParentFile()
						.getAbsolutePath(), false);// 返回的路径应为父级文件路径
				list.add(0, myFile);
			}

			// 创建适配器
			adapter = new FileAdapter(list, this);
			fileListLv.setAdapter(adapter);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (adapter != null) {
			adapter.imageLoader.lru.evictAll();
		}
		this.finish();
	}

	/**
	 * 打开PopupWindow，可以对文件进行删除、打开、显示详情
	 * 
	 * @param v
	 *            选中文件对应的的listView条目
	 * @param f
	 *            选中的文件
	 */
	public void openPopWindow(View v) {
		// 文件操作列表
		RelativeLayout opListRl = (RelativeLayout) View.inflate(
				getApplicationContext(), R.layout.file_pop, null);
		popUpWindow = new PopupWindow(opListRl, DensityUtil.dip2px(
				MainActivity.this, 200f), // 宽
				DensityUtil.dip2px(MainActivity.this, 60f), // 高
				true);// 是否可以获得焦点
		// 设置背景，不设，将无法获取焦点
		popUpWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.local_popup_bg));
		// popUpWindow.showAsDropDown(v,
		// DensityUtil.dip2px(MainActivity.this, 70f), -v.getHeight()
		// + DensityUtil.dip2px(MainActivity.this, 1f));
		// 计算点击的item控件在屏幕上的坐标
		int[] location = new int[2];
		v.getLocationInWindow(location);
		popUpWindow.showAtLocation(v, Gravity.LEFT | Gravity.TOP, location[0]
				+ DensityUtil.dip2px(MainActivity.this, 70f), location[1]);

		// 初始化文件操作控列表，里面含有对文件的删除、打开、显示详情控件
		initFileOperationList(opListRl);
	}

	/**
	 * 初始化文件操作列表
	 * 
	 * @param popUpWindow
	 * 
	 * @param opListRl
	 *            文件操作列表
	 * @param f
	 *            选中的文件
	 */
	private void initFileOperationList(RelativeLayout opListRl) {
		ImageView openIv = (ImageView) opListRl.findViewById(R.id.iv_open);
		openIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 打开文件
				try {
					File file=new File(selectedFile.getPath());
					//如果是text/plain类型的，在新的activity里打开
					if(FileUtil.getMIMEType(file).equals("text/plain")){
						Intent intent=new Intent(MainActivity.this,TextEditActivity.class);
						intent.setData(Uri.fromFile(file));
						startActivity(intent);
					} 
					// 否则调用系统软件打开
					else {
						FileUtil.openFile(MainActivity.this,file);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "未知的文件类型",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		ImageView deleteIv = (ImageView) opListRl.findViewById(R.id.iv_delete);
		deleteIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 弹出确认删除对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("删除确认");
				builder.setMessage("确定要删除该文件吗？");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								list.remove(selectedFile);
								// 删除文件
								FileUtil.deleteFile(new File(selectedFile
										.getPath()));
								adapter.notifyDataSetChanged();
								popUpWindow.dismiss();
								dialog.dismiss();
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

		// 显示文件详情
		ImageView detailIv = (ImageView) opListRl.findViewById(R.id.iv_detail);
		detailIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				File f = new File(selectedFile.getPath());
				// 弹出一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				LayoutInflater inflater = LayoutInflater
						.from(MainActivity.this);
				View customeView = inflater.inflate(R.layout.file_detail, null);
				builder.setView(customeView);
				// builder.setTitle("文件详情");

				// 计算文件大小：byte-->M转化(/1024 得到KB ,再/ 1024得到M)
				// double fileSize = FileUtil.getFileSize(f) /1024d / 1024d;
				// MyLog.i("jzhao", "fileSize-->"+fileSize);
				// // 保留6位小数
				// BigDecimal b = new BigDecimal(fileSize);
				// double fileSizeT = b.setScale(6, BigDecimal.ROUND_HALF_UP)
				// .doubleValue();
				// builder.setMessage("文件名：" + f.getName() + "\n"+"文件大小：" +
				// fileSizeT + " M");
				// builder.setPositiveButton("确定",
				// new DialogInterface.OnClickListener() {
				//
				// public void onClick(DialogInterface dialog,
				// int which) {
				// dialog.dismiss();
				// }
				// });
				AlertDialog dialog = builder.create();
				buildeDetailDailog(dialog, customeView, f);
				dialog.show();

			}
		});
	}

	/**
	 * 构造自定义弹出窗的内容
	 * 
	 * @param dialog
	 * @param customeView
	 * @param file
	 */
	private void buildeDetailDailog(final AlertDialog dialog, View customeView,
			File file) {
		TextView tv_detail_title = (TextView) customeView
				.findViewById(R.id.tv_detail_title);
		TextView tv_detail_filename = (TextView) customeView
				.findViewById(R.id.tv_detail_filename);
		TextView tv_detail_filesize = (TextView) customeView
				.findViewById(R.id.tv_detail_filesize);
		TextView tv_detail_nowtime = (TextView) customeView
				.findViewById(R.id.tv_detail_nowtime);
		Button bt_detail_confirm = (Button) customeView
				.findViewById(R.id.bt_detail_confirm);

		tv_detail_title.setText("文件详情");
		tv_detail_filename.setText(file.getName());

		// 计算文件大小：byte-->M转化(/1024 得到KB ,再/ 1024得到M)
		double fileSize = FileUtil.getFileSize(file) / 1024d / 1024d;
		MyLog.i("jzhao", "fileSize-->" + fileSize);
		// 保留6位小数
		BigDecimal b = new BigDecimal(fileSize);
		double fileSizeT = b.setScale(6, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		tv_detail_filesize.setText(fileSizeT + " M");

		// 获取当前日前
		tv_detail_nowtime.setText(DateUtil.getNowTimeStr());
		timeUpdateFlag=true;
		// 启动一个异步，每秒更新一下时间
		UpdateTimeTask timeTask = new UpdateTimeTask(tv_detail_nowtime);
		timeTask.execute(null);
		//点击确定按钮，停止时间更新，关闭弹出窗
		bt_detail_confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				timeUpdateFlag=false;
				dialog.dismiss();
			}
		});
		
		//对时间加点击事件,弹出TimePickerDialog，对时间进行修改
		tv_detail_nowtime.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showTimePickerDialogUpdateTime();
			}
		});

	}
	
	/**
	 * 显示TimePickerDialog，来更改当前时间
	 */
	private void showTimePickerDialogUpdateTime(){
		
		TimePickerDialog timePicker=new TimePickerDialog(
				MainActivity.this, 
				new OnTimeSetListener(){
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						//将设置的时间保存为系统时间
						Calendar nowCal=Calendar.getInstance();
						nowCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
						nowCal.set(Calendar.MINUTE, minute);
						MyLog.i("jzhao", "updated time-->"+DateUtil.getDateTimeStr(nowCal.getTime()));
						//修改系统时间，需要root权限，暂时搞不定
						SystemClock.setCurrentTimeMillis(nowCal.getTimeInMillis());
					}
				},
				new Date().getHours(),
				new Date().getMinutes(), 
				true);//以24小时时间显示
		timePicker.show();
	}

	/**
	 * 更新文件详情中的当前时间
	 * @author Administrator
	 *
	 */
	private class UpdateTimeTask extends AsyncTask<Void, String, String> {

		private TextView tv_detail_nowtime;

		public UpdateTimeTask(TextView tv_detail_nowtime) {
			this.tv_detail_nowtime = tv_detail_nowtime;
		}

		@Override
		protected String doInBackground(Void... params) {
			String nowTime=null;
			while (timeUpdateFlag) {
				nowTime = DateUtil.getNowTimeStr();
				MyLog.i("jzhao", "nowTime-->"+nowTime);
				publishProgress(nowTime);
				try {
					//每200ms更新一下当前时间
					Thread.currentThread().sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return nowTime;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			MyLog.i("jzhao", "values-->"+values[0]);
			tv_detail_nowtime.setText(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			MyLog.i("jzhao", "result-->"+result);
			tv_detail_nowtime.setText(result);
		}
	}
}