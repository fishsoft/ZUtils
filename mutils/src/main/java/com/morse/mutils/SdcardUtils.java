/** Copyright © 2015-2020 100msh.com All Rights Reserved */
package com.morse.mutils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SD卡相关的辅助类
 * 
 * @author Frank
 * @date 2015年8月7日上午10:34:43
 */

public class SdcardUtils {

	private SdcardUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断内置SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

	}

	/**
	 * 获取内置SD卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}

	/**
	 * 获取外部缓存目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getExternalCacheDir(Context context) {
		String path = context.getExternalCacheDir().getAbsolutePath();
		return path;
	}

	/**
	 * 获取手机内置和外置SDcard存储路径
	 * 
	 * @param context
	 * @return
	 */
	public static String[] getStorageList(Context context) {
		StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		Method methodGetPaths = null;
		try {
			methodGetPaths = manager.getClass().getMethod("getVolumePaths");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		String[] paths = null;
		try {
			paths = (String[]) methodGetPaths.invoke(manager);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return paths;
	}

	/**
	 * 获取外置SDcard路径
	 * 
	 * @param context
	 * @return
	 */
	public static String getExternalSDCartPath(Context context) {
		String[] paths = getStorageList(context);

		if (paths.length == 2) {
			for (String path : paths) {
				if (path != null && !path.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
					return path+File.separator;
				}
			}
			return null;
		} else {
			return null;
		}
	}

}
