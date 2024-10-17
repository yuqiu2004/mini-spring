package org.example.config;

import org.example.ioc.config.BeanDefinition;
import org.example.ioc.config.BeanFactoryPostProcessor;
import org.example.ioc.config.PropertyValue;
import org.example.ioc.config.PropertyValues;
import org.example.ioc.factory.ConfigurableListableBeanFactory;
import org.example.exception.BeansException;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        //将person的name属性改为ivy
        propertyValues.addPropertyValue(new PropertyValue("name", "ivy"));
    }
}
