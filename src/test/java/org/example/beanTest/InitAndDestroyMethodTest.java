package org.example.beanTest;

import org.example.beans.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
//        applicationContext.registerShutdownHook();  //或者手动关闭 applicationContext.close();
        applicationContext.close();
    }
}
