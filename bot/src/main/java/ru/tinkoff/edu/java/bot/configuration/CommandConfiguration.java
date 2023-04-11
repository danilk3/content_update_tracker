package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.service.commands.*;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

import java.util.Map;

@Configuration
public class CommandConfiguration {

    @Bean
    public Map<String, Command> commandMap() {
        return Map.of(
                CommandNameEnum.HELP.getValue(), new HelpCommand(),
                CommandNameEnum.START.getValue(), new StartCommand(),
                CommandNameEnum.LIST.getValue(), new ListCommand(),
                CommandNameEnum.TRACK.getValue(), new TrackCommand(),
                CommandNameEnum.UNTRACK.getValue(), new UntrackCommand()
        );
    }
}
