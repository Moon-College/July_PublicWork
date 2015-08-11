package tz.holylight.voiceview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xiong on 2015/8/2.
 */
public class VoiceView extends View {

    private AudioManager manager;
    private int maxvalue;
    private int currtvalue;
    private int graycount;
    private int height=80;
    public VoiceView(Context context) {
        this(context,null);
    }

    public VoiceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        manager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        maxvalue = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
        currtvalue = manager.getStreamVolume(AudioManager.STREAM_RING);
        graycount= maxvalue-currtvalue;
        this.setFocusableInTouchMode(true);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VoiceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Paint paint=new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < maxvalue; i++) {
            if (i<graycount){
                paint.setColor(Color.GRAY);
            }else {
                paint.setColor(Color.GREEN);
            }
            canvas.drawRect(0,height*i+10,200,(height*i+height),paint);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
// 音量减小
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                currtvalue = manager.getStreamVolume(AudioManager.STREAM_RING);
                currtvalue--;
                graycount= maxvalue-currtvalue;
                invalidate();
                return false;
// 音量增大
            case KeyEvent.KEYCODE_VOLUME_UP:
                currtvalue = manager.getStreamVolume(AudioManager.STREAM_RING);
                currtvalue++;
                graycount= maxvalue-currtvalue;
                invalidate();
                return false;
        }
      return false;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y= event.getY();
        graycount= (int) (y*maxvalue/((height+10)*maxvalue));
        manager.setStreamVolume(AudioManager.STREAM_RING,maxvalue-graycount,AudioManager.FLAG_SHOW_UI);
        invalidate();
        return super.onTouchEvent(event);
    }
}
