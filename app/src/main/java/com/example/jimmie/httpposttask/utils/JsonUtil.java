package com.example.jimmie.httpposttask.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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

            result = (String) object.get(RESULT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static HashMap<String, Object> getJsonUserInfoMap(String jsonString) {
        JSONObject object = null;
        JSONObject result = null;
        HashMap<String, Object> jsonResult = null;
        try {
            object = new JSONObject(jsonString);
            result = (JSONObject) object.get(RESULT);
            jsonResult = new HashMap<>();
            jsonResult.put(UID, result.get(UID));
            jsonResult.put(USERNAME, result.get(USERNAME));
            jsonResult.put(BINDEDPHONE, result.get(BINDEDPHONE));
            jsonResult.put(NICK, result.get(NICK));
            jsonResult.put(AVATAR_MIDDLE, result.get(AVATAR_MIDDLE));
            jsonResult.put(ACCESS_TOKEN, result.get(ACCESS_TOKEN));
            jsonResult.put(STATE, result.get(STATE));
            jsonResult.put(CODE, result.get(CODE));
            jsonResult.put(ACCOUNT_TYPE, result.get(ACCOUNT_TYPE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static String getJsonUserInfoString(String jsonString){
        HashMap<String,Object> map = getJsonUserInfoMap(jsonString);
        StringBuffer sb = new StringBuffer();
        sb.append(UID+"\n\t"+map.get(UID)+"\n\n");
        sb.append(USERNAME+"\n\t"+map.get(USERNAME)+"\n\n");
        sb.append(BINDEDPHONE+"\n\t"+map.get(BINDEDPHONE)+"\n\n");
        sb.append(NICK+"\n\t"+map.get(NICK)+"\n\n");
        sb.append(AVATAR_MIDDLE+"\n\t"+map.get(AVATAR_MIDDLE)+"\n\n");
        sb.append(ACCESS_TOKEN+"\n\t"+map.get(ACCESS_TOKEN)+"\n\n");
        sb.append(STATE+"\n\t"+map.get(STATE)+"\n\n");
        sb.append(CODE+"\n\t"+map.get(CODE)+"\n\n");
        sb.append(ACCOUNT_TYPE+"\n\t"+map.get(ACCOUNT_TYPE)+"\n\n");
        return sb.toString();
    }
}
