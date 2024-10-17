package org.example.ioc.context;

import org.example.ioc.factory.DefaultListableBeanFactory;
import org.example.ioc.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载解析配置文件
     * @param beanFactory
     */
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigurations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置路径列表
     * @return
     */
    protected abstract String[] getConfigurations();
}
