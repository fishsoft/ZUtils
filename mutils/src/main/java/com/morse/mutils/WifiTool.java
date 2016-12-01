package com.morse.mutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class Name: WifiAdmin.java<br>
 * Function:Wifi连接管理工具类<br>
 */
public class WifiTool implements Comparator<ScanResult> {
    // 定义一个WifiManager对象
    private WifiManager mWifiManager;
    // 定义一个WifiInfo对象
    private WifiInfo mWifiInfo;
    // 扫描出的网络连接列表
    private List<ScanResult> mWifiList;
    // 网络连接列表
    private List<WifiConfiguration> mWifiConfigurations;
    private WifiLock mWifiLock;
    private Context mContext;
    private Handler mHandler;

    public WifiTool(Context context) {
        // 取得WifiManager对象
        this.mContext = context;
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        // 取得WifiInfo对象
        mWifiInfo = mWifiManager.getConnectionInfo();
        mHandler = new Handler();
    }

    /**
     * Function:信号强度转换为字符串<br>
     *
     * @param level <br>
     * @author ZYT DateTime 2014-5-14 下午2:14:42<br>
     */
    public static String singlLevToStr(int level) {

        String resuString = "无信号";

        if (Math.abs(level) > 100) {
        } else if (Math.abs(level) > 80) {
            resuString = "弱";
        } else if (Math.abs(level) > 70) {
            resuString = "强";
        } else if (Math.abs(level) > 60) {
            resuString = "强";
        } else if (Math.abs(level) > 50) {
            resuString = "较强";
        } else {
            resuString = "极强";
        }
        return resuString;
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED && info[i].isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Function:关闭wifi<br>
     * www.javaapk.com更改
     *
     * @author ZYT DateTime 2014-5-15 上午12:17:37<br>
     * @return<br>
     */
    public boolean closeWifi() {
        if (mWifiManager.isWifiEnabled()) {
            return mWifiManager.setWifiEnabled(false);
        }
        return false;
    }

    /**
     * Gets the Wi-Fi enabled state.检查当前wifi状态
     *
     * @return One of {@link WifiManager#WIFI_STATE_DISABLED},
     * {@link WifiManager#WIFI_STATE_DISABLING},
     * {@link WifiManager#WIFI_STATE_ENABLED},
     * {@link WifiManager#WIFI_STATE_ENABLING},
     * {@link WifiManager#WIFI_STATE_UNKNOWN}
     */
    public int checkState() {
        return mWifiManager.getWifiState();
    }

    /**
     * 锁定wifiLock
     */
    public void acquireWifiLock() {
        mWifiLock.acquire();
    }

    /**
     * 解锁wifiLock
     */
    public void releaseWifiLock() {
        // 判断是否锁定
        if (mWifiLock.isHeld()) {
            mWifiLock.acquire();
        }
    }

    /**
     * 创建一个wifiLock
     */
    public void createWifiLock() {
        mWifiLock = mWifiManager.createWifiLock("test");
    }

    /**
     * 得到配置好的网络
     *
     * @return
     */
    public List<WifiConfiguration> getConfiguration() {
        return mWifiConfigurations;
    }

    /**
     * 指定配置好的网络进行连接
     *
     * @param index
     */
    public void connetionConfiguration(int index) {
        if (index > mWifiConfigurations.size()) {
            return;
        }
        // 连接配置好指定ID的网络
        mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId, true);
    }

    /**
     * 扫描wifi列表
     */
    public void startScan() {
        // openWifi();
        mWifiManager.startScan();

        SystemClock.sleep(1500);

        // 得到扫描结果
        mWifiList = mWifiManager.getScanResults();
        // 得到配置好的网络连接
        mWifiConfigurations = mWifiManager.getConfiguredNetworks();
    }

    /**
     * 得到网络列表
     *
     * @return
     */
    public List<ScanResult> getWifiList() {
        return mWifiList;
    }

