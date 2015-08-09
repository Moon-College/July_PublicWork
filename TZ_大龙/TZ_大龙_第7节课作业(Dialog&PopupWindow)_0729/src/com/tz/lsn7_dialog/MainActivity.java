package com.tz.lsn7_dialog;

import java.util.Calendar;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context mCnt ;
	private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mCnt = this;
    }
    /**
     * ����onClick�¼�
     * @param v
     */
    public void click(View v) {
		switch (v.getId()) {
		case R.id.btn_normal: //��ͨ�Ի���
			
			AlertDialog.Builder builder =  new AlertDialog.Builder(mCnt);
			builder.setTitle("��������");
			builder.setMessage("\t���������������Ӧ��̶��ѧԺ����ѧ�������λ�ϸ����أ������������Ƿ�ͬ�⣿");
			builder.setPositiveButton("ͬ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(mCnt, "лл���", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("��ͬ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(mCnt, "���治�Ǹ��ú��ӣ�", Toast.LENGTH_SHORT).show();
				}
			});
			
			dialog = builder.show();
			//�رնԻ���
//			dialog.dismiss();
			
			break;
		case R.id.btn_progress:
			ProgressAsyncTask task = new ProgressAsyncTask();
			task.execute("http:/xxxxx.com/zip");
			break;
		case R.id.btn_date:
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			DatePickerDialog dpd = new DatePickerDialog(mCnt, new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// ������ɺ������
					Toast.makeText(mCnt, "���ú��������ǣ�" + year + "-" +  (++monthOfYear) + "-" +  dayOfMonth, Toast.LENGTH_SHORT).show();
					
				}
			}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
			
			dpd.show();
			
			break;
		case R.id.btn_time:
			
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTimeInMillis(System.currentTimeMillis());
			TimePickerDialog tpd = new TimePickerDialog(mCnt, new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// ������ɺ��ʱ�� 
					Toast.makeText(mCnt, "���ú���ʱ���ǣ�" + hourOfDay + "��" + minute, Toast.LENGTH_SHORT).show();
				}
			}, calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE), true);
			
			tpd.show();
			
			break;
			
		case R.id.btn_default:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(mCnt);
			builder2.setTitle("�û���½");
			View view = View.inflate(mCnt, R.layout.dialog_default_layout, null);
			builder2.setView(view);
			
			Button btn_login = (Button) view.findViewById(R.id.btn_login);
			btn_login.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(mCnt, "��½�ɹ�", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
				}
			});
			
			dialog = builder2.show();
			
			break;
			
		case R.id.btn_popup:
			
			View contentView = View.inflate(mCnt, R.layout.dialog_default_layout, null);
			PopupWindow pw = new PopupWindow(contentView,
					200, 150,
					true);
			
			Button btn_login2 = (Button) contentView.findViewById(R.id.btn_login);
			final EditText et_name2 = (EditText) contentView.findViewById(R.id.et_name);
			btn_login2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(mCnt, "��ϲ��" + et_name2.getText().toString() + "��½�ɹ�", Toast.LENGTH_SHORT).show();
				}
			});
			
			pw.setBackgroundDrawable(getResources().getDrawable(R.drawable.local_popup_bg));
			
			//��ǰ�ؼ����浯������
			//pw.showAsDropDown(v, 100, 0);
			
			int[] location = new int[2];
			v.getLocationInWindow(location);
			
			pw.showAtLocation(v, Gravity.TOP | Gravity.LEFT, location[0] + 100, location[1]);
			
			break;	
			
		default:
			break;
		}
    }

    
    class ProgressAsyncTask extends AsyncTask<String, Integer, Boolean> {

    	private ProgressDialog pd;
		@Override
    	protected void onPreExecute() {
    		// ��ִ���߳�ǰ������
    		super.onPreExecute();
    		
    		pd = new ProgressDialog(mCnt);
			pd.setTitle("��������������...");
			pd.setMax(10000);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.show();
    	}
    	
		@Override
		protected Boolean doInBackground(String... params) {
			// �߳�ִ����ִ��
			//ģ�����أ����½�����
			
			int  progress = 0;
			try {
				while(true) {
					
					progress += 100;
					Thread.sleep(100);
					//pd.setProgress(progress);
					publishProgress(progress);
					if(progress >= pd.getMax()) {
						break;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// ���½��ȵķ���
			super.onProgressUpdate(values);
			Integer progress = values[0];
			pd.setProgress(progress);
		}
		
		@Override
		protected void onCancelled() {
			// �û�ȡ��
			super.onCancelled();
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// ���߳�ִ����Ϻ���� 
			super.onPostExecute(result);
			
			if(result) {
				Toast.makeText(mCnt, "�������", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mCnt, "���س���", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
