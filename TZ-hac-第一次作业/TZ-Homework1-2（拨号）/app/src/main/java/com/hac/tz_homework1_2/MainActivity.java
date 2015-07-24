//TZ-hac 第一次作业 拨打电话 2015.7.20
package com.hac.tz_homework1_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText et_number;
    Button bt_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_number = (EditText)findViewById(R.id.et_number);
        bt_call = (Button)findViewById(R.id.bt_call);

        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = et_number.getText().toString().trim();
                if(number.equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入正确的号码", Toast.LENGTH_SHORT).show();
                }
                else {
                    //拨打电话
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);
                }
            }
        });
    }


}
