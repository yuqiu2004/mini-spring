package org.example.to.event;

import org.example.ioc.event.ApplicationListener;
import org.example.ioc.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass());
    }
}
