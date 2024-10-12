package org.example.beans;

import org.example.exception.BeansException;

/**
 * bean实例化策略
 *
 * 两个实现类 SimpleInstantiationStrategy(default) | CglibSubclassingInstantiationStrategy
 * difference:
 *  SimpleInstantiationStrategy
 *      1.使用Java反射直接调用构造方法生成的普通对象
 *
 *  CglibSubclassingInstantiationStrategy
 *      1. 通过CGLIB Enhancer生成的`代理对象`
 *      2. 用于需要增强类(AOP)的场景、允许对类的方法进行拦截 -- 得益于生成的是代理子类
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
