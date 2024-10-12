package org.example.beans.registry;

/**
 * 单例注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
