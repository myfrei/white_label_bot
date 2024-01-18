package ru.white.bot;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import ru.white.bot.anotation.AdminCommand;
import ru.white.service.SendBotMessageService;
import ru.white.service.TelegramUserService;

import java.util.List;

import static java.util.Objects.nonNull;
import static ru.white.bot.CommandName.START;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService,
                            List<String> admins) {

        this.admins = admins;
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command findCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
