package com.tz.qq;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ������
 * 
 * @author David.Yu 2013-07-31 11:43:21
 * 
 */
public class Tools {

	/**
	 * ����ʱ�������ʱ���� --- ����ʱ
	 * 
	 * @param time
	 * @return
	 */
	public static String getMSTime(long time1) {
		long time = time1 / 1000;
		long a, b, c = 0;
		a = time % 60;
		b = time / 60;
		if (b > 60) {
			c = b / 60;
			b = b % 60;
		}
		return c + ":" + b + ":" + a;
	}

	/**
	 * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int DPtoPX(Context context, int dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * �жϵ�ǰ��������
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkIsOnLine(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			// ��ȡ�������ӹ���Ķ���
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				// �жϵ�ǰ�����Ƿ��Ѿ�����
				if (info.isConnected()) {
					return true;
				}
			}
		}
		return false;
	}

	// /** ��ȡ�ַ��� */
	// public static String getString(Context context, int stringid) {
	// String content;
	// if (context == null || stringid == 0) {
	// content = "error";
	// } else {
	// content = context.getString(stringid);
	// }
	// return content;
	// }

	// /** ���ַ���ת������ */
	// public static int stringToInt(String num) {
	// if (isNull(num)) {
	// num = "0";
	// }
	// return Integer.parseInt(num);
	// }

	// /**
	// * ����ѡ���е�ֵ
	// *
	// * @param context
	// * @param spKey
	// * @param edKey
	// * @return
	// */
	// public static String getSharePrefenceData(Context context, String spKey,
	// String edKey) {
	// SharedPreferences sp = context.getSharedPreferences(spKey,
	// Context.MODE_PRIVATE);
	// return sp.getString(edKey, "");
	// }

	/**
	 * ��ȡ��Ļ��Ϣ
	 * 
	 * @author Michael.Zhang 2013-10-31 ����5:16:01
	 */
	// public static void getScreenWidth(Activity activity) {
	// DisplayMetrics dm = new DisplayMetrics();
	// activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	// StaticField.SCREEN_HEIGHT = dm.heightPixels;
	// StaticField.SCREEN_WIDHT = dm.widthPixels;
	// }

	/**
	 * ѹ��ͼƬ
	 * 
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
		// ���ͼƬ�Ŀ��
		int width = bm.getWidth();
		int height = bm.getHeight();
		// ������Ҫ�Ĵ�С
		int newWidth1 = newWidth;
		int newHeight1 = newHeight;
		// �������ű���
		float scaleWidth = ((float) newWidth1) / width;
		float scaleHeight = ((float) newHeight1) / height;
		// ȡ����Ҫ���ŵ�matrix����
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		matrix.postRotate(0);

		return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
	}

	/**
	 * ��ͼƬ��sdcard
	 * 
	 * @author Michael.Zhang
	 * @param bitmap1
	 */
	// public static void storeInSD(Bitmap bitmap, String img_name) {
	// File file = new File(StaticField.SDCARD_PATH);
	// File imageFile = new File(file, img_name);
	// try {
	// imageFile.createNewFile();
	// FileOutputStream fos = new FileOutputStream(imageFile);
	// bitmap.compress(CompressFormat.JPEG, 100, fos);
	// fos.flush();
	// fos.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * �ж� ����ֶε�ֵ��Ϊ��
	 * 
	 * @author Michael.Zhang 2013-08-02 13:34:43
	 * @return trueΪnull���; false��null���
	 */
	public static boolean isNull(String... ss) {
		for (int i = 0, m = ss.length; i < m; i++) {
			if (null == ss[i] || ss[i].equals("")
					|| ss[i].equalsIgnoreCase("null")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �ж� һ���ֶε�ֵ��Ϊ��
	 * 
	 * @author Michael.Zhang 2013-9-7 ����4:39:00
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s) {
		if (null == s || "".equals(s) || s.equalsIgnoreCase("null")) {
			return true;
		}

		return false;
	}

	/**
	 * �ж������ֶ��Ƿ�һ��
	 * 
	 * @author Michael.Zhang 2013-08-02 13:32:51
	 */
	public static boolean isEquals(String s0, String s1) {
		return s0 != null && null != s1 && s0.equals(s1);
	}

	/**
	 * 
	 * ��ʱ���תΪ�ַ��� ����
	 * 
	 * 
	 * @author Michael.Zhang 2013-08-05 14:09:23
	 * @param cc_time
	 * @return
	 */
	public static String getStrTime(String cc_time) {
		String re_StrTime = null;
		if (cc_time == null) {
			cc_time = System.currentTimeMillis() + "";
		}

		if (cc_time.length() == 10) { // ��λ ��
			cc_time += "000";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time));

		return re_StrTime;

	}

	/**
	 * 
	 * ��ʱ���תΪ�ַ��� ����
	 * 
	 * 
	 * @author Michael.Zhang 2013-08-05 14:09:17
	 * @param cc_time
	 * @return
	 */
	public static String getStrDate(String cc_time) {
		String re_StrTime = "";
		if (cc_time == null) {
			cc_time = System.currentTimeMillis() + "";
		}

		if (cc_time.length() == 10) {
			cc_time += "000";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time));

		return re_StrTime;
	}

