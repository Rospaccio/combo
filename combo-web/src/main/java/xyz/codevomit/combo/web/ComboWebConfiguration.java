package xyz.codevomit.combo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import xyz.codevomit.combo.bot.Combo;
import xyz.codevomit.combo.data.DataService;
import xyz.codevomit.combo.web.console.HelloController;

@Configuration
@ComponentScan(basePackageClasses = HelloController.class)
public class ComboWebConfiguration {

    @Bean
    @Autowired
    public Combo comboBot(DataService dataService){
        return new Combo(dataService);
    }

    @Bean
    @Autowired
    public TelegramBotsApi telegramBotsApi(Combo comboBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(comboBot);
        return telegramBotsApi;
    }

}
