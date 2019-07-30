package com.weixin.controller;


import com.weixin.model.WeixinUser;
import com.weixin.result.Result;
import com.weixin.service.Impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.weixin.result.ResultCode.FAIL;
import static com.weixin.result.ResultCode.SUCCESS;
import static com.weixin.result.ResultFactory.buidResult;
import static com.weixin.utils.SmsUtil.sendCode;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private DataServiceImpl dataService;

    //绑定
    @RequestMapping("bind")
    @ResponseBody
    public Result accountBind(@RequestParam String phone,
                              @RequestParam String username,
                              @RequestParam String openid,
                              @RequestParam String code){
        if(!code.equals(sendCode(phone))){
            return buidResult(FAIL,"验证码错误",null);
        }
        Boolean addResult =dataService.addUser(phone,username,openid);
        if(addResult){
            return buidResult(SUCCESS,"绑定成功",null);
        }
        return buidResult(FAIL,"绑定失败",null);
    }

    //查询信息
    @RequestMapping("info")
    @ResponseBody
    public Result UserInfo(@RequestParam String openid){
        WeixinUser user=dataService.findByOpenId(openid);
        if(user!=null){
            return buidResult(SUCCESS,"查询成功",user);
        }
        return buidResult(FAIL,"用户未绑定",null);
    }

    //解绑
    @RequestMapping("unBind")
    @ResponseBody
    public Result unBind(String openId){
        Boolean deleteResult =dataService.deleteUser(openId);
        if(deleteResult){
            return buidResult(SUCCESS,"解绑成功",null);
        }
        return buidResult(FAIL,"解绑失败",null);
    }
}
