package org.example.ioc.registry;

import org.example.ioc.config.BeanDefinition;
import org.example.exception.BeansException;

/**
 * BeanDefinition注册表接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     * @param name
     * @param beanDefinition
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException 找不到抛出异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 返回定义的bean名称列表
     * @return
     */
    String[] getBeanDefinitionNames();
}
