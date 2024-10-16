package org.example.ioc.support;

import org.example.ioc.registry.SingletonBeanRegistry;
import org.example.exception.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**单例对象存储容器*/
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**拥有自定义销毁方法的对象*/
    private Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public void addSingleton(String name, Object object){
        singletonObjects.put(name, object);
    }

    public void destroySingletons() {
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        this.disposableBeans.put(beanName, bean);
    }
}
