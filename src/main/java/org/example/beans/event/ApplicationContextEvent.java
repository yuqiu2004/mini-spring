package org.example.beans.event;

import org.example.beans.context.ApplicationContext;
import org.example.beans.event.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
