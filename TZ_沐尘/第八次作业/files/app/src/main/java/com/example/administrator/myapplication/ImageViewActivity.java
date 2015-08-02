package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/8/2 0002.
 */
public class ImageViewActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_iamgeview);
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        Log.i("MEPAI","图片的路径:"+source);
        image = (ImageView) findViewById(R.id.image);
        createImage(source);
    }
    //生成图片
    private void createImage(String path) {
        //获取手机分辨率
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int scale = 1;
        int scaleX = 1;
        int scaleY = 1;
        if (outWidth>width){
            scaleX = outWidth/width;
        }
        if (outHeight>height){
            scaleY = outHeight/height;
        }
        scale = scaleX>scaleY?scaleX:scaleY;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        image.setImageBitmap(bitmap);
    }
}
