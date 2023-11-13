package xyz.codevomit.combo.command;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;
import xyz.codevomit.combo.api.CommandPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class ComboCommand<T> {

    private static Map<String, Function<Message, ComboCommand>> messageToCommandCunstructorMap;

    static {
        messageToCommandCunstructorMap = new HashMap<>();
        messageToCommandCunstructorMap.put("/daily", DailyCommand::new);
        messageToCommandCunstructorMap.put("/on", OnCommand::new);
    }

    @Getter
    private Message message;

    public ComboCommand(Message message){
        this.message = message;
    }

    public static ComboCommand buildCommand(Message message){
        String commandText = firstTokenOfText(message.getText());
        Function<Message, ComboCommand> constructor = messageToCommandCunstructorMap.get(commandText);
        ComboCommand command = constructor.apply(message);
        return command;
    }

    private static String firstTokenOfText(String text){
        return text.split(" ")[0];
    }

    public abstract T applyPolicy(CommandPolicy<T> policy);
}
