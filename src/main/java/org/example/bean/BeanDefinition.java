package org.example.bean;

/**
 * 保存bean信息
 */
public class BeanDefinition {
    private Class clazz;

    public BeanDefinition(){}
    public BeanDefinition(Class<?> clazz){
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
