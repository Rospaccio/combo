package xyz.codevomit.combo.bot;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import xyz.codevomit.combo.command.ComboCommand;
import xyz.codevomit.combo.data.DataService;
import xyz.codevomit.combo.data.telegram.ReceivedMessage;
import xyz.codevomit.combo.data.telegram.TelegramChat;
import xyz.codevomit.combo.policy.SimpleUpdatePolicy;
import xyz.codevomit.combo.scrap.search.Scraper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Slf4j
public class Combo extends TelegramLongPollingBot {

    private Scraper scraper;
    private DataService dataService;

    public Combo(DataService dataService){
        scraper = new Scraper();
        this.dataService = dataService;
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
                if(stream == null){
                    log.error("ERROR! File 'secret' not found");
                }
                token = IOUtils.toString(stream, Charset.defaultCharset());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        registerUpdate(update);
        if(update.getMessage().isCommand()){
            ComboCommand<Message> command = ComboCommand.buildCommand(update.getMessage());
            SimpleUpdatePolicy policy = new SimpleUpdatePolicy(this, scraper);
            Message sent = command.applyPolicy(policy);
        }
    }

    private ReceivedMessage registerUpdate(Update update){
        try {
            int messageId = update.getMessage().getMessageId();
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();
            String name = update.getMessage().getFrom().getFirstName();
            String surname = update.getMessage().getFrom().getLastName();
            int unixDate = update.getMessage().getDate();
            ZonedDateTime serverTime = ZonedDateTime.now();
            String text = StringUtils.substring(update.getMessage().getText(), 0, 512);

            return dataService.saveReceivedMessage(messageId, chatId, username, name, surname, unixDate, serverTime, text);
        }
        catch (Exception e){
            log.error("Error", e);
            return null;
        }
    }
}
