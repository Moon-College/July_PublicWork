package com.tz.dallon.lsn20_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tz.dallon.lsn20_fragment.Adaption_tablet_phone.AdaptionTabletPhoneActivity;
import com.tz.dallon.lsn20_fragment.FragmentDynamic.DynamicActivity;
import com.tz.dallon.lsn20_fragment.FragmentLifecycle.LifecycleActivity;
import com.tz.dallon.lsn20_fragment.FragmentLogin.LoginActivity;
import com.tz.dallon.lsn20_fragment.FragmentWeixin.WeixinActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.bt_dynamic:
                startActivity(new Intent(this, DynamicActivity.class));
                break;
            case R.id.bt_weixin:
                startActivity(new Intent(this, WeixinActivity.class));
                break;
            case R.id.bt_adaption:
                startActivity(new Intent(this, AdaptionTabletPhoneActivity.class));
                break;
            case R.id.bt_lifecycle:
                startActivity(new Intent(this, LifecycleActivity.class));
                break;
            default:
                break;
        }
    }

}
