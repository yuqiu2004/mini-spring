package org.example.to.event;

import org.example.beans.context.ApplicationContext;
import org.example.beans.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
