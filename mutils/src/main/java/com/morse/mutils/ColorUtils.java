package com.morse.mutils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Pattern;

public class ColorUtils {

	/**
	 * 根据颜色资源Id，取得颜色
	 * 
	 * @param colorId
	 * @return color
	 */
	public static int getResourcesColor(Context context, int colorId) {
		int color = 0x00ffffff;
		try {
			color = context.getResources().getColor(colorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return color;
	}

	/**
	 * 将十六进制 颜色代码 转换为 int
	 * 
	 * @return color
	 */
	public static int HextoColor(String color) {
		// #00000000 - #ffffffff
		String reg = "#[a-f0-9A-F]{8}";
		if (!Pattern.matches(reg, color)) {
			color = "#ffffffff";
		}
		return Color.parseColor(color);
	}

	/**
	 * 设置颜色透明度
	 * 
	 * @param color
	 * @param alpha
	 * @return color
	 */
	public static int setColorAlpha(int color, int alpha) {
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return Color.argb(alpha, red, green, blue);
	}

	/**
	 * 设置整个字符串中某部分字体的颜色
	 * 
	 * @param context
	 * @param text
	 * @param start
	 * @param end
	 * @param colorId
	 * @return
	 */
	public static SpannableStringBuilder setPartTextColor(Context context, String text, int start, int end, int colorId) {
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		// ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
		ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResourcesColor(context, colorId));
		builder.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return builder;
	}

}
