package com.mercadolibre.starWars.rebels.aspect;

import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ControllerLogHandler {
	 
	/**
	 * PointCut for any RestController
	 */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }
 
    /**
     * PointCut for any method execution in RestController
     */
    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }
    
    @Before("restController() && allMethod()")
    public void logBefore(JoinPoint joinPoint) {
 
        log.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        log.debug("Request: " + getPayload(joinPoint));
    }
    
    @AfterReturning(pointcut = "restController() && allMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.debug("Method Return value : " + returnValue);
    }
    
    @AfterThrowing(pointcut = "restController() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " method");
        log.error(exception.getMessage(), exception);
    }
    
    @AfterThrowing(pointcut = "restController() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, ConstraintViolationException exception) {
        log.info("Request has validation errors. ", exception);
    }
    
    @AfterThrowing(pointcut = "restController() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, MethodArgumentNotValidException exception) {
        log.info("Request has validation errors. ", exception);
    }
    
    @Around("restController() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
         
        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.debug("Method " + className + "." + methodName + " ()" + " execution time : " + elapsedTime + " ms");
         
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in " + joinPoint.getSignature().getName() + "method");
            throw e;
        }
    }
    
    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append("ParameterName: ")
	            .append(parameterName)
	            .append(": ")
	            .append(joinPoint.getArgs()[i].toString())
	            .append(", ");
        }
        return builder.toString();
    }

}
