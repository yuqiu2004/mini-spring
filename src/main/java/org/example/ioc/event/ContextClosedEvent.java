package org.example.ioc.event;

import org.example.ioc.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
