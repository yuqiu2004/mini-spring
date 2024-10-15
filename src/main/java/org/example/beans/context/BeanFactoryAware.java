package org.example.beans.context;

import org.example.beans.factory.BeanFactory;
import org.example.exception.BeansException;

/**
 * 实现该接口 能感知所属BeanFactory
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
