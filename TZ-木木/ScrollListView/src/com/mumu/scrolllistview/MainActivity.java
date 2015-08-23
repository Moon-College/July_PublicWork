package com.mumu.scrolllistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	
	private ListView lv;
	private ArrayList<Map<String, String>> list;
	private String[] province = {"������(��)",
			"�����(��)",
			"�Ϻ���(��)",
			"������(��)",
			"�ӱ�ʡ(��)",
			"����ʡ(ԥ)",
			"����ʡ(��)",
			"����ʡ(��)",
			"������ʡ(��)",
			"����ʡ(��)",
			"����ʡ(��)",
			"ɽ��ʡ(³)",
			"�½�ά���(��)",
			"����ʡ(��)",
			"�㽭ʡ(��)",
			"����ʡ(��)",
			"����ʡ(��)",
			"����׳��(��)",
			"����ʡ(��)",
			"ɽ��ʡ(��)",
			"���ɹ�(��)",
			"����ʡ(��)",
			"����ʡ(��)",
			"����ʡ(��)",
			"����ʡ(��)",
			"�㶫ʡ(��)",
			"�ຣʡ(��)"};
	private ScrollView sv;
	private Button btn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.lv);
		lv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {  
                case MotionEvent.ACTION_DOWN:  
                    Log.d("INFO", "ListView --> DOWN.............");  
                    break;  
                case MotionEvent.ACTION_MOVE:  
                	//lv.
                	sv.requestDisallowInterceptTouchEvent(true);
                    Log.d("INFO", "ListView --> MOVE.............");  
                    break;  
                case MotionEvent.ACTION_CANCEL:  
                    Log.d("INFO", "ListView --> CANCEL.............");  
                    break;  
                case MotionEvent.ACTION_UP:  
                    Log.d("INFO", "ListView --> UP.............");  
                    break;  
                default:  
                    break;  
                } 
				return lv.onTouchEvent(event);
			}
		});
		sv = (ScrollView) findViewById(R.id.sv);
		sv.requestDisallowInterceptTouchEvent(true);
		btn = (Button) findViewById(R.id.button);
		btn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 switch (event.getAction()) {  
	                case MotionEvent.ACTION_DOWN:  
	                    Log.d("INFO", "ACTION_DOWN.............");  
	                    break;  
	                case MotionEvent.ACTION_MOVE:  
	                    Log.d("INFO", "ACTION_MOVE.............");  
	                    break;  
	                case MotionEvent.ACTION_CANCEL:  
	                    Log.d("INFO", "ACTION_CANCEL.............");  
	                    break;  
	                case MotionEvent.ACTION_UP:  
	                    Log.d("INFO", "ACTION_UP.............");  
	                    break;  
	                default:  
	                    break;  
	                } 
				return false;
			}
		});
		btn.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Log.d("INFO", "OnLongClick");  
				return true;
			}
		});
		
		list = new ArrayList<Map<String, String>>();
		for(int i = 0; i < province.length; i++ ){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("image", Integer.toString(R.drawable.ball));
			map.put("value", province[i]);
			list.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"image", "value"}, new int[]{R.id.iv, R.id.tv});
		lv.setAdapter(adapter);
		
		lv.getParent().requestDisallowInterceptTouchEvent(true);
		
	}
	
	
	public void buttonClick(View v){
		Log.d("INFO", "OnClick");  
	}

}
