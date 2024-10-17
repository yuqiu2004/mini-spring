package org.example.to.event;

import org.example.ioc.context.ApplicationContext;
import org.example.ioc.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
