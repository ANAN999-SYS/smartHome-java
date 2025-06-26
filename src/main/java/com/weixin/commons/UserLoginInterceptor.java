package com.weixin.commons;

import com.weixin.Utils.JWTUtils;
import com.weixin.Utils.TokenBlack;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    TokenBlack tokenBlack;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }
        // 获取请求头，header值为Authorization，承载token
        String token = request.getHeader(JWTUtils.header);
        //token不存在
        if (token == null || token.equals("")) {
            log.info("传入token为空");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token为空!");
            return false;
        }
        //验证token
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals("")) {
            log.info("token验证失败");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token验证失败!");
            return false;
        }
        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.header, newToken);
        }
        return true;
    }
}
