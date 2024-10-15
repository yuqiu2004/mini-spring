package org.example.beans.context;

import org.example.exception.BeansException;

/**
 * xml文件的应用上下文
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    /**
     * 配置文件xml路径列表
     */
    private String[] configurations;

    /**
     * 从xml中加载beanDefinition并自动刷新上下文
     * @param configLocation
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从xml中加载beanDefinition并自动刷新上下文
     * @param configurations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configurations) throws BeansException{
        this.configurations = configurations;
        refresh();
    }

    protected String[] getConfigurations() {
        return this.configurations;
    }
}
