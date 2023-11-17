package xyz.codevomit.combo.scrap.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ComicKey {
    private String name;
    private LocalDate date;
}
