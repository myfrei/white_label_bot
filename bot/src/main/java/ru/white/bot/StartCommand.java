package ru.white.bot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.white.data.model.TelegramUser;
import ru.white.service.SendBotMessageService;
import ru.white.service.TelegramUserService;

import static ru.white.utils.CommandUtils.getChatId;

/**
 * Start {@link Command}.
 */
@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String START_MESSAGE = "Привет. Я WhiteLabel Telegram Bot.\n " +
            "Я помогу тебе быть в курсе событий, которые тебе интересны.\n\n" +
            "Не знаешь о чем я? Напиши /help, чтобы узнать что я умею.";

    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
