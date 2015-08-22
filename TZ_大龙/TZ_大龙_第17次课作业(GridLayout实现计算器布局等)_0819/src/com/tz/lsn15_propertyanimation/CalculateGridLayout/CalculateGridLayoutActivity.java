package com.tz.lsn15_propertyanimation.CalculateGridLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.tz.lsn15_propertyanimation.R;

public class CalculateGridLayoutActivity extends Activity {

    private GridLayout gl_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_grid_layout);

        gl_calc = (GridLayout) findViewById(R.id.gl_calc);

    }

    public void btnClick(View v) {
        Button bt = (Button)v;

        EditText et = (EditText)v.findViewById(R.id.et_calc);
        et.setText(bt.getText());
    }


}
