package com.example.superior_pricebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

 
 

public class PriceBar extends View {

	private Bitmap grayBg;
	private Bitmap greenBar;
	private Bitmap numBg;
	private int bgWidth;
	private int bgHeight;
	private Paint mPaint;
	private float scale;
	private int span;
	private float top = 0;
	private float bottom = 0;
	private String[] prices;
	private Bitmap Btn;

	public PriceBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusableInTouchMode(true);
		grayBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.axis_before);
		greenBar = BitmapFactory.decodeResource(getResources(),
				R.drawable.axis_after);
		Btn = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		numBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg_number);
		bgWidth = grayBg.getWidth();
		bgHeight = grayBg.getHeight();
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#8B0000"));
		prices = new String[] { "不限", "1000", "500", "200", "0" };

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int measuredHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize
				: bgHeight;

		measuredHeight = Math.min(measuredHeight, heightSize);
		int measuredWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize
				: measuredHeight * 2 / 3;

		measuredWidth = Math.min(measuredWidth, widthSize);

		scale = measuredHeight * 1.0f / bgHeight;
		span = (bgHeight - bgWidth) / 4;
		top = bgWidth / 3;
		bottom = bgHeight;

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	float lastY = 0;
	boolean isTopChanged = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		isFirst = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastY = event.getY();
			if (event.getY() - top * scale < 0) {
				top = event.getY() / scale;
				isTopChanged = true;
			}

			if (event.getY() - bottom * scale > 0) {
				bottom = event.getY() / scale;
				isTopChanged = false;
			}

			else {
				if (event.getY() - top * scale >= bottom * scale - event.getY()) {
					bottom = event.getY() / scale;
					isTopChanged = false;
				} else {
					top = event.getY() / scale;
					isTopChanged = true;
				}
			}

			Log.d("fantasyado===>>", "y is :" + event.getY());

			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			if (isTopChanged) {
				top = event.getY() / scale;
			}
			else {
				bottom = event.getY() / scale;
			}

			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.scale(scale, scale);
		canvas.drawBitmap(grayBg, (getWidth() / scale - bgWidth) / 2, 0, mPaint);
		mPaint.setTextSize(40 / scale);
		float deltaY = (-mPaint.ascent() + mPaint.descent()) / 2;
		for (int i = 0; i < prices.length; i++) {
			canvas.drawText(prices[i],
					(getWidth() / scale + bgWidth) / 2 * 6 / 5, i * span
							+ bgWidth / 2 + deltaY, mPaint);
		}
		drawDecoration(canvas);
		drawPrice(canvas);
		canvas.restore();
		super.onDraw(canvas);
	}

	/**
	 * 根据上下的游标距离换算成相应的价格
	 * 
	 * @author Fantastyado
	 */

	private String getPriceFromDistance(float dis) {
		if (dis < bgWidth / 2 + span && dis > bgWidth / 2) {
			return (int) (1000 + Math.floor(10 * (bgWidth / 2 + span - dis)
					/ span) * 1000)
					+ "";
		} else if (dis >= bgWidth / 2 + span && dis < bgWidth / 2 + 2 * span) {
			return (int) (500 + Math.floor(25 * (bgWidth / 2 + 2 * span - dis)
					/ span) * 20)
					+ "";
		}

		else if (dis >= bgWidth / 2 + 2 * span && dis < bgWidth / 2 + 3 * span) {
			return (int) (200 + Math.floor(15 * (bgWidth / 2 + 3 * span - dis)
					/ span) * 20)
					+ "";
		}

		else if (dis >= bgWidth / 2 + 3 * span && dis <= bgWidth / 2 + 4 * span) {
			return (int) Math.floor(10 * (bgWidth / 2 + 4 * span - dis) / span)
					* 20 + "";
		}

		else {
			return "不限";
		}

	}

	String topprice = "";
	String bottomPrice = "";
	boolean isFirst = true;

	/**
	 * 绘制价格文字
	 * 
	 * @author Fantastyado
	 */
	private void drawPrice(Canvas canvas) {
		Paint textPaint = new Paint();
		textPaint.setTextSize(40 / scale);
		topprice = getPriceFromDistance(top);
		bottomPrice = getPriceFromDistance(bottom);
		float topTextX = getWidth() / scale / 15 + numBg.getWidth() / 4;
		float topTextY = top + numBg.getHeight() / 4;
		float bottomTextX = getWidth() / scale / 15 + numBg.getWidth() / 4;
		float bottomTextY = bottom + numBg.getHeight() / 4;

		// 根据用户是否改变过价格设置不同的价格颜色
		if (isFirst) {
			textPaint.setColor(Color.GRAY);
		}

		else {
			textPaint.setColor(Color.BLUE);
		}

		canvas.drawText(topprice, topTextX, topTextY, textPaint);
		canvas.drawText(bottomPrice, bottomTextX, bottomTextY, textPaint);

	}

	/**
	 * 绘制一些背景和装饰物
	 * 
	 * @author Fantastyado
	 */

	private void drawDecoration(Canvas canvas) {

		// 绘制价格文字的背景图片

		canvas.drawBitmap(numBg, getWidth() / scale / 15,
				top - numBg.getHeight() / 2, mPaint);

		canvas.drawBitmap(numBg, getWidth() / scale / 15,
				bottom - numBg.getHeight() / 2, mPaint);

		canvas.drawBitmap(Btn, (getWidth() / scale - Btn.getWidth()) / 2, top
				- Btn.getHeight() / 2, mPaint);
		canvas.drawBitmap(Btn, (getWidth() / scale - Btn.getWidth()) / 2,
				bottom - Btn.getHeight() / 2, mPaint);

		RectF dest = new RectF((getWidth() / scale - bgWidth) / 2, top,
				(getWidth() / scale + bgWidth) / 2, bottom);

		Rect src = new Rect(0, (int) top, greenBar.getWidth(),
				(int) greenBar.getHeight());
		// 绘制绿色滑竿
		canvas.drawBitmap(greenBar, src, dest, mPaint);

	}

}
