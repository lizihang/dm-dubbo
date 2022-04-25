package com.dm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月25日 10:39</p>
 * <p>类全名：com.dm.aop.TestAspect</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Aspect
@Component
public class TestAspect {
    private final Logger logger = LoggerFactory.getLogger(TestAspect.class);

    /**
     * 以自定义 @DmLog 注解为切点
     */
    @Pointcut("@annotation(com.dm.aop.TestDmLog)")
    public void testPointcut() {
    }

    /**
     * 切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("testPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("doBefore");
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("testPointcut()")
    public void doAfter() throws Throwable {
        logger.info("doAfter");
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("testPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("doAround before");
        Object result = proceedingJoinPoint.proceed();
        logger.info("doAround after");
        return result;
    }

    @AfterReturning(value = "testPointcut()", returning = "result")
    public void doAfterReturning(Object result) {
        if (result != null) {
            logger.info("doAfterReturning,result:" + result.toString());
        } else {
            logger.info("doAfterReturning");
        }
    }

    /**
     * 异常通知
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "testPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error(e.getMessage());
    }
}
