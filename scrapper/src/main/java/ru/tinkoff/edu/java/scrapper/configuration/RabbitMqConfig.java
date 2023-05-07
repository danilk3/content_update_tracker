package ru.tinkoff.edu.java.scrapper.configuration;

public record RabbitMqConfig(String queueName,
                             String topicExchangeName,
                             String deadLetterTopicExchangeName,
                             String deadLetterRoutingKey) {
}
