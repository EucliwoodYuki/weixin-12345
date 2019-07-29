package com.weixin.model;

public class WeixinUser {

    String phone;
    String name;
    String openId;

    public WeixinUser() {
    }

    public WeixinUser(String phone, String name, String openId) {
        this.phone = phone;
        this.name = name;
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
