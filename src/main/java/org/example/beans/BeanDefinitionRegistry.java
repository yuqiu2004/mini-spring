package org.example.beans;

/**
 * BeanDefinition注册表接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     * @param name
     * @param beanDefinition
     */
    void registryBean(String name, BeanDefinition beanDefinition);
}
