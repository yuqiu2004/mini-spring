package org.example.ioc.factory;

import org.example.ioc.config.BeanPostProcessor;
import org.example.ioc.config.FactoryBean;
import org.example.ioc.support.DefaultSingletonBeanRegistry;
import org.example.ioc.config.BeanDefinition;
import org.example.exception.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象实例工厂
 * 使用单例的、懒汉式的模式创建和获取实例
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();


    @Override
    public Object getBean(String name) throws BeansException {
        if(null == name || "".equals(name)) return null;
        Object o = getSingleton(name);
        if(null != o) return getObjectForBeanInstance(o, name);
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean, name);
    }

    protected Object getObjectForBeanInstance(Object bean, String name) {
        Object object = bean;
        if (bean instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) bean;
            try {
                if (factoryBean.isSingleton()) {
                    //singleton作用域bean，从缓存中获取
                    object = this.factoryBeanObjectCache.get(name);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(name, object);
                    }
                } else {
                    //prototype作用域bean，新创建bean
                    object = factoryBean.getObject();
                }
            } catch (Exception ex) {
                throw new BeansException("FactoryBean threw exception on object[" + name + "] creation", ex);
            }
        }
        return object;
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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String name);
}
