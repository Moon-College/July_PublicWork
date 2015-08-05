package com.tz.filesbrowser.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tz.filesbrowser.R;


/**
 * Created by 屈发 on 2015/8/3.
 */
public abstract class BaseActivity extends Activity {
    private LinearLayout ly_content;
    private TextView tv_titlebar_left, tv_titlebar_right, tv_titlebar_center;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_title_bar);
        /**
         * 1.BaseActivity---》common_title.xml
         * 2.xxx_layout.addView(view);
         * 3.添加一个方法：setContentLayout(View v);
         * 				setContentLayout(int resId);
         * 4.再处理返回按钮、标题设置等操作。
         */
        ly_content = (LinearLayout) findViewById(R.id.ly_content);
        tv_titlebar_left = (TextView) findViewById(R.id.tv_titlebar_left);
        tv_titlebar_right = (TextView) findViewById(R.id.tv_titlebar_right);
        tv_titlebar_center = (TextView) findViewById(R.id.tv_titlebar_center);

    }


    protected void setContentLayout(int resId) {
        View contentView = View.inflate(getApplicationContext(), resId, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(lp);
        if (ly_content != null) {
            ly_content.addView(contentView);
        }
    }

    protected TextView getTitlebarLeft() {
        return tv_titlebar_left;
    }

    protected TextView getTitlebarRight() {
        return tv_titlebar_right;
    }

    protected TextView getTitlebarCenter() {
        return tv_titlebar_center;
    }

    protected void hideTitlebarLeft() {
        if (tv_titlebar_left != null) {
            tv_titlebar_left.setVisibility(View.INVISIBLE);
        }
    }

    protected void hideTitlebarRight() {
        if (tv_titlebar_right != null) {
            tv_titlebar_right.setVisibility(View.INVISIBLE);
        }
    }

    protected void setBarTitle(String title) {
        if (tv_titlebar_center != null) {
            tv_titlebar_center.setText(title);
        }
    }

}