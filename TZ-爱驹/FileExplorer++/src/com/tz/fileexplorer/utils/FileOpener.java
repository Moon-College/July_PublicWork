package com.tz.fileexplorer.utils;

import java.io.File;
import java.util.Locale;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FileOpener {

	private static final String[][] suffixes = Suffix.suffixes;
/*	public static final String[][] suffixes = new String[][] {
		{ ".png", ".jpg", ".jpeg", ".bmp" }, { ".mp3" },
			{ ".txt", ".log", ".xml" }, };*/
	/**
	 * 打开图像类文件的操作<br>
	 * 已存在&nbsp;<code>png</code>、<code>jpg</code>、<code>jpeg</code>、
	 * <code>bmp</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param c
	 *            上下文
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openImage(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 2, getImageIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * 打开图像类文件的操作<br>
	 * 已存在&nbsp;<code>png</code>、<code>jpg</code>、<code>jpeg</code>、
	 * <code>bmp</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param f
	 *            碎片
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openImage(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 2, getImageIntent(file),
				mSuffix);

		if (intent != null) {
			f.startActivity(intent);
		}
	}

	/**
	 * 打开音乐类文件的操作<br>
	 * 已存在&nbsp;<code>mp3</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param c
	 *            上下文
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openMusic(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 0, getMusicIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * 打开音乐类文件的操作<br>
	 * 已存在&nbsp;<code>mp3</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param f
	 *            碎片
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openMusic(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 0, getMusicIntent(file),
				mSuffix);

		if (intent != null) {
			f.startActivity(intent);
		}
	}
	/**
	 * 打开视频类文件的操作<br>
	 * 已存在&nbsp;<code>mp4</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param c
	 *            上下文
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openVideo(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 1, getVideo(file),
				mSuffix);
		
		if (intent != null) {
			c.startActivity(intent);
		}
	}
	
	/**
	 * 打开视频类文件的操作<br>
	 * 已存在&nbsp;<code>mp4</code>&nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param f
	 *            碎片
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openVideo(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 1, getVideo(file),
				mSuffix);
		
		if (intent != null) {
			f.startActivity(intent);
		}
	}

	/**
	 * 打开文本类文件的操作 已存在&nbsp;<code>txt</code>、<code>log</code>、<code>xml</code>
	 * &nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param c
	 *            上下文
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openTextFile(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 3, getTextIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * 打开文本类文件的操作 已存在&nbsp;<code>txt</code>、<code>log</code>、<code>xml</code>
	 * &nbsp;的后缀名，使用时可选择添加其他类型的图像文件
	 * 
	 * @param f
	 *            碎片
	 * @param file
	 *            所操作的文件
	 * @param mSuffix
	 *            自定义的后缀名
	 */
	public static void openTextFile(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 3, getTextIntent(file),
				mSuffix);

		if (intent != null) {
			f.startActivity(intent);
		}
	}

	private static Intent createVariousIntent(File file, int index,
			Intent intent, String[] mSuffix) {
		String fileName = file.getName().toLowerCase(Locale.ENGLISH);
		// --core code--
		for (int i = 0; i < suffixes [index].length; i++) {
			if (fileName.endsWith(suffixes[index][i])) {
				return intent;
			} else {
				for (int j = 0; mSuffix != null && j < mSuffix.length; j++) {
					if (mSuffix[j] != null && fileName.endsWith(mSuffix[j])) {
						return intent;
					}
				}
			}
		}
		return null;
	}

	private static Intent getImageIntent(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW)
				.addCategory(Intent.CATEGORY_DEFAULT)
				.setDataAndType(Uri.fromFile(file), "image/*");
		return intent;
	}

	private static Intent getMusicIntent(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW)
				.addCategory(Intent.CATEGORY_DEFAULT)
				.setDataAndType(Uri.fromFile(file), "audio/*");
		return intent;
	}

	private static Intent getTextIntent(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW)
				.addCategory(Intent.CATEGORY_DEFAULT)
				.setData(Uri.fromFile(file));
		return intent;
	}

	private static Intent getVideo(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW)
		.addCategory(Intent.CATEGORY_DEFAULT)
		.setDataAndType(Uri.fromFile(file),"video/*");
		return intent;
	}
	/**
	 * 尚未完工
	 * 
	 * @param file
	 */
	private static Intent openOtherFiles(File file) {
		Intent intent = null;
		String fileName = file.getName().toLowerCase(Locale.ENGLISH);

		for (int i = 0; i < suffixes.length; i++) {
			for (int j = 0; j < suffixes[i].length; j++) {
				if (!fileName.endsWith(suffixes[i][j])) {
					// intent = new Intent();
					// intent.setAction(Intent.ACTION_CHOOSER);
					break;
				}
			}
		}
		return intent;
	}

	static class NoSuchSuffixException extends IllegalArgumentException {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4617849251764704891L;

		NoSuchSuffixException(String msg) {
			super(msg);
		}
	}
}
