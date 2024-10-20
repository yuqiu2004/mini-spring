package org.example.aop.proxy.advice;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * 返回前增强
 */
public interface AfterReturningAdvice extends Advice {
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
