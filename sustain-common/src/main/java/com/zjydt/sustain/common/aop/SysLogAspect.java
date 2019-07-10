package com.zjydt.sustain.common.aop;

import com.google.gson.Gson;
import com.zjydt.sustain.common.annotation.SysLog;
import com.zjydt.sustain.common.constants.MqQueueNameConstant;
import com.zjydt.sustain.common.dto.SysLogDTO;
import com.zjydt.sustain.common.enums.OperationStatusEnum;
import com.zjydt.sustain.common.util.UrlUtil;
import com.zjydt.sustain.common.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * @author: lee_bw
 * @date: 2018/11/14 11:26
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Around("execution(public com.zjydt.sustain.common.util.ApiResult *(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws SQLException {
        Object result = null;
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        long startTime = System.currentTimeMillis();
        SysLogDTO sysLogDTO = new SysLogDTO();
        // 需要记录日志存库
        if(targetMethod.isAnnotationPresent(SysLog.class)) {
            Gson gson = new Gson();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 获取注解
            SysLog sysLog = targetMethod.getAnnotation(SysLog.class);
            sysLogDTO
                    .setServiceId(sysLog.serviceId())
                    .setModuleName(sysLog.moduleName())
                    .setActionName(sysLog.actionName())
                    .setParams(gson.toJson(request.getParameterMap()).getBytes())
                    .setRemoteAddr(UrlUtil.getRemoteHost(request))
                    .setMethod(request.getMethod())
                    .setRequestUri(request.getRequestURI())
                    .setUserAgent(request.getHeader("user-agent"));

            // 获取当前用户名
            String username = UserUtil.getUserName(request);
            sysLogDTO.setCreateBy(username);
        }
        try {
            result = pjp.proceed();
            sysLogDTO.setStatus(OperationStatusEnum.SUCCESS.getCode());
        } catch (Throwable e) {
            //sysLogDTO.setException(UrlUtil.getTrace(e));
            sysLogDTO.setException("".getBytes());
            sysLogDTO.setStatus(OperationStatusEnum.FAIL.getCode());
        }
        // 本次操作用时（毫秒）
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        sysLogDTO.setTime((String.valueOf(elapsedTime)).getBytes());

        // 发送消息到 系统日志队列
        if(targetMethod.isAnnotationPresent(SysLog.class)) {
            rabbitTemplate.convertAndSend(MqQueueNameConstant.SYS_LOG_QUEUE, sysLogDTO);
        }
        return result;
    }








}
