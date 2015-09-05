package com.tz.dallon.lsn20_fragment.FragmentLifecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.tz.dallon.lsn20_fragment.R;

public class LifecycleActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_addA:
                addA();
                break;
            case R.id.bt_addB:
                addB();
                break;
            case R.id.bt_removeA:
                removeA();
                break;
            case R.id.bt_removeB:
                removeB();
                break;

        }
    }

    private void addA() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AFragment aFragment = new AFragment();
        ft.add(R.id.fl_content, aFragment, "A");
//        ft.add(aFragment, "tag"); //这个方法就是用来做纯粹的后台处理的fragment
        ft.commit();
    }

    private void addB() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BFragment bFragment = new BFragment();
        ft.replace(R.id.fl_content, bFragment, "B"); //生命周期A先销毁，f2再创建
        // 加入返回栈中，按返回键时回滚
        //将Fragment添加到回退栈里面(回退栈--Activity)--相当于回滚
        ft.addToBackStack("fragment");
        ft.commit();
    }

    private void removeA() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag("A");
        if (fragment != null) {
            ft.remove(fragment);
        } else {
            Toast.makeText(this, "A Fragment 不存在", Toast.LENGTH_LONG).show();
        }
        ft.commit();

    }

    private void removeB() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag("B");
        if (fragment != null) {
            ft.remove(fragment);
        } else {
            Toast.makeText(this, "B Fragment 不存在", Toast.LENGTH_LONG).show();
        }
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
