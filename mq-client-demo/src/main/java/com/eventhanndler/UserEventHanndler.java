package com.eventhanndler;

import com.api.event.UserCreatedEvent;
import com.comm.api.HlcbCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by water on 2016/4/14.
 */

@Slf4j
@Component
public class UserEventHanndler {

    @Autowired
    EventBus eventBus;


    @EventHandler
    public  void hanndle(UserCreatedEvent event){
        log.warn("---------------------:{}",event);
    }

}
