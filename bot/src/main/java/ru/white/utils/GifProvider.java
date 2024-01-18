package ru.white.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Component
public class GifProvider {

    public InputFile getDollarGifUrl() throws MalformedURLException {

        URL url = new URL(
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgifer.com%2Fen%2FZXv0&psig=AOvVaw28evrXZ0hSpmIBExClMJ6d&ust=1705688518675000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOjPwobH54MDFQAAAAAdAAAAABAJ");

        try (InputStream in = new BufferedInputStream(url.openStream())) {
            // Читаем данные в массив байт
            byte[] fileContent = in.readAllBytes();

            // Создаем ByteArrayInputStream на основе массива байт
            ByteArrayInputStream bais = new ByteArrayInputStream(fileContent);

            // Создаем InputFile используя ByteArrayInputStream
            return new InputFile(bais, "file");

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Возвращаем null или выбрасываем исключение
        }
    }
}
