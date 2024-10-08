package org.example.beanTest;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {

//    @Test
//    public void testGetBean(){
//        BeanFactory beanFactory = new BeanFactory();
//        beanFactory.registerBean("hello", new Hello());
//        Hello hello = (Hello)beanFactory.getBean("hello");
//        assertThat(hello).isNotNull();
//        assertThat(hello.sayHello()).isEqualTo("hello");
//    }

    class Hello{
        public String sayHello(){
            System.out.println("hello");
            return "hello";
        }
    }
}
