package org.example.beans.config;

import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.exception.BeansException;

/**
 * 允许自定义修改BeanDefinition的属性值
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在BeanDefinition加载完成之后 bean实例化之前 提供修改BeanDefinition的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
