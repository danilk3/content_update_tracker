package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import io.micrometer.core.instrument.Counter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Bot implements AutoCloseable, UpdatesListener {

    private final UserMessageProcessor userMessageProcessor;

    private final TelegramBot telegramBot;

    private final Counter tgMetricsCounter;

    public Bot(TelegramBot telegramBot, UserMessageProcessor userMessageProcessor, Counter tgMetricsCounter) {
        this.telegramBot = telegramBot;
        this.userMessageProcessor = userMessageProcessor;
        this.tgMetricsCounter = tgMetricsCounter;
    }

    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        BaseResponse response = telegramBot.execute(request);
        log.info("Response ended up with status - {} , description - {}", response.isOk(), response.description());
    }

    public void start() {
        log.info("Bot started");
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            tgMetricsCounter.increment();
            log.info("Got update from chat with id - {}", update.message().chat().id());
            execute(userMessageProcessor.process(update));
        }
        return CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void close() {
        telegramBot.shutdown();
    }
}
