package org.example.beans.factory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import org.example.beans.InstantiationStrategy;
import org.example.beans.config.BeanPostProcessor;
import org.example.beans.config.BeanReference;
import org.example.beans.context.BeanFactoryAware;
import org.example.beans.support.DisposableBean;
import org.example.beans.support.DisposableBeanAdapter;
import org.example.beans.support.InitializingBean;
import org.example.beans.support.SimpleInstantiationStrategy;
import org.example.beans.config.BeanDefinition;
import org.example.beans.config.PropertyValue;
import org.example.core.convert.ConversionService;
import org.example.exception.BeansException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 实现对bean的创建管理
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    private static final String DEFAULT_INIT_METHOD_NAME = "afterPropertiesSet";
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
            applyPropertyValues(name, bean, beanDefinition); // 注入属性
            bean = initializeBean(name, bean, beanDefinition); // 执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
        }catch (Exception e){
            throw new BeansException("new instance bean failed", e);
        }
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);
        addSingleton(name, bean);
        return bean;
    }

    /**
     * 注册有销毁方法的bean，即bean继承自DisposableBean或有自定义的销毁方法
     * @param name
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String name, Object bean, BeanDefinition beanDefinition) {
        // 只有单例的才会执行销毁方法
        if (beanDefinition.isSingleton()) {
            if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
                registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition));
            }
        }
    }

    protected Object initializeBean(String name, Object bean, BeanDefinition beanDefinition) {
        // 让实现BeanFactoryAware的类能感知所属的BeanFactory
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        //执行BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);
        try {
            invokeInitMethods(name, wrappedBean, beanDefinition);
        } catch (Throwable e) {
            throw new BeansException("Invocation of init method of bean[" + name + "] failed", e);
        }
        //执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, name);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 执行bean的初始化方法
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws Throwable
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
        if (bean instanceof InitializingBean) ((InitializingBean) bean).afterPropertiesSet();
        if (StrUtil.isNotEmpty(beanDefinition.getInitMethodName()) &&
            !(bean instanceof  InitializingBean) &&
            DEFAULT_INIT_METHOD_NAME.equals(beanDefinition.getDestroyMethodName())) {
            Method method = bean.getClass().getMethod(beanDefinition.getInitMethodName());
            if (null == method) throw new BeansException("Could not find init method in " + beanName);
            method.invoke(bean);
        }
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
