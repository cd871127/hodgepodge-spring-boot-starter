//package io.github.cd871127.hodgepodge.log;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class ServiceLogger {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Pointcut("@within(org.springframework.stereotype.Service)&&execution(public * *(..))")
//    public void controller() {
//    }
//
//    @Before("controller()")
//    public void deBefore(JoinPoint joinPoint) {
//        // 接收到请求，记录请求内容
//        logger.info("*************** start handle service : " + joinPoint.toString());
//        logger.info("*************** ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        logger.debug("*************** CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "controller()")
//    public void doAfterReturning(Object ret) {
//        logger.debug("*************** RESULT : " + ret);
//    }
//
//    @After("controller()")
//    public void after(JoinPoint joinPoint) {
//        logger.info("*************** finish handle service : " + joinPoint.toString());
//    }
//}
