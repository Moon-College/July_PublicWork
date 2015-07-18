/*TZ_hac 第二次作业-仿QQ布局(代码动态添加) 2015.7.17*/
package com.hac.tz_homework2_2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态添加控件
        mAddView(R.id.ll_item, 1000, new BitmapFactory().decodeResource(getResources(), R.drawable.yx), "游戏", 0);
        mAddView(R.id.ll_item, 1001, new BitmapFactory().decodeResource(getResources(), R.drawable.gw), "购物", 0);
        mAddView(R.id.ll_item, 1002, new BitmapFactory().decodeResource(getResources(), R.drawable.yyb), "应用宝", 0);
        mAddView(R.id.ll_item, 1003, new BitmapFactory().decodeResource(getResources(), R.drawable.fjdq), "附近的群", 30);
        mAddView(R.id.ll_item, 1004, new BitmapFactory().decodeResource(getResources(), R.drawable.chwl), "吃喝玩乐", 0);
        mAddView(R.id.ll_item, 1005, new BitmapFactory().decodeResource(getResources(), R.drawable.tcfw), "同城服务", 0);

    }

    /**
     * @param parentID：父控件ID
     * @param imgID：图片控件ID
     * @param bitmap：图片src
     * @param name：TextView的内容
     * @param marginTop：marginTop
     */
    private void mAddView(int parentID, int imgID, Bitmap bitmap, String name, int marginTop) {
        LinearLayout ll_item = (LinearLayout)findViewById(parentID);

        //以View来占位，宽为MATCH_PARENT，高度为1
        View v = new View(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        //设置marginTop属性
        llp.setMargins(0, marginTop, 0, 0);
        v.setLayoutParams(llp);

        //每一条item是一个相对布局，宽为MATCH_PARENT，高为WRAP_CONTENT
        RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置点击效果
        rl.setBackgroundResource(R.drawable.selector_background);
        //一定要设置clickable属性为true，否则没有效果
        rl.setClickable(true);
        rl.setLayoutParams(rlp);

        //item左侧的图标
        ImageView img = new ImageView(this);
        img.setId(imgID);
        rlp = new RelativeLayout.LayoutParams((int)getResources().getDimension(R.dimen.width_item_img), (int)getResources().getDimension(R.dimen.height_item_img));
        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
        int margin = (int)getResources().getDimension(R.dimen.margin_item_img);
        rlp.setMargins(margin, margin, margin, margin);
        img.setImageBitmap(bitmap);
        img.setLayoutParams(rlp);

        //item文字
        TextView tv = new TextView(this);
        rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rlp.addRule(RelativeLayout.RIGHT_OF, imgID);
        int padding = (int) getResources().getDimension(R.dimen.padding_tv);
        tv.setPadding(padding, padding, padding, padding);
        tv.setText(name);
        tv.setLayoutParams(rlp);

        //item右侧的箭头
        ImageView img_arrow = new ImageView(this);
        rlp = new RelativeLayout.LayoutParams((int)getResources().getDimension(R.dimen.width_img_arrow), (int)getResources().getDimension(R.dimen.height_img_arrow));
        rlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        img_arrow.setImageResource(R.drawable.jvh);
        img_arrow.setLayoutParams(rlp);

        //将这些控件添加到布局中
        rl.addView(img);
        rl.addView(tv);
        rl.addView(img_arrow);
        ll_item.addView(v);
        ll_item.addView(rl);
    }

}