    /**
     * 查看扫描结果
     *
     * @return
     */
    @SuppressLint("UseValueOf")
    public StringBuffer lookUpScan() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mWifiList.size(); i++) {
            sb.append("Index_" + new Integer(i + 1).toString() + ":");
            // 将ScanResult信息转换成一个字符串包
            // 其中把包括：BSSID、SSID、capabilities、frequency、level
            sb.append((mWifiList.get(i)).toString()).append("\n");
        }
        return sb;
    }

    /**
     * 获取手机mac地址
     *
     * @return
     */
    public String getMacAddress() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
    }

    /**
     * 获取路由器mac地址 Return the basic service set identifier (BSSID) of the current
     * access point. The BSSID may be {@code null} if there is no network
     * currently connected.
     *
     * @return the BSSID, in the form of a six-byte MAC address:
     * {@code XX:XX:XX:XX:XX:XX}
     */
    public String getBSSID() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
    }

    /**
     * 获取手机IP
     *
     * @return
     */
    public int getIpAddress() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
    }

    /**
     * 获取wifi SSID
     *
     * @return
     */
    public String getSSID() {
        return (mWifiManager.getConnectionInfo() == null) ? "NULL" : mWifiManager.getConnectionInfo().getSSID();
    }

    /**
     * 获取连接速度
     *
     * @return
     */
    public int getLinkSpeed() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getLinkSpeed();
    }

    /**
     * 获取RSSI
     *
     * @return
     */
    public int getRssi() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getRssi();
    }

    /**
     * 获取网络ID Each configured network has a unique small integer ID, used to
     * identify the network when performing operations on the supplicant. This
     * method returns the ID for the currently connected network.
     *
     * @return the network ID, or -1 if there is no currently connected network
     */
    public int getNetWordId() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
    }

    /**
     * Function: 得到wifiInfo的所有信息<br>
     *
     * @author ZYT DateTime 2014-5-14 上午11:03:32<br>
     * @return<br>
     */
    public String getWifiInfo() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
    }

    /**
     * 添加一个网络并连接
     *
     * @param configuration
     */
    public void addNetWork(WifiConfiguration configuration) {
        int wcgId = mWifiManager.addNetwork(configuration);
        mWifiManager.enableNetwork(wcgId, true);
    }

    /**
     * 断开指定ID的网络
     *
     * @param netId
     */
    public void disConnectionWifi(int netId) {
        mWifiManager.disableNetwork(netId);
        disConnect();
    }

    public void disConnect() {
        mWifiManager.disconnect();
    }

    /**
     * Function: 提供一个外部接口，传入要连接的无线网 <br>
     *
     * @param SSID
     *            SSID
     * @param Password
     * @param Type
     * <br>
     *            没密码：{@linkplain WifiCipherType#WIFICIPHER_NOPASS}<br>
     *            WEP加密： {@linkplain WifiCipherType#WIFICIPHER_WEP}<br>
     *            WPA加密： {@linkplain WifiCipherType#WIFICIPHER_WPA}
     * @return true:连接成功；false:连接失败<br>
     */
    // public boolean connect(String SSID, String Password, WifiCipherType Type)
    // {
    // if (!this.openWifi()) {
    // return false;
    // }
    // // 开启wifi功能需要一段时间(我在手机上测试一般需要1-3秒左右)，所以要等到wifi
    // // 状态变成WIFI_STATE_ENABLED的时候才能执行下面的语句
    // while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
    // openWifi();
    // }
    // WifiConfiguration wifiConfig = createWifiInfo(SSID, Password, Type);
    // if (wifiConfig == null) {
    // return false;
    // }
    // WifiConfiguration tempConfig = this.isExsits(SSID);
    // int tempId = wifiConfig.networkId;
    // if (tempConfig != null) {
    // tempId = tempConfig.networkId;
    // mWifiManager.removeNetwork(tempConfig.networkId);
    // }
    // int netID = mWifiManager.addNetwork(wifiConfig);
    // // 断开连接
    // mWifiManager.disconnect();
    // // 重新连接
    // netID = wifiConfig.networkId;
    // // 设置为true,使其他的连接断开
    // boolean bRet = mWifiManager.enableNetwork(netID, true);
    // mWifiManager.reconnect();
    // return bRet;
    // }

    /**
     * Function: 打开wifi功能<br>
     *
     * @return true:打开成功；false:打开失败<br>
     * @author ZYT DateTime 2014-5-14 上午11:01:11<br>
     */
    public boolean openWifi() {
        boolean bRet = true;
        if (!mWifiManager.isWifiEnabled()) {
            bRet = mWifiManager.setWifiEnabled(true);
        }
        return bRet;
    }

    /**
     * 创建一个wifi连接
     *
     * @param SSID
     * @param Password
     * @param Type
     * @return
     */
    // private WifiConfiguration createWifiInfo(String SSID, String Password,
    // WifiCipherType Type) {
    // WifiConfiguration config = new WifiConfiguration();
    // config.allowedAuthAlgorithms.clear();
    // config.allowedGroupCiphers.clear();
    // config.allowedKeyManagement.clear();
    // config.allowedPairwiseCiphers.clear();
    // config.allowedProtocols.clear();
    // config.SSID = "\"" + SSID + "\"";
    // if (Type == WifiCipherType.WIFICIPHER_NOPASS) {
    // config.wepKeys[0] = "";
    // config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    // config.wepTxKeyIndex = 0;
    // }
    // if (Type == WifiCipherType.WIFICIPHER_WEP) {
    // config.preSharedKey = "\"" + Password + "\"";
    // config.hiddenSSID = true;
    // config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
    // config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    // config.wepTxKeyIndex = 0;
    // }
    // if (Type == WifiCipherType.WIFICIPHER_WPA) {
    // // 修改之后配置
    // config.preSharedKey = "\"" + Password + "\"";
    // config.hiddenSSID = true;
    // config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
    // config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
    // config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
    // // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
    // config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    // config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
    //
    // } else {
    // return null;
    // }
    // return config;
    // }

    /**
     * 查看以前是否也配置过这个网络
     *
     * @param SSID
     * @return
     */
    public boolean isExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function:判断扫描结果是否连接上<br>
     *
     * @param result
     * @author ZYT DateTime 2014-5-14 上午11:31:40<br>
     * @return<br>
     */
    public boolean isConnect(ScanResult result) {
        if (result == null) {
            return false;
        }
        mWifiInfo = mWifiManager.getConnectionInfo();
        String g2 = "\"" + result.SSID + "\"";
        return mWifiInfo.getSSID() != null && mWifiInfo.getSSID().endsWith(g2)
                && result.BSSID.equals(mWifiInfo.getBSSID());
    }

    /**
     * 获取已连接的wifi的网络ID
     *
     * @return
     */
    public int getConnNetId() {
        // result.SSID;
        mWifiInfo = mWifiManager.getConnectionInfo();
        return mWifiInfo.getNetworkId();
    }

    /**
     * 添加到网络
     *
     * @param wcg
     */
    public boolean addNetwork(WifiConfiguration wcg) {
        if (wcg == null) {
            return false;
        }
        int wcgID = mWifiManager.addNetwork(wcg);
        boolean b = mWifiManager.enableNetwork(wcgID, true);
        mWifiManager.saveConfiguration();
        return b;
    }

    /**
     * 连接指定AP
     *
     * @param scan
     * @return
     */
    public boolean connectSpecificAP(ScanResult scan) {

        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        boolean networkInSupplicant = false;
        boolean connectResult = false;
        disConnect();
        // 阻塞线程连接网络
        if (null != list) {
            for (WifiConfiguration w : list) {
                // 将指定AP 名字转化并连接网络
                if (w.BSSID != null && w.BSSID.equals(scan.BSSID)) {
                    connectResult = mWifiManager.enableNetwork(w.networkId, true);
                    networkInSupplicant = true;
                    break;
                }
            }

            if (!networkInSupplicant) {
                WifiConfiguration config = CreateWifiInfo(scan, "");
                connectResult = addNetwork(config);
            }
        }
        return connectResult;
    }

    /**
     * 然后是一个实际应用方法，只验证过没有密码的情况：
     *
     * @param scan
     * @param Password
     * @return
     */
    public WifiConfiguration CreateWifiInfo(ScanResult scan, String Password) {
        WifiConfiguration config = new WifiConfiguration();
        config.hiddenSSID = false;
        config.status = WifiConfiguration.Status.ENABLED;
        if (scan.capabilities.contains("WEP")) {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.SSID = "\"" + scan.SSID + "\"";
            config.wepTxKeyIndex = 0;
            config.wepKeys[0] = Password;
        } else if (scan.capabilities.contains("PSK")) {
            config.SSID = "\"" + scan.SSID + "\"";
            config.preSharedKey = "\"" + Password + "\"";
        } else if (scan.capabilities.contains("EAP")) {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.SSID = "\"" + scan.SSID + "\"";
            config.preSharedKey = "\"" + Password + "\"";
        } else {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.SSID = "\"" + scan.SSID + "\"";
            config.preSharedKey = null;
            config.wepKeys[0] = "\"" + "\"";
            config.wepTxKeyIndex = 0;
        }
        return config;
    }

    /**
     * 判断wifi是否加密
     */
    public boolean getSecurity(ScanResult result) {
        return result.capabilities.contains("WEP") || result.capabilities.contains("PSK")
                || result.capabilities.contains("EAP");
    }

    /**
     * 判断wifi是否连接
     *
     * @param context
     * @return
     */
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取网络连接类型
     *
     * @return
     */
    public String getNetType() {
        String type = "";
        ConnectivityManager connectMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info != null) {
            if (1 == info.getType()) {
                return "wifi";
            } else {
                int subType = info.getSubtype();
                if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                        || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                    return type = "2g";
                } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                        || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                        || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                        || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                        || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                    return type = "3g";
                } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                    // LTE是3g到4g的过渡，是3.9G的全球标准
                    return type = "4g";
                }
            }
        }
        return type;
    }

    /**
     * 判断wifi打开状态
     *
     * @return
     */
    public boolean checkNetCardState() {
        boolean flag = false;
        if (mWifiManager.getWifiState() == mWifiManager.WIFI_STATE_DISABLING) {
            flag = false;
        } else if (mWifiManager.getWifiState() == mWifiManager.WIFI_STATE_DISABLED) {
            flag = false;
        } else if (mWifiManager.getWifiState() == mWifiManager.WIFI_STATE_ENABLING) {
        } else
            flag = mWifiManager.getWifiState() == mWifiManager.WIFI_STATE_ENABLED;
        return flag;
    }

    /**
     * wifi重连
     *
     * @return boolean
     */
    public boolean wifiReConnect() {
        return mWifiManager.reconnect();
    }

    /**
     * 按wifi信号排序
     */
    @Override
    public int compare(ScanResult lhs, ScanResult rhs) {
        // TODO Auto-generated method stub
        if (Math.abs(lhs.level) - Math.abs(rhs.level) <= 0)
            return 1;
        else
            return -1;
    }

    /**
     * 连接WIFI
     *
     * @param ssid
     * @param pwd
     */
    public void connectWifi(final String ssid, final String pwd) {
        if (TextUtils.isEmpty(ssid)) {
            return;
        }
        final String ssidSrc = "\"" + ssid + "\"";
        if (NetUtils.isWifiConnect(mContext) && ssidSrc.equals(getSSID())) {
            return;
        }
        new Thread() {
            public void run() {
                startScan();
                for (ScanResult scanResult : mWifiList) {
                    if (!ssid.equals(scanResult.SSID))
                        continue;
                    if (NetUtils.isWifiConnect(mContext)) {
                        disConnect();
                    }
                    if (getSecurity(scanResult)) {
                        LogUtils.show("connectWifi: " + ssid + " * " + pwd);
                        if (!TextUtils.isEmpty(pwd)) {
                            addNetwork(CreateWifiInfo(scanResult, pwd));
                        }
                    } else {
                        // 未加密
                        LogUtils.show("connectWifi: " + ssid + " * " + pwd);
                        connectSpecificAP(scanResult);
                    }
                }
            }
        }.start();
    }

    public boolean isSsidConnet(String ssid, ScanResult scanResult) {
        return ssid.equals(getSSID()) && getBSSID().equals(scanResult.BSSID);
    }

    /**
     * 延迟一定的时间，主动连接Wifi
     *
     * @param delay
     */
    public void connectWifiDelay(long delay) {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                String ssid = (String) SpfUtils.get(mContext, "wifi_ssid", "");
                String pwd = (String) SpfUtils.get(mContext, "wifi_pwd", "");
                connectWifi(ssid, pwd);
            }
        }, delay);
    }

    //******************* 搜索Wifi列表，并按WiFi信号强度排序 *******************//
    public void scanWifiList(final IScanWifiCallBack callBack) {
        try {
            mWifiManager.startScan();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<ScanResult> scanResults = mWifiManager.getScanResults();
                    if (scanResults == null || scanResults.size() == 0) {
                        if (callBack != null) {
                            callBack.onFailure();
                        }
                        return;
                    }

                    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                    Collections.sort(scanResults, new Comparator<ScanResult>() {
                        @Override
                        public int compare(ScanResult scanResult, ScanResult t1) {
                            return Math.abs(scanResult.level) - Math.abs(t1.level) <= 0 ? 1 : -1;
                        }
                    });
                    if (callBack != null) {
                        callBack.onSuccess(scanResults);
                    }
                }
            }, 1500);

        } catch (Exception e) {
            LogUtils.show(e.getMessage());
            if (callBack != null) {
                callBack.onFailure();
            }
        }
    }

    /**
     * 连接到指定的SSID
     *
     * @param scanResult
     * @param pwd
     */
    public void connectSsid(final ScanResult scanResult, final String pwd) {
        final String ssid = scanResult.SSID;
        if (TextUtils.isEmpty(ssid)) {
            return;
        }
        final String ssidSrc = "\"" + ssid + "\"";
        if (NetUtils.isWifiConnect(mContext) && ssidSrc.equals(getSSID())) {
            return;
        }

        new Thread() {

            @Override
            public void run() {
                if (TextUtils.isEmpty(pwd)) {
                    connectSpecificAP(scanResult);
                } else {
                    addNetwork(CreateWifiInfo(scanResult, pwd));
                }
            }
        }.start();

    }

    /**
     * 定义几种加密方式，一种是WEP，一种是WPA，还有没有密码的情况
     *
     * @author Morse
     */
    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    public interface IScanWifiCallBack {

        void onSuccess(List<ScanResult> scanResults);

        void onFailure();

    }

}

