package org.example.beans.event;

import org.example.beans.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
