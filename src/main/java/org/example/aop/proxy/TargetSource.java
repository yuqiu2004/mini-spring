package org.example.aop.proxy;

/**
 * 被代理对象的封装
 */
public class TargetSource {

    private final Object target;


    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
