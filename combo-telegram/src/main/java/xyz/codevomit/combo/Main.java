package xyz.codevomit.combo;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import xyz.codevomit.combo.bot.Combo;

@Slf4j
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        var combo = new Combo();
        botsApi.registerBot(combo);
        log.info("Started");
    }
}