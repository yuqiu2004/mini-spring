package org.example.aop;

import org.example.aop.proxy.CglibAopProxy;
import org.example.aop.proxy.JdkDynamicAopProxy;
import org.example.aop.proxy.ProxyFactory;
import org.example.aop.proxy.TargetSource;
import org.example.aop.proxy.support.AdvisedSupport;
import org.example.to.WorldService;
import org.example.to.WorldServiceImpl;
import org.example.to.WorldServiceInterceptor;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.example.to.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
        this.advisedSupport = advisedSupport;
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory() throws Exception {
        // 使用jdk动态代理
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        // 使用cglib动态代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }
}
