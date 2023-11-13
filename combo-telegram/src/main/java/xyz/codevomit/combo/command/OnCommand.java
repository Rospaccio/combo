package xyz.codevomit.combo.command;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;
import xyz.codevomit.combo.api.CommandPolicy;

import java.time.LocalDate;

@Slf4j
public class OnCommand extends ComboCommand<Void> {

    @Getter
    private LocalDate dateParam;

    @Getter
    private String error = null;

    public OnCommand(Message message){
        super(message);
        try {
            dateParam = ParseUtils.parseDateOfOnCommand(message.getText());
        }
        catch (Exception e){
            log.error("Error parsing date: {}", e);
            error = "Sorry, I do not understand the date. The accepted format for the date is yyyyMMdd (e.g. 20231104)";
        }
    }

    @Override
    public Void applyPolicy(CommandPolicy<Void> policy) {
        return policy.processOnCommand(this);
    }
}
