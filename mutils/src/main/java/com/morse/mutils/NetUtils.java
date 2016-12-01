/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.morse.mutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络相关的工具类
 * 
 * @author Frank
 * @date 2015年8月7日上午10:31:25
 */

public class NetUtils {
	private NetUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifiConnect(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		}

		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}

		return info.getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent();
		intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
		intent.putExtra("extra_prefs_show_button_bar", true);
		intent.putExtra("wifi_enable_next_on_connect", true);
		activity.startActivityForResult(intent, 0);
	}

}
