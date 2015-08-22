package com.tz.lsn15_propertyanimation.GifEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by dallon2 on 2015/8/21.
 */
public class GifSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private String gifPath;
    private Movie moive;
    private int x;
    private int y;
    float scale;
    int delayMillis = 200;
    boolean isRunning = false;
    Handler handler = new Handler();
    private Runnable gifRun = new Runnable() {
        @Override
        public void run() {
            draw();
        }
    };

    //绘制Gif
    private void draw() {
        Canvas canvas = holder.lockCanvas();//锁定画布
        if(canvas != null) {
            canvas.save(); //保存画面的状态
            canvas.scale(1f / scale, 1f / scale); //进行缩放
            moive.draw(canvas, x, y);
            canvas.restore();//恢复画板状态
            holder.unlockCanvasAndPost(canvas);
        }
        //不断偏移时间 控件播放时间 duration 5s 0,1,2,3,4->0,1,2,3,4
        moive.setTime((int)System.currentTimeMillis()%moive.duration());
        handler.removeCallbacks(gifRun);
        handler.postDelayed(gifRun, delayMillis);
    }

    public GifSurfaceView(Context context) {
        super(context);
    }

    public GifSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifSurfaceView(Context context, String gifPath) {
        super(context);
        this.gifPath = gifPath;
        holder = this.getHolder();
        holder.addCallback(this);
    }

    public void stopGif() {
        //停止线程 移除线程
        handler.removeCallbacks(gifRun);
    }

    public void startGif() {
        handler.post(gifRun);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //创建 回调
        try {
            initGifView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        isRunning = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopGif();
    }

    /**
     * 创建Gif容器 并根据GifSurfaceView的宽高与图片Gif的宽高进行缩放居中
     */
    private void initGifView() throws Exception{
        moive = Movie.decodeStream(getContext().getAssets().open(gifPath));
        int width = moive.width();
        int height = moive.height();
        float scale_w = width/(float)this.getWidth();
        float scale_h = height/(float)this.getHeight();


        if(scale_w > scale_h) {
            scale = scale_w;
            x = 0;
            y = (int)(getHeight() * scale_w - height)/2;
        } else {
            scale = scale_h;
            y = 0;
            x = (int)(getWidth() * scale_h)/2;
        }
        handler.post(gifRun);
    }


    /**
     * 如何做成全屏操作
     * onMeasure()
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
