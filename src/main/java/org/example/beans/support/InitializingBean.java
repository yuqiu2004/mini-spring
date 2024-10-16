package org.example.beans.support;

/**
 * 自定义初始化接口
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
