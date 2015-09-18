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

	//游戏当前场景
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
	
	//计算得分
	public static int SCORE=0;

	public Drawer(Context context,SurfaceHolder holder,int width,int height ){
		this.context=context;
		this.holder=holder;
		this.w=width;
		this.h=height;
		paint=new Paint();
		isStart=true;
		
		//初始化精灵
		initSpirits();
		//初始化精灵相关属性
		initAttrs();
	}
	
	public void initAttrs() {
		//设置准备图层的坐标
		getReady.setX((w-getReady.getWidth())/2);
		getReady.setY(h/3);
		//设置准备层上的tap从标
		tap.setX((w-tap.getWidth())/2);
		tap.setY(h/2);
		//设置gameOver坐标
		gameOver.setX((w-gameOver.getWidth())/2);
		gameOver.setY(h/3);
		//设置分数面板从标
		panel.setX((w-panel.getWidth())/2);
		panel.setY(h/2);
		
		//小鸟从标、速度
		bird.setX(w/4);
		bird.setY(h/2);
		bird.setYSpeed(2);
		
		//管子的间隙
		pipeSpan=h/4;
		//上下两根管子的高度（加起来＝h/2）
		pipeTopHeight1=h/4;
		pipeTopHeight2=h/3;
		
		//管子从标属性
		//游戏开始第一根管子位于屏幕右端
		pipeTop1.setX(w);
		pipeTop1.setScoreBeforeX(pipeTop1.getX());//管子得分前位置
		pipeTop1.setY(0);
		pipeBottom1.setX(w);
		pipeBottom1.setY(pipeTopHeight1+pipeSpan);
		
		//第二根管子坐标
		pipeTop2.setX(3f*w/2+pipeBottom1.getWidth()/2);
		pipeTop2.setScoreBeforeX(pipeTop2.getX());//管子得分前位置
		pipeTop2.setY(0);
		pipeBottom2.setX(3f*w/2);
		pipeBottom2.setY(pipeTopHeight2+pipeSpan);
		
		//初始化得分
		this.SCORE=0;
		isHit=false;
		
	}

	private void initSpirits() {
		//背景
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
		
		//第一对管子
		pipeTopBm=BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe1);
		pipeTop1=new Spirit(pipeTopBm);
		pipeBottomBm=BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe2);
		pipeBottom1=new Spirit(pipeBottomBm);
		//第二对管子
		pipeTop2=pipeTop1.getCloneObject();
		pipeBottom2=pipeBottom1.getCloneObject();
		
		//鸟
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
	 *  起一个线程不停上绘制水游戏画面
	 */
	@Override
	public void run() {
		while(isStart){
			//锁定画板
			canvas=holder.lockCanvas();
			if(canvas!=null){
				//画游戏视图
				drawGameView(canvas);
			}
			//解除画板
			holder.unlockCanvasAndPost(canvas);
		}
		
	}

	/**
	 * 画游戏各种视图
	 * @param canvas
	 */
	private void drawGameView(Canvas canvas) {
		//城市背景
		canvas.drawBitmap(bg.getBitmap(), null, new Rect(0,0,w,h), paint);
		//画地板
		canvas.drawBitmap(floor.getBitmap(), null, new Rect(0,3*h/4,w,h), paint);
		//判断当前游戏场景
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
		//游戏结束界面：gameover图层 分数面板
		gameOver.drawSelf(canvas);
		panel.drawSelf(canvas);
		//画得分
		paint.setColor(Color.GREEN);
		paint.setTextSize(25);
		float score0Width=paint.measureText(0+"");
		float scoreWidth=paint.measureText(SCORE+"");
		//计算偏移量
		float offsetWidth=(scoreWidth-score0Width)/2;
		canvas.drawText(SCORE+"", panel.getX()+180-offsetWidth, panel.getY()+53, paint);
		
	}

	private void drawGameRunningView(Canvas canvas) {
		// 游戏运行时
		//绘制管子的运加
		//设计管子：规定界面里最多出再两对管子，当其中一对滑出去了，就直接重置该管子（x=W）
		//碰接到了，管子就不动了，鸟继续下落
		if(!isHit){
			//第一对管子
			//记录得分之前位置
			pipeTop1.setScoreBeforeX(pipeTop1.getX());
			pipeTop1.setX(pipeTop1.getX()+PIPE_SPEED);
			
			//从管子原图创建一个子集，去掉不该显示的部分
			pipeTop1.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight1, pipeTopBm.getWidth(), pipeTopHeight1));
			
			pipeBottom1.setX(pipeTop1.getX());
			pipeBottom1.setY(pipeTopHeight1+pipeSpan);
			//约定两根管子的高度加起来＝h/2
			pipeBottom1.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottom1.getWidth(), h/2-pipeTopHeight1));
			
			//第二对管子
			//记录得分之前位置
			pipeTop2.setScoreBeforeX(pipeTop2.getX());
			pipeTop2.setX(pipeTop2.getX()+PIPE_SPEED);
			pipeTop2.setBitmap(Bitmap.createBitmap(pipeTopBm, 0, pipeTopBm.getHeight()-pipeTopHeight2, pipeTopBm.getWidth(), pipeTopHeight2));
			pipeBottom2.setX(pipeTop2.getX());
			pipeBottom2.setY(pipeTopHeight2+pipeSpan);
			pipeBottom2.setBitmap(Bitmap.createBitmap(pipeBottomBm, 0, 0, pipeBottomBm.getWidth(), h/2-pipeTopHeight2));
		
			//判断是否得分
			List<Spirit> pipeList=new ArrayList<Spirit>();
			pipeList.add(pipeTop1);
			pipeList.add(pipeTop2);
			addScore(pipeList);
		}
		
		//设置小鸟的位置
		//给小鸟一个加速度
		bird.setYSpeed(bird.getYSpeed()+Spirit.ADD_YSPEED);
		bird.setY(bird.getY()+bird.getYSpeed());
		
		//检测小鸟的碰撞
		//判断两个矩形是否交叉，交叉了就表示碰撞了
		if(bird.getY()<=0||birdHitPip(pipeTop1)||birdHitPip(pipeBottom1)||birdHitPip(pipeTop2)||birdHitPip(pipeBottom2)){
			isHit=true;
		}
		
		//小鸟碰到地板，场景变成gameover
		if(bird.getY()+bird.getHeight()>=3*h/4){
			setSceneState(Drawer.OVER);
		}
		//管子出界了，让该对管子再回到最右边，同时给其高度为随机的高度
		checkPipeOutBound(pipeTop1,true);
		checkPipeOutBound(pipeTop2,false);
		
		//让各图层绘制自己
		pipeTop1.drawSelf(canvas);
		pipeTop2.drawSelf(canvas);
		pipeBottom1.drawSelf(canvas);
		pipeBottom2.drawSelf(canvas);
		bird.drawSelf(canvas);
		
	}

	/**
	 * 判断是否得分，并增加分数
	 * @param pipeList 所有管子
	 */
	private void addScore(List<Spirit> pipeList) {
		if(pipeList==null||pipeList.size()==0){
			return;
		}
		for(Spirit pipe:pipeList){
			//如果得分前位置在鸟右边，当前位置在鸟左边，则得分
			if((pipe.getScoreBeforeX()+pipe.getWidth())>bird.getX()&&(pipe.getX()+pipe.getWidth())<=bird.getX()){
				SCORE++;
			}
		}
	}

	/**
	 * 检察管子是否移出了屏幕
	 * @param pipe 
	 * @param isFirt 是否是第一对管子
	 */
	private void checkPipeOutBound(Spirit pipe, boolean isFirt) {
		if((pipe.getX()+pipe.getWidth())<=0){
			//管子出界了回到最右边
			pipe.setX(w);
			if(isFirt){
				//高度范 h/9 ~ 4h/9
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
		//准备界面包括：准备图层 tap 小鸟
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
