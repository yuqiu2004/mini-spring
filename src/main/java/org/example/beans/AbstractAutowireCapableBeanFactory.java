package org.example.beans;

import org.example.exception.BeansException;

/**
 * 实现对bean的创建管理
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(name, beanDefinition);
    }

    protected Object doCreateBean(String name, BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getClazz();
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition);
        }catch (Exception e){
            throw new BeansException("new instance bean failed", e);
        }
        addSingleton(name, bean);
        return bean;
    }
    
    public InstantiationStrategy getInstantiationStrategy(){
        return this.instantiationStrategy;
    }
    
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
        this.instantiationStrategy = instantiationStrategy;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition){
        return instantiationStrategy.instantiate(beanDefinition);
    }
}
