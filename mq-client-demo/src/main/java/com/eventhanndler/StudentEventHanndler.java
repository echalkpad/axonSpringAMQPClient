package com.eventhanndler;

import com.cqrs.StudentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by water on 2016/4/15.
 */
@Component
@Slf4j
public class StudentEventHanndler {


    @EventHandler
    public void handle(StudentCreatedEvent event){
        log.warn("dddddddddddddddddddddd:{}"+event);
    }


}
