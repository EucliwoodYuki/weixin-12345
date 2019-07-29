package com.weixin.handler;

import com.weixin.utils.UrlUtil;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**菜单回复事件
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        String appId= weixinService.getWxMpConfigStorage().getAppId();
        WxMpXmlOutNewsMessage.Item item=getAppealSubmission(appId,wxMessage.getEventKey());
        return WxMpXmlOutMessage.NEWS()
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser())
            .addArticle(item)
            .build();

    }


    public WxMpXmlOutNewsMessage.Item getAppealSubmission(String appId,String eventKey){
        String state,description,PicUrl,Title;
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        switch (eventKey){
            case "Appeal_Submission":
                state="1";
                description=eventKey;
                PicUrl="";
                Title=eventKey;
                break;
            case "Question_Query":
                state="2";
                description=eventKey;
                PicUrl="";
                Title=eventKey;
                break;
            case "My_Appeal":
                state="3";
                description=eventKey;
                PicUrl="";
                Title=eventKey;
                break;
            case "Progress_Query":
                state="4";
                description=eventKey;
                PicUrl="";
                Title=eventKey;
                break;
            case "Binding_Account":
                state="5";
                description=eventKey;
                PicUrl="";
                Title=eventKey;
                break;
            default:
                state="null";
                description="null";
                PicUrl="null";
                Title="null";
        }

        String url= UrlUtil.getBaseUrl(appId,state);
        item.setUrl(url);
        item.setDescription(description);
        item.setPicUrl(PicUrl);
        item.setTitle(Title);
        return item;
    }

}
