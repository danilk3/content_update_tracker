package ru.tinkoff.edu.java.bot.configuration;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.service.commands.Command;
import ru.tinkoff.edu.java.bot.service.commands.HelpCommand;
import ru.tinkoff.edu.java.bot.service.commands.ListCommand;
import ru.tinkoff.edu.java.bot.service.commands.StartCommand;
import ru.tinkoff.edu.java.bot.service.commands.TrackCommand;
import ru.tinkoff.edu.java.bot.service.commands.UntrackCommand;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

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
