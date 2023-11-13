package xyz.codevomit.combo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import xyz.codevomit.combo.bot.Combo;

@Configuration
public class ComboWebConfiguration {

    @Bean
    public Combo comboBot(){
        return new Combo();
    }

    @Bean
    @Autowired
    public TelegramBotsApi telegramBotsApi(Combo comboBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(comboBot);
        return telegramBotsApi;
    }

}
