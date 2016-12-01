/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.morse.mutils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 打开或关闭软键盘
 * 
 * @author Frank
 * @date 2015年8月7日上午10:30:32
 */

public class KeyBoardUtils {

//	/**
//	 * 关闭软键盘
//	 *
//	 * @param context
//	 */
//	public static void closeKeybord(Activity activity) {
//		InputMethodManager inputMethodManager = (InputMethodManager) activity
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
//				InputMethodManager.HIDE_NOT_ALWAYS);
//	}
//
//	/**
//	 * 打卡软键盘
//	 *
//	 * @param mEditText
//	 *            输入框
//	 * @param mContext
//	 *            上下文
//	 */
//	public static void openKeybord(EditText mEditText, Context mContext) {
//		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
//		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//	}
//
//	/**
//	 * 关闭软键盘
//	 *
//	 * @param mEditText
//	 *            输入框
//	 * @param mContext
//	 *            上下文
//	 */
//	public static void closeKeybord(EditText mEditText, Context mContext) {
//		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
//	}

	/**
	 * 显示软键盘
	 * View ： EditText、TextView
	 * wantPop : true = show , false = hide
	 *
	 * @param context
	 * @param view
	 * @param wantPop
	 */
	public static void popSoftKeyboard(Context context, View view, boolean wantPop) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (wantPop) {
			view.requestFocus();
			imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		} else {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}


	/**
	 * 显示软键盘
	 *
	 * @param view
	 */
	public static void showSoftKeyboard(View view) {
		Context context = view.getContext();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		view.requestFocus();
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * 隐藏软键盘
	 *
	 * @param view
	 */
	public static void hideSoftKeyboard(View view) {
		Context context = view.getContext();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

}