	/**
	 * ���ַ������� ת��Ϊʱ���
	 * 
	 * @author ZhuZhouJun 2014-4-3 ����5:06:49
	 * @param user_time
	 * @return
	 */
	public static String getTime(String user_time) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d;
		try {
			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 13);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}

	/**
	 * ��ȡ�ո�ǰ����ַ���
	 * 
	 * @author ZhuZhouJun 2014-2-21 ����2:08:23
	 * 
	 * @param time
	 * @return
	 */
	public static String getDate(String time) {
		String date = "";
		date = time.substring(0, time.indexOf(' '));
		return date;
	}

	/**
	 * ��ȡ��ǰϵͳʱ��
	 * 
	 * @author ZhuZhouJun 2014-1-15 16:58:17
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);

		return str;
	}

	/**
	 * ����תΪԪ
	 * 
	 * @author Michael.Zhang
	 * @return
	 */
	public static double getMoney(String money) {
		if (money != null && !money.equals("") && !money.equals("null")) {
			return Double.parseDouble(money) / 100.0;
		}

		return 0.00;
	}

	/**
	 * ��֤���֤����
	 * 
	 * @author TangWei
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCard(String idCard) {
		if (isNull(idCard))
			return false;
		String pattern = "^[0-9]{17}[0-9|xX]{1}$";
		return idCard.matches(pattern);
	}

	/**
	 * ��֤�ֻ�����
	 * 
	 * @author TangWei
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (isNull(phone))
			return false;
		String pattern = "^((13[0-9])|(147)|(15[^4,\\D])|(18[0,2-9]))\\d{8}$";

		return phone.matches(pattern);
	}

	/**
	 * �ж�email��ʽ�Ƿ���ȷ
	 * 
	 * @author Michael.Zhang 2013-10-31 ����3:17:37
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * �򵥵���֤һ�����п���
	 * 
	 * @param bankCard
	 *            ���ÿ���16λ����������13-19λ
	 * @return
	 */
	public static boolean isBankCard(String bankCard) {
		if (isNull(bankCard))
			return false;
		String pattern = "^\\d{13,24}$";
		return bankCard.matches(pattern);
	}

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

	// /**
	// * ��px���͵ĳߴ�ת����dp���͵ĳߴ�
	// *
	// * @param size
	// * @param context
	// * @return
	// */
	// public static int PXtoDP(Context context, int pxValue) {
	// final float scale = context.getResources().getDisplayMetrics().density;
	// return (int) (pxValue / scale + 0.5f);
	// }

	// /**
	// * ��dp���͵ĳߴ�ת����px���͵ĳߴ�
	// *
	// * @param size
	// * @param context
	// * @return
	// */
	// public static int DPtoPX(Context context, int dipValue) {
	// final float scale = context.getResources().getDisplayMetrics().density;
	// return (int) (dipValue * scale + 0.5f);
	// }

	/**
	 * double ����
	 * 
	 * @return
	 */
	public static Double roundDouble(double val, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = ((0 == val) ? new BigDecimal("0.0") : new BigDecimal(
				Double.toString(val)));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ж� �б��Ƿ�Ϊ��
	 * 
	 * @return trueΪnull���; false��null���
	 */
	public static boolean isEmptyList(List list) {
		return list == null || list.size() == 0;
	}

	/**
	 * �ж�sd���Ƿ����
	 * 
	 * @author Michael.Zhang 2013-07-04 11:30:54
	 * @return
	 */
	public static boolean judgeSDCard() {
		String status = Environment.getExternalStorageState();
		return status.equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * �ж� http ����
	 * 
	 * @author Michael.Zhang
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		return null != url && url.startsWith("http://");
	}

	/**
	 * �ж�ͼƷ·��
	 * 
	 * @author Michael.Zhang 2013-11-8 ����7:50:30
	 * @return
	 */
	public static boolean isImgUrl(String imgUrl) {
		return isUrl(imgUrl) && imgUrl.endsWith(".jpg");
	}

	/**
	 * ���hashmap��value��ֵ,��List ����
	 * 
	 * @author Michael.Zhang 2013-08-21 13:56:07
	 * @param hashMap
	 * @return
	 */
	public static List<String> getListByHashMap(HashMap<String, String> hashMap) {
		List<String> list = new ArrayList<String>();
		Iterator iter = hashMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			list.add((String) val);
		}

		return list;
	}

	/**
	 * ��ȡ�汾�� ���û�����
	 * 
	 * @author Michael.Zhang 2013-08-29 22:14:37
	 * @return
	 */
	public static double getVersionName(Activity activity) {
		String versionName = "0";
		if (getPackageInfo(activity) != null) {
			versionName = getPackageInfo(activity).versionName;
		}

		return Double.parseDouble(versionName);
	}

	/**
	 * ��ȡ�汾�� ϵͳʶ���õ�
	 * 
	 * @author Michael.Zhang 2013-08-31 18:47:09
	 * @return
	 */
	public static int getVersionCode(Activity activity) {
		int versionCode = 0;
		if (getPackageInfo(activity) != null) {
			versionCode = getPackageInfo(activity).versionCode;
		}

		return versionCode;
	}

	private static PackageInfo getPackageInfo(Activity activity) {
		String packageName = activity.getPackageName();
		try {
			return activity.getPackageManager().getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ��ȡ���浽View��Tag�е��ַ���
	 * 
	 * @param v
	 * @return
	 */
	public static String getViewTagString(View v) {
		try {
			return v.getTag().toString();
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * ������λС��
	 */
	public static double sishewurubaoliuliangwei(double maxAmount) {
		DecimalFormat fnum = new DecimalFormat("##0.00");
		double decimalmaxamount = Double.parseDouble(fnum.format(maxAmount));
		return decimalmaxamount;
	};

	/**
	 * �ж�str�Ƿ�Ϊ��
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s)
			return true;
		if (s.length() == 0)
			return true;
		if (s.trim().length() == 0)
			return true;
		return false;
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static void jisuan(String shuzi, TextView tv, TextView tv1) {
		if (shuzi.substring(0, 1).equals("0")
				&& !shuzi.substring(1, 2).equals("0")) {
			tv.setText(shuzi.substring(1, shuzi.length()));
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& !shuzi.substring(2, 3).equals("0")) {// ǧ0 ��0 ʮ��0
														// ��0 0022
			tv.setText(shuzi.substring(2, shuzi.length()));
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& shuzi.substring(2, 3).equals("0")
				& !shuzi.substring(3, 4).equals("0")) {// ǧ0 ��0 ʮ��0 ��0
														// 0022
			tv.setText(shuzi.substring(3, shuzi.length()));
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& shuzi.substring(2, 3).equals("0")
				& shuzi.substring(3, 4).equals("0")) {
			tv.setVisibility(View.GONE);
			tv1.setVisibility(View.GONE);
		} else {
			tv.setText(shuzi);
		}
	}

	public static String jisuan(String shuzi) {
		if (shuzi.substring(0, 1).equals("0")
				&& !shuzi.substring(1, 2).equals("0")) {
			return shuzi.substring(1, shuzi.length());
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& !shuzi.substring(2, 3).equals("0")) {// ǧ0 ��0 ʮ��0
														// ��0 0022
			return shuzi.substring(2, shuzi.length());
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& shuzi.substring(2, 3).equals("0")
				& !shuzi.substring(3, 4).equals("0")) {// ǧ0 ��0 ʮ��0 ��0
														// 0022
			return shuzi.substring(3, shuzi.length());
		} else if (shuzi.substring(0, 1).equals("0")
				&& shuzi.substring(1, 2).equals("0")
				&& shuzi.substring(2, 3).equals("0")
				& shuzi.substring(3, 4).equals("0")) {
		} else {
			return shuzi;
		}
		return "";
	}

	public static String getAppInfo(Context context) {
		try {
			String pkName = context.getPackageName();
			String versionName = context.getPackageManager().getPackageInfo(
					pkName, 0).versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public static StringBuffer format(String qianduoduo_total) {
		String qianduoduo_total_rate1 = qianduoduo_total.substring(0,
				qianduoduo_total.lastIndexOf("."));
		String qianduoduo_total_rate6 = qianduoduo_total.substring(
				qianduoduo_total.lastIndexOf("."), qianduoduo_total.length());

		int length = qianduoduo_total_rate1.length();
		StringBuffer sb = new StringBuffer();

		String qianduoduo_total_rate4;
		String qianduoduo_total_rate3;
		String qianduoduo_total_rate2;
		String qianduoduo_total_rate5;

		if (length <= 9 && length > 6) { // 7-9λ������
			qianduoduo_total_rate4 = qianduoduo_total_rate1.substring(0,
					length - 6);
			sb.append(qianduoduo_total_rate4);
			sb.append(",");

			qianduoduo_total_rate3 = qianduoduo_total_rate1.substring(
					length - 6, length - 3);

			sb.append(qianduoduo_total_rate3);
			sb.append(",");

			qianduoduo_total_rate2 = qianduoduo_total_rate1.substring(
					length - 3, length);
			sb.append(qianduoduo_total_rate2);

			sb.append(qianduoduo_total_rate6);

		} else if (length <= 6 && length > 3) { // 4-6λ������
			qianduoduo_total_rate3 = qianduoduo_total_rate1.substring(0,
					length - 3);
			sb.append(qianduoduo_total_rate3);
			sb.append(",");

			qianduoduo_total_rate2 = qianduoduo_total_rate1.substring(
					length - 3, length);
			sb.append(qianduoduo_total_rate2);

			sb.append(qianduoduo_total_rate6);

		} else if (length <= 3 && length > 0) { // 1-3λ������
			sb.append(qianduoduo_total);
		} else { // Ŀǰֻ�ܿ��Ƶ�ǧ��
			qianduoduo_total_rate5 = qianduoduo_total_rate1.substring(0,
					length - 9);

			sb.append(qianduoduo_total_rate5);
			sb.append(",");

			qianduoduo_total_rate4 = qianduoduo_total_rate1.substring(
					length - 9, length - 6);
			sb.append(qianduoduo_total_rate4);
			sb.append(",");

			qianduoduo_total_rate3 = qianduoduo_total_rate1.substring(
					length - 6, length - 3);
			sb.append(qianduoduo_total_rate3);
			sb.append(",");

			qianduoduo_total_rate2 = qianduoduo_total_rate1.substring(
					length - 3, length);
			sb.append(qianduoduo_total_rate2);
			sb.append(qianduoduo_total_rate6);
		}

		return sb;
	}

}
