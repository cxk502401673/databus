package com.zjydt.sustain.auth.handler;

import com.zjydt.sustain.auth.exception.CustomOauth2Exception;
import com.zjydt.sustain.common.constants.MqQueueNameConstant;
import com.zjydt.sustain.common.constants.SustainServiceNameConstants;
import com.zjydt.sustain.common.dto.SysLogDTO;
import com.zjydt.sustain.common.enums.OperationStatusEnum;
import com.zjydt.sustain.common.enums.SysLogTypeEnum;
import com.zjydt.sustain.common.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lee_bw
 * @date: 2018/10/12 10:25
 * @description: oauth2异常处理器
 */

@Component("customWebResponseExceptionTranslator")
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = request.getParameter("username");
        SysLogDTO sysLogDTO = new SysLogDTO();
        sysLogDTO
                .setCreateBy(username)
                .setRequestUri(request.getRequestURI())
                .setUserAgent(UrlUtil.getRemoteHost(request))
                .setType(SysLogTypeEnum.LOGIN.getCode())
                .setStatus(OperationStatusEnum.FAIL.getCode())
                .setModuleName("auth认证模块")
                .setActionName("登录")
                .setServiceId(SustainServiceNameConstants.SUSTAIN_AUTH)
                .setRemoteAddr(UrlUtil.getRemoteHost(request))
                .setMethod(request.getMethod())
                //.setException(UrlUtil.getTrace(e));
                .setException("".getBytes());
        rabbitTemplate.convertAndSend(MqQueueNameConstant.SYS_LOG_QUEUE, sysLogDTO);
        log.error(e.getStackTrace().toString());
        if (!(e instanceof OAuth2Exception)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CustomOauth2Exception(e.getMessage()));
        }
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new CustomOauth2Exception(oAuth2Exception.getMessage()));
    }
}
