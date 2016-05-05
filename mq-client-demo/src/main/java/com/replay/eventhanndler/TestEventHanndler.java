package com.replay.eventhanndler;

import com.cqrs.StudentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by water on 2016/4/20.
 */
@Service
@Slf4j
public class TestEventHanndler {

    @EventHandler
    public void handle(StudentCreatedEvent event){
        log.warn("testtttttttttttttttttt:{}"+event);
    }

}
