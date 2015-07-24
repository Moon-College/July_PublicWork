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
        //Log.i("INFO", "view loaded");//日志输出
        // Log.println(Log.INFO, "INFO", "view loaded---- isDebug ");
        //MyLog.i("INFO", "MyLog Hello INFO");
        //MyLog.d("Debug", "MyLog Debug");
        //MyLog.w("WARN", "MyLog WARN");
        //	MyLog.println(Log.DEBUG, "DEBUG"," MyLog======DEBUG");
        //空指针异常
		btn_Log =  (Button) findViewById(R.id.btnlog);//控件赋值不能放于【setContentView】前面否则报空指针异常
       //通过id查找到相关的信息进行初步赋值
        //ctrl + 1+ 回车
        MyLog.isDebug =true;
        
        btn_Log.setOnClickListener(this);//给按钮绑定监视器 (注意添加了监视器必须绑定否则报错)   
    }
     
    /**
     * 添加监视器通过onclick事件进行收集日志
     * @author jiandan
     */  
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			readLog();//点击按钮时，对日志进行收集
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 对日志进行收集
	 * @author jiandan
	 * @param bufid  日志级别
	 * @param tag 日志标签
	 * @param msg 日志消息	
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
	 * 对日志进行收集
	 * @author jiandan
	 * @param bufid  日志级别
	 * @param flag 是否清除日志
	 * @return String
	 */
	private String GetLogInfo(String bufid,boolean flag ){
		StringBuffer sbuff = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		//使用arraylist是为了方便对于参数长度的分配
		cmdLine.add("logcat");
		if(flag){
		cmdLine.add("-d");//收集一次日志就停止
		cmdLine.add("-s");//过滤
		cmdLine.add(bufid);		
		}else{
			cmdLine.add("-c");//清除日志	
		}
		
		try {
			   //创建命令
				Process  exec = Runtime.getRuntime().exec(cmdLine.toArray(new String [cmdLine.size()]));
			
				//获取执行命令后的输入流
				//使用InputStream获取日志
				InputStream inputstream = exec.getInputStream();
				InputStreamReader inread = new InputStreamReader(inputstream);
				BufferedReader bufferreader = new  BufferedReader(inread);
				String str = null;
				while((str= bufferreader.readLine())!= null){//
					sbuff.append(str);//将内容进行拼接
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
	
	//context 资源加载器
	//出现异常---1、红色-运行时异常  2、可调试异常 ---- 橙色
	//收集系统日志--设置权限--读取日志的权限
	//Connection attempts 连接尝试
    
}