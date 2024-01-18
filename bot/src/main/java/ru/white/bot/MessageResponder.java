package ru.white.bot;

import java.net.MalformedURLException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.white.utils.GifProvider;

@Component
@RequiredArgsConstructor
public class MessageResponder {

    private final TelegramLongPollingBot bot;
    private final GifProvider gifProvider;

    public void respondToStartCommand(long chatId) {
        String response = "Привет! Я ваш помощник-бот.";
        sendTextMessage(chatId, response);
    }

    public void respondToTeamCommand(long chatId) {
        String teamInfo = "Информация о команде: ..."; // Получите реальные данные
        sendTextMessage(chatId, teamInfo);
    }

    public void respondTo5BucksPhrase(long chatId) throws MalformedURLException {
        sendGif(chatId);
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendGif(long chatId) throws MalformedURLException {
        SendAnimation animation = new SendAnimation();
        animation.setChatId(String.valueOf(chatId));
        animation.setAnimation(gifProvider.getDollarGifUrl());
        try {
            bot.execute(animation);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
