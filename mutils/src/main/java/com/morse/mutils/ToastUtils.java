/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.morse.mutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast统一管理类
 * 
 * @author Frank
 * @date 2015年8月7日上午10:41:00
 */

public class ToastUtils extends Toast {

	public static ToastUtils toast = null;
	private static View view = null;
	public static TextView tvContent = null;

	/**
	 * 是否显示Toast
	 */
	public static boolean isShow = true;
	private static Toast mToast;

	/**
	 * @param context
	 */
	@SuppressLint("InflateParams")
	private ToastUtils(Context context) {
		super(context);
		if (toast == null) {
			toast = new ToastUtils(context);
		}
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.toast, null);
		}
		if (tvContent == null) {
			tvContent = (TextView) view.findViewById(R.id.toast_text);
		}
		toast.setView(view);
	}

	// /**
	// * @Title: getMyToast
	// * @Description: TODO使用单例模式,得到当前Toast对象
	// * @param context
	// * @return
	// * @return: MyToast
	// */
	// @SuppressLint("InflateParams")
	// public static ToastUtils getInstance(Context context) {
	// if (toast == null) {
	// toast = new ToastUtils(context);
	// }
	// if (view == null) {
	// view = LayoutInflater.from(context).inflate(R.layout.toast, null);
	// }
	// if (tvContent == null) {
	// tvContent = (TextView) view.findViewById(R.id.toast_text);
	// }
	// toast.setView(view);
	// return toast;
	// }

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void show(Context context, CharSequence message) {
		if (isShow)
			show(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void show(Context context, int message) {
		if (isShow)
			show(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow)
			show(context, message, Toast.LENGTH_LONG);
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow)
			show(context, message, Toast.LENGTH_LONG);
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (isShow) {
			if (mToast == null) {
				mToast = Toast.makeText(context, message, duration);
			}

			mToast.setText(message);
			mToast.show();
		}
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow) {
			if (mToast == null) {
				mToast = Toast.makeText(context, message, duration);
			}
			mToast.setText(message);
			mToast.show();
		}
	}

	/**
	 * @Title: show
	 * @Description:自己定义Toast显示的位置，以及时间，大小
	 * @param message
	 *            要显示的内容
	 * @param gravity
	 *            位置
	 * @param xOffset
	 *            X轴的偏移量
	 * @param yOffset
	 *            Y轴偏移量
	 * @param duration
	 *            时长
	 * @return: void
	 */
	public static void show(String message, int gravity, int xOffset, int yOffset, int duration) {
		if (!TextUtils.isEmpty(message)) {
			tvContent.setText(message);
			if (gravity == 0) {
				gravity = Gravity.BOTTOM;
			}
			if (yOffset == 0) {
				yOffset = 100;
			}
			toast.setGravity(gravity, xOffset, yOffset);
			toast.setDuration(duration);
			toast.show();
		}
	}

}
