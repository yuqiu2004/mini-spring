package org.example.to;

import org.example.beans.context.ApplicationContext;
import org.example.beans.context.ApplicationContextAware;
import org.example.beans.context.BeanFactoryAware;
import org.example.beans.factory.BeanFactory;
import org.example.exception.BeansException;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}