package org.example.ioc.context;

import org.example.ioc.event.ApplicationEventPublisher;
import org.example.ioc.factory.HierarchicalBeanFactory;
import org.example.ioc.factory.ListableBeanFactory;
import org.example.core.io.ResourceLoader;

/**
 * 应用上下文接口
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
