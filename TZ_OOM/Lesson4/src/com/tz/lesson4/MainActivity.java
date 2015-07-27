package com.tz.lesson4;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * ����ʵ�� ��񲼾�
 *
 */
public class MainActivity extends Activity {

	private int[] columnBgColors = new int[] {Color.RED, Color.GREEN, Color.BLUE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		initData();
		setContentLayout();
	}
	
	private void setContentLayout() {
		// ��ʼ��һ����񲼾�
		TableLayout tableLayout = new TableLayout(this);
		// ��ʼ����񲼾�LayoutParams
		TableLayout.LayoutParams params = new TableLayout.LayoutParams(
																TableLayout.LayoutParams.MATCH_PARENT, 
																TableLayout.LayoutParams.MATCH_PARENT);
		
		// ���ñ�񲼾ֲ���
		tableLayout.setLayoutParams(params);;
		// ��ȡ����
		List<List<String>> list = initData();
		
		// �������ݣ�����TableRow
		for (int i = 0; i < list.size(); i++) {
			// ��ʼ��TableRow
			TableRow tableRow = new TableRow(this);
			// ��ʼ��TableRow LayoutParams
			TableRow.LayoutParams tableRowLayoutParams = new TableRow.LayoutParams(
																		TableRow.LayoutParams.MATCH_PARENT, 
																		TableRow.LayoutParams.WRAP_CONTENT);
			// ����TableRow ���ֲ���
			tableRow.setLayoutParams(tableRowLayoutParams);
			// ��ȡÿһ�е���
			List<String> columnDatas = list.get(i);
			
			// ÿ�еĲ��ֲ���
			TableRow.LayoutParams columnParams = new TableRow.LayoutParams(
																TableRow.LayoutParams.WRAP_CONTENT, 
																TableRow.LayoutParams.WRAP_CONTENT);
			// ����ÿ�е�Ȩ��
			columnParams.weight = 1.0f;
			
			for(int j = 0; j< columnDatas.size(); j++) {
				// ��ȡÿ�е���ʾ������
				String columnData = columnDatas.get(j);
				
				TextView textView = new TextView(this);
				textView.setLayoutParams(columnParams);
				// ����textVeiw ����ɫ
				textView.setBackgroundColor(columnBgColors[j]);
				textView.setTextColor(Color.WHITE);
				// ����textView �ڱ߾�
				textView.setPadding(0, 5, 0, 5);
				// ���õ�����ʾ 
				textView.setSingleLine(true);
				textView.setGravity(Gravity.CENTER);
				textView.setText(columnData);
				
				tableRow.addView(textView);
			}
			// ��TableRow ��ӵ�TableLayout��
			tableLayout.addView(tableRow);
		}
		
		setContentView(tableLayout);
	}

	/**
	 * ��ʼ������
	 * @return 
	 */
	private List<List<String>> initData() {
		List<List<String>> list = new ArrayList<List<String>>();
		for(int i = 0; i< 10; i++) {
			List<String> subList = new ArrayList<String>();
			for(int j = 0; j< 3; j++) {
				subList.add((i+1) + "�� " + (j+1)+"��");
			}
			list.add(subList);
		}
		return list;
	}
	
}
