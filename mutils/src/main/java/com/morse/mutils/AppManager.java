package com.morse.mutils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Window;

import java.util.List;
import java.util.Stack;

/**
 * 
 * @ClassName: AppManager
 * @Description:用来管理所有的Activity. 每一个Activity都会加载到activityList集合当中。
 *                               每一个Service都会加载到serviceList当中
 *                               当程序需要退出的时候关闭所有的Activity和所有的service
 * @author libiao
 * @date 2015-7-30 下午4:21:10
 * 
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 移除Activity到堆栈
	 */
	public void removeActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.remove(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		if (activity != null) {
			finishActivity(activity);
		}

	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	// public void finishActivities() {
	// for (Activity activity : activityStack) {
	// finishActivity(activity);
	// }
	// }

	/**
	 * 结束所有Activity
	 */
//	public void finishAllActivity() {
//		BaseApplication.isLogin = false;
//		for (int i = 1, size = activityStack.size(); i < size; i++) {
//			if (null != activityStack.get(i) && i != size) {
//				activityStack.get(i).finish();
//			}
//		}
//		activityStack.clear();
//	}

	/**
	 * 退出应用程序
	 */
//	public void AppExit(Context context) {
//		try {
//			finishAllActivity();
//			finishActivity();
//			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//			activityMgr.killBackgroundProcesses(context.getPackageName());
//			System.exit(0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 得到当前类名
	 */
	public String getClassName(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
		RunningTaskInfo cinfo = runningTasks.get(0);
		ComponentName component = cinfo.topActivity;
		return component.getClassName();
	}

	/**
	 * 获取本地的app版本号
	 * 
	 * @return
	 * @throws Exception
	 */
	public static PackageInfo getVersion(Context context) throws Exception {
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		return packInfo;
	}

	public static void clearTitle(Activity activity) {
		activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);// 去除标题栏
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置只能竖屏
		AppManager.getAppManager().addActivity(activity);
	}
}
