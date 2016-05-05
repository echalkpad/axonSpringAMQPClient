package com.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by water on 2016/4/15.
 */

@Configuration
@Slf4j
public class ConsumerEventBusConfig {

    @Autowired
    @Qualifier(value = "replayingCluster")
    private Cluster replayingCluster;

    @Autowired
    private EventBusTerminal terminal;

    @Bean
    ClusterSelector autowiringClusterSelector(){
        return new AutowiringClusterSelector();
    }


    @Bean
    public EventBus eventBus(){
        EventBus bus = new ConsumerEventBus(autowiringClusterSelector(),terminal,replayingCluster);
//        EventBus bus = new ConsumerEventBus(autowiringClusterSelector(),terminal);
        return bus;
    }


}
