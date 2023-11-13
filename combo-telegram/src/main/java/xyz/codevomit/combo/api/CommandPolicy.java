package xyz.codevomit.combo.api;

import xyz.codevomit.combo.command.DailyCommand;
import xyz.codevomit.combo.command.OnCommand;

public interface CommandPolicy<T> {

    T processDailyCommand(DailyCommand dailyCommand);

    T processOnCommand(OnCommand onCommand);
}
