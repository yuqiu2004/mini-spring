package org.example.beans;

/**
 * 单例注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
