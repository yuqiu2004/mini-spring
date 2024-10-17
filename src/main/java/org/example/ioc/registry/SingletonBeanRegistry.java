package org.example.ioc.registry;

/**
 * 单例注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);

    void addSingleton(String beanName, Object singletonObjects);
}
