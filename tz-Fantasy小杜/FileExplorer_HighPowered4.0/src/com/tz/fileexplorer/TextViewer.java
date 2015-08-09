package com.tz.fileexplorer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * created at:2015年8月5日 上午8:25:46 project name:FileExplorer_HighPowered
 * 
 * @author Fantasy ado
 * @version 1.0
 * @since JDK 1.7 File name:TextViewer.java description:
 */

public class TextViewer extends Activity {

	private TextView tv_title;
	private TextView tv_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_layout);

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_content = (TextView) findViewById(R.id.tv_content);
		
		Uri uri = getIntent().getData();
		
		String path = uri.getPath();	 
		 
		parseFile(  path );

	}

	private void parseFile(String path ) {
		// TODO Auto-generated method stub
		
		File file = new File(path);
	//	tv_title.setText(file.getName());
		setTitle(file.getName());
	
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"gb2312");
			BufferedReader br = new BufferedReader(isr);
			String buffer = null;
			StringBuilder sb = new StringBuilder();
			while ((buffer=br.readLine())!=null) {
				sb.append(buffer) ;
				sb.append("\n");
				
			}
			tv_content.setText(sb);
			br.close();
			isr.close();			
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
