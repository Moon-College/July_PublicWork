package com.tz.lsn15_propertyanimation.GifEngine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tz.lsn15_propertyanimation.R;

public class GifEngineActivity extends Activity {

    private int screenWidth = 0;
    private int screenHeight = 0;
    private FrameLayout fl;
    private GifSurfaceView gifView;
    boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_engine);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        fl = (FrameLayout) findViewById(R.id.fl);

        gifView = new GifSurfaceView(this,"20140728194855.gif");

        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(screenWidth, screenHeight, Gravity.CENTER);
        gifView.setLayoutParams(flp);

        fl.addView(gifView);


    }

    public void btnClick(View v) {
        Button bt = (Button)v;
        if(isStart) {
            gifView.stopGif();
            bt.setText("start");
        } else {
            gifView.startGif();
            bt.setText("stop");
        }
        isStart = !isStart;

    }

}
