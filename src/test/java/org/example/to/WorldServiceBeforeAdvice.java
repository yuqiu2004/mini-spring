package org.example.to;

import org.example.aop.proxy.advice.BeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceBeforeAdvice implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before advice execute !");
    }
}
