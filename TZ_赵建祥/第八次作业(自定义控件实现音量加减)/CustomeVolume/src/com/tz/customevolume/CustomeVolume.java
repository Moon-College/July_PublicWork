package com.tz.customevolume;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomeVolume extends View {

	private Paint paint;
	private int maxVolume;// 最大音量
	private int volume;// 当前音量
	private int beforeVolume;// 保存静音之前的音量
	private Bitmap gray;// 灰色图片
	private Bitmap green;// 绿色图片，表示音量
	private AudioManager am;
	private int block;// 中间空间高度
	private int allHeight;// 控件高度

	private float volumeTextSize;
	private int volumeTextColor;
	private String volumeText;
	private float top;// 静音文字高度

	public CustomeVolume(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取自定控件属性
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomeVolume);
		volumeTextSize = typedArray.getDimension(
				R.styleable.CustomeVolume_volumeTextSize, 10);
		volumeTextColor = typedArray.getColor(
				R.styleable.CustomeVolume_volumeTextColor, Color.RED);
		volumeText = typedArray.getString(R.styleable.CustomeVolume_volumeText);
		// 获取最大音量、当前音量
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
		volume = am.getStreamVolume(AudioManager.STREAM_RING);
		// 加载图片
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		block = green.getHeight();
		allHeight = (green.getHeight() + block) * maxVolume - block;

		// 初始化画笔
		paint = new Paint();
		// 静音
		paint.setTextSize(volumeTextSize);
		paint.setColor(volumeTextColor);
		// 计算文字高度
		FontMetrics fm = paint.getFontMetrics();
		top = fm.top;// 基线到顶底的高度
		// 刷新页面
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 获取触摸坐标
		float x = event.getX();
		float y = event.getY();
		if (y <= allHeight && x < green.getWidth()) {
			//将静音文字变成绿色
			volumeTextColor = Color.GREEN;
			paint.setColor(volumeTextColor);

			// 通过比例，计算当前音量 自定义控件高度/y=maxVolume/(maxVolume-volume)
			volume = maxVolume - (int) (maxVolume / (allHeight / y));
			// 显示音量UI，并播放声音
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// 重绘
			invalidate();

		}
		// 当点击静音时
		if (allHeight < y && y <= (allHeight - top) && x < green.getWidth()) {
			if (volumeTextColor == Color.GREEN) {
				volumeTextColor = Color.RED;
				// 重新设置颜色
				paint.setColor(volumeTextColor);
				beforeVolume = volume;
				volume = 0;
				// 设置音量为0
				am.setStreamVolume(AudioManager.STREAM_RING, volume,
						AudioManager.FLAG_SHOW_UI
								| AudioManager.FLAG_PLAY_SOUND);
			} else {
				volumeTextColor = Color.GREEN;
				// 重新设置颜色
				paint.setColor(volumeTextColor);
				volume = beforeVolume;
				am.setStreamVolume(AudioManager.STREAM_RING, volume,
						AudioManager.FLAG_SHOW_UI
								| AudioManager.FLAG_PLAY_SOUND);
			}
			// 重绘
			invalidate();

		}
		return false;
	}

	/**
	 * 监听物理按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			volume = volume > 1 ? volume - 1 : 0;
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// 重绘
			invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			volume = volume < maxVolume ? volume + 1 : maxVolume;
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// 重绘
			invalidate();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 将所有的先画成绿色
		for (int i = 0; i < maxVolume; i++) {
			// 图片被截取的部分
			Rect src = new Rect(0, 0, green.getWidth(), green.getHeight());
			// 要被画到的地方
			Rect dst = new Rect(0, (green.getHeight() + block) * i,
					green.getWidth(), (green.getHeight() + block) * i
							+ green.getHeight());
			canvas.drawBitmap(green, src, dst, paint);
		}

		// 再绘制灰色
		for (int i = 0; i < maxVolume - volume; i++) {
			// 图片被截取的部分
			Rect src = new Rect(0, 0, gray.getWidth(), gray.getHeight());
			// 要被画到的地方
			Rect dst = new Rect(0, (gray.getHeight() + block) * i,
					gray.getWidth(), (gray.getHeight() + block) * i
							+ gray.getHeight());
			canvas.drawBitmap(gray, src, dst, paint);
		}
		// 画静音
		canvas.drawText(volumeText, 0, allHeight - top,// 不设置对齐方式，这里的X,Y指文本基线的坐标
				paint);

		// 刷新页面
		invalidate();
	}

}
