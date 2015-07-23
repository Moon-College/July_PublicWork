package com.tz.www.tablelayoutui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //添加滚动 ScrollView
        ScrollView sv = new ScrollView(this);
        ScrollView.LayoutParams svlp = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.FILL_PARENT);
        sv.setLayoutParams(svlp);
        //设置TableLayout布局
        TableLayout tl = new TableLayout(this);
        TableLayout.LayoutParams tllp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tl.setLayoutParams(tllp);

        TableRow tr = null;
        TextView tvText = null;

        for (int i = 0; i < 20; i++) {
            tr = new TableRow(this);
            TableRow.LayoutParams trlp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            if(i%2==0){
                tr.setBackgroundColor(Color.RED);
            }else{
                tr.setBackgroundColor(Color.YELLOW);
            }
            tr.setLayoutParams(trlp);
            for (int j = 0; j<4;j++){
                tvText = new TextView(this);
                int iNum = i+1;
                int jNum = j+1;
                TableRow.LayoutParams trlp_in = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
                tvText.setText("第"+iNum+"行"+"\n"+"第"+jNum+"列");
                tvText.setTextColor(Color.GREEN);
                tvText.setTextSize(10);
                tvText.setPadding(15, 15, 15, 15);
                tvText.setGravity(Gravity.CENTER);
                tvText.setLayoutParams(trlp_in);
                tr.addView(tvText);
            }
            tl.addView(tr);
        }
        sv.addView(tl);
        setContentView(sv);

    }


}
