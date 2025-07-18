package com.cap.admin.catalogo.infrastructure.configuration;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cap.admin.catalogo.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.cap.admin.catalogo.infrastructure.configuration.properties.amqp.QueueProperties;
import com.cap.admin.catalogo.infrastructure.services.EventService;
import com.cap.admin.catalogo.infrastructure.services.impl.RabbitEventService;
import com.cap.admin.catalogo.infrastructure.services.local.InMemoryEventService;

@Configuration
public class EventConfig {

    @Bean
    @VideoCreatedQueue
    @Profile({ "development" })
    EventService localVideoCreatedEventService() {
        return new InMemoryEventService();
    }

    @Bean
    @VideoCreatedQueue
    @ConditionalOnMissingBean
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}