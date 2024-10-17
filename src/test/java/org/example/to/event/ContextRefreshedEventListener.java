package org.example.to.event;

import org.example.ioc.event.ApplicationListener;
import org.example.ioc.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass());
    }
}
