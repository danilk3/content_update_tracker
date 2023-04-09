package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public non-sealed class StartCommand extends Command {

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Starts the bot";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(
                update.message().chat().id(),
                "Hello, " + update.message().chat().firstName())
                .replyMarkup(new ReplyKeyboardMarkup(
                        new KeyboardButton("/start"),
                        new KeyboardButton("/help"),
                        new KeyboardButton("/list"),
                        new KeyboardButton("/track"),
                        new KeyboardButton("/untrack"))
                        .oneTimeKeyboard(false)
                        .resizeKeyboard(true)
                );
    }
}
