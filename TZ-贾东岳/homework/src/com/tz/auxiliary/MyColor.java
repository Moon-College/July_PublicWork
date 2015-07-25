package com.tz.auxiliary;

import android.graphics.Color;

import com.tz.main.MyApplication;

/**
 * 方便用某些获取color用的,有的地方用某些方法获取太长了. 另外是对获取color的方法进行收集; 所有方法都是静态方法，打点可查看，全有注释；
 * 
 * @author JDY
 */
public class MyColor {
	/**
	 * 使用getResources().getColor(R.color.yanse)获取颜色用的 int rColorMyColor
	 * 传入R.color.yanse格式的值
	 * 
	 * @return int 类型的color
	 */
	public static int getColorByR(int rColorMyColor) {
		return MyApplication.appContext().getResources()
				.getColor(rColorMyColor);
	}

	/**
	 * 写这个是为了记住颜色可直接用八位十六进制数字表示。
	 * 
	 * @param hexadecimal
	 *            例如 0xff123456。 前两位的0x代表十六进制的意思,后面的数字为固定的8位十六进制数字.
	 *            十六进制的数字可以有16个值，从小到大分别为0123456789abcdef.
	 *            前两位ff代表透明度的,00是全透明,e0是半透明,ff为不透明. ；
	 *            12处代表红色的值,34处代表蓝色的值, 56处代表绿色的值,取值范围全是0~255，值越小颜色越浅.
	 * @return 返回传入的值
	 */
	public static int getColorByHexadecimal(int hexadecimal) {
		return hexadecimal;
	}

	/**
	 * 此函数调用Color.argb(0xff, 0x00, 0x00, 0x00)。
	 * 
	 * 前两位的0x代表十六进制的意思,后面的数字为固定的2位十六进制数字.
	 * 十六进制的数字可以有16个值，从小到大分别为0123456789abcdef. 12处代表红色的值,越小颜色越浅；
	 * 34处代表蓝色的值,越小颜色越浅； 56处代表绿色的值,越小颜色越浅；
	 * 
	 * @param alpha
	 *            格式是 0xff 代表透明度的,00是全透明,e0是半透明,ff为不透明;取值范围是0~255。
	 * @param red
	 *            格式是0x00 代表红色的值,越小颜色越浅,取值范围是0~255。
	 * @param green
	 *            格式是0x00 代表绿色的值,越小颜色越浅,取值范围是0~255。
	 * @param blue
	 *            格式是0x00 代表蓝色的值,越小颜色越浅,取值范围是0~255。
	 * @return 返回int类型的color
	 */
	public static int getColorByHexadecimal(int alpha, int red, int green,
			int blue) {
		return Color.argb(0xff, 0x00, 0x00, 0x00);
	}
}
