package org.example.beans.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.example.beans.config.BeanDefinition;
import org.example.exception.BeansException;

import java.lang.reflect.Method;

/**
 * 可销毁bean适配器
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String name;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String name, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.name = name;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        //避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            //执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + name + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
