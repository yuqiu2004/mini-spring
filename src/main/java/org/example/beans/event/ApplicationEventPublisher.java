package org.example.beans.event;

/**
 * 事件发布者接口
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
