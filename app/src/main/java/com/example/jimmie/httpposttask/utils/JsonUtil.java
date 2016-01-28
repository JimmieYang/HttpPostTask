package com.example.jimmie.httpposttask.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jimmie on 16/1/24.
 */
public class JsonUtil {
    public static final String RESULT = "result";
    public static final String UID = "uid";
    public static final String USERNAME = "username";
    public static final String BINDEDPHONE = "bindedphone";
    public static final String NICK = "nick";
    public static final String AVATAR_MIDDLE = "avatar_middle";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String STATE = "state";
    public static final String CODE = "code";
    public static final String ACCOUNT_TYPE = "account_type";

    /**
     * 从json结果中获取 result 字段
     *
     * @param jsonString JSON字段
     * @return result字段
     */
    public static String getJsonResult(String jsonString) {
        JSONObject object = null;
        String result = null;
        try {
            object = new JSONObject(jsonString);
            result = object.optString(RESULT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
