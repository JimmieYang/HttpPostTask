package com.example.jimmie.httpposttask.sdk.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jimmie on 16/1/24.
 */
public class JsonUtil {
    public static final String RESULT = "result";

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
