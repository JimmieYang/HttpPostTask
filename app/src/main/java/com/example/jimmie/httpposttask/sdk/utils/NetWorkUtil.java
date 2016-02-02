package com.example.jimmie.httpposttask.sdk.utils;

/**
 * Created by 4399-1126 on 2016/1/27.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetWorkUtil {
    private static final String TAG = "NetWorkUtil";

    public static final String NETWORK_TYPE_WIFI = "WIFI";
    public static final String NETWORK_TYPE_UNKOWN = "unkown";
    public static final String NETWORK_TYPE_UNCONNECTED = "unconnected";
    public static final String NETWORK_TYPE_2_G = "2G";
    public static final String NETWORK_TYPE_3_G = "3G";
    public static final String NETWORK_TYPE_4_G = "4G";

    /**
     *  判断网络是断开/WiFi/2G/3G/4G
     * @param context
     * @return 返回网络情况
     */
    public static String getNetWorkType(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_TYPE_WIFI;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return getNetworkClass(context);
            }
        }
        return NETWORK_TYPE_UNCONNECTED;
    }

    /**
     * 从TelephonyManager类中拷贝的方法
     *
     * @param context
     * @return
     */
    private static  String getNetworkClass(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_TYPE_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_TYPE_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_TYPE_4_G;
            default:
                return NETWORK_TYPE_UNKOWN;
        }
    }
}
