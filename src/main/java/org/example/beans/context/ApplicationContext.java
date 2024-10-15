package org.example.beans.context;

import org.example.beans.event.ApplicationEventPublisher;
import org.example.beans.factory.HierarchicalBeanFactory;
import org.example.beans.factory.ListableBeanFactory;
import org.example.core.io.ResourceLoader;

/**
 * 应用上下文接口
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
