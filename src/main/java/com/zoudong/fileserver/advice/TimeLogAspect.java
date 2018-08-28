package com.zoudong.fileserver.advice;

import com.zoudong.fileserver.result.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeLogAspect {
    private static final String POINT = "execution (* com.zoudong.permission.controller..*.*(..))";

    @Pointcut(POINT)
    public void performance() {
    }

    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        obj = joinPoint.proceed(args);
        if (!(obj instanceof BaseResult)) {
            return obj;
        }
        long endTime = System.currentTimeMillis();
        BaseResult baseResult = (BaseResult) obj;
        baseResult.setTime(endTime - startTime);
        return baseResult;
    }
}
