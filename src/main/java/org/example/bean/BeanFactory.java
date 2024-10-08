package org.example.bean;

/**
 * 实例工厂
 * 定义了获取实例的接口
 */
public interface BeanFactory {

    /**
     * 获取实例
     * @param name
     * @return
     */
    public Object getBean(String name);
}
