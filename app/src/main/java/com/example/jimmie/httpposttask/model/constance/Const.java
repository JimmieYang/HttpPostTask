package com.example.jimmie.httpposttask.model.constance;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public class Const {
    // 访问的URL
    public static final String HTTP_URL = "http://m.4399api.com/openapi/oauth.html";

    // post 实体参数名称
    public static final String STATE = "state";
    public static final String DEVICE = "device";
    public static final String TOPBAR = "topbar";
    public static final String USERNAMES = "usernames";

    // device 的参数
    public static final String DEVICE_IDENTIFIER = "DEVICE_IDENTIFIER";
    public static final String SCREEN_RESOLUTION = "SCREEN_RESOLUTION";
    public static final String DEVICE_MODEL = "DEVICE_MODEL";
    public static final String DEVICE_MODEL_VERSION = "DEVICE_MODEL_VERSION";
    public static final String SYSTEM_VERSION = "SYSTEM_VERSION";
    public static final String PLATFORM_TYPE = "PLATFORM_TYPE";
    public static final String SDK_VERSION = "SDK_VERSION";
    public static final String GAME_KEY = "GAME_KEY";
    public static final String CANAL_IDENTIFIER = "CANAL_IDENTIFIER";
    public static final String GAME_VERSION = "GAME_VERSION";
    public static final String BID = "BID";
    public static final String IMSI = "IMSI";
    public static final String PHONE = "PHONE";
    public static final String UDID = "UDID";
    public static final String DEBUG = "DEBUG";
    public static final String NETWORK_TYPE = "NETWORK_TYPE";
    public static final String UID = "UID";

    // device 参数中固定的值
    public static final String DEVICE_MODEL_VERSION_VALUE = "";
    public static final String PLATFORM_TYPE_VALUE = "Android";
    public static final String SDK_VERSION_VALUE = "2.7.0.2";
    public static final String GAME_KEY_VALUE = "40001";
    public static final String CANAL_IDENTIFIER_VALUE = "";
    public static final String GAME_VERSION_VALUE = "2.0.0";
    public static final String BID_VALUE = "cn.m4399.game";
    public static final String UDID_VALUE = "";
    public static final String DEBUG_VALUE = "false";


    // 设置JavaScript中的调用方法名称
    public static final String JAVESCRIP_METHOD_NAME = "onSumbit";
    // 设置JavaScript中的方法
    public static final String JAVESCRIP_METHOD = "javascript:window.onSumbit.getBodyContent(document.body.innerHTML);";
    // 对比此URL,看是否登录成功
    public static final String START_WITH_URL = "http://m.4399api.com/openapi/oauth-callback";
}
