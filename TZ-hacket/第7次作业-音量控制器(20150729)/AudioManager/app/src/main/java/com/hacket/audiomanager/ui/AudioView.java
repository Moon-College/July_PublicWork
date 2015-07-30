package com.hacket.audiomanager.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hacket.audiomanager.R;

/**
 * 音量控制View
 * Created by zengfansheng on 2015/7/30 0030.
 */
public class AudioView extends View {

    private static final String TAG = "AudioView";
    private int mDividerHeight;

    private Context mContext;
    private AudioManager mAudioManager;
    private int mMaxVolume;
    private int mCurrentVolumn;
    private Paint mPaint;
    private Bitmap mGrayBm;
    private Bitmap mGreenBm;
    private VolumeReceiver mVolumeReceiver;

    public AudioView(Context context) {
        this(context, null);
    }

    public AudioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

        // 获取系统总音量和当前音量
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        mCurrentVolumn = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        // 初始化Bitmap
        mGreenBm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.green);
        mGrayBm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.gray);

        // 间隔
        mDividerHeight = mGreenBm.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = mGreenBm.getWidth();
        int heightSize = mMaxVolume * (mGreenBm.getHeight() + mDividerHeight) - mDividerHeight;

        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);

        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画所有绿色level
        for (int i = 0; i < mMaxVolume; i++) {
            Rect srcRect = new Rect(0, 0, mGreenBm.getWidth(), mGreenBm.getWidth());
            RectF dstRectF = new RectF();
            dstRectF.left = 0;
            dstRectF.right = mGreenBm.getWidth();
            dstRectF.top = i * (mGreenBm.getHeight() + mDividerHeight);
            dstRectF.bottom = i * (mGreenBm.getHeight() + mDividerHeight) + mGreenBm.getHeight();
            /***
             * drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint)
             * bitmap：要被画的bitmap
             * src:要被画的bitmap的子集，或者一部分
             * dst:要画到目标区域
             * paint:画笔
             */
            canvas.drawBitmap(mGreenBm, srcRect, dstRectF, mPaint);
        }

        // 画灰色level
        for (int i = 0; i < mMaxVolume - mCurrentVolumn; i++) {
            Rect srcRect = new Rect(0, 0, mGrayBm.getWidth(), mGrayBm.getWidth());
            RectF dstRectF = new RectF();
            dstRectF.left = 0;
            dstRectF.right = mGrayBm.getWidth();
            dstRectF.top = i * (mGrayBm.getHeight() + mDividerHeight);
            dstRectF.bottom = i * (mGrayBm.getHeight() + mDividerHeight) + mGrayBm.getHeight();
            canvas.drawBitmap(mGrayBm, srcRect, dstRectF, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float startY = event.getY();
                float mDY = startY / getHeight();
                changeSystemVolumn(mMaxVolume - (int) (mDY * mMaxVolume));
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return true;
    }

    private void changeSystemVolumn(int volumn) {
        mCurrentVolumn = volumn;
        Log.d(TAG, "mCurrentVolumn:" + mCurrentVolumn);
        /***
         *  改变系统音量
         *  setStreamVolume (int streamType, int index, int flags)
         */
        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, volumn, AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_VIBRATE);
        invalidate();
    }


    /**
     * 注册当音量发生变化时接收的广播
     */
    private void registerReceiver() {
        mVolumeReceiver = new VolumeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        mContext.registerReceiver(mVolumeReceiver, filter);
    }

    private void unRegisterReceiver() {
        if (mVolumeReceiver != null) {
            mContext.unregisterReceiver(mVolumeReceiver);
            mVolumeReceiver = null;
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        unRegisterReceiver();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerReceiver();
    }

    /**
     * 处理音量变化时的界面显示
     */
    private class VolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING); // 当前的媒体音量
                changeSystemVolumn(currVolume);
            }
        }
    }
}