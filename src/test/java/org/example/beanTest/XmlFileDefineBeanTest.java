package org.example.beanTest;

import org.example.ioc.factory.DefaultListableBeanFactory;
import org.example.ioc.xml.XmlBeanDefinitionReader;
import org.example.to.Car;
import org.example.to.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("derek");
        assertThat(person.getCar().getBrand()).isEqualTo("porsche");

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        assertThat(car.getBrand()).isEqualTo("porsche");
    }
}
