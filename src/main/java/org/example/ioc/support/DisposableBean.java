package org.example.ioc.support;

/**
 * 自定义销毁接口
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
