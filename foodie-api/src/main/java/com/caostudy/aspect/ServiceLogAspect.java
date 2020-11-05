package com.caostudy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 曹学习
 * @description ServiceLogAspect
 * @date 2020/11/4 8:12
 */
@Component
@Aspect
public class ServiceLogAspect {
    /**
     * Aop通知
     * 1.前置通知：在方法调用前执行
     * 2.后置通知：在方法正常调用之后执行
     * 3.环绕通知：在方法调用之前和调用之后，都可以分别执行
     * 4.异常通知：如果方法调用过程中发生异常则通知
     * 5.最终通知：在方法调用之后执行
     */
    public static final Logger log=
            LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 切面表达式：
     * execution 代表所要执行表达式的主体
     * 第一处 * 代表方法的返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 ..代表该包以及其子包下的所有类方法
     * 第四处 *代表类名, *代表所有类
     * 第五处 *（..） *代表类中的方法名，(..)表示方法中的任何参数
     * @param joinPoint proceedingJoinPoint
     * @return jsonObject
     * @throws Throwable exception
     */
    @Around("execution(* com.caostudy.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录某个Controller的执行方法
        log.info("===== 开始执行 {}.{} =====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        //记录开始时间
        long begin=System.currentTimeMillis();

        //执行目标 service
        Object result =joinPoint.proceed();

        //记录结束时间
        long end=System.currentTimeMillis();
        long takeTime=end-begin;

        if(takeTime>3000){
            log.error("===== 执行结束 {}.{},耗时:{} 毫秒 ======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    takeTime);
        }else if(takeTime>2000){
            log.warn("===== 执行结束 {}.{},耗时:{} 毫秒 ======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    takeTime);
        }else{
            log.info("===== 执行结束 {}.{},耗时:{} 毫秒 ======",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(),
                    takeTime);
        }
        return result;
    }
}
