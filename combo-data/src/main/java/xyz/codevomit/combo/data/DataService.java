package xyz.codevomit.combo.data;

import xyz.codevomit.combo.data.repo.ReceivedMessageRepository;
import xyz.codevomit.combo.data.repo.TelegramChatRepository;
import xyz.codevomit.combo.data.telegram.ReceivedMessage;
import xyz.codevomit.combo.data.telegram.TelegramUser;

import java.time.ZonedDateTime;

public class DataService {

    private TelegramChatRepository telegramChatRepo;

    private ReceivedMessageRepository receivedMessageRepo;

    public DataService(TelegramChatRepository telegramChatRepo,
            ReceivedMessageRepository receivedMessageRepo){

        this.telegramChatRepo = telegramChatRepo;
        this.receivedMessageRepo = receivedMessageRepo;
    }

    public ReceivedMessage saveReceivedMessage(Integer messageId,
            Long chatId,
            String username,
            String name,
            String surname,
            Integer unixDate,
            ZonedDateTime serverTime,
            String text){

        ReceivedMessage receivedMessage = new ReceivedMessage();
        receivedMessage.setMessageId(messageId);
        receivedMessage.setChatId(chatId);
        receivedMessage.setUsername(username);
        receivedMessage.setFirstName(name);
        receivedMessage.setSurname(surname);
        receivedMessage.setUnixTime(unixDate);
        receivedMessage.setServerTime(serverTime);
        receivedMessage.setText(text);

        return receivedMessageRepo.save(receivedMessage);
    }
}
