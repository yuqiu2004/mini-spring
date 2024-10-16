package org.example.to.event;

import org.example.beans.event.ApplicationListener;
import org.example.beans.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass());
    }
}
