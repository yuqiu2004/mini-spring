package org.example.beans.factory;

import org.example.beans.config.BeanPostProcessor;
import org.example.beans.support.DefaultSingletonBeanRegistry;
import org.example.beans.config.BeanDefinition;
import org.example.exception.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象实例工厂
 * 使用单例的、懒汉式的模式创建和获取实例
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
