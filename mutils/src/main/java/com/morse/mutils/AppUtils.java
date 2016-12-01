/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.morse.mutils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/**
 * 应用工具类
 * 
 * @author Frank
 * @date 2015年8月7日上午10:23:42
 */

public class AppUtils {

	private AppUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序版本名称信息
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序版本号
	 * 
	 * @param context
	 * @return 当前应用的版本号
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 安装应用程序
	 * 
	 * @param context
	 * @param apkFile
	 */
	public static void installApp(Context context, File apkFile)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 显示/隐藏密码
	 * 
	 * @param isHidden
	 * @param editText
	 */
	public static void showPassword(boolean isHidden, EditText editText) {
		if (isHidden) {
			editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		} else {
			editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}
		editText.postInvalidate();

		CharSequence charSequence = editText.getText();
		if (charSequence instanceof Spannable) {
			Spannable spanText = (Spannable) charSequence;
			Selection.setSelection(spanText, charSequence.length());
		}
	}

}
