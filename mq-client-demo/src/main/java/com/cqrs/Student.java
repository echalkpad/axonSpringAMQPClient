package com.cqrs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.eventhandling.amqp.spring.SpringAMQPTerminal;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by water on 2016/4/15.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;

    private String name;

    private Long version;

    public  Student(CreateStudentCommand command){
        StudentCreatedEvent e = new StudentCreatedEvent(command.getId(),command.getName());
        apply(e);
    }

    @EventSourcingHandler
    public void handle(StudentCreatedEvent event){
        this.id = event.getId();
        this.name = event.getName();
    }


}
