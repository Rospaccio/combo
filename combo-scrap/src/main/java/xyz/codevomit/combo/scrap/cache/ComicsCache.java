package xyz.codevomit.combo.scrap.cache;

import java.time.LocalDate;
import java.util.HashMap;

public class ComicsCache {
    private HashMap<ComicKey, byte[]> comicKeyToContentMap;

    public ComicsCache(){
        this.comicKeyToContentMap = new HashMap<>();
    }

    public void store(String comicName, LocalDate date,  byte[] content){
        ComicKey key = new ComicKey(comicName, date);
        comicKeyToContentMap.put(key, content);
    }

    public byte[] get(String comicName, LocalDate date){
        ComicKey key = new ComicKey(comicName, date);
        return comicKeyToContentMap.get(key);
    }

    public boolean contains(String comicName, LocalDate date){
        return comicKeyToContentMap.containsKey(new ComicKey(comicName, date));
    }
}
