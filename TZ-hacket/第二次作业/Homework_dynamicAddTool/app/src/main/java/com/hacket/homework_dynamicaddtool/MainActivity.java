package com.hacket.homework_dynamicaddtool;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout contentView = new LinearLayout(getApplicationContext());
        contentView.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);

        EditText child1 = new EditText(getApplicationContext());
        LinearLayout.LayoutParams child1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        child1.setLayoutParams(child1Params);
        child1.setHint("this is hint");
        contentView.addView(child1);

        Button child2 = new Button(getApplicationContext());
        LinearLayout.LayoutParams child2Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        child2.setText("点击");
        child2.setLayoutParams(child2Params);
        contentView.addView(child2);

        setContentView(contentView);
    }

}
