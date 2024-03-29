package ru.white.bot;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.white.service.SendBotMessageService;

import static ru.white.utils.CommandUtils.getChatId;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Не понимаю тебя \uD83D\uDE1F, напиши /help чтобы узнать что я понимаю.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), UNKNOWN_MESSAGE);
    }
}
