package xyz.codevomit.combo.command;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;
import xyz.codevomit.combo.api.CommandPolicy;

public class DailyCommand extends ComboCommand<Void> {

    public DailyCommand(Message message){
        super(message);
    }

    @Override
    public Void applyPolicy(CommandPolicy<Void> policy) {
        return policy.processDailyCommand(this);
    }
}
