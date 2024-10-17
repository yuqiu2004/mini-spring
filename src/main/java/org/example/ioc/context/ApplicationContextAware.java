package org.example.ioc.context;

import org.example.exception.BeansException;

/**
 * 实现该接口 能感知所属ApplicationContext
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
