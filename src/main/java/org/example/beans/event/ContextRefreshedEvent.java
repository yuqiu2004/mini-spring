package org.example.beans.event;

import org.example.beans.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
