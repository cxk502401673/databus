package com.zjydt.sustain.common.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sustain.token.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      log.info("验证token中。。。");
        boolean flag = false;
        // 获取 HTTP HEAD 中的 TOKEN
        String token  = request.getHeader("token");
        String username  = request.getHeader("username")==null?"":request.getHeader("username");
        // 校验 TOKEN
        flag = StringUtils.isNotBlank(token) ? JwtUtil.checkJWT(token) : false;
        // 如果校验未通过，返回 401 状态
        if (!flag)
            log.error("用户"+username+" token 验证失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        return flag;
    }
}
