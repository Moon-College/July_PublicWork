package com.tz.hellomyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
  
import android.app.Activity;
import android.os.Bundle; 
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tz.hellomyapp.utils.MyLog;

public class HelloMyAppFstActivity extends Activity implements OnClickListener  {
    /** Called when the activity is first created. */
	Button btn_Log;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);      
        //Log.i("INFO", "view loaded");//��־���
        // Log.println(Log.INFO, "INFO", "view loaded---- isDebug ");
        //MyLog.i("INFO", "MyLog Hello INFO");
        //MyLog.d("Debug", "MyLog Debug");
        //MyLog.w("WARN", "MyLog WARN");
        //	MyLog.println(Log.DEBUG, "DEBUG"," MyLog======DEBUG");
        //��ָ���쳣
		btn_Log =  (Button) findViewById(R.id.btnlog);//�ؼ���ֵ���ܷ��ڡ�setContentView��ǰ����򱨿�ָ���쳣
       //ͨ��id���ҵ���ص���Ϣ���г�����ֵ
        //ctrl + 1+ �س�
        MyLog.isDebug =true;
        
        btn_Log.setOnClickListener(this);//����ť�󶨼����� (ע������˼���������󶨷��򱨴�)   
    }
     
    /**
     * ��Ӽ�����ͨ��onclick�¼������ռ���־
     * @author jiandan
     */  
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			readLog();//�����ťʱ������־�����ռ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����־�����ռ�
	 * @author jiandan
	 * @param bufid  ��־����
	 * @param tag ��־��ǩ
	 * @param msg ��־��Ϣ	
	 * @throws IOException 
	 */
	private void readLog() throws IOException {
		MyLog.println(Log.INFO, "INFO"," MyLog======INFO");
		MyLog.println(Log.ERROR, "ERROR"," MyLog======Error");
		MyLog.println(Log.WARN, "WARN"," MyLog======WARN");
		// TODO Auto-generated method stub
		String info=GetLogInfo("INFO",true);
		String erroe=GetLogInfo("ERROR",true);
		String warn=GetLogInfo("WARN",true);
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(info+"\n");
		sbuffer.append(erroe+"\n");
		sbuffer.append(warn);
		Toast.makeText(this, sbuffer.toString(),1).show();		
		String claerlog = GetLogInfo("",false);
		sbuffer.append(claerlog);
	}
	/**
	 * ����־�����ռ�
	 * @author jiandan
	 * @param bufid  ��־����
	 * @param flag �Ƿ������־
	 * @return String
	 */
	private String GetLogInfo(String bufid,boolean flag ){
		StringBuffer sbuff = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		//ʹ��arraylist��Ϊ�˷�����ڲ������ȵķ���
		cmdLine.add("logcat");
		if(flag){
		cmdLine.add("-d");//�ռ�һ����־��ֹͣ
		cmdLine.add("-s");//����
		cmdLine.add(bufid);		
		}else{
			cmdLine.add("-c");//�����־	
		}
		
		try {
			   //��������
				Process  exec = Runtime.getRuntime().exec(cmdLine.toArray(new String [cmdLine.size()]));
			
				//��ȡִ��������������
				//ʹ��InputStream��ȡ��־
				InputStream inputstream = exec.getInputStream();
				InputStreamReader inread = new InputStreamReader(inputstream);
				BufferedReader bufferreader = new  BufferedReader(inread);
				String str = null;
				while((str= bufferreader.readLine())!= null){//
					sbuff.append(str);//�����ݽ���ƴ��
					sbuff.append("\n");
					if(!flag){
						Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()])); 
					}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	  return sbuff.toString();
	}
	
	//context ��Դ������
	//�����쳣---1����ɫ-����ʱ�쳣  2���ɵ����쳣 ---- ��ɫ
	//�ռ�ϵͳ��־--����Ȩ��--��ȡ��־��Ȩ��
	//Connection attempts ���ӳ���
    
}