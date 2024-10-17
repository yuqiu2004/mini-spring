package org.example.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配接口
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
