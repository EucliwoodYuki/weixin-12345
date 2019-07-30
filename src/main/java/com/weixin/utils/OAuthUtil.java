package com.weixin.utils;


import java.net.URLEncoder;

public class OAuthUtil {
    private static String redirect_uri="http://www.mailsys.top:8080";
    private static String scope1="snsapi_base";
    private static String scope2="snsapi_userinfo";
    private static String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";


    public static String getBaseUrl(String appId,String state){
        try {
            redirect_uri= URLEncoder.encode(redirect_uri,"utf-8");
            url=url.replace("APPID",appId)
                .replace("REDIRECT_URI",redirect_uri)
                .replace("SCOPE",scope1)
                .replace("STATE",state);
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getInfoUrl(String appId,String state){
        try {
            redirect_uri= URLEncoder.encode(redirect_uri,"utf-8");
            url=url.replace("APPID",appId)
                .replace("REDIRECT_URI",redirect_uri)
                .replace("SCOPE",scope2)
                .replace("STATE",state);
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
