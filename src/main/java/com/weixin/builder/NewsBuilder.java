package com.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

public class NewsBuilder extends AbstractBuilder{
    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {

        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
            .fromUser("fromUser")
            .toUser("toUser")
            .addArticle()
            .build();
        return null;
    }

}
