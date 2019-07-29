package com.weixin.utils;

import static com.weixin.utils.HttpUtil.post;

public class SmsUtil {


    /**
     * 调用api发送短信
     * @return
     */
    public static String sendCode(String phone){

        String url="";
        String body="";


        return post(url,body);
    }



    public static String getCode(){
        return (int)((Math.random()*9+1)*100000)+"";
    }
}
