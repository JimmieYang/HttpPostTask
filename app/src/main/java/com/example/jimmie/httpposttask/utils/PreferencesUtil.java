package com.example.jimmie.httpposttask.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 4399-1126 on 2016/1/25.
 */
public class PreferencesUtil {
    public static final String USER_INFO = "user_info";

    public static void saveUserInfo(Context context, String name, String value) {
        SharedPreferences userInfo = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getJsonUserInfo(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        String result = settings.getString(name, null);
        return result;
    }
}
