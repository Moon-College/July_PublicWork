package com.tz.volumecontrolerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VolumeView extends View{
    private int BLOCK;
    public AudioManager manager;
    private int maxVolume;
    private int volume;
    private Bitmap greenBm;
    private Bitmap grayBm;
    private Paint paint;
    private int grayCount;
    private int allHeight;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        // 得到系统的当前音量值---绿色格子数
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
        volume = manager.getStreamVolume(AudioManager.STREAM_RING);
        //系统音量(铃声、音乐、警告)最大值
        maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
        
        //初始化图片宽高
        greenBm = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
        BLOCK = greenBm.getHeight();
        grayCount = maxVolume-volume;
        allHeight = maxVolume*(greenBm.getHeight()+BLOCK);
        //提醒重绘
        invalidate();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 计算灰色格子数
        float y = event.getY();
        float x = event.getX();
        if (y <= allHeight&&x <= greenBm.getWidth()) {
            //比例:图片的总高度/y = 总格子数/灰色格子数  --->灰色格子数 =总格子数*y/图片的总高度
            grayCount = (int) (y*maxVolume/allHeight);
            invalidate();
            //调整音量
            manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume-grayCount, AudioManager.FLAG_PLAY_SOUND|AudioManager.FLAG_SHOW_UI);
        }
        return super.onTouchEvent(event);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // 全部绘制绿色格子
        for (int i = 0; i < maxVolume; i++) {
            /**
             * Rect src从greenBm里面扣一个矩形区域图片
             * RectF dst，放到画板上面的某一个矩形区域图片
             */
            Rect src = new Rect(0, 0, greenBm.getWidth(), greenBm.getHeight());
            Rect dst = new Rect(0, i*(greenBm.getHeight()+BLOCK), greenBm.getWidth(), i*(greenBm.getHeight()+BLOCK)+greenBm.getHeight());
            canvas.drawBitmap(greenBm, src, dst, paint);
        }
        //绘制灰色格子
        for (int i = 0; i < grayCount; i++) {
            Rect src = new Rect(0, 0, grayBm.getWidth(), grayBm.getHeight());
            Rect dst = new Rect(0, i*(grayBm.getHeight()+BLOCK), grayBm.getWidth(), i*(grayBm.getHeight()+BLOCK)+greenBm.getHeight());
            canvas.drawBitmap(grayBm, src, dst, paint);
        }
        super.onDraw(canvas);
    }
}
