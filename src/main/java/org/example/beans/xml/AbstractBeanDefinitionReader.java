package org.example.beans.xml;

import org.example.beans.registry.BeanDefinitionRegistry;
import org.example.core.io.DefaultResourceLoader;
import org.example.core.io.ResourceLoader;
import org.example.exception.BeansException;

/**
 * bean解析抽象实现
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /** bean注册器 */
    private final BeanDefinitionRegistry registry;

    /** 资源加载器 */
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader (BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
