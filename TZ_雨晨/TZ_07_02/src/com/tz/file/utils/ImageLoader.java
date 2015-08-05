package com.tz.file.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.util.LruCache;

/**
 * User: Tokey (tokeyhot@gmail.com) 
 * Date: 2015-07-27 Time: 14:39
 * Function:(简单的图片缓存类) FIXME
 */
public class ImageLoader {

	/** 图片最大宽度. */
	public static final int MAX_WIDTH = 4096 / 2;

	/** 图片最大高度. */
	public static final int MAX_HEIGHT = 4096 / 2;
	public static final String TAG = ImageLoader.class.getSimpleName();
	private static LruCache<String, Bitmap> mLruCache;

	private ImageLoader() {

		// 获得手机的最大内存是多少
		long maxMemorySize = Runtime.getRuntime().maxMemory();

		// 设置缓存的大小为，手机最大内存的（1/10）
		mLruCache = new LruCache<String, Bitmap>((int) (maxMemorySize / 10)) {
			@Override
			protected int sizeOf(String key, Bitmap value)
			{
				// 计算图所占用的内存空间
				// getByteCount() = getRowBytes() * getHeight()，也就是说位图所占用的内存空间数
				// 等于位图的每一行所占用的空间数乘以位图的行数
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	private static class SingletonHolder {
		private final static ImageLoader INSTANCE = new ImageLoader();
	}

	/**
	 * 属于懒汉式单例， 因为Java机制规定，内部类SingletonHolder只有在getInstance()方法第一次调用的时候
	 * 才会被加载（实现了lazy），而且其加载过程是线程安全的。 内部类加载的时候实例化一次instance。
	 *
	 * @return
	 */
	public static ImageLoader getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	/**
	 * 添加图片到LRU缓存中
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
			// 判断缓存中是否存在当前Key的图片
			if (getBitMapFromMemoryCache(key) == null) {
				// 不存在，添加图片到缓存中
				mLruCache.put(key, value);
			}
		}

		return true;
	}

	/**
	 * 从LRU缓存中获取到图片
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
	 * 图片压缩
	 */

	public static Bitmap getScaleBitmap(String bitmapPath, int desiredWidth,
			int desiredHeight)
	{
		Bitmap resizeBmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(bitmapPath);
		// 获取图片的原始宽度高度
		int srcWidth = opts.outWidth;
		int srcHeight = opts.outHeight;
		int[] size = resizeToMaxSize(srcWidth, srcHeight, desiredWidth,
				desiredHeight);
		desiredWidth = size[0];
		desiredHeight = size[1];

		// 缩放的比例
		float scale = getMinScale(srcWidth, srcHeight, desiredWidth,
				desiredHeight);
		int destWidth = srcWidth;
		int destHeight = srcHeight;
		if (scale != 0) {
			destWidth = (int) (srcWidth * scale);
			destHeight = (int) (srcHeight * scale);
		}

		// 默认为ARGB_8888.
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		// inSampleSize=2 表示图片宽高都为原来的二分之一，即图片为原来的四分之一
		// 缩放的比例，SDK中建议其值是2的指数值，通过inSampleSize来进行缩放，其值表明缩放的倍数
		if (scale < 0.25) {
			// 缩小到4分之一
			opts.inSampleSize = 2;
		} else if (scale < 0.125) {
			// 缩小到8分之一
			opts.inSampleSize = 4;
		} else {
			opts.inSampleSize = 1;
		}

		// 设置大小
		opts.outWidth = destWidth;
		opts.outHeight = destHeight;

		// 创建内存
		opts.inJustDecodeBounds = false;
		// 使图片不抖动
		opts.inDither = false;

		resizeBmp = BitmapFactory.decodeFile(bitmapPath, opts);
		// 缩小或者放大
		resizeBmp = getScaleBitmap(resizeBmp, scale);
		return resizeBmp;
	}

	private static float getMinScale(int srcWidth, int srcHeight,
			int desiredWidth, int desiredHeight)
	{
		// 缩放的比例
		float scale = 0;
		// 计算缩放比例，宽高的最小比例
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
			// 重新计算大小
			desiredWidth = MAX_WIDTH;
			float scaleWidth = (float) desiredWidth / srcWidth;
			desiredHeight = (int) (desiredHeight * scaleWidth);
		}

		if (desiredHeight > MAX_HEIGHT) {
			// 重新计算大小
			desiredHeight = MAX_HEIGHT;
			float scaleHeight = (float) desiredHeight / srcHeight;
			desiredWidth = (int) (desiredWidth * scaleHeight);
		}
		size[0] = desiredWidth;
		size[1] = desiredHeight;
		return size;
	}

	/**
	 * 描述：根据等比例缩放图片.
	 * 
	 * @param bitmap
	 *            the bitmap
	 * @param scale
	 *            比例
	 * @return Bitmap 新图片
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
			// 获取Bitmap资源的宽和高
			int bmpW = bitmap.getWidth();
			int bmpH = bitmap.getHeight();

			// 注意这个Matirx是android.graphics底下的那个
			Matrix matrix = new Matrix();
			// 设置缩放系数，分别为原来的0.8和0.8
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
