package xyz.codevomit.combo.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnixTimeTest {
    @Test
    public void testUnixTimeConversion(){
        long unixTime = 1701017315L;
        LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.of("CET"));

        assertNotNull(datetime);
    }

    @Test
    public void testUnixTimeConversion2(){
        long unixTime = (133333888840003968L / 10_000_000) - 11644473600L;
        LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.of("CET"));

        assertNotNull(datetime);
    }

    // 133327602037141074
}
