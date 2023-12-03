package xyz.codevomit.combo.policy;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import xyz.codevomit.combo.api.CommandPolicy;
import xyz.codevomit.combo.bot.Combo;
import xyz.codevomit.combo.command.DailyCommand;
import xyz.codevomit.combo.command.OnCommand;
import xyz.codevomit.combo.command.SelectionCommand;
import xyz.codevomit.combo.scrap.search.ComicsIds;
import xyz.codevomit.combo.scrap.search.Scraper;

import java.io.InputStream;
import java.time.LocalDate;

@Slf4j
public class SimpleUpdatePolicy implements CommandPolicy<Message> {

    private Combo combo;
    private Scraper scraper;

    public SimpleUpdatePolicy(Combo combo, Scraper scraper) {
        this.combo = combo;
        this.scraper = scraper;
    }

    @Override
    public Message processDailyCommand(DailyCommand dailyCommand) {
        InputStream imageStream = scraper.contentAsInputStream(ComicsIds.CALVIN_AND_HOBBES, LocalDate.now());
        InputFile inputFile = new InputFile(imageStream, "Daily comic");
        SendPhoto photo = SendPhoto.builder()
                .photo(inputFile)
                .chatId("" + dailyCommand.getMessage().getChatId())
                .build();
        try {
            return combo.execute(photo);
        } catch (TelegramApiException te) {
            log.error("Error!", te);
            throw new RuntimeException(te);
        }
    }

    @Override
    public Message processOnCommand(OnCommand onCommand) {
        try {
            if (onCommand.getError() != null) {
                return sendError(onCommand);
            }
            InputStream imageStream = scraper.contentAsInputStream(ComicsIds.CALVIN_AND_HOBBES, onCommand.getDateParam());
            InputFile inputFile = new InputFile(imageStream, "Daily comic");
            SendPhoto photo = SendPhoto.builder()
                    .photo(inputFile)
                    .chatId("" + onCommand.getMessage().getChatId())
                    .build();

            return combo.execute(photo);
        } catch (TelegramApiException te) {
            log.error("Error!", te);
            throw new RuntimeException(te);
        }
    }

    @Override
    public Message processSelectionCommand(SelectionCommand selectionCommand) {
        Message message = null;
        for (String id : ComicsIds.IDS){
            try {
                InputStream stream = scraper.contentAsInputStream(id, LocalDate.now());
                InputFile file = new InputFile(stream, id + " daily");
                SendPhoto photo = SendPhoto.builder()
                        .photo(file)
                        .chatId("" + selectionCommand.getMessage().getChatId())
                        .build();
                message = combo.execute(photo);
            }
            catch (Exception te){
                log.error("Error!", te);
            }
        }
        return message;
    }

    private Message sendError(OnCommand onCommand) throws TelegramApiException {
        SendMessage message = new SendMessage("" + onCommand.getMessage().getChatId(), onCommand.getError());
        return combo.execute(message);
    }
}
