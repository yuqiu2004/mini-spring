package org.example.beans.factory;

import org.example.beans.support.DefaultSingletonBeanRegistry;
import org.example.beans.config.BeanDefinition;
import org.example.exception.BeansException;

/**
 * 抽象实例工厂
 * 使用单例的、懒汉式的模式创建和获取实例
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        if(null == name || "".equals(name)) return null;
        Object o = getSingleton(name);
        if(null != o) return o;
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;
    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeansException;
}
