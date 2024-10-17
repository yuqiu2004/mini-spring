package org.example.ioc.factory;

import org.example.ioc.config.BeanPostProcessor;
import org.example.ioc.registry.SingletonBeanRegistry;
import org.example.core.convert.ConversionService;

public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {

    /**
     * 添加后置操作器
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();

//    void addEmbeddedValueResolver(StringValueResolver valueResolver);

//    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

//    ConversionService getConversionService();
}
