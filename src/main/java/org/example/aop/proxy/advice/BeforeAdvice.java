package org.example.aop.proxy.advice;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * 前置增强
 */
public interface BeforeAdvice extends Advice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
