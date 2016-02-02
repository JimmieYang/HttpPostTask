package com.example.jimmie.httpposttask.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4399-1126 on 2016/1/25.
 */
public class PreferencesUtil {
    public static final String USER_INFO = "user_info";
    public static final String USER = "user";
    public static final String USER_NAMES = "usernames";


    public static void saveUserInfo(Context context, String name, String value) {
        SharedPreferences userInfo = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getUserInfo(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        String result = settings.getString(name, "");
        return result;
    }

    public static void saveNames(Context context, String username) {
        boolean isRepeat = false;
        String namesStr = PreferencesUtil.getUserInfo(context, PreferencesUtil.USER_NAMES);
        List<String> namesList = new ArrayList<>();
        if (namesStr != null && !"".equals(namesStr)) {
            String[] names = namesStr.split(",");
            for (int z = 0; z < names.length; z++) {
                namesList.add(names[z]);
            }
            int length = namesList.size();
            for (int i = 0; i < length; i++) {
                if (username.equalsIgnoreCase(namesList.get(i))) {
                    isRepeat = true;
                    if (i == 0)
                        break;
                    String temp = namesList.get(i);

                    for (int j = i; j >= 1; j--) {
                        namesList.set(j, namesList.get(j - 1));
                    }
                    namesList.set(0, temp);
                    break;
                }
            }
            if (!isRepeat) {
                for (int i = length; i > 0; i--) {
                    if (i == length) {
                        namesList.add(i, namesList.get(i - 1));
                    } else {
                        namesList.set(i, namesList.get(i - 1));
                    }
                }
                namesList.set(0, username);
            }
        } else {
            namesList.add(0, username);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < namesList.size(); i++) {
            if (i == namesList.size() - 1) {
                sb.append(namesList.get(i) + "");
            } else {
                sb.append(namesList.get(i) + ",");
            }
        }
        PreferencesUtil.saveUserInfo(context, PreferencesUtil.USER_NAMES, sb.toString());
    }
}
