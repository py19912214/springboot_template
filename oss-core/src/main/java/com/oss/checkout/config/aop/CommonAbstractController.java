package com.oss.checkout.config.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO  jar提供的不好用 会吧错误代码带出来
 *
 * @author: yuepan
 * @date: 2018/9/20
 */
@ControllerAdvice
public class CommonAbstractController {
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public String handleException(Throwable ex) {
        ex.printStackTrace();
        return "错误";
    }
}
