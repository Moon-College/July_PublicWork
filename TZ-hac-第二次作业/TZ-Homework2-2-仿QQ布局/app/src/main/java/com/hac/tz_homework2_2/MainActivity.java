/*TZ_hac 第二次作业-仿QQ布局 2015.7.16*/
package com.hac.tz_homework2_2;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    final int ID_IMG = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//
//        LinearLayout ll_item = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_main, null).findViewById(R.id.ll_item);
//
//        View v = new View(this);
//        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//        v.setLayoutParams(llp);
//
//        RelativeLayout rl = new RelativeLayout(this);
//        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        rl.setLayoutParams(rlp);
//
//        ImageView img = new ImageView(this);
//        img.setId(ID_IMG);
//        rlp = new RelativeLayout.LayoutParams((int)getResources().getDimension(R.dimen.width_item_img), (int)getResources().getDimension(R.dimen.height_item_img));
//        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
//        int margin = (int)getResources().getDimension(R.dimen.margin_item_img);
//        rlp.setMargins(margin, margin, margin, margin);
//        img.setLayoutParams(rlp);
//
//        TextView tv = new TextView(this);
//        rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
//        rlp.addRule(RelativeLayout.RIGHT_OF, ID_IMG);
//        int padding = (int) getResources().getDimension(R.dimen.padding_tv);
//        tv.setPadding(padding, padding, padding, padding);
//        tv.setLayoutParams(rlp);
//
//        ImageView img_arrow = new ImageView(this);
//        rlp = new RelativeLayout.LayoutParams((int)getResources().getDimension(R.dimen.width_img_arrow), (int)getResources().getDimension(R.dimen.height_img_arrow));
//        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
//        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        img_arrow.setLayoutParams(rlp);
//
//        rl.addView(img);
//        rl.addView(tv);
//        rl.addView(img_arrow);
//        ll_item.addView(v);
//        ll_item.addView(rl);
//
//        addContentView(ll_item, null);
    }

}
