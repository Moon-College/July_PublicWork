package com.example.administrator.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建容器
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(-1, -1);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(paramss);

        //创建button,并给button设置参数,button宽高都为wrap_content
        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        button.setText("搜索");
        button.setLayoutParams(params);

        //创建编辑框,并给编辑框设置参数,编辑框占距button剩下的所有的空间
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(-1, -2);
        param.weight = 1 ;
        param.width = 0 ;
        editText.setHint("请输入要查询的东西");
        editText.setLayoutParams(param);

        //把button和editText都放进容器
        layout.addView(editText);
        layout.addView(button);
        //把布局展示出来
        setContentView(layout);
    }


}
