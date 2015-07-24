package com.ws.tablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TableLayout tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
//		���ز���
		setContentView(tl);
	}
// ��ʼ���ؼ�
	private void init() {
		//newһ��TableLayout����
		tl = new TableLayout(this);
		//���ò��ֵĿ��
		TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tl.setLayoutParams(lp);
		for (int i = 0; i < 5; i++) {
			//newһ��TableRow
			TableRow tr = new TableRow(this);
			//����TableRow�Ŀ��
			tr.setLayoutParams(lp);
			for (int j = 0; j < 3; j++) {
				//newһ��TextView
				TextView tv = new TextView(this);
				//����TextView�Ŀ��
				TableRow.LayoutParams tvlp = new TableRow.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						android.widget.TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
				//Ӧ�õ�TextView��
				tv.setLayoutParams(tvlp);
				//����TextView���ֵĶ��䷽ʽ
				tv.setGravity(Gravity.CENTER);
				//��TextView�ŵ�TableRow��
				tr.addView(tv);
				//����TextView��ʾ������
				tv.setText("��" + i + "��" + "����" + j + "��");
				//������������textView�ı�����ɫ
				switch (j) {
				case 0:
					tv.setBackgroundColor(Color.BLUE);
					break;
				case 1:
					tv.setBackgroundColor(Color.RED);
					break;
				case 2:
					tv.setBackgroundColor(Color.GREEN);
					break;

				}
			}
			//��TableRow�ŵ�TableLayout��
			tl.addView(tr);
		}

	}

}
