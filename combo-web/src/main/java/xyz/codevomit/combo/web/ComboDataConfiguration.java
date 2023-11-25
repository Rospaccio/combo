package xyz.codevomit.combo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.codevomit.combo.data.DataService;
import xyz.codevomit.combo.data.repo.ReceivedMessageRepository;
import xyz.codevomit.combo.data.repo.TelegramChatRepository;
import xyz.codevomit.combo.data.telegram.TelegramChat;

@Configuration
@EnableJpaRepositories(basePackageClasses = TelegramChatRepository.class)
@EntityScan(basePackageClasses = TelegramChat.class)
public class ComboDataConfiguration {

    @Bean
    @Autowired
    public DataService dataService(TelegramChatRepository telegramChatRepo,
            ReceivedMessageRepository receivedMessageRepo){
        return new DataService(telegramChatRepo, receivedMessageRepo);
    }
}
