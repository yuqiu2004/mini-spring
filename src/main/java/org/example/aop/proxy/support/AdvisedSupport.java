package org.example.aop.proxy.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.example.aop.MethodMatcher;
import org.example.aop.proxy.TargetSource;

public class AdvisedSupport {

    /**
     * 是否使用cglib代理
     */
    private boolean proxyTargetClass = false;

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    // transient修饰的属性不被序列化
//    private transient Map<Integer, List<Object>> methodCache;


    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
}
