package com.example.jimmie.httpposttask.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.example.jimmie.httpposttask.model.constance.Const;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public class DeviceUtil {
    private Context context;
    private TelephonyManager tm;

    public DeviceUtil(Context context) {
        this.context = context;
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

    }

    private String getScreenResolution() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels + "*" + dm.widthPixels;
    }

    private String getDeviceIdentifier(){
        return tm.getDeviceId();
    }

    private String getDeviceModel(){
        return Build.MODEL;
    }

    private String getSystemVersion(){
        return Build.VERSION.RELEASE;
    }

    private String getImsi(){
        return tm.getSimSerialNumber();
    }

    private String getPhone(){
       return tm.getLine1Number();
    }

    private String getNetworkType(){
        return NetWorkUtil.getNetWorkType(context);
    }

    public String getDeviceParams(){
        JSONObject object = new JSONObject();
        try {
            object.putOpt(Const.DEVICE_IDENTIFIER,getDeviceIdentifier());
            object.putOpt(Const.SCREEN_RESOLUTION,getScreenResolution());
            object.putOpt(Const.DEVICE_MODEL,getDeviceModel());
            object.putOpt(Const.DEVICE_MODEL_VERSION,Const.DEVICE_MODEL_VERSION_VALUE);
            object.putOpt(Const.SYSTEM_VERSION,getSystemVersion());
            object.putOpt(Const.PLATFORM_TYPE,Const.PLATFORM_TYPE_VALUE);
            object.putOpt(Const.SDK_VERSION,Const.SDK_VERSION_VALUE);
            object.putOpt(Const.GAME_KEY,Const.GAME_KEY_VALUE);
            object.putOpt(Const.CANAL_IDENTIFIER,Const.CANAL_IDENTIFIER_VALUE);
            object.putOpt(Const.GAME_VERSION,Const.GAME_VERSION_VALUE);
            object.putOpt(Const.BID,Const.BID_VALUE);
            object.putOpt(Const.IMSI,getImsi());
            object.putOpt(Const.PHONE,getPhone());
            object.putOpt(Const.UDID,Const.UDID_VALUE);
            object.putOpt(Const.DEBUG,Const.DEBUG_VALUE);
            object.putOpt(Const.NETWORK_TYPE,getNetworkType());
            object.putOpt(Const.UID,Const.UDID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
}
