package com.tz.flappy_bird;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class Drawer extends Thread {

	//��Ϸ��ǰ����
	public static final int READY=0;
	public static final int RUNNING=1;
	public static final int OVER=2;
	
	
	private boolean isStart;
	private Context context;
	private SurfaceHolder holder;
	private int w;
	private int h;
	private Spirit bg;
	private Spirit floor;
	private Spirit getReady;
	private Spirit tap;
	private Spirit gameOver;
	private Bitmap pipeTopBm;
	private Spirit pipeTop1;
	private Bitmap pipeBottomBm;
	private Spirit pipeBottom1;
	private Spirit pipeTop2;
	private Spirit pipeBottom2;
	private Spirit bird;
	private Spirit panel;
	private int pipeSpan;
	private int pipeTopHeight1;
	private int pipeTopHeight2;
	private Canvas canvas;
	private Paint paint;
	private int sceneState;
	private boolean isHit;
	private final float PIPE_SPEED=-12;
	
	//����÷�
	public static int SCORE=0;

	public Drawer(Context context,SurfaceHolder holder,int width,int height ){
		this.context=context;
		this.holder=holder;
		this.w=width;
		this.h=height;
		paint=new Paint();
		isStart=true;
		
		//��ʼ������
		initSpirits();
		//��ʼ�������������
		initAttrs();
	}
	
	public void initAttrs() {
		//����׼��ͼ�������
		getReady.setX((w-getReady.getWidth())/2);
		getReady.setY(h/3);
		//����׼�����ϵ�tap�ӱ�
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		//����gameOver����
		gameOver.setX((w-gameOver.getWidth())/2);
		gameOver.setY(h/3);
		//���÷������ӱ�
		panel.setX((w-panel.getWidth())/2);
		panel.setY(h/2);
		
		//С��ӱꡢ�ٶ�
		bird.setX(w/4);
		bird.setY(h/2);
		bird.setYSpeed(2);
		
		//���ӵļ�϶
		pipeSpan=h/4;
		//�����������ӵĸ߶ȣ���������h/2��
		pipeTopHeight1=h/4;
		pipeTopHeight2=h/3;
		
		//���Ӵӱ�����
		//��Ϸ��ʼ��һ������λ����Ļ�Ҷ�
		pipeTop1.setX(w);
		pipeTop1.setScoreBeforeX(pipeTop1.getX());//���ӵ÷�ǰλ��
		pipeTop1.setY(0);
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1+pipeSpan);
		
		//�ڶ�����������
		pipeTop2.setX(3f*w/2+pipeBottom1.getWidth()/2);
		pipeTop2.setScoreBeforeX(pipeTop2.getX());//���ӵ÷�ǰλ��
		pipeTop2.setY(0);
		pipeBottom2.setX(3f*w/2);
		pipeBottom2.setY(pipeTopHeight2+pipeSpan);
		
		//��ʼ���÷�
		this.SCORE=0;
		isHit=false;
		
	}

	private void initSpirits() {
		//����
		Bitmap bm_bg=BitmapFactory.decodeResource(context.getResources(), R.drawable.bg1);
		bg=new Spirit(bm_bg);
		Bitmap bm_floor=BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
		floor=new Spirit(bm_floor);
		Bitmap bm_ready=BitmapFactory.decodeResource(context.getResources(), R.drawable.getready);
		getReady=new Spirit(bm_ready);
		Bitmap bm_tap=BitmapFactory.decodeResource(context.getResources(), R.drawable.tap);
		tap=new Spirit(bm_tap);
		
		Bitmap bm_over=BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
		gameOver=new Spirit(bm_over);
		Bitmap bm_panel=BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
		panel=new Spirit(bm_panel);
		
		//��һ�Թ���
		pipeTopBm=BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
		pipeTop1=new Spirit(pipeTopBm);
		pipeBottomBm=BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
		pipeBottom1=new Spirit(pipeBottomBm);
		//�ڶ��Թ���
		pipeTop2=pipeTop1.getCloneObject();
		pipeBottom2=pipeBottom1.getCloneObject();
		
		//��
		int[] paths=new int[]{
				R.drawable.bird_1,
				R.drawable.bird_2,
				R.drawable.bird_3
		};
		Bitmap[] bms=new Bitmap[paths.length];
		for(int i=0;i<bms.length;i++){
			Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),paths[i]);
			bms[i]=bitmap;
		}
		bird=new Spirit(bms);
	}

	/**
	 *  ��һ���̲߳�ͣ�ϻ���ˮ��Ϸ����
	 */
	@Override
	public void run() {
		while(isStart){
			//��������
			canvas=holder.lockCanvas();
			if(canvas!=null){
				//����Ϸ��ͼ
				drawGameView(canvas);
			}
			//�������
			holder.unlockCanvasAndPost(canvas);
		}
		
	}

	/**
	 * ����Ϸ������ͼ
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//���б���
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0,0,w,h), paint);
		//���ذ�
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0,3*h/4,w,h), paint);
		//�жϵ�ǰ��Ϸ����
		switch(getSceneState()){
			case Drawer.READY:
				drawGameReadyView(canvas);
				break;
			case Drawer.RUNNING:
				drawGameRunningView(canvas);
				break;
			case Drawer.OVER:
				drawGameOverView(canvas);
				break;
			default:
				break;
			
		}
		
		
	}

	private void drawGameOverView(Canvas canvas) {
		//��Ϸ�������棺gameoverͼ�� �������
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
		//���÷�
		paint.setColor(Color.GREEN);
		paint.setTextSize(25);
		float score0Width=paint.measureText(0+"");
		float scoreWidth=paint.measureText(SCORE+"");
		//����ƫ����
		float offsetWidth=(scoreWidth-score0Width)/2;
		canvas.drawText(SCORE+"", panel.getX()+180-offsetWidth, panel.getY()+53, paint);
		
	}

	private void drawGameRunningView(Canvas canvas) {
		// ��Ϸ����ʱ
		//���ƹ��ӵ��˼�
		//��ƹ��ӣ��涨���������������Թ��ӣ�������һ�Ի���ȥ�ˣ���ֱ�����øù��ӣ�x=W��
		//���ӵ��ˣ����ӾͲ����ˣ����������
		if(!isHit){
			//��һ�Թ���
			//��¼�÷�֮ǰλ��
			pipeTop1.setScoreBeforeX(pipeTop1.getX());
			pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);
			
			//�ӹ���ԭͼ����һ���Ӽ���ȥ��������ʾ�Ĳ���
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
			
			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1+pipeSpan);
			//Լ���������ӵĸ߶ȼ�������h/2
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottom1.getWidth(), h/2-pipeTopHeight1));
			
			//�ڶ��Թ���
			//��¼�÷�֮ǰλ��
			pipeTop2.setScoreBeforeX(pipeTop2.getX());
			pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2+pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight2));
		
			//�ж��Ƿ�÷�
			List<Spirit> pipeList=new ArrayList<Spirit>();
			pipeList.add(pipeTop1);
			pipeList.add(pipeTop2);
			addScore(pipeList);
		}
		
		//����С���λ��
		//��С��һ�����ٶ�
		bird.setYSpeed(bird.getYSpeed()+Spirit.ADD_YSPEED);
		bird.setY(bird.getY()+bird.getYSpeed());
		
		//���С�����ײ
		//�ж����������Ƿ񽻲棬�����˾ͱ�ʾ��ײ��
		if(bird.getY()<=0||birdHitPip(pipeTop1)||birdHitPip(pipeBottom1)||birdHitPip(pipeTop2)||birdHitPip(pipeBottom2)){
			isHit=true;
		}
		
		//С�������ذ壬�������gameover
		if(bird.getY()+bird.getHeight()>=3*h/4){
			setSceneState(Drawer.OVER);
		}
		//���ӳ����ˣ��øöԹ����ٻص����ұߣ�ͬʱ����߶�Ϊ����ĸ߶�
		checkPipeOutBound(pipeTop1,true);
		checkPipeOutBound(pipeTop2,false);
		
		//�ø�ͼ������Լ�
		pipeTop1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		bird.drawSelf(canvas);
		
	}

	/**
	 * �ж��Ƿ�÷֣������ӷ���
	 * @param pipeList ���й���
	 */
	private void addScore(List<Spirit> pipeList) {
		if(pipeList==null||pipeList.size()==0){
			return;
		}
		for(Spirit pipe:pipeList){
			//����÷�ǰλ�������ұߣ���ǰλ��������ߣ���÷�
			if((pipe.getScoreBeforeX()+pipe.getWidth())>bird.getX()&&(pipe.getX()+pipe.getWidth())<=bird.getX()){
				SCORE++;
			}
		}
	}

	/**
	 * �������Ƿ��Ƴ�����Ļ
	 * @param pipe 
	 * @param isFirt �Ƿ��ǵ�һ�Թ���
	 */
	private void checkPipeOutBound(Spirit pipe, boolean isFirt) {
		if((pipe.getX()+pipe.getWidth())<=0){
			//���ӳ����˻ص����ұ�
			pipe.setX(w);
			if(isFirt){
				//�߶ȷ� h/9 ~ 4h/9
				pipeTopHeight1=(int)(Math.random()*(3*h/9)+h/9);
			}else{
				pipeTopHeight2=(int)(Math.random()*(3*h/9)+h/9);
			}
		}
		
	}

	private boolean birdHitPip(Spirit pipeTop) {
		//intersect
		if(bird.getSpiritRectF().intersect(pipeTop.getSpiritRectF())){
			return true;
		}
		return false;
	}

	private void drawGameReadyView(Canvas canvas) {
		//׼�����������׼��ͼ�� tap С��
		getReady.drawSelf(canvas);
		tap.drawSelf(canvas);
		bird.drawSelf(canvas);
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public int getSceneState() {
		return sceneState;
	}

	public void setSceneState(int sceneState) {
		this.sceneState = sceneState;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	public Spirit getBird() {
		return bird;
	}

	public void setBird(Spirit bird) {
		this.bird = bird;
	}
	
}
