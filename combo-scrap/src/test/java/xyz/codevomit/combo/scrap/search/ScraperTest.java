package xyz.codevomit.combo.scrap.search;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ScraperTest {

    @Test
    public void downloadComicImage() throws IOException {
        Scraper scraper = new Scraper();
        byte[] content = scraper.downloadComicImage("calvinandhobbes", LocalDate.now());
        assertNotNull(content);
        File file = File.createTempFile("combo_", ".gif");
        FileUtils.writeByteArrayToFile(file, content);
        assertTrue(file.exists());
        log.info(file.getAbsolutePath());
    }
}