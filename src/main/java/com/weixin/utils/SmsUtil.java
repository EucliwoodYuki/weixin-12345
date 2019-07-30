package com.weixin.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import static com.weixin.utils.HttpUtil.sendPost;

public class SmsUtil {
    public static String  accessKeyId="LTAIxXRjYSfcTAey";
    public static String accessSecret="GrOOGHcsNtrIwIsY4EyaIjBiGFRcE8";
    public static String templateCode="SMS_171354788";
    public static String signName="皮皮热线微信公众号";

    /**
     * 调用api发送短信
     * @return
     */

    public static String sendCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        String code=getCode();
        JSONObject templateParam =new JSONObject();
        templateParam.put("code",code);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject result=JSONObject.parseObject(response.getData());
            if(result.get("Code").equals("OK")){
                return code;
            }

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static String getCode(){
        return (int)((Math.random()*9+1)*100000)+"";
    }
}
