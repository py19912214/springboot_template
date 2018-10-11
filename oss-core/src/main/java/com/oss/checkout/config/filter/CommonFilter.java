package com.oss.checkout.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/18
 */
@Configuration
@WebFilter(urlPatterns = "*",filterName = "CommonFilter")
 /**
  * @Description:  公共filter用于打印日志 打印出入参数
  * @Param:
  * @return:
  * @Author: yuepan
  * -------------------------------------------
  * @Date: 2018/9/18
  * -----------------------------------------
  */
@Slf4j
public class CommonFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取所有的消息头名称
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        if(log.isDebugEnabled()){
            StringBuilder sb=new StringBuilder(40);
            while(headerNames.hasMoreElements()){
                String nextElement = headerNames.nextElement();
                sb.append(nextElement).append(":").append(httpServletRequest.getHeader(nextElement)).append(",");
            }
            log.debug("请求header信息:"+sb);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
