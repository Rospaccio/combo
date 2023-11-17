package xyz.codevomit.combo.scrap.search;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import xyz.codevomit.combo.scrap.cache.ComicsCache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Slf4j
public class Scraper {

    public static final String BASE_URL = "https://www.gocomics.com/";

    private ComicsCache cache;

    public Scraper() {
        this.cache = new ComicsCache();
    }

    public byte[] downloadComicImage(String comicId, LocalDate date) {

        if(cache.contains(comicId, date)) {
            log.info("Cache hit: " + comicId + ", " + date.toString());
            return cache.get(comicId, date);
        }

        try {
            HttpClient client = HttpClient.newBuilder().build();
            String html = downloadHtml(client, comicId, date);
            String imageUrl = readImgUrl(html);
            byte[] content = downloadContent(client, imageUrl);
            cache.store(comicId, date, content);
            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream contentAsInputStream(String comicId, LocalDate date) {

        byte[] content = downloadComicImage(comicId, date);
        return new ByteArrayInputStream(content);
    }

    private String downloadHtml(HttpClient client, String comicId, LocalDate date)
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(comicUrl(comicId, date)))
                .build();
        HttpResponse<String> htmlResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        String html = htmlResponse.body();
        return html;
    }

    private String readImgUrl(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("picture.item-comic-image");
        Elements images = elements.get(0).select("img");
        String imageUrl = images.get(0).attributes().get("data-srcset")
                .split(" ")[0];
        return imageUrl;
    }

    private byte[] downloadContent(HttpClient client, String imageUrl)
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(imageUrl))
                .build();
        HttpResponse<byte[]> htmlResponse = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] content = htmlResponse.body();
        return content;
    }

    private String comicUrl(String comicId, LocalDate date){
        StringBuilder builder = new StringBuilder(BASE_URL);
        builder.append(comicId)
                .append("/")
                .append(date.get(ChronoField.YEAR))
                .append("/")
                .append(date.get(ChronoField.MONTH_OF_YEAR))
                .append("/")
                .append(date.get(ChronoField.DAY_OF_MONTH));
        return builder.toString();
    }

}
