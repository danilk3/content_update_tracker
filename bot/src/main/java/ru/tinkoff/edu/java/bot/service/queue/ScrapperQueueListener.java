package ru.tinkoff.edu.java.bot.service.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;
import ru.tinkoff.edu.java.bot.service.BotService;

@RabbitListener(queues = {"${app.rabbitmq.queueName}", "${app.rabbitmq.dql}"})
public class ScrapperQueueListener {

    private final BotService botService;

    public ScrapperQueueListener(BotService botService) {
        this.botService = botService;
    }

    @RabbitHandler
    public void receiver(LinkUpdate update) {
        botService.notifyOfUpdates(update);
    }
}
