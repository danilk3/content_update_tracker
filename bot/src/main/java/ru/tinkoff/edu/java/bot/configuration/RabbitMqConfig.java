package ru.tinkoff.edu.java.bot.configuration;

public record RabbitMqConfig(String queueName,
                             String topicExchangeName,
                             String deadLetterTopicExchangeName,
                             String deadLetterRoutingKey,
                             String dql) {
}

