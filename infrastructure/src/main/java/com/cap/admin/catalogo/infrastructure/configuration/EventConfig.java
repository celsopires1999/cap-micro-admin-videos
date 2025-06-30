package com.cap.admin.catalogo.infrastructure.configuration;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cap.admin.catalogo.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.cap.admin.catalogo.infrastructure.configuration.properties.amqp.QueueProperties;
import com.cap.admin.catalogo.infrastructure.services.EventService;
import com.cap.admin.catalogo.infrastructure.services.impl.RabbitEventService;

@Configuration
public class EventConfig {

    @Bean
    @VideoCreatedQueue
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}