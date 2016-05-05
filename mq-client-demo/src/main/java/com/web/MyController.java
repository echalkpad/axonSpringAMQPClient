package com.web;

import com.cqrs.CreateStudentCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.Cluster;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by water on 2016/4/15.
 */

@Controller
@Slf4j
public class MyController {

    @Autowired
    EventBus eventBus;

    @Autowired
    private CommandGateway gateway;

    @Autowired
    @Qualifier(value = "replayingCluster")
    private ReplayingCluster replayingCluster;

    @ResponseBody
    @RequestMapping("/dd")
    public  String  ddd(){
        CreateStudentCommand createStudentCommand = new CreateStudentCommand();
        createStudentCommand.setId(UUID.randomUUID().toString());
        createStudentCommand.setName("zhangsan");
        gateway.send(createStudentCommand);
        return "success";
    }


    @ResponseBody
    @RequestMapping("cc")
    public  void cc(){
        log.warn("replayingCluster starting ------");
        replayingCluster.startReplay();
        log.warn("replayingCluster end-------");
    }

}
