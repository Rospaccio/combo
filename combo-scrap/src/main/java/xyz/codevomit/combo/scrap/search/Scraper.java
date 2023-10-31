package xyz.codevomit.combo.scrap.search;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
                    .uri(new URI("https://www.gocomics.com/calvinandhobbes/2023/10/30"))
                    .build();
            HttpResponse<String> htmlResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = htmlResponse.body();

            Document document = Jsoup.parse(html);
            Elements elements = document.select("picture.item-comic-image");
            Elements images = elements.get(0).select("img");
            String imageUrl = images.get(0).attributes().get("data-srcset")
                    .split(" ")[0];

            HttpRequest request2 = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(imageUrl))
                    .build();
            HttpResponse<byte[]> htmlResponse2 = client.send(request2, HttpResponse.BodyHandlers.ofByteArray());
            byte[] content = htmlResponse2.body();

            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
