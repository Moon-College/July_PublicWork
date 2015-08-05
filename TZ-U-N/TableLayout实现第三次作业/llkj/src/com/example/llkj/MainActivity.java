package com.example.llkj;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	//�����ű�����ݵĶ�ά����
	private String[][] dataBrr = new String[10][3];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.main);
		//��ʼ������
		initData();
		//��ʼ����ͼ�ؼ�
		initTableView();
	}


	private void initTableView() {
	
	//�����񲼾�
		TableLayout tableLayout= new TableLayout(this);
		ViewGroup.LayoutParams  params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
		        ViewGroup.LayoutParams.WRAP_CONTENT	);			
		   tableLayout.setLayoutParams(params);
		   //���嵥Ԫ�����
		   TableRow.LayoutParams params2 = new TableRow.LayoutParams(0,   ViewGroup.LayoutParams.WRAP_CONTENT	);
		   params2.weight=1.0f;//����һ�б������Ԫ��ƽ��
		   
			//������ֱ�ߣ����Ϊ1;
		   TableRow.LayoutParams params3 = new TableRow.LayoutParams(1,ViewGroup.LayoutParams.MATCH_PARENT);
		   //����ˮƽֱ�ߣ��߶�Ϊ1;
		   TableRow.LayoutParams params4= new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
		   
		   
		   TableRow coco ;
		   TextView te_xt;
		   View vmiew,vline;
		   for (int i = 0; i < dataBrr.length; i++) {
			
			   coco = new TableRow(this);
			   for (int j = 0; j < dataBrr[i].length; j++) {
				
				   te_xt = new TextView(this);
				   te_xt.setLayoutParams(params2);
				 //  te_xt.setText(Color.BLUE);
			       te_xt.setText(dataBrr[i][j]);
				   te_xt.setGravity(Gravity.CENTER_HORIZONTAL);
				   te_xt.setPadding(0, 10, 0, 10);
				 //����һ����ֱ��
				   vline = new View(this);
				   vline.setLayoutParams(params3);
				   vline.setBackgroundColor(Color.BLUE);	
				 //��ӵ�Ԫ��TextView
				   coco.addView(te_xt);
				   coco.addView(vline);		//�������һ�����ֵ�Ԫ��		   
			}
			 //���һ��
			   tableLayout.addView(coco);
			 //���һ��ˮƽ�ߣ��߶�Ϊ1px
			   vmiew = new View(this);
			   vmiew.setLayoutParams(params4);
			   vmiew.setBackgroundColor(Color.RED);
			   tableLayout.addView(vmiew);			   			   
		}	
		   
		   		   setContentView(tableLayout); 
	}


	private void initData() {
		for (int i = 0; i < dataBrr.length; i++) {
			for (int j = 0; j < dataBrr[i].length; j++) {
				dataBrr[i][j] ="��"+(i+1)+"��"+(j+1)+"��";
			}
		}
		
	}
}
