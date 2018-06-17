package io.github.cd871127.hodgepodge.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerLogger {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("(@within(org.springframework.web.bind.annotation.RestController)||@within(org.springframework.stereotype.Controller))&&@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controller() {
    }

    @Before("controller()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info(">>>>>>>>>>>>>>> start handle request : " + joinPoint.toString());
        logger.info(">>>>>>>>>>>>>>> URL : " + request.getRequestURL().toString());
        logger.debug(">>>>>>>>>>>>>>> HTTP_METHOD : " + request.getMethod());
        logger.info(">>>>>>>>>>>>>>> ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.debug(">>>>>>>>>>>>>>> IP : " + request.getRemoteAddr());
        logger.debug(">>>>>>>>>>>>>>> CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "ret", pointcut = "controller()")
    public void doAfterReturning(Object ret) {
        logger.debug(">>>>>>>>>>>>>>> RESULT : " + ret);
    }

    @After("controller()")
    public void after(JoinPoint joinPoint) {
        logger.info(">>>>>>>>>>>>>>> finish handle request : " + joinPoint.toString());
    }
}
