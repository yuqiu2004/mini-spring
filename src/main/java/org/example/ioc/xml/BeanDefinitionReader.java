package org.example.ioc.xml;

import org.example.ioc.registry.BeanDefinitionRegistry;
import org.example.core.io.Resource;
import org.example.core.io.ResourceLoader;
import org.example.exception.BeansException;

/**
 * 读取bean信息接口 (解析beanDefinition)
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
