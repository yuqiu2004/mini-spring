package org.example.ioc.factory;

import org.example.exception.BeansException;

/**
 * 实例工厂
 * 定义了获取实例的接口
 */
public interface BeanFactory {

    /**
     * 获取实例
     * @param name
     * @return
     */
    public Object getBean(String name) throws BeansException;

    /**
     * 根据名称和类型查找bean
     * @param name
     * @param requiredType
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);
}
