package com.tz.dynamictablelayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tz.dynamictablelayout.util.ColorUtil;
import com.tz.dynamictablelayout.util.DensityUtil;
import com.tz.dynamictablelayout.util.MyLog;

/**
 * ��񲼾�
 * @author �Խ���
 *
 */
public class MainActivity extends Activity {

	public static final String MY_TAG="MY_MainActivity";
	
	private static int TD_NUMS = 30;// ��Ԫ���ܸ���
	private static int TR_NUMS = 3;// ÿ�зŵĵ�Ԫ������

	private String[][] tds;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.ISDEBUG=true;
		// setContentView(R.layout.main);
		tds = initData(TD_NUMS, TR_NUMS);

		createTableLayout();
	}

	/**
	 * ������񲼾�
	 */
	private void createTableLayout() {
		//���һ����Բ��֣��������ڲ�TableLayout�������ݰ���
		RelativeLayout displayRl=new RelativeLayout(this);
		displayRl.setBackgroundColor(Color.WHITE);
		LayoutParams displayRlParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		displayRl.setLayoutParams(displayRlParams);
		
		// ����table
		TableLayout table = new TableLayout(this);
		table.setOrientation(TableLayout.VERTICAL);
		table.setBackgroundColor(Color.RED);// ������ɫ����
		table.setPadding(DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f),
				DensityUtil.dip2px(this, 5f));
		RelativeLayout.LayoutParams tableParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		tableParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		table.setLayoutParams(tableParams);

		displayRl.addView(table);
		
		//������������
		attachDataToTable(table, tds);
		setContentView(displayRl);
	}

	/**
	 * ����ɿ��������
	 * 
	 * @param tl ��񲼾ֶ���
	 * @param tds2 ���Ԫ����
	 */
	private TableLayout attachDataToTable(TableLayout tl, String[][] tds2) {
		if (tds2 != null && tds2.length > 0) {
			for (int i = 0; i < tds2.length; i++) {
				// �����
				TableRow tr = new TableRow(this);
				for (int j = 0; j < tds2[i].length; j++) {
					//�����
					TextView td = new TextView(this);
					td.setText(tds2[i][j]);
					td.setTextColor(Color.WHITE);
					td.setBackgroundColor(ColorUtil.generateRandomColor());
					td.setGravity(Gravity.CENTER);
					TableRow.LayoutParams tdParams = new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, 1);
					tdParams.setMargins(
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f), 
							DensityUtil.dip2px(this, 5f));
					td.setLayoutParams(tdParams);
					tr.addView(td);
				}
				tl.addView(tr);
			}
		}
		return tl;
	}

	/**
	 * ��ʼ�����ݣ���̬�����������
	 * 
	 * @param tD_NUMS2
	 *            ��Ԫ���ܸ���
	 * @param tR_NUMS2
	 *            ÿ�зŵĵ�Ԫ������
	 * @return
	 */
	private String[][] initData(int tD_NUMS2, int tR_NUMS2) {
		// ����������������������Ϊ�̣������������ټ�1
		int i = tD_NUMS2 / tR_NUMS2 + (tD_NUMS2 % tR_NUMS2 != 0 ? 1 : 0);
		int j = tR_NUMS2;
		String[][] tempData = new String[i][j];
		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				// ���ݴ�1���ε���
				tempData[m][n] = (m * tR_NUMS2 + n + 1) + "";
				MyLog.i(MY_TAG, tempData[m][n]);
			}
		}
		return tempData;
	}
}