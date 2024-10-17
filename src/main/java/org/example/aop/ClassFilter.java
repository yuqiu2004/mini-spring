package org.example.aop;

/**
 * 匹配类接口
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
