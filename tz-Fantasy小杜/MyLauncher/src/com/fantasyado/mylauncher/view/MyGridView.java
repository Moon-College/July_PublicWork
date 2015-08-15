package com.fantasyado.mylauncher.view;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fantasyado.mylauncher.R;
import com.fantasyado.mylauncher.adapter.LauncherAdapter;

/**  
 * created at:2015年8月11日 下午12:08:59  
 * project name:MyLauncher  
 * @author Fantasy ado  
 * @version 1.0  
 * @since JDK 1.7  
 * File name:MyGridView.java  
 * description:  
 */

public class MyGridView extends GridView {

	private int dragSrcPosition;
	private int dragPosition;
	private int dragPointX;
	private int dragPointY;
	private int dragOffsetX;
	private int dragOffsetY;
	private int upScrollBounce;
	private int downScrollBounce;
	private int scaledTouchSlop;
	private ImageView dragImageView;
	private WindowManager.LayoutParams windowParams;
	private WindowManager windowManager;
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public MyGridView(Context context ) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	@Override 
	public boolean onInterceptTouchEvent(MotionEvent ev) { 
	if(ev.getAction()==MotionEvent.ACTION_DOWN){ 
	int x = (int)ev.getX(); 
	int y = (int)ev.getY(); 

	dragSrcPosition = dragPosition = pointToPosition(x, y); 
	if(dragPosition==AdapterView.INVALID_POSITION){ 
	return super.onInterceptTouchEvent(ev); 
	} 

	ViewGroup itemView = (ViewGroup) getChildAt(dragPosition-getFirstVisiblePosition()); 
	dragPointX = x - itemView.getLeft(); 
	dragPointY = y - itemView.getTop(); 
	dragOffsetX = (int) (ev.getRawX() - x); 
	dragOffsetY = (int) (ev.getRawY() - y); 

	View dragger = itemView.findViewById(R.layout.item_adapter); 
	//如果选中拖动图标 
	if(dragger!=null&&dragPointX>dragger.getLeft()&&dragPointX<dragger.getRight()&&dragPointY>dragger.getTop()&&dragPointY<dragger.getBottom()+20){ 

	upScrollBounce = Math.min(y-scaledTouchSlop, getHeight()/4); 
	downScrollBounce = Math.max(y+scaledTouchSlop, getHeight()*3/4); 

	itemView.setDrawingCacheEnabled(true); 
	Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache()); 
	startDrag(bm, x, y); 
	} 
	return false; 
	} 
	return super.onInterceptTouchEvent(ev); 
	} 

	@Override 
	public boolean onTouchEvent(MotionEvent ev) { 
	if(dragImageView!=null&&dragPosition!=INVALID_POSITION){ 
	int action = ev.getAction(); 
	switch(action){ 
	case MotionEvent.ACTION_UP: 
	int upX = (int)ev.getX(); 
	int upY = (int)ev.getY(); 
	stopDrag(); 
	onDrop(upX,upY); 
	break; 
	case MotionEvent.ACTION_MOVE: 
	int moveX = (int)ev.getX(); 
	int moveY = (int)ev.getY(); 
	onDrag(moveX,moveY); 
	break; 
	default:break; 
	} 
	return true; 
	} 
	return super.onTouchEvent(ev); 
	} 



	public void startDrag(Bitmap bm, int x, int y){ 
	stopDrag(); 

	windowParams = new WindowManager.LayoutParams(); 
	windowParams.gravity = Gravity.TOP|Gravity.LEFT; 
	windowParams.x = x - dragPointX + dragOffsetX; 
	windowParams.y = y - dragPointY + dragOffsetY; 
	windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT; 
	windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT; 
	windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE 
	| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE 
	| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON 
	| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN; 
	windowParams.format = PixelFormat.TRANSLUCENT; 
	windowParams.windowAnimations = 0; 

	ImageView imageView = new ImageView(getContext()); 
	imageView.setImageBitmap(bm); 
	windowManager = (WindowManager)getContext().getSystemService("window"); 
	windowManager.addView(imageView, windowParams); 
	dragImageView = imageView; 
	} 

	public void onDrag(int x, int y){ 
	if(dragImageView!=null){ 
	windowParams.alpha = 0.8f; 
	windowParams.x = x - dragPointX + dragOffsetX; 
	windowParams.y = y - dragPointY + dragOffsetY; 
	windowManager.updateViewLayout(dragImageView, windowParams); 
	} 

	int tempPosition = pointToPosition(x, y); 
	if(tempPosition!=INVALID_POSITION){ 
	dragPosition = tempPosition; 
	} 

	//滚动 
	if(y<upScrollBounce||y>downScrollBounce){ 
	//使用setSelection来实现滚动 
	setSelection(dragPosition); 
	} 
	} 

	public void onDrop(int x, int y){ 
	//为了避免滑动到分割线的时候，返回-1的问题 
	int tempPosition = pointToPosition(x, y); 
	if(tempPosition!=INVALID_POSITION){ 
	dragPosition = tempPosition; 
	} 
	//超出边界处理 
	if(y<getChildAt(0).getTop()){ 
	//超出上边界 
	dragPosition = 0; 
	}else if(y>getChildAt(getChildCount()-1).getBottom()||(y>getChildAt(getChildCount()-1).getTop()&&x>getChildAt(getChildCount()-1).getRight())){ 
	//超出下边界 
	dragPosition = getAdapter().getCount()-1; 
	} 
	//数据交换 
	if(dragPosition!=dragSrcPosition&&dragPosition>-1&&dragPosition<getAdapter().getCount()){ 
	 LauncherAdapter adapter = (LauncherAdapter)getAdapter(); 
	ResolveInfo dragItem = (ResolveInfo) adapter.getItem(dragSrcPosition); 
	adapter.remove(dragItem);
	adapter.insert(dragItem, dragPosition); 
	Toast.makeText(getContext(),  "done", Toast.LENGTH_SHORT).show(); 
	} 
	} 


	/*** 
	* 停止拖动，去掉拖动时候的影像 
	*/ 
	public void stopDrag(){ 
	if(dragImageView != null){ 
	windowManager.removeView(dragImageView); 
	dragImageView = null; 
	} 
	} 
	} 
 

 
