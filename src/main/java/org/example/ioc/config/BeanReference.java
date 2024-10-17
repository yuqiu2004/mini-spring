package org.example.ioc.config;

/**
 * 对bean的引用
 */
public class BeanReference {

    private final String beanName;

    public String getBeanName() {
        return beanName;
    }

    public BeanReference(String beanName){
        this.beanName = beanName;
    }
}
