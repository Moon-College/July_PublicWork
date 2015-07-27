package com.tz.logcat;

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

public class MainActivity extends Activity implements OnClickListener{
	private Button bt_save;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /**
         * verborse
         * debug ������Ե�ʱ�����ѡ���������Ĵ�ӡ
         * info ��ӡ��Ϣ
         * warm ����
         * error ����
         * assert ����
         */
        Log.i("INFO", "onCreate����");
        Log.d("DEBUG", "����debug��Ϣ");
        
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(this);
    }
	public void onClick(View v) {
		//����cmd�������־
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//ֻ�ռ�һ����־
		cmdLine.add("-s");//����
		cmdLine.add("INFO");
		String[] strs = new String[cmdLine.size()];
		try {
			Process process = Runtime.getRuntime().exec(cmdLine.toArray(strs));
			InputStream is = process.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}