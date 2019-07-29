package com.weixin.mapper;

import com.weixin.model.WeixinUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作数据库
 */

@Mapper
public interface DataMapper {


    //查询用户信息
    public WeixinUser findByOpenId(@Param("openId") String openId);

    //添加用户讯息
    public Boolean addUser(@Param("phone") String phone,
                           @Param("username") String username,
                           @Param("openId") String openId);

    //删除用户
    public Boolean deleteUser(@Param("phone") String openId);
}
