package com.tz;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TableLayout tableLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ������ά����
		String[][] strs = new String[5][3];
		String str = null;
		// ����Tablelayout
		tableLayout = new TableLayout(this);
		tableLayout.setOrientation(TableLayout.VERTICAL);
		// �趨tablelayout�������
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		// �󶨲���
		tableLayout.setLayoutParams(lp);
		
		
		for (int i = 1; i <= strs.length; i++) {
			// ����tablerow
			TableRow tableRow = new TableRow(this);
			tableRow.setOrientation(TableRow.HORIZONTAL);
			tableRow.setBackgroundColor(Color.GREEN);
			//�趨tablerow�Ŀ��
			TableRow.LayoutParams trLp = new TableRow.LayoutParams(
					TableLayout.LayoutParams.MATCH_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT);
			tableRow.setLayoutParams(trLp);
			
			
			for (int j = 1; j <= strs[0].length; j++) {
				str = "��" + i + "�У�" + "��" + j + "��";
				
				TextView mtView = new TextView(this);
				
				// ���textview�ı���Ϣ
				mtView.setText(str);
				
				if(j==1){
					mtView.setBackgroundColor(Color.RED);
				}else if(j==2){
					mtView.setBackgroundColor(Color.GREEN);
				}else if(j==3){
					mtView.setBackgroundColor(Color.BLUE);
				}
				// �趨textview����Լ�Ȩ��
				
				TableRow.LayoutParams tvLp = new TableRow.LayoutParams(0,
						TableRow.LayoutParams.WRAP_CONTENT, 1);
				// ��textview�趨����
				mtView.setLayoutParams(tvLp);
				
				// ��textview�����tablerwo��
				tableRow.addView(mtView);
				
				Toast.makeText(this, str, 1).show();
			}
			tableLayout.addView(tableRow);
		}
		setContentView(tableLayout);
	}
}
