package org.example.beanTest;

import org.example.ioc.context.ClassPathXmlApplicationContext;
import org.example.to.Car;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryBeanTest {
    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        applicationContext.getBean("car");
        assertThat(car.getBrand()).isEqualTo("sp-porsche");
    }
}
