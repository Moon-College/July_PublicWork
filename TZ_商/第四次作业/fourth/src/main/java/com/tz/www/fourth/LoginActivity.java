package com.tz.www.fourth;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {
    private EditText mName;
    private EditText mPassword;
    private EditText mPassword2;
    private EditText mEmail;
    private RadioGroup mSex;
    private RadioButton mMan;
    private RadioButton mWam;
    private Spinner mCity;
    private CheckBox mReceive;
    private Button btn;
    private boolean mEnble = false;
    private String[] cities = {
            "北京",
            "上海",
            "深圳",
            "长沙",
            "武汉",
            "西安",
            "广州"
    };


    @Override
    public void initLinster() {

        if ((!TextUtils.isEmpty(mName.getText().toString())) &&
                (!TextUtils.isEmpty(mPassword.getText().toString())) &&
                (!TextUtils.isEmpty(mPassword2.getText().toString())) &&
                (!TextUtils.isEmpty(mEmail.getText().toString())) &&
                (mMan.isChecked() || mWam.isChecked()) && (mReceive.isChecked())) {
            mEnble = true;
        }
        mReceive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && (!TextUtils.isEmpty(mName.getText().toString())) &&
                        (!TextUtils.isEmpty(mPassword.getText().toString())) &&
                        (!TextUtils.isEmpty(mPassword2.getText().toString())) &&
                        (!TextUtils.isEmpty(mEmail.getText().toString())) &&
                        (mMan.isChecked() || mWam.isChecked())) {
                    mEnble = true;
                } else {
                    mEnble = false;
                }
                btn.setEnabled(mEnble);
            }
        });
        btn.setEnabled(mEnble);
        btn.setOnClickListener(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        mName = (EditText) findViewById(R.id.et_name);
        mPassword = (EditText) findViewById(R.id.et_password);
        mPassword2 = (EditText) findViewById(R.id.et_password2);
        mEmail = (EditText) findViewById(R.id.et_email);
        mSex = (RadioGroup) findViewById(R.id.rg_sex);
        mMan = (RadioButton) findViewById(R.id.rb_man);
        mWam = (RadioButton) findViewById(R.id.rb_wm);
        mCity = (Spinner) findViewById(R.id.sp_city);
        mReceive = (CheckBox) findViewById(R.id.cb_receive);
        btn = (Button) findViewById(R.id.btn);
//        ArrayAdapter<String> adappter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cities);
        ArrayAdapter<String> adappter = new ArrayAdapter<String>(this,R.layout.listitem,R.id.textView1,cities);
        mCity.setAdapter(adappter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Toast.makeText(this, mName.getText().toString() + mPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
