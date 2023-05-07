package ru.tinkoff.edu.java.scrapper.configuration.bot_updater;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.services.ScrapperQueueProducer;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "useQueue", havingValue = "true")
public class RabbitMqAccessConfiguration {

    @Bean
    public ScrapperQueueProducer botUpdater(RabbitTemplate rabbitTemplate, Binding binding) {
        return new ScrapperQueueProducer(rabbitTemplate, binding);
    }
}
