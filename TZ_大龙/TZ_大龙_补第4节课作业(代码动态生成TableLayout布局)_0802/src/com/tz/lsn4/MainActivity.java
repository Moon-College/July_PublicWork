package com.tz.lsn4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TableLayout tl;
	
	private String[][] stdArray = new String[][]{
			{"���","����","�Ա�","EQ+IQ"},
			{"tz001","������","��","90"},
			{"tz002","�޶�","��","95"},
			{"tz003","�ܽ���","��","80"},
			{"tz004","��Ӣ","Ů","70"},
			{"tz005","���� ","��","80"},
			{"tz006","������","Ů","70"},
			{"tz007","�»���","Ů","70"},
			{"tz008","����","��","80"},
			{"tz009","����","Ů","50"},
			{"tz010","����","Ů","60"}
			
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		//����һ��TableLayout 
		tl = new TableLayout(this);
		//����Ϊ���򲼾�
		tl.setOrientation(TableLayout.VERTICAL);
		//����һ���������ԣ������õ�TableLayout��
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT);
		tl.setLayoutParams(tableLayoutParams);
		
		//��ҵ������˵��
		TextView tv_des = new TextView(this);
		TableRow.LayoutParams tv_desLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
		tv_des.setLayoutParams(tv_desLayoutParams);
		tv_des.setBackgroundColor(Color.BLACK);
		tv_des.setText("��4�ڿ���ҵ��TableLayout���� ���붯̬����");
		tv_des.setTextColor(Color.RED);
		tv_des.setPadding(5, 10, 5, 10);//��������
		tv_des.setTextSize(18);
		tl.addView(tv_des);
		
		//����һά����
		for (int i = 0; i < stdArray.length; i++) {
			//����һ��TableRow
			TableRow tr = new TableRow(this);
			//����һ��TableLayout �ӿؼ������� �������õ�TableRow��
			TableRow.LayoutParams tl_lParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			
			tl_lParams.column = 2;
			tr.setLayoutParams(tl_lParams);
			
			//������ά����
			for (int j = 0; j < stdArray[i].length; j++) {
				//����һ��TextView
				TextView tv = new TextView(this);
				//����һ��TableLayout �ӿؼ�������
				TableRow.LayoutParams tvParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
				tvParams.weight = 1;
				
				if(i== 0) {
					tv.setTextSize(25);
					tv.setBackgroundColor(Color.BLUE);
					tv.setTextColor(Color.WHITE);
				} else {
					tv.setTextSize(18);
				}
				
				tv.setPadding(5, 5, 5, 5); //��������
				
				//�����ı�����
				tv.setGravity(Gravity.CENTER);
				
				//����TextView ������
				tv.setText(stdArray[i][j]);
				//���õ�TextView��
				tv.setLayoutParams(tvParams);
				
				//��TextView ���ص�  TableRow ��
				tr.addView(tv);
				
				//��ֱ�������һ���յ�TextView�����ñ�����ɫ��Ϊ�߿���
				TextView tv_empty = new TextView(this);
				TableRow.LayoutParams tv_emptyLayoutParams = new TableRow.LayoutParams(3,TableRow.LayoutParams.MATCH_PARENT);
				tv_empty.setLayoutParams(tv_emptyLayoutParams);
				tv_empty.setBackgroundColor(Color.BLACK);
				tr.addView(tv_empty);
				
			}
			//��TableRow ���ص�  TableLayout ��
			tl.addView(tr);
			
			//ˮƽ�������һ���յ�TextView�����ñ�����ɫ��Ϊ�߿���
			TextView tv_empty_vertical = new TextView(this);
			TableRow.LayoutParams tv_emptyLayoutParams_vertical = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,3);
			tv_empty_vertical.setLayoutParams(tv_emptyLayoutParams_vertical);
			tv_empty_vertical.setBackgroundColor(Color.BLACK);
			tl.addView(tv_empty_vertical);
		}
		
		//��TableLayout ���ؽ���ͼ
		setContentView(tl);
	}
	
	



}
