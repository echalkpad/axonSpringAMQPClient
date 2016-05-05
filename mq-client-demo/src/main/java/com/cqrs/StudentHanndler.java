package com.cqrs;

import com.comm.api.CreateHlcbCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.io.Serializable;

/**
 * Created by water on 2016/4/15.
 */

@Component
public class StudentHanndler implements Serializable{

    @Autowired
    @Qualifier("studentRepository")
    private Repository<Student> studentRepository;

    @CommandHandler
    public  void hanndle(CreateStudentCommand command){
        try{
            studentRepository.load(command.getId());
        }catch (Exception e){

        }
        Student s = new Student(command);
        studentRepository.add(s);
    }

}
