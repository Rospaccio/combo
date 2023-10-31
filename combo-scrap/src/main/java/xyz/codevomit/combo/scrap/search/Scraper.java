package xyz.codevomit.combo.scrap.search;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class Scraper {

    public Scraper() {

    }

    public byte[] downloadComicImage(String comicId, LocalDate date) {

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://assets.amuniversal.com/9b5432401f62013c1150005056a9545d"))
                    .build();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            var content = response.body();

            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
