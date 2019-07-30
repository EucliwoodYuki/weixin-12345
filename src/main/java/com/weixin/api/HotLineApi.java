package com.weixin.api;

import static com.weixin.utils.HttpUtil.sendGet;

public class HotLineApi {

    static String url="http://148.70.156.25:8011";
    static String operationUser="d3015250724b498b86f1c9ebaf36b79d";


    /**
     * 根据电话查找微信用户的工单
     * @param phone
     * @return
     */
    public static String QueryByPhone(String phone){
        String resultUrl=url+"/cs12345/ComQueryController/comQuery?" +
                "operationUser=OPERATIONUSER" +
                "&pageIndex=1&pageSize=10" +
                "&userNo=&sendUser=&visitUser=&investUser=&auditUser=&handleDept=&callDateBegin=&callDateEnd=&sendDateBegin=&sendDateEnd=&signDateBegin=&signDateEnd=&appealDateBegin=&appealDateEnd=&visitDateBegin=&visitDateEnd=&handleDateBegin=&handleDateEnd=&caseNum=&callerName=&caseSource=&" +
                "callerNum=CALLERNUM" +
                "&referenceID=&caseInfo=&caseLevel=&caseItemState=&caseType=&callType=&appealState=&caseVisit=&visitState=&satis=&caseCode=\n";

        resultUrl=resultUrl.replace("CALLERNUM",phone).replace("OPERATIONUSER",operationUser);

        return sendGet(resultUrl);
    }
}
