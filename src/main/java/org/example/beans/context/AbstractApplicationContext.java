package org.example.beans.context;

import org.example.beans.config.BeanFactoryPostProcessor;
import org.example.beans.config.BeanPostProcessor;
import org.example.beans.event.*;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.core.convert.ConversionService;
import org.example.core.io.DefaultResourceLoader;
import org.example.exception.BeansException;

import java.util.Collection;
import java.util.Map;

/**
 * 抽象应用上下文
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    public static final String CONVERSION_SERVICE_BEAN_NAME = "conversionService";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void refresh() throws BeansException {
        // 创建BeanFactory 加载BeanFactory
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 添加ApplicationContextAwareProcessor 让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // bean实例化之前 执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);
        // 初始化事件发布者
        initApplicationEventMulticaster();
        // 注册事件监听器
        registerListeners();
        // 注册类型转换器和提前实例化单例bean
        finishBeanFactoryInitialization(beanFactory);
        // 发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 发布容器刷新完成事件
     */
    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 设置类型转换器
        if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME)) {
            Object conversionService  = beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME);
            if (conversionService instanceof ConversionService) {
                beanFactory.setConversionService((ConversionService) conversionService);
            }
        }

        // 提前实例化单例bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 注册事件监听器
     */
    protected void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    /**
     * 初始化事件发布者
     */
    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册BeanPostProcessor
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * bean实例化之前执行BeanFactoryPostProcessor
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 创建BeanFactory 加载BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public void close() {
        doClose();
    }

    protected void doClose() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        // 执行单例bean的销毁方法
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(() -> doClose());
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }
}
