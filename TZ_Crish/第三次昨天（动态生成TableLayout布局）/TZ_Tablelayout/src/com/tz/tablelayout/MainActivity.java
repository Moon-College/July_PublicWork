package com.tz.tablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * ʹ�ô��붯̬����һ����񲼾�
 * @author Crish
 * 
 */
public class MainActivity extends Activity {
	
	//�����ű�����ݵĶ�ά����
	private String[][] dataArr = new String[10][3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//��ʼ������
		initData();
		
		//��ʼ����ͼ�ؼ�
		initTableView();
	}

	private void initTableView() {
		//�����񲼾ָ�����
		TableLayout rootView = new TableLayout(this);
		ViewGroup.LayoutParams rootParam = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		rootView.setLayoutParams(rootParam);
		
		//���嵥Ԫ�����
		TableRow.LayoutParams tvParam = new TableRow.LayoutParams(
				0, ViewGroup.LayoutParams.WRAP_CONTENT);
		tvParam.weight = 1.0f; //����һ�б�������Ԫ��ƽ��
		
		//������ֱ�ߣ����Ϊ1;
		TableRow.LayoutParams vLineParam = new TableRow.LayoutParams(
				1, ViewGroup.LayoutParams.MATCH_PARENT);
		//����ˮƽֱ�ߣ��߶�Ϊ1;
		TableRow.LayoutParams hLineParam = new TableRow.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 1);
		
		TableRow rowView;  //�������һ������TableRow
		TextView colTv;    //����һ����Ԫ��TextView
		View vLine, hLine; 
		for(int i = 0; i < dataArr.length; i++) {
			rowView = new TableRow(this); 
			for(int j = 0; j < dataArr[i].length; j++) {
				//��ʼ����Ԫ��TextView, �������������
				colTv = new TextView(this);
				colTv.setLayoutParams(tvParam);
				colTv.setTextColor(Color.BLUE);
				colTv.setText(dataArr[i][j]);
				colTv.setGravity(Gravity.CENTER_HORIZONTAL);
				colTv.setPadding(0, 10, 0, 10);
				
				//����һ����ֱ��
				vLine = new View(this);
				vLine.setLayoutParams(vLineParam);
				vLine.setBackgroundColor(Color.RED);
				
				//��ӵ�Ԫ��TextView
				rowView.addView(colTv);
				rowView.addView(vLine); //�������һ�����ֵ�Ԫ��
				
			}
			//���һ��
			rootView.addView(rowView);
			
			//���һ��ˮƽ�ߣ��߶�Ϊ1px
			hLine = new View(this);
			hLine.setLayoutParams(hLineParam);
			hLine.setBackgroundColor(Color.RED);
			rootView.addView(hLine);
		}
		
		setContentView(rootView);
	}

	//��ʼ������
	private void initData() {
		for(int i = 0; i < dataArr.length; i++) {
			for(int j = 0; j < dataArr[i].length; j++) {
				dataArr[i][j] = "��" + (i+1) + "��" + (j+1) + "��";
			}
		}
		
	}
}
