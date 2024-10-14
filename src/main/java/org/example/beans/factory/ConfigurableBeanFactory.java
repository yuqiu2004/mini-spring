package org.example.beans.factory;

import org.example.beans.registry.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {

//    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
//    void destroySingletons();

//    void addEmbeddedValueResolver(StringValueResolver valueResolver);

//    String resolveEmbeddedValue(String value);

//    void setConversionService(ConversionService conversionService);

//    ConversionService getConversionService();
}
