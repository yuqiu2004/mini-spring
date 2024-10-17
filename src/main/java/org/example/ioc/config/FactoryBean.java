package org.example.ioc.config;

/**
 * 特殊bean 获取时返回getObject
 * @param <T>
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
