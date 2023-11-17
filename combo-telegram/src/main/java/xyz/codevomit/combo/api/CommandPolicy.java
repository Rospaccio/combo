package xyz.codevomit.combo.api;

import xyz.codevomit.combo.command.DailyCommand;
import xyz.codevomit.combo.command.OnCommand;
import xyz.codevomit.combo.command.SelectionCommand;

public interface CommandPolicy<T> {

    T processDailyCommand(DailyCommand dailyCommand);

    T processOnCommand(OnCommand onCommand);

    T processSelectionCommand(SelectionCommand selectionCommand);
}
