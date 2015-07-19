package com.tz.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private String Data[][] = new String[][] {
			{"第一行第1列","第一行第2列","第一行第3列"},
			{"第二行第1列","第二行第2列","第二行第3列"},
			{"第三行第1列","第三行第2列","第三行第3列"},
			{"第四行第1列","第四行第2列","第四行第3列"},
			{"第五行第1列","第五行第2列","第五行第3列"},
			{"第六行第1列","第六行第2列","第六行第3列"},
			{"第七行第1列","第七行第2列","第七行第3列"},
			{"第八行第1列","第八行第2列","第八行第3列"},
			{"第九行第1列","第九行第2列","第九行第3列"},
			{"第十行第1列","第十行第2列","第十行第3列"},
			};//定义一个十行三列的数组
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