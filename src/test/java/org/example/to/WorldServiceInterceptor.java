package org.example.to;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before the world explode i wanna kiss you last time");
        Object result = methodInvocation.proceed();
        System.out.println("after the explosion everything is down");
        return result;
    }
}
