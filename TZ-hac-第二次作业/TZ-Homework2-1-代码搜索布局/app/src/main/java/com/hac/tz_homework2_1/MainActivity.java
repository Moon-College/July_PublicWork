/*TZ_hac 第二次作业-代码实现线性搜索布局) 2015.7.16*/

package com.hac.tz_homework2_1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //新建一个线性布局，宽和高均为MATCH_PARENT，方向为水平方向
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp_whole = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp_whole);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        //新建一个EditText，宽为MATCH_PARENT，高为WRAP_CONTENT，权重为1
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp_et = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        et.setLayoutParams(lp_et);

        //新建一个Button，宽和高均为WRAP_CONTENT，并设置text为“search”
        Button bt = new Button(this);
        LinearLayout.LayoutParams lp_bt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bt.setLayoutParams(lp_bt);
        bt.setText("Search");

        //将这两个控件添加到LinearLayout中
        ll.addView(et);
        ll.addView(bt);

        //为MainActivity设置布局
        setContentView(ll);

    }


}
