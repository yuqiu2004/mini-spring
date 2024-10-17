package org.example.beanTest;

import org.example.ioc.context.ClassPathXmlApplicationContext;
import org.example.to.HelloService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AwareInterfaceTest {
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:hello.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();
    }
}
