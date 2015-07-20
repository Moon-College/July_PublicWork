package com.tz.com.second;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class JavaCodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //相对布局
        RelativeLayout reLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        reLayout.setLayoutParams(lp);
        //线性布局
        LinearLayout ll = new LinearLayout(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(rlp);
        //编辑框
        EditText et = new EditText(this);
        LinearLayout.LayoutParams llp_et = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.FILL_PARENT,1);
        et.setLayoutParams(llp_et);
        //按钮
        Button btn = new Button(this);
        LinearLayout.LayoutParams llp_btn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT, 0);
        btn.setLayoutParams(llp_btn);
        btn.setText("搜索");
        //图片
        ImageView iv = new ImageView(this);
        RelativeLayout.LayoutParams lp_iv = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp_iv);
        iv.setImageResource(R.mipmap.ic_launcher);
        //线性布局添加编辑框和按钮
        ll.addView(et);
        ll.addView(btn);
        //相对布局添加线性布局和图片
        reLayout.addView(ll);
        reLayout.addView(iv);

        setContentView(reLayout);
    }


}
