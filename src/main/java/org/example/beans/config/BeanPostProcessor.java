package org.example.beans.config;

import org.example.exception.BeansException;

/**
 * 修改bean实例化之后的bean
 */
public interface BeanPostProcessor {

    /**
     * 在bean执行初始化方法之前执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean执行初始化方法之后执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
