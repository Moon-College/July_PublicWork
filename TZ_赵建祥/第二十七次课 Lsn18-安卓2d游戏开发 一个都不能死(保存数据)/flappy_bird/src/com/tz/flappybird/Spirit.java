package com.tz.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

public class Spirit implements Cloneable{
	//上鸟向上飞的速度
	public static final int KEY_DOWN_YSPEED=-10;
	
	//下落YSPEED每次增加值
	public static float ADD_YSPEED=0.5f;
	
	private static final long SPAN_TIME=50;
	
	public static final float PIPE_SPEED = -5;
	
	
	//坐标X,Y
	private float x;
	private float y;
	private float YSpeed;
	private Bitmap bitmap;
	private Bitmap[] bms;
	private int width;
	private int height;

	private int index=0;

	private long lastTime;
	//管子得分前位置
	private float scoreBeforeX;
	
	public Spirit(Bitmap bitmap) {
		this.bitmap=bitmap;
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
	}
	public Spirit(Bitmap[] bms) {
		this.bms=bms;
		this.bitmap=bms[0];
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getYSpeed() {
		return YSpeed;
	}
	public void setYSpeed(float ySpeed) {
		YSpeed = ySpeed;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
	}
	public Bitmap[] getBms() {
		return bms;
	}
	public void setBms(Bitmap[] bms) {
		this.bms = bms;
		Bitmap bitmap=bms[0];
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	//clone对象
	public Spirit getCloneObject(){
		try {
			return (Spirit) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void drawSelf(Canvas canvas) {
		nextBitmap(SPAN_TIME);
		canvas.drawBitmap(bitmap, x, y, null);
	}
	private void nextBitmap(long spanTime) {
		if(bms==null||bms.length==0){
			return;
		}
		bitmap=bms[index];
		if(System.currentTimeMillis()-lastTime>=spanTime){
			lastTime=System.currentTimeMillis();
			index++;
			if(index>=bms.length){
				index=0;
			}
		}
	}
	
	public RectF getSpiritRectF(){
		return new RectF(this.getX(),this.getY(),this.getX()+this.getWidth(),this.getY()+this.getHeight());
	}
	
	public float getScoreBeforeX() {
		return scoreBeforeX;
	}
	public void setScoreBeforeX(float scoreBeforeX) {
		this.scoreBeforeX = scoreBeforeX;
	}
	
	
	
}
