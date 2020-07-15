package com.njupt.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.njupt.common.exception.GlobalException;
import com.njupt.common.utils.HttpContextUtil;
import com.njupt.common.utils.IPUtil;
import com.njupt.system.entity.SysLog;
import com.njupt.system.entity.SysUser;
import com.njupt.system.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.njupt.common.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            long beginTime = System.currentTimeMillis();
            // 获取Request请求
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            // 设置IP地址
            String ip = IPUtil.getIpAddr(request);
            // 记录时间（毫秒）
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            SysLog log = new SysLog();
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(proceedingJoinPoint, log);
            log.setUsername(sysUser.getUsername());
        }
        return result;
    }
}
