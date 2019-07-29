package com.weixin.aop;

import com.weixin.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.weixin.result.ResultCode.*;
import static com.weixin.result.ResultCode.INTERNAL_SERVER_ERROR;
import static com.weixin.result.ResultFactory.buidResult;

/**
 * 用于捕获全局异常
 */

@ControllerAdvice  //控制器切面
public class GlobalExceptionHandler {

    //处理异常方法
    @ExceptionHandler(RuntimeException.class) //捕获运行时异常
    @ResponseBody
    public Result exceptionHandler(){
        return buidResult(INTERNAL_SERVER_ERROR,"系统错误",null);
    }
}
