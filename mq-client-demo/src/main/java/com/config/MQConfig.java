package com.config;

import org.axonframework.serializer.xml.XStreamSerializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by water on 2016/4/13.
 */

@Configuration
public class MQConfig {

    @Value(value="${mq.host}")
    String host;
    @Value(value="${mq.port}")
    Integer port;
    @Value(value="${mq.password}")
    String password;
    @Value(value="${mq.username}")
    String username;

    @Bean(name = "serializer")
    XStreamSerializer xStreamSerializer(){
        return  new XStreamSerializer();
    }

    @Bean(name = "amqpConnection")
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory fc = new CachingConnectionFactory (host,port);
        fc.setPassword(password);
        fc.setUsername(username);
        return fc;
    }


    @Bean
    public AmqpAdmin amqpAdmin(){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        admin.declareExchange(exchange());
        admin.declareQueue(orderReBookQueue());
        admin.declareBinding(binding());
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Exchange exchange(){
        return new FanoutExchange("userExchange");
    }

    @Bean(name="userClientQueue")
    public Queue orderReBookQueue() {
        Queue queue = new Queue("userClientQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Binding binding(){
        FanoutExchange hlcbCoreExchange = (FanoutExchange) exchange();
        return  BindingBuilder.bind(orderReBookQueue()).to(hlcbCoreExchange);
    }


}
