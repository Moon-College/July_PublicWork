package com.tz.dallon.lsn20_fragment.FragmentWeixin;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tz.dallon.lsn20_fragment.R;

public class WeixinActivity extends FragmentActivity {
    private FrameLayout fl;
    private RadioGroup rg;
    FragmentManager fm;

    WeixinFragment wxFragment;
    TongxunluFragment txlFragment;
    FaxianFragment fxFragment;
    WoFragment woFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);

        fl = (FrameLayout) findViewById(R.id.fl_content);
        rg = (RadioGroup)findViewById(R.id.rg);

        fm = getSupportFragmentManager();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkChangedItem(checkedId);
            }
        });

        RadioButton rb = (RadioButton)findViewById(R.id.rb_wx);
        rb.setChecked(true);

    ////看源码，checkedChange方法会调两次，是个坑
    //  rg.check(R.id.rb_wx);

        //checkChangedItem(R.id.rb_wx);

    }

    /**
     * 野路子：
     * 1.每次new fragment
     * 2.replace：1）销毁fragment1；2）创建fragment2;
     * 会造成GC不断回收对象以及创建对象。
     * 还会造成什么问题？fragment销毁和创建会浪费资源---内存、数据清空重新加载了。
     * 推荐不使用replace,使用hide和show
     */
    private void checkChangedItem(int checkedId) {
        FragmentTransaction ft = fm.beginTransaction();

        switch (checkedId) {
            case R.id.rb_wx:

                if(txlFragment != null) {
                    ft.hide(txlFragment);
                }
                if(fxFragment != null) {
                    ft.hide(fxFragment);
                }
                if(woFragment != null) {
                    ft.hide(woFragment);
                }

                if(wxFragment == null) {
                    wxFragment = new WeixinFragment();
                    ft.add(R.id.fl_content, wxFragment, "wx");
                } else {
                    ft.show(wxFragment);
                }

                ft.commit();

                break;
            case R.id.rb_txl:

                if(wxFragment != null) {
                    ft.hide(wxFragment);
                }
                if(fxFragment != null) {
                    ft.hide(fxFragment);
                }
                if(woFragment != null) {
                    ft.hide(woFragment);
                }

                if(txlFragment == null) {
                    txlFragment = new TongxunluFragment();
                    ft.add(R.id.fl_content, txlFragment, "txl");
                } else {
                    ft.show(txlFragment);
                }
                ft.commit();
                break;
            case R.id.rb_fx:

                if(txlFragment != null) {
                    ft.hide(txlFragment);
                }
                if(wxFragment != null) {
                    ft.hide(wxFragment);
                }
                if(woFragment != null) {
                    ft.hide(woFragment);
                }

                if(fxFragment == null) {
                    fxFragment = new FaxianFragment();
                    ft.add(R.id.fl_content, fxFragment, "fx");
                } else {
                    ft.show(fxFragment);
                }
                ft.commit();
                break;
            case R.id.rb_w:
                if(txlFragment != null) {
                    ft.hide(txlFragment);
                }
                if(wxFragment != null) {
                    ft.hide(wxFragment);
                }
                if(fxFragment != null) {
                    ft.hide(fxFragment);
                }
                if(woFragment == null) {
                    woFragment = new WoFragment();
                    ft.add(R.id.fl_content, woFragment, "wo");
                } else {
                    ft.show(woFragment);
                }
                ft.commit();
                break;
            default:
                break;
        }
    }

}
