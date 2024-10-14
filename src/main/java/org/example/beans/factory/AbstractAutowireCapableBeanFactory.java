package org.example.beans.factory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import org.example.beans.InstantiationStrategy;
import org.example.beans.config.BeanReference;
import org.example.beans.support.SimpleInstantiationStrategy;
import org.example.beans.config.BeanDefinition;
import org.example.beans.config.PropertyValue;
import org.example.core.convert.ConversionService;
import org.example.exception.BeansException;

/**
 * 实现对bean的创建管理
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    private ConversionService conversionService;

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(name, beanDefinition);
    }

    protected Object doCreateBean(String name, BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition);
        }catch (Exception e){
            throw new BeansException("new instance bean failed", e);
        }
        applyPropertyValues(name, bean, beanDefinition);
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

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValue[] propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
            for (PropertyValue propertyValue : propertyValues) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    // 获取依赖的bean
                    BeanReference beanReference =  (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                } else {
                    // 类型转换
                    Class<?> valueClass = value.getClass();
                    Class<?> fieldClass = (Class)TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService != null) {
                        if (conversionService.canConvert(valueClass, fieldClass)) {
                            value = conversionService.convert(value, fieldClass);
                        }
                    }
                }
                // 通过反射设置属性
                BeanUtil.setProperty(bean, name, value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }

    public ConversionService getConversionService(){
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
