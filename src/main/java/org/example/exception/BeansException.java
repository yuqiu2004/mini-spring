package org.example.exception;

/**
 * 自定义创建过程容器异常
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) { super(msg); }
    public BeansException(String msg, Throwable cause) { super(msg, cause); }
}
