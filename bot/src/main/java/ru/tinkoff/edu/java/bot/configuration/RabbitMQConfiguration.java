package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public DirectExchange directExchange(ApplicationConfig applicationConfig) {
        return new DirectExchange(applicationConfig.rabbitMq().topicExchangeName());
    }

    @Bean
    public DirectExchange deadLetterDirectExchange(ApplicationConfig applicationConfig) {
        return new DirectExchange(applicationConfig.rabbitMq().deadLetterTopicExchangeName());
    }

    @Bean
    public Queue queue(ApplicationConfig applicationConfig) {
        return QueueBuilder.durable(applicationConfig.rabbitMq().queueName())
                .withArgument("x-dead-letter-exchange", applicationConfig.rabbitMq().deadLetterTopicExchangeName())
                .withArgument("x-dead-letter-routing-key", applicationConfig.rabbitMq().deadLetterRoutingKey()).build();
    }

    @Bean
    public Queue dql(ApplicationConfig applicationConfig) {
        return new Queue(applicationConfig.rabbitMq().dql(), false);
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue, @Qualifier("directExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }

    @Bean
    public Binding dqlBinding(@Qualifier("dql") Queue queue, @Qualifier("deadLetterDirectExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}