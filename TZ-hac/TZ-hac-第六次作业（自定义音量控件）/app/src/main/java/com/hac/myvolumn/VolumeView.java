package com.hac.myvolumn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

/**
 * Created by hp on 2015/7/30.
 */
public class VolumeView extends View{

    Paint paint;
    Bitmap bmGreen;
    Bitmap bmGray;

    //格子数目
    int numGreen;
    int numGray;
    int numTotal;
    //两个格子之间的空隙
    int space;
    //一个格子的高度
    int height;
    //总宽高
    int heightTotal;
    int widthTotal;
    //绘制的坐标
    float x;
    float y;
    AudioManager audioManager;
    Context context;

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //获得自定义控件属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VolumeView);
        x = typedArray.getDimensionPixelSize(R.styleable.VolumeView_x, -1);
        y = typedArray.getDimensionPixelSize(R.styleable.VolumeView_y, -1);

        bmGreen = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmGray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
        space = bmGreen.getHeight();
        height = bmGreen.getHeight();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        numTotal = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        heightTotal = numTotal*(height+space);
        widthTotal = bmGreen.getWidth();
        //获得当前铃声音量并赋值给numGreen
        numGreen = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        numGray = numTotal - numGreen;
        paint = new Paint();
        //使其在Touch Mode下也能获得焦点，否则无法响应音量按键
        setFocusableInTouchMode(true);
        typedArray.recycle();
        //强制重绘
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bm;
        //绘制我们的控件
        for(int i=0; i<numTotal; i++) {
            if(i<numGray)
                bm = bmGray;
            else
                bm = bmGreen;
            canvas.drawBitmap(bm, x, y+(height+space)*i, paint);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            //音量增加
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(numGray>0) {
                    numGray--;
                    numGreen++;
                }
                invalidate();
                break;
            //音量减小
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(numGreen>0) {
                    numGray++;
                    numGreen--;
                }
                invalidate();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float _x = event.getX();
        float _y = event.getY();
        //判断点击位置是否在我们的view上
        if(_x>=x && _x<=widthTotal+x && _y>=y && _y<=heightTotal+y) {
            numGray = (int)(_y-y)/(height + space);
            numGreen = numTotal-numGray;
            audioManager.setStreamVolume(AudioManager.STREAM_RING, numGreen, AudioManager.FLAG_SHOW_UI|AudioManager.FLAG_VIBRATE|AudioManager.FLAG_PLAY_SOUND);
            invalidate();
        }
        return false;
    }
}
