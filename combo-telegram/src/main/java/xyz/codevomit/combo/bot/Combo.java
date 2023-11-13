package xyz.codevomit.combo.bot;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import xyz.codevomit.combo.command.ComboCommand;
import xyz.codevomit.combo.policy.SimpleUpdatePolicy;
import xyz.codevomit.combo.scrap.search.ComicsIds;
import xyz.codevomit.combo.scrap.search.Scraper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Slf4j
public class Combo extends TelegramLongPollingBot {

    private Scraper scraper;

    public Combo(){
        scraper = new Scraper();
    }

    private String token = null;

    @Override
    public String getBotUsername() {
        return "Combo";
    }

    @Override
    public String getBotToken() {
        if (token == null) {
            try {
                var stream = getClass().getClassLoader().getResourceAsStream("secret");
                token = IOUtils.toString(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage().isCommand()){
            ComboCommand<Message> command = ComboCommand.buildCommand(update.getMessage());
            SimpleUpdatePolicy policy = new SimpleUpdatePolicy(this, scraper);
            Message sent = command.applyPolicy(policy);
            log.info("Message enqueued: {}", sent);
        }
    }
}
