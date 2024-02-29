package com.ms.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${broker.queue.email.name}")
    private String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
