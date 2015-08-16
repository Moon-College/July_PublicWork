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
        // �õ�ϵͳ�ĵ�ǰ����ֵ---��ɫ������
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
        volume = manager.getStreamVolume(AudioManager.STREAM_RING);
        //ϵͳ����(���������֡�����)���ֵ
        maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
        
        //��ʼ��ͼƬ���
        greenBm = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        grayBm = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
        BLOCK = greenBm.getHeight();
        grayCount = maxVolume-volume;
        allHeight = maxVolume*(greenBm.getHeight()+BLOCK);
        //�����ػ�
        invalidate();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // �����ɫ������
        float y = event.getY();
        float x = event.getX();
        if (y <= allHeight&&x <= greenBm.getWidth()) {
            //����:ͼƬ���ܸ߶�/y = �ܸ�����/��ɫ������  --->��ɫ������ =�ܸ�����*y/ͼƬ���ܸ߶�
            grayCount = (int) (y*maxVolume/allHeight);
            invalidate();
            //��������
            manager.setStreamVolume(AudioManager.STREAM_RING, maxVolume-grayCount, AudioManager.FLAG_PLAY_SOUND|AudioManager.FLAG_SHOW_UI);
        }
        return super.onTouchEvent(event);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // ȫ��������ɫ����
        for (int i = 0; i < maxVolume; i++) {
            /**
             * Rect src��greenBm�����һ����������ͼƬ
             * RectF dst���ŵ����������ĳһ����������ͼƬ
             */
            Rect src = new Rect(0, 0, greenBm.getWidth(), greenBm.getHeight());
            Rect dst = new Rect(0, i*(greenBm.getHeight()+BLOCK), greenBm.getWidth(), i*(greenBm.getHeight()+BLOCK)+greenBm.getHeight());
            canvas.drawBitmap(greenBm, src, dst, paint);
        }
        //���ƻ�ɫ����
        for (int i = 0; i < grayCount; i++) {
            Rect src = new Rect(0, 0, grayBm.getWidth(), grayBm.getHeight());
            Rect dst = new Rect(0, i*(grayBm.getHeight()+BLOCK), grayBm.getWidth(), i*(grayBm.getHeight()+BLOCK)+greenBm.getHeight());
            canvas.drawBitmap(grayBm, src, dst, paint);
        }
        super.onDraw(canvas);
    }
}
