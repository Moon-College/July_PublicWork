//TZ-hac 第三次作业 代码实现TableLayout 2015.7.18
package com.hac.tz_homework3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //新建TableLayout，宽为MATCH_PARENT，高为WRAP_CONTENT
        TableLayout tl = new TableLayout(this);
        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tl.setLayoutParams(tlp);

        //新建TableRow和TextView
        TableRow tr = null;
        TextView tv = null;

        //向TableLayout中添加TableRow和控件
        for(int i=0; i<10; i++) {
            tr = new TableRow(this);
            TableRow.LayoutParams trlp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            tr.setBackgroundColor(Color.RED);
            tr.setLayoutParams(trlp);

            for(int j=0; j<3; j++) {
                tv = new TextView(this);
                TableRow.LayoutParams trlp_in = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
                tv.setText("row"+String.valueOf(i));
                tv.setTextColor(Color.WHITE);
                tv.setPadding(10, 10, 10, 10);
                tv.setGravity(Gravity.CENTER);
                tv.setLayoutParams(trlp_in);

                //将TextView添加到TableRow中
                tr.addView(tv);
            }

            //将TableRow添加到TableLayout中
            tl.addView(tr);
        }

        //将布局设置为TableLayout
        setContentView(tl);
    }

}
