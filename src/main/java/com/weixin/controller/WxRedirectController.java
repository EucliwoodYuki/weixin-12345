package com.weixin.controller;

import com.weixin.model.WeixinUser;
import com.weixin.result.Result;
import com.weixin.service.DataService;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.weixin.result.ResultCode.FAIL;
import static com.weixin.result.ResultCode.UNAUTHORIZED;
import static com.weixin.result.ResultFactory.buidResult;
import static com.weixin.result.ResultFactory.buildSuccessResult;

/**
 * @author Edward
 */
@AllArgsConstructor
@RestController
@RequestMapping("/wx/app/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;

    @Autowired
    private DataService dataService;


    //根据code获取用户信息
    @RequestMapping(value = "/code",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result greetUser(@PathVariable String appid, @RequestParam String code) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        System.out.println("code:::::::" + code);

        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            //获取数据库用户信息
            WeixinUser weixinUser=dataService.UserInfo(user.getOpenId());
            if(weixinUser!=null){
                return buildSuccessResult(weixinUser);
            }
            return buidResult(UNAUTHORIZED,"用户未绑定",user);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return buidResult(FAIL,"无法获取用户信息",null);

    }

//@RequestMapping("/greet")
//@ResponseBody
//public String wechatLogin(@PathVariable String appid, @RequestParam String code, Model model) throws Exception {
//
//    // 2. 通过code换取网页授权access_token
//    if (code != null || !(code.equals(""))) {
//
//        String APPID = wxService.getWxMpConfigStorage().getAppId();
//        String SECRET = wxService.getWxMpConfigStorage().getSecret();
//        String CODE = code;
//        String WebAccessToken = "";
//        String openId = "";
//        String nickName, sex, openid = "";
//        String REDIRECT_URI = "http://www.mailsys.top";
//        String SCOPE = "snsapi_userinfo";
//
//           /* String getCodeUrl = UserInfoUtil.getCode(APPID, REDIRECT_URI, SCOPE);
//            logger.info("第一步:用户授权, get Code URL:{}", getCodeUrl);*/
//
//        // 替换字符串，获得请求access token URL
//        String tokenUrl = UserInfoUtil.getWebAccess(APPID, SECRET, CODE);
//
//        // 通过https方式请求获得web_access_token
//        String response = HttpsUtil.httpsRequestToString(tokenUrl, "GET", null);
//
//        JSONObject jsonObject = JSON.parseObject(response);
//
//        if (null != jsonObject) {
//            try {
//                WebAccessToken = jsonObject.getString("access_token");
//                openId = jsonObject.getString("openid");
//
//                // 3. 使用获取到的 Access_token 和 openid 拉取用户信息
//                String userMessageUrl = UserInfoUtil.getUserMessage(WebAccessToken, openId);
//
//                // 通过https方式请求获得用户信息响应
//                String userMessageResponse = HttpsUtil.httpsRequestToString(userMessageUrl, "GET", null);
//
//                JSONObject userMessageJsonObject = JSON.parseObject(userMessageResponse);
//
//                if (userMessageJsonObject != null) {
//                    if ("40001".equals(userMessageJsonObject.getString("errcode"))){
//                        result.setIsSucc(Boolean.FALSE);
//                        result.setCode(ResultCode.ACCESS_TOKEN__INVALID_CODE);
//                        result.setMsg(ResultCode.ACCESS_TOKEN__INVALID_MSG);
//                        return result;
//                    }
//                    //用户昵称
//                    nickName = userMessageJsonObject.getString("nickname");
//                    //用户性别
//                    sex = userMessageJsonObject.getString("sex");
//                    sex = (sex.equals("1")) ? "男" : "女";
//                    //用户唯一标识
//                    openid = userMessageJsonObject.getString("openid");
//
//                    logger.info("用户昵称:{}", nickName);
//                    logger.info("用户性别:{}", sex);
//                    logger.info("OpenId:{}", openid);
//
//                    //根据OpenId获取用户信息
//                    UserVO userVO = wxUserService.findUserByOpenID(openid);
//                    if (userVO != null){
//                        UserInfo userInfo = createTokenForUserInfo(userVO.getName(),userVO.getPassword(),false);
//                        result.setObj(userInfo);
//                    }
//                }
//            } catch (JSONException e) {
//                logger.error("获取Web Access Token失败");
//                result.setIsSucc(Boolean.FALSE);
//                result.setCode(ResultCode.JSSON_TRANSFER_ERROR_CODE);
//                result.setMsg(ResultCode.JSSON_TRANSFER_ERROR_MSG);
//            }
//        }
//    }
//    result.setIsSucc(Boolean.TRUE);
//    result.setCode(ResultCode.SUCCESS_CODE);
//    result.setMsg(ResultCode.SUCCESS_MSG);
//    return result;
//}
//    public static class UserInfoUtil{
//        // 2.获取Web_access_tokenhttps的请求地址
//        public static String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
//
//        // 替换字符串
//        public static String getWebAccess(String APPID, String SECRET, String CODE) {
//            return String.format(Web_access_tokenhttps, APPID, SECRET, CODE);
//        }
//
//        // 3.拉取用户信息的请求地址
//        public static String User_Message = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
//
//        // 替换字符串
//        public static String getUserMessage(String access_token, String openid) {
//            return String.format(User_Message, access_token, openid);
//        }
//    }
//
//    public static class HttpsUtil{
//
//    }
}



