package xyz.codevomit.combo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import xyz.codevomit.combo.bot.Combo;
import xyz.codevomit.combo.data.DataService;
import xyz.codevomit.combo.web.console.HelloController;
import xyz.codevomit.combo.web.ui.UnixTimeToStringFormatter;

@Configuration
@ComponentScan(basePackageClasses = HelloController.class)
public class ComboWebConfiguration implements WebMvcConfigurer {

    @Bean
    @Autowired
    public Combo comboBot(@Value("${telegram.secretFile.name}") String secretFileName, DataService dataService){
        return new Combo(secretFileName, dataService);
    }

    @Bean
    @Autowired
    public TelegramBotsApi telegramBotsApi(Combo comboBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(comboBot);
        return telegramBotsApi;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new UnixTimeToStringFormatter());
    }
}
