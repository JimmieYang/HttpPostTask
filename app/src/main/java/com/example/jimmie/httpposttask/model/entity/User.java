package com.example.jimmie.httpposttask.model.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 4399-jimmie on 2016/1/26.
 */
public class User  {
    private final String RESULT = "result";
    private final String UID = "uid";
    private final String USERNAME = "username";
    private final String BINDEDPHONE = "bindedphone";
    private final String NICK = "nick";
    private final String AVATAR_MIDDLE = "avatar_middle";
    private final String ACCESS_TOKEN = "access_token";
    private final String STATE = "state";
    private final String CODE = "code";
    private final String ACCOUNT_TYPE = "account_type";

    private String uid;
    private String username;
    private String bindedphone;
    private String nick;
    private String avatar_middle;
    private String access_token;
    private String state;
    private String account_type;

    public User() {

    }

    public User(String jsonStr) {
        parse(jsonStr);
    }

    public void parse(String jsonStr) {
        try {
            JSONObject result = new JSONObject(jsonStr);
            Log.d("LoginControlImpl", "jsonStr:" + jsonStr);
            Log.d("LoginControlImpl", "jsonStr.length:" + result.length());
            uid = result.optString(UID);
            username = result.optString(USERNAME);
            bindedphone = result.optString(BINDEDPHONE);
            avatar_middle = result.optString(AVATAR_MIDDLE);
            access_token = result.optString(ACCESS_TOKEN);
            nick = result.optString(NICK);
            state = result.optString(STATE);
            account_type = result.optString(ACCOUNT_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getBindedphone() {
        return bindedphone;
    }

    public String getNick() {
        return nick;
    }

    public String getAvatar_middle() {
        return avatar_middle;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getState() {
        return state;
    }

    public String getAccount_type() {
        return account_type;
    }

}
