package org.example.ioc.context;

import org.example.ioc.factory.BeanFactory;
import org.example.exception.BeansException;

/**
 * 实现该接口 能感知所属BeanFactory
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
