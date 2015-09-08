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
	 * ��ͼ�����ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>png</code>��<code>jpg</code>��<code>jpeg</code>��
	 * <code>bmp</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param c
	 *            ������
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openImage(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 2, getImageIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * ��ͼ�����ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>png</code>��<code>jpg</code>��<code>jpeg</code>��
	 * <code>bmp</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param f
	 *            ��Ƭ
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openImage(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 2, getImageIntent(file),
				mSuffix);

		if (intent != null) {
			f.startActivity(intent);
		}
	}

	/**
	 * ���������ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>mp3</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param c
	 *            ������
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openMusic(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 0, getMusicIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * ���������ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>mp3</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param f
	 *            ��Ƭ
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openMusic(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 0, getMusicIntent(file),
				mSuffix);

		if (intent != null) {
			f.startActivity(intent);
		}
	}
	/**
	 * ����Ƶ���ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>mp4</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param c
	 *            ������
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openVideo(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 1, getVideo(file),
				mSuffix);
		
		if (intent != null) {
			c.startActivity(intent);
		}
	}
	
	/**
	 * ����Ƶ���ļ��Ĳ���<br>
	 * �Ѵ���&nbsp;<code>mp4</code>&nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param f
	 *            ��Ƭ
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openVideo(Fragment f, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 1, getVideo(file),
				mSuffix);
		
		if (intent != null) {
			f.startActivity(intent);
		}
	}

	/**
	 * ���ı����ļ��Ĳ��� �Ѵ���&nbsp;<code>txt</code>��<code>log</code>��<code>xml</code>
	 * &nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param c
	 *            ������
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
	 */
	public static void openTextFile(Context c, File file, String[] mSuffix) {
		Intent intent = createVariousIntent(file, 3, getTextIntent(file),
				mSuffix);

		if (intent != null) {
			c.startActivity(intent);
		}
	}

	/**
	 * ���ı����ļ��Ĳ��� �Ѵ���&nbsp;<code>txt</code>��<code>log</code>��<code>xml</code>
	 * &nbsp;�ĺ�׺����ʹ��ʱ��ѡ������������͵�ͼ���ļ�
	 * 
	 * @param f
	 *            ��Ƭ
	 * @param file
	 *            ���������ļ�
	 * @param mSuffix
	 *            �Զ���ĺ�׺��
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
	 * ��δ�깤
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
