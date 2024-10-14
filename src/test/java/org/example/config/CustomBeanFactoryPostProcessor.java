package org.example.config;

import org.example.beans.config.BeanDefinition;
import org.example.beans.config.BeanFactoryPostProcessor;
import org.example.beans.config.PropertyValue;
import org.example.beans.config.PropertyValues;
import org.example.beans.factory.ConfigurableListableBeanFactory;
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
