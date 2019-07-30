package com.weixin.service.Impl;

import com.weixin.mapper.DataMapper;
import com.weixin.model.WeixinUser;
import com.weixin.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    //用户是否绑定
//    public Boolean IsBind(String openId) {
//        return UserInfo(openId)!=null;
//    }

    //查询用户信息
    public WeixinUser findByOpenId(String openId){
        return dataMapper.findByOpenId(openId);
    }

    //添加用户
    public Boolean addUser(String phone, String username, String openId){
        try {
            return dataMapper.addUser(phone,username,openId);
        }catch (Exception e){
            return false;
        }
    }

    //删除用户
    public Boolean deleteUser(String openId){
        return dataMapper.deleteUser(openId);
    }
}
