package com.weixin.service;

import com.weixin.model.WeixinUser;

public interface DataService {

//    public Boolean IsBind(String openId);

    public WeixinUser UserInfo(String openId);

    public Boolean addUser(String phone, String username, String openId);

    public Boolean deleteUser(String openId);
}
