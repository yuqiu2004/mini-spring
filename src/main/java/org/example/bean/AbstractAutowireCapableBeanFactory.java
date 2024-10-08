package org.example.bean;

import org.example.exception.BeansException;

/**
 * 实现对bean的创建管理
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(name, beanDefinition);
    }

    protected Object doCreateBean(String name, BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getClazz();
        Object bean = null;
        try{
            bean = beanClass.newInstance();
        }catch (Exception e){
            throw new BeansException("new instance bean failed", e);
        }
        addSingleton(name, bean);
        return bean;
    }
}
