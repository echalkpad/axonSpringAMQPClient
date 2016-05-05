package com.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/spring/core-context.xml","classpath:META-INF/spring/cqrs-infrastructure-context.xml"})
public class CoreCQRSContext {
	
	@Autowired
	CommandBus commandBus;
	
	@Bean
	public CommandGateway commandGateWay() {
		return new DefaultCommandGateway(commandBus);
	}
}
