package ru.tinkoff.edu.java.scrapper.services;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

@Service
public class ScrapperQueueProducer implements BotUpdater {

    private final RabbitTemplate rabbitTemplate;

    private final Binding binding;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, Binding binding) {
        this.rabbitTemplate = rabbitTemplate;
        this.binding = binding;
    }

    @Override
    public void sendUpdate(LinkUpdate update) {
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), update);
    }
}