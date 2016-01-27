package com.example.jimmie.httpposttask.model.entity;

/**
 * Created by 4399-1126 on 2016/1/27.
 */
public class PostEntiy {
    private String state ;
    private String topbar ;
    private String device ;
    private String usernames ;

    public PostEntiy (){
        state = "";
        topbar ="false";
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setTopbar(String topbar) {
        this.topbar = topbar;
    }

    public String getTopbar() {
        return topbar;
    }

    public String getState() {
        return state;
    }

    public String getDevice() {
        return device;
    }

    public String getUsernames() {
        return usernames;
    }
}
