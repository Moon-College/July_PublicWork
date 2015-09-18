package com.tz.flappy_bird;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Callback {

	
    private SurfaceView surfaceView;
	private Drawer drawer;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        surfaceView.getHolder().addCallback(this);
        setContentView(surfaceView);
    }

    /**
     * surface创建完后执行
     * @param holder
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	/**
	 * surface改变时调用
	 * @param holder
	 * @param format
	 * @param width
	 * @param height
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	/**
	 * surface销毁时调用
	 * @param holder
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawer.setStart(false);
		drawer=null;
	}
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//判断当前是哪种场影
			switch(drawer.getSceneState()){
				case Drawer.READY:
					//进入运行场景
					drawer.setSceneState(Drawer.RUNNING);
					break;
				case Drawer.RUNNING:
					//运行时小鸟没撞死，按键飞起来,即改变加速度方向
					if(!drawer.isHit()){
						drawer.getBird().setYSpeed(Spirit.KEY_DOWN_YSPEED);
					}
					break;
				case Drawer.OVER:
					//进入READY场景
					drawer.setSceneState(Drawer.READY);
					//初始数据
					drawer.initAttrs();
					break;
				default:
					break;
			}
		}
		//自己消耗掉
		return false;
	}
}
