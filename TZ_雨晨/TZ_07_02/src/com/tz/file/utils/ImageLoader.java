package com.tz.file.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.util.LruCache;

/**
 * User: Tokey (tokeyhot@gmail.com) 
 * Date: 2015-07-27 Time: 14:39
 * Function:(�򵥵�ͼƬ������) FIXME
 */
public class ImageLoader {

	/** ͼƬ�����. */
	public static final int MAX_WIDTH = 4096 / 2;

	/** ͼƬ���߶�. */
	public static final int MAX_HEIGHT = 4096 / 2;
	public static final String TAG = ImageLoader.class.getSimpleName();
	private static LruCache<String, Bitmap> mLruCache;

	private ImageLoader() {

		// ����ֻ�������ڴ��Ƕ���
		long maxMemorySize = Runtime.getRuntime().maxMemory();

		// ���û���Ĵ�СΪ���ֻ�����ڴ�ģ�1/10��
		mLruCache = new LruCache<String, Bitmap>((int) (maxMemorySize / 10)) {
			@Override
			protected int sizeOf(String key, Bitmap value)
			{
				// ����ͼ��ռ�õ��ڴ�ռ�
				// getByteCount() = getRowBytes() * getHeight()��Ҳ����˵λͼ��ռ�õ��ڴ�ռ���
				// ����λͼ��ÿһ����ռ�õĿռ�������λͼ������
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	private static class SingletonHolder {
		private final static ImageLoader INSTANCE = new ImageLoader();
	}

	/**
	 * ��������ʽ������ ��ΪJava���ƹ涨���ڲ���SingletonHolderֻ����getInstance()������һ�ε��õ�ʱ��
	 * �Żᱻ���أ�ʵ����lazy������������ع������̰߳�ȫ�ġ� �ڲ�����ص�ʱ��ʵ����һ��instance��
	 *
	 * @return
	 */
	public static ImageLoader getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	/**
	 * ���ͼƬ��LRU������
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean setBitmapToMemoryCahce(String key, Bitmap value)
	{
		if (key == null || value == null) {
			throw new NullPointerException("key == null || value == null");
		}
		synchronized (ImageLoader.class) {
			// �жϻ������Ƿ���ڵ�ǰKey��ͼƬ
			if (getBitMapFromMemoryCache(key) == null) {
				// �����ڣ����ͼƬ��������
				mLruCache.put(key, value);
			}
		}

		return true;
	}

	/**
	 * ��LRU�����л�ȡ��ͼƬ
	 *
	 * @param key
	 * @return
	 */
	public static Bitmap getBitMapFromMemoryCache(String key)
	{
		if (key == null) {
			throw new NullPointerException("key == null");
		}

		return mLruCache.get(key) == null ? null : mLruCache.get(key);
	}

	/**
	 * ͼƬѹ��
	 */

	public static Bitmap getScaleBitmap(String bitmapPath, int desiredWidth,
			int desiredHeight)
	{
		Bitmap resizeBmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(bitmapPath);
		// ��ȡͼƬ��ԭʼ��ȸ߶�
		int srcWidth = opts.outWidth;
		int srcHeight = opts.outHeight;
		int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth,
				desiredHeight);
		desiredWidth = size[0];
		desiredHeight = size[1];

		// ���ŵı���
		float scale = getMinScale(srcWidth, srcHeight, desiredWidth,
				desiredHeight);
		int destWidth = srcWidth;
		int destHeight = srcHeight;
		if (scale != 0) {
			destWidth = (int) (srcWidth * scale);
			destHeight = (int) (srcHeight * scale);
		}

		// Ĭ��ΪARGB_8888.
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		// inSampleSize=2 ��ʾͼƬ��߶�Ϊԭ���Ķ���֮һ����ͼƬΪԭ�����ķ�֮һ
		// ���ŵı�����SDK�н�����ֵ��2��ָ��ֵ��ͨ��inSampleSize���������ţ���ֵ�������ŵı���
		if (scale < 0.25) {
			// ��С��4��֮һ
			opts.inSampleSize = 2;
		} else if (scale < 0.125) {
			// ��С��8��֮һ
			opts.inSampleSize = 4;
		} else {
			opts.inSampleSize = 1;
		}

		// ���ô�С
		opts.outWidth = destWidth;
		opts.outHeight = destHeight;

		// �����ڴ�
		opts.inJustDecodeBounds = false;
		// ʹͼƬ������
		opts.inDither = false;

		resizeBmp = BitmapFactory.decodeFile(bitmapPath, opts);
		// ��С���߷Ŵ�
		resizeBmp = getScaleBitmap(resizeBmp, scale);
		return resizeBmp;
	}

	private static float getMinScale(int srcWidth, int srcHeight,
			int desiredWidth, int desiredHeight)
	{
		// ���ŵı���
		float scale = 0;
		// �������ű�������ߵ���С����
		float scaleWidth = (float) desiredWidth / srcWidth;
		float scaleHeight = (float) desiredHeight / srcHeight;
		if (scaleWidth > scaleHeight) {
			scale = scaleWidth;
		} else {
			scale = scaleHeight;
		}

		return scale;
	}

	private static int[] resizeToMaxSize(int srcWidth, int srcHeight,
			int desiredWidth, int desiredHeight)
	{
		int[] size = new int[2];
		if (desiredWidth <= 0) {
			desiredWidth = srcWidth;
		}
		if (desiredHeight <= 0) {
			desiredHeight = srcHeight;
		}
		if (desiredWidth > MAX_WIDTH) {
			// ���¼����С
			desiredWidth = MAX_WIDTH;
			float scaleWidth = (float) desiredWidth / srcWidth;
			desiredHeight = (int) (desiredHeight * scaleWidth);
		}

		if (desiredHeight > MAX_HEIGHT) {
			// ���¼����С
			desiredHeight = MAX_HEIGHT;
			float scaleHeight = (float) desiredHeight / srcHeight;
			desiredWidth = (int) (desiredWidth * scaleHeight);
		}
		size[0] = desiredWidth;
		size[1] = desiredHeight;
		return size;
	}

	/**
	 * ���������ݵȱ�������ͼƬ.
	 * 
	 * @param bitmap
	 *            the bitmap
	 * @param scale
	 *            ����
	 * @return Bitmap ��ͼƬ
	 */
	@SuppressWarnings("null")
	public static Bitmap getScaleBitmap(Bitmap bitmap, float scale)
	{

		if (bitmap!=null) {
			return null;
		}

		if (scale == 1) {
			return bitmap;
		}

		Bitmap resizeBmp = null;
		try {
			// ��ȡBitmap��Դ�Ŀ�͸�
			int bmpW = bitmap.getWidth();
			int bmpH = bitmap.getHeight();

			// ע�����Matirx��android.graphics���µ��Ǹ�
			Matrix matrix = new Matrix();
			// ��������ϵ�����ֱ�Ϊԭ����0.8��0.8
			matrix.postScale(scale, scale);
			resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bmpW, bmpH, matrix,
					true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resizeBmp != bitmap) {
				bitmap.recycle();
			}
		}
		return resizeBmp;
	}

}
