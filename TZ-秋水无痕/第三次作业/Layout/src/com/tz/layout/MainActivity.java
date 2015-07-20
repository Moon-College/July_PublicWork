package com.tz.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private String Data[][] = new String[][] {
			{"��һ�е�1��","��һ�е�2��","��һ�е�3��"},
			{"�ڶ��е�1��","�ڶ��е�2��","�ڶ��е�3��"},
			{"�����е�1��","�����е�2��","�����е�3��"},
			{"�����е�1��","�����е�2��","�����е�3��"},
			{"�����е�1��","�����е�2��","�����е�3��"},
			{"�����е�1��","�����е�2��","�����е�3��"},
			{"�����е�1��","�����е�2��","�����е�3��"},
			{"�ڰ��е�1��","�ڰ��е�2��","�ڰ��е�3��"},
			{"�ھ��е�1��","�ھ��е�2��","�ھ��е�3��"},
			{"��ʮ�е�1��","��ʮ�е�2��","��ʮ�е�3��"},
			};//����һ��ʮ�����е�����
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        TableLayout tl = new TableLayout(this);
        tl.setOrientation(TableLayout.VERTICAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        for (int i = 0; i < Data.length; i++) {
        	TableRow tr = new TableRow(this);
        	tr.setOrientation(TableRow.HORIZONTAL);
        	tr.setWeightSum(3);
			for (int j = 0; j < Data[i].length; j++) {
				TextView tv = new TextView(this);
				tv.setText(Data[i][j]);
				tr.addView(tv);
			}
			
			tl.addView(tr);
		}
       setContentView(tl, lp); 
    }
}