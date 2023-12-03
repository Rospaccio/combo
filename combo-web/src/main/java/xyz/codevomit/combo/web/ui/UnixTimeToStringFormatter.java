package xyz.codevomit.combo.web.ui;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UnixTimeToStringFormatter implements Formatter<Long> {
    @Override
    public String print(Long unixTime, Locale locale) {
        Instant unixInstant = Instant.ofEpochSecond(unixTime);
        LocalDateTime datetime = LocalDateTime.ofInstant(unixInstant, ZoneId.systemDefault());
        return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public Long parse(String text, Locale locale) throws ParseException {
        return null;
    }
}
