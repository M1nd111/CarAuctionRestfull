package spring.ws.core.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
public class FirstAspect {

//    @Pointcut("@within(org.springframework.stereotype.Controller)")
//    public void isControllerLayer(){
//    }
//    @Pointcut("within(*.service.*Service)")
//    public void isServiceLayer(){
//    }
//    @Pointcut("target(org.springframework.stereotype.Repository)")
//    public void isRepositoryLayer(){
//    }
//    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void hasGetMapping(){
//    }
//    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
//    public void hasModelArg(){
//    }
//    @Pointcut("isControllerLayer() && @args(*.validator.UserInfo,..)")
//    public void hasUserInfoParamAnnotation(){
//    }
//    @Pointcut("bean(userService)")
//    public void isUserService(){
//    }
//    @Pointcut("bean(*Service)")
//    public void isServiceLayerBeen(){
//    }

    @Pointcut("execution(public * *.service.*Service.findById(*,..))")
    public void anyServiceFindByIdMethod(){
    }
//    @Pointcut("execution(public * *.service.*Service.findById(*,..))")
//    public void anyFindByIdMethod(){
//    }

    @Before(value = "anyServiceFindByIdMethod()" +
            "&& args(id)" +
            "&& target(service)" +
            "&& this(serviceProxy)" +
            "&& @within(transactional)")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional){
        log.info("Before invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyServiceFindByIdMethod()" +
            "&& target(service)", returning = "result", argNames = "service,result")
    public void addLogging(Object service, Object result) {
        log.info("AfterReturning invoke findById method in class {}, with result {}", service, result);
    }

    @AfterThrowing(value = "anyServiceFindByIdMethod()" +
            "&& target(service)", throwing = "ex", argNames = "service,ex")
    public void addLogging(Object service, Throwable ex){
        log.info("AfterThrowing invoke findById method in class {}, with ex {}", service, ex);
    }

    @After(value = "anyServiceFindByIdMethod()" +
            "&& target(service)")
    public void addLogging(Object service){
        log.info("After invoke findById method in class {}, with ex {}", service);
    }

}
