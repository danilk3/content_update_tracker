package ru.tinkoff.edu.java.scrapper.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public DirectExchange directExchange(ApplicationConfig applicationConfig) {
        return new DirectExchange(applicationConfig.rabbitMq().topicExchangeName());
    }

    @Bean
    public Queue queue(ApplicationConfig applicationConfig) {
        return QueueBuilder.durable(applicationConfig.rabbitMq().queueName())
            .withArgument("x-dead-letter-exchange", applicationConfig.rabbitMq().deadLetterTopicExchangeName())
            .withArgument("x-dead-letter-routing-key", applicationConfig.rabbitMq().deadLetterRoutingKey()).build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).withQueueName();
    }

    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate", LinkUpdate.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.dto.bot.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }
}
