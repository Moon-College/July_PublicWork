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
	private int maxVolume;// �������
	private int volume;// ��ǰ����
	private int beforeVolume;// ���澲��֮ǰ������
	private Bitmap gray;// ��ɫͼƬ
	private Bitmap green;// ��ɫͼƬ����ʾ����
	private AudioManager am;
	private int block;// �м�ռ�߶�
	private int allHeight;// �ؼ��߶�

	private float volumeTextSize;
	private int volumeTextColor;
	private String volumeText;
	private float top;// �������ָ߶�

	public CustomeVolume(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ��ȡ�Զ��ؼ�����
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomeVolume);
		volumeTextSize = typedArray.getDimension(
				R.styleable.CustomeVolume_volumeTextSize, 10);
		volumeTextColor = typedArray.getColor(
				R.styleable.CustomeVolume_volumeTextColor, Color.RED);
		volumeText = typedArray.getString(R.styleable.CustomeVolume_volumeText);
		// ��ȡ�����������ǰ����
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
		volume = am.getStreamVolume(AudioManager.STREAM_RING);
		// ����ͼƬ
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		block = green.getHeight();
		allHeight = (green.getHeight() + block) * maxVolume - block;

		// ��ʼ������
		paint = new Paint();
		// ����
		paint.setTextSize(volumeTextSize);
		paint.setColor(volumeTextColor);
		// �������ָ߶�
		FontMetrics fm = paint.getFontMetrics();
		top = fm.top;// ���ߵ����׵ĸ߶�
		// ˢ��ҳ��
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��ȡ��������
		float x = event.getX();
		float y = event.getY();
		if (y <= allHeight && x < green.getWidth()) {
			//���������ֱ����ɫ
			volumeTextColor = Color.GREEN;
			paint.setColor(volumeTextColor);

			// ͨ�����������㵱ǰ���� �Զ���ؼ��߶�/y=maxVolume/(maxVolume-volume)
			volume = maxVolume - (int) (maxVolume / (allHeight / y));
			// ��ʾ����UI������������
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// �ػ�
			invalidate();

		}
		// ���������ʱ
		if (allHeight < y && y <= (allHeight - top) && x < green.getWidth()) {
			if (volumeTextColor == Color.GREEN) {
				volumeTextColor = Color.RED;
				// ����������ɫ
				paint.setColor(volumeTextColor);
				beforeVolume = volume;
				volume = 0;
				// ��������Ϊ0
				am.setStreamVolume(AudioManager.STREAM_RING, volume,
						AudioManager.FLAG_SHOW_UI
								| AudioManager.FLAG_PLAY_SOUND);
			} else {
				volumeTextColor = Color.GREEN;
				// ����������ɫ
				paint.setColor(volumeTextColor);
				volume = beforeVolume;
				am.setStreamVolume(AudioManager.STREAM_RING, volume,
						AudioManager.FLAG_SHOW_UI
								| AudioManager.FLAG_PLAY_SOUND);
			}
			// �ػ�
			invalidate();

		}
		return false;
	}

	/**
	 * ��������ť
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			volume = volume > 1 ? volume - 1 : 0;
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// �ػ�
			invalidate();
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			volume = volume < maxVolume ? volume + 1 : maxVolume;
			am.setStreamVolume(AudioManager.STREAM_RING, volume,
					AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
			// �ػ�
			invalidate();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// �����е��Ȼ�����ɫ
		for (int i = 0; i < maxVolume; i++) {
			// ͼƬ����ȡ�Ĳ���
			Rect src = new Rect(0, 0, green.getWidth(), green.getHeight());
			// Ҫ�������ĵط�
			Rect dst = new Rect(0, (green.getHeight() + block) * i,
					green.getWidth(), (green.getHeight() + block) * i
							+ green.getHeight());
			canvas.drawBitmap(green, src, dst, paint);
		}

		// �ٻ��ƻ�ɫ
		for (int i = 0; i < maxVolume - volume; i++) {
			// ͼƬ����ȡ�Ĳ���
			Rect src = new Rect(0, 0, gray.getWidth(), gray.getHeight());
			// Ҫ�������ĵط�
			Rect dst = new Rect(0, (gray.getHeight() + block) * i,
					gray.getWidth(), (gray.getHeight() + block) * i
							+ gray.getHeight());
			canvas.drawBitmap(gray, src, dst, paint);
		}
		// ������
		canvas.drawText(volumeText, 0, allHeight - top,// �����ö��뷽ʽ�������X,Yָ�ı����ߵ�����
				paint);

		// ˢ��ҳ��
		invalidate();
	}

}
