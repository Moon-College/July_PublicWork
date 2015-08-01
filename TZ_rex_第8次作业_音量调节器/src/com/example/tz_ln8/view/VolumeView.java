package com.example.tz_ln8.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.tz_ln8.R;

public class VolumeView extends View
{

    private int ringMaxVol;
    private int currtRingVol;
    private Bitmap grayBit;
    private Bitmap greenBit;
    private Paint paint;
    private int BLOCK;
    private float x;
    private float y;
    private int grayCount;
    private int allHeight;
    private AudioManager audioManager;

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public VolumeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        audioManager = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        ringMaxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currtRingVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // 初始化图片的宽高
        grayBit = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.gray);
        greenBit = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.green);

        grayCount = ringMaxVol - currtRingVol;
        BLOCK = greenBit.getHeight();
        paint = new Paint();
        x = 0;
        y = 0;

        allHeight = ringMaxVol * (greenBit.getHeight() + BLOCK);

        // 提醒重绘
        invalidate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        int repeatCount = event.getRepeatCount();
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Log.i("abc", "音量DOWN");
            if ( repeatCount == 0)
            {
                if(grayCount < ringMaxVol)
                {
                    grayCount++;
                }
            }
            else
            {
                if((grayCount + repeatCount) >= ringMaxVol)
                {
                    grayCount= ringMaxVol;
                }
                else
                {
                    grayCount = grayCount + repeatCount;
                }
            }
            
            repaint2();
            return true;
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            Log.i("abc", "音量UP");
            
            if (repeatCount == 0)
            {
                if(grayCount > 0)
                {
                    grayCount--;
                }
            }
            else
            {
                if((grayCount - repeatCount) <= 0)
                {
                    grayCount = 0;
                }
                else
                {
                    grayCount = grayCount - repeatCount;
                }
            }

            repaint2();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float y = event.getY();
        float x = event.getX();

        if (y <= allHeight && x <= greenBit.getWidth())
        {
            // 有个比例关系：图片的总高度/y = 总格子数/灰色格子数
            grayCount = (int) ((y * ringMaxVol) / allHeight);

            repaint2();
        }

        return super.onTouchEvent(event);
    }
    
    private void repaint2()
    {
        invalidate();

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, ringMaxVol
                - grayCount, AudioManager.FLAG_SHOW_UI);
    }

    public VolumeView(Context context)
    {
        super(context);
        init(context);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        // 全部绘制绿色格子
        for (int i = 0; i < ringMaxVol; i++)
        {
            Rect src = new Rect(0, 0, greenBit.getWidth(), greenBit.getHeight());
            RectF dst = new RectF(x, y + i * (greenBit.getHeight() + BLOCK), x
                    + greenBit.getWidth(), y + i
                    * (greenBit.getHeight() + BLOCK) + greenBit.getHeight());
            // canvas.drawBitmap(greenBit, left, top, paint);
            canvas.drawBitmap(greenBit, src, dst, paint);
        }

        for (int i = 0; i < grayCount; i++)
        {
            Rect src = new Rect(0, 0, grayBit.getWidth(), grayBit.getHeight());
            RectF dst = new RectF(x, y + i * (grayBit.getHeight() + BLOCK), x
                    + grayBit.getWidth(), y + i * (grayBit.getHeight() + BLOCK)
                    + grayBit.getHeight());
            // canvas.drawBitmap(greenBit, left, top, paint);
            canvas.drawBitmap(grayBit, src, dst, paint);
        }

    }

}
