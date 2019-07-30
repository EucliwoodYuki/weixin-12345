package com.weixin.service.Impl;

import com.weixin.service.SmsService;
import com.weixin.utils.SmsUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class SmsServiceImpl implements SmsService {
    @Override


    public String sendCode(String phone) {

        String code= SmsUtil.sendCode(phone);


        return null;
    }


}
