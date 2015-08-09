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
 * �ļ������
 * 
 * @author �Խ���
 * 
 */
public class MainActivity extends Activity {

	// �ļ��б�
	private ListView fileListLv;
	// �б��ļ�����
	private List<MyFile> list = new ArrayList<MyFile>();
	// ����SD��·�������ȡ��������ȡ����SD��
	private String sdPath = SdCardUtils.getOneExterSdcardPath();
	// �Զ���������
	private FileAdapter adapter;

	// ��ǰѡ����ļ�
	private MyFile selectedFile = null;
	private PopupWindow popUpWindow;
	public static boolean timeUpdateFlag=false;//ʱ����±�־

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
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		fileListLv = (ListView) findViewById(R.id.lv_filelist);
		// �������SD����ȻΪ�գ����ȡ����SD��
		if (sdPath == null || sdPath.trim().equalsIgnoreCase("")) {
			// �ж�SD�Ƿ��ѹ���
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
			// ��������SD�����Ҳ����˳�ϵͳ
			this.finish();
		}
		MyLog.i("jzhao", "sdPath-->" + sdPath + "fileListLv="
				+ (fileListLv == null));

		// �����ļ��������
		fileListLv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				File f = new File(myFile.getPath());
				if (f.isDirectory()) {
					initData(myFile.getPath());
				}
				// Ϊ�ļ���ͼƬ������popwindow�ļ��������ڡ�(����ɾ�����򿪡���ʾ�ļ�����)����ϵͳ�����
				else {
					selectedFile = myFile;
					// ��pop����
					openPopWindow(view);
				}
			}
		});
	}

	/**
	 * ��ʼ������
	 */
	public void initData(String path) {
		if (list != null && list.size() > 0) {
			list.clear();
		}

		File file = new File(path);
		if (file.isDirectory()) {
			// ѭ���ļ����µ������ļ�
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {

					// ������ļ���
					if (f.isDirectory()) {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.dirs), f.getAbsolutePath(),
								false);
						list.add(myFile);
					}
					// �����ͼƬ��ʵ���첽����
					else if (f.getPath().toLowerCase().endsWith(".png")
							|| f.getPath().toLowerCase().endsWith(".jpg")
							|| f.getPath().toLowerCase().endsWith(".jpeg")
							|| f.getPath().toLowerCase().endsWith(".gif")) {
						MyFile myFile = new MyFile(f.getName(), null, // ��ͼƬ���Ȳ����أ���̨�첽����ʵ��
								f.getAbsolutePath(), true);
						list.add(myFile);
					}
					// �����ļ�
					else {
						MyFile myFile = new MyFile(f.getName(),
								BitmapFactory.decodeResource(getResources(),
										R.drawable.file), f.getAbsolutePath(),
								false);
						list.add(myFile);
					}
				}
			}
			// ���ļ������ļ���������
			Collections.sort(list, new MyFile());
			// ����һ������
			if (!path.equalsIgnoreCase(sdPath)) {
				MyFile myFile = new MyFile("����", BitmapFactory.decodeResource(
						getResources(), R.drawable.dirs), file.getParentFile()
						.getAbsolutePath(), false);// ���ص�·��ӦΪ�����ļ�·��
				list.add(0, myFile);
			}

			// ����������
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
	 * ��PopupWindow�����Զ��ļ�����ɾ�����򿪡���ʾ����
	 * 
	 * @param v
	 *            ѡ���ļ���Ӧ�ĵ�listView��Ŀ
	 * @param f
	 *            ѡ�е��ļ�
	 */
	public void openPopWindow(View v) {
		// �ļ������б�
		RelativeLayout opListRl = (RelativeLayout) View.inflate(
				getApplicationContext(), R.layout.file_pop, null);
		popUpWindow = new PopupWindow(opListRl, DensityUtil.dip2px(
				MainActivity.this, 200f), // ��
				DensityUtil.dip2px(MainActivity.this, 60f), // ��
				true);// �Ƿ���Ի�ý���
		// ���ñ��������裬���޷���ȡ����
		popUpWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.local_popup_bg));
		// popUpWindow.showAsDropDown(v,
		// DensityUtil.dip2px(MainActivity.this, 70f), -v.getHeight()
		// + DensityUtil.dip2px(MainActivity.this, 1f));
		// ��������item�ؼ�����Ļ�ϵ�����
		int[] location = new int[2];
		v.getLocationInWindow(location);
		popUpWindow.showAtLocation(v, Gravity.LEFT | Gravity.TOP, location[0]
				+ DensityUtil.dip2px(MainActivity.this, 70f), location[1]);

		// ��ʼ���ļ��������б����溬�ж��ļ���ɾ�����򿪡���ʾ����ؼ�
		initFileOperationList(opListRl);
	}

	/**
	 * ��ʼ���ļ������б�
	 * 
	 * @param popUpWindow
	 * 
	 * @param opListRl
	 *            �ļ������б�
	 * @param f
	 *            ѡ�е��ļ�
	 */
	private void initFileOperationList(RelativeLayout opListRl) {
		ImageView openIv = (ImageView) opListRl.findViewById(R.id.iv_open);
		openIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ���ļ�
				try {
					File file=new File(selectedFile.getPath());
					//�����text/plain���͵ģ����µ�activity���
					if(FileUtil.getMIMEType(file).equals("text/plain")){
						Intent intent=new Intent(MainActivity.this,TextEditActivity.class);
						intent.setData(Uri.fromFile(file));
						startActivity(intent);
					} 
					// �������ϵͳ�����
					else {
						FileUtil.openFile(MainActivity.this,file);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "δ֪���ļ�����",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		ImageView deleteIv = (ImageView) opListRl.findViewById(R.id.iv_delete);
		deleteIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ����ȷ��ɾ���Ի���
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("ɾ��ȷ��");
				builder.setMessage("ȷ��Ҫɾ�����ļ���");
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								list.remove(selectedFile);
								// ɾ���ļ�
								FileUtil.deleteFile(new File(selectedFile
										.getPath()));
								adapter.notifyDataSetChanged();
								popUpWindow.dismiss();
								dialog.dismiss();
							}
						});
				builder.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		});

		// ��ʾ�ļ�����
		ImageView detailIv = (ImageView) opListRl.findViewById(R.id.iv_detail);
		detailIv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				File f = new File(selectedFile.getPath());
				// ����һ���Ի���
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				LayoutInflater inflater = LayoutInflater
						.from(MainActivity.this);
				View customeView = inflater.inflate(R.layout.file_detail, null);
				builder.setView(customeView);
				// builder.setTitle("�ļ�����");

				// �����ļ���С��byte-->Mת��(/1024 �õ�KB ,��/ 1024�õ�M)
				// double fileSize = FileUtil.getFileSize(f) /1024d / 1024d;
				// MyLog.i("jzhao", "fileSize-->"+fileSize);
				// // ����6λС��
				// BigDecimal b = new BigDecimal(fileSize);
				// double fileSizeT = b.setScale(6, BigDecimal.ROUND_HALF_UP)
				// .doubleValue();
				// builder.setMessage("�ļ�����" + f.getName() + "\n"+"�ļ���С��" +
				// fileSizeT + " M");
				// builder.setPositiveButton("ȷ��",
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
	 * �����Զ��嵯����������
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

		tv_detail_title.setText("�ļ�����");
		tv_detail_filename.setText(file.getName());

		// �����ļ���С��byte-->Mת��(/1024 �õ�KB ,��/ 1024�õ�M)
		double fileSize = FileUtil.getFileSize(file) / 1024d / 1024d;
		MyLog.i("jzhao", "fileSize-->" + fileSize);
		// ����6λС��
		BigDecimal b = new BigDecimal(fileSize);
		double fileSizeT = b.setScale(6, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
		tv_detail_filesize.setText(fileSizeT + " M");

		// ��ȡ��ǰ��ǰ
		tv_detail_nowtime.setText(DateUtil.getNowTimeStr());
		timeUpdateFlag=true;
		// ����һ���첽��ÿ�����һ��ʱ��
		UpdateTimeTask timeTask = new UpdateTimeTask(tv_detail_nowtime);
		timeTask.execute(null);
		//���ȷ����ť��ֹͣʱ����£��رյ�����
		bt_detail_confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				timeUpdateFlag=false;
				dialog.dismiss();
			}
		});
		
		//��ʱ��ӵ���¼�,����TimePickerDialog����ʱ������޸�
		tv_detail_nowtime.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showTimePickerDialogUpdateTime();
			}
		});

	}
	
	/**
	 * ��ʾTimePickerDialog�������ĵ�ǰʱ��
	 */
	private void showTimePickerDialogUpdateTime(){
		
		TimePickerDialog timePicker=new TimePickerDialog(
				MainActivity.this, 
				new OnTimeSetListener(){
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						//�����õ�ʱ�䱣��Ϊϵͳʱ��
						Calendar nowCal=Calendar.getInstance();
						nowCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
						nowCal.set(Calendar.MINUTE, minute);
						MyLog.i("jzhao", "updated time-->"+DateUtil.getDateTimeStr(nowCal.getTime()));
						//�޸�ϵͳʱ�䣬��ҪrootȨ�ޣ���ʱ�㲻��
						SystemClock.setCurrentTimeMillis(nowCal.getTimeInMillis());
					}
				},
				new Date().getHours(),
				new Date().getMinutes(), 
				true);//��24Сʱʱ����ʾ
		timePicker.show();
	}

	/**
	 * �����ļ������еĵ�ǰʱ��
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
					//ÿ200ms����һ�µ�ǰʱ��
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