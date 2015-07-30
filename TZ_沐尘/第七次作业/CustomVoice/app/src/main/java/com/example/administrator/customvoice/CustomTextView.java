package com.example.administrator.customvoice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.security.spec.ECField;

/**
 * Created by Administrator on 2015/7/30 0030.
 */
public class CustomTextView extends View {

    private Paint paint;
    private Bitmap mGreenBitmap;
    private Bitmap mGrayBitmap;
    private int maxVolume;
    private int volume;
    private int mGreenHeight;
    private int mGreenWidth;
    private int sumHeight;
    private int mGrayVolume;
    private AudioManager manager;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mGreenBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.green);
        mGrayBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gray);
        mGreenWidth = mGreenBitmap.getWidth()*3;
        mGreenHeight = mGreenBitmap.getHeight()*3;
        mGrayVolume =maxVolume - volume;
        sumHeight = mGreenHeight*2 * maxVolume;

        //注册音量变化广播
        registBroaderCastVoice(context);
    }
    /**注册音量变化广播*/
    private void registBroaderCastVoice(Context context) {
        VoiceBroaderCast broaderCast = new VoiceBroaderCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        context.registerReceiver(broaderCast ,filter);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画绿色的音量图
        Rect mGreenRect = new Rect(0, 0, mGreenWidth, mGreenHeight);
        for (int i = 0; i < maxVolume; i++) {
            Log.i("MEPAI", i + "....");
            Rect mDest = new Rect(0, i * mGreenHeight * 2, mGreenWidth, i * mGreenHeight * 2 + mGreenHeight);
            canvas.drawBitmap(mGreenBitmap, mGreenRect, mDest, paint);
        }
        //画灰色的图
        for (int i = 0; i < mGrayVolume; i++) {
            Log.i("MEPAI",i+"........");
            Rect mDest = new Rect(0, i * mGreenHeight * 2, mGreenWidth, i * mGreenHeight * 2 + mGreenHeight);
            canvas.drawBitmap(mGrayBitmap, mGreenRect, mDest, paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float rawY = event.getY();
        if (sumHeight>rawY){
            mGrayVolume = (int) (maxVolume*rawY / sumHeight);
            invalidate();
            manager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume-mGrayVolume, AudioManager.FLAG_VIBRATE);
        }

        return super.onTouchEvent(event);
    }

    private class VoiceBroaderCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.media.VOLUME_CHANGED_ACTION")){
                int streamVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
                //获取到该画的灰色音量的小方块
                mGrayVolume = maxVolume - streamVolume;
                invalidate(); //重绘
            }
        }
    }

}
