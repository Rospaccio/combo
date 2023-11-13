package xyz.codevomit.combo.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseUtils {

    public static LocalDate parseDateOfOnCommand(String messageText){
        String[] components = messageText.split(" ");
        if(components.length < 2){
            throw new IllegalArgumentException("At least 2 components");
        }
        String dateParam = components[1];
        LocalDate date = LocalDate.parse(dateParam, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return date;
    }
}
