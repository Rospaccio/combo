package xyz.codevomit.combo.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import xyz.codevomit.combo.api.CommandPolicy;

public class SelectionCommand extends ComboCommand{
    public SelectionCommand(Message message) {
        super(message);
    }

    @Override
    public Object applyPolicy(CommandPolicy policy) {
        return policy.processSelectionCommand(this);
    }
}
