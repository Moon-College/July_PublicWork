package com.example.administrator.mybaseactivitydemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private TextView finish;
    private TextView menu;
    private TextView titles;
    private TextView textView;
    private ImageView imageView;

    @Override
    public void initView() {
        textView = new TextView(this);
        textView.setText("点击打开照相机");
        textView.setId(new Integer(110));
        textView.setTextSize(16);
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.GREEN);
        setLayoutView(textView);
        addImageView();
        finish = getFinish();
        menu = getMenu();
        menu.setText("下一页");
        titles = getTitles();
        titles.setText("主页");
    }
    //生成一个图片
    private void addImageView() {
        imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        setLayoutView(imageView);
    }

    @Override
    public void setListener() {
        finish.setOnClickListener(this);
        menu.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish:
                finish();
                break;
            case R.id.menu:
                //利用隐式意图打开activity
                startMyActivity();
                break;
            case 110:
                //打开照相机,拍照并且显示出来
                startCamer();
                break;
        }
    }
    //打开相册
    private void startCamer() {
        Intent it = new Intent();
        it.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 1);
    }

    //利用隐式意图打开新的activity
    private void startMyActivity() {
        Intent intent = new Intent();
        intent.setAction("second");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    //相机的回调

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            Toast.makeText(MainActivity.this,bitmap.getWidth()+"",Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
