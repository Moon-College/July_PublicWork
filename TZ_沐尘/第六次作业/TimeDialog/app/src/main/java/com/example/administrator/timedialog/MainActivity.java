package com.example.administrator.timedialog;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePickerDialog pickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mTimeDialgo = (Button) findViewById(R.id.mTimeDialgo);
        mTimeDialgo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mTimeDialgo:

                showTimeDialgo();
                break;
        }
    }
    /**弹出时间dialog*/
    private void showTimeDialgo() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this, "您选择了时间:" + hourOfDay + "点" + minute + "分钟", Toast.LENGTH_SHORT).show();
                pickerDialog.dismiss();
            }
        };
        pickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        pickerDialog.show();
    }
}
