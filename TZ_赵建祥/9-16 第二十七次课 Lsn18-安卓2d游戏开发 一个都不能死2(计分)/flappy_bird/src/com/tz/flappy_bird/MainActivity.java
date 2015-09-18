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
     * surface�������ִ��
     * @param holder
     */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	/**
	 * surface�ı�ʱ����
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
	 * surface����ʱ����
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
			//�жϵ�ǰ�����ֳ�Ӱ
			switch(drawer.getSceneState()){
				case Drawer.READY:
					//�������г���
					drawer.setSceneState(Drawer.RUNNING);
					break;
				case Drawer.RUNNING:
					//����ʱС��ûײ��������������,���ı���ٶȷ���
					if(!drawer.isHit()){
						drawer.getBird().setYSpeed(Spirit.KEY_DOWN_YSPEED);
					}
					break;
				case Drawer.OVER:
					//����READY����
					drawer.setSceneState(Drawer.READY);
					//��ʼ����
					drawer.initAttrs();
					break;
				default:
					break;
			}
		}
		//�Լ����ĵ�
		return false;
	}
}
