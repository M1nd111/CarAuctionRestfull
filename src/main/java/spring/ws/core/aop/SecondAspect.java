package spring.ws.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class SecondAspect {
    @Around(value = "by.spring.aop.FirstAspect.anyServiceFindByIdMethod()" +
            "&& target(service)" + "&& args(id)", argNames = "joinPoint,service,id")
    public Object addLogging(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("Before invoke findById method in class {}, with id {}", service, id);
        try {
            var result = joinPoint.proceed();
            log.info("AfterReturning invoke findById method in class {}, with result {}", service, result);
            return result;
        }
        catch (Throwable ex){
            log.info("AfterThrowing invoke findById method in class {}, with ex {}", service, ex);
            throw ex;
        }
        finally {
            log.info("After invoke findById method in class {}, with ex {}", service);

        }
    }
}
