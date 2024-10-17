package org.example.ioc.support;

/**
 * 自定义初始化接口
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
