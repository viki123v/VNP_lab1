package mk.finki.ukim.mk.lab.factories.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RSongFactory implements RandomFactory<Song> {

    private static AtomicLong trackIdGen = new AtomicLong(0);
    private static AtomicLong idGen = new AtomicLong(0);

    private final Random random = new Random();
    private final RandomFactory<String> stringFac;
    private final RandomFactory<Album> selectAlbum;

    @Autowired
    public RSongFactory(
        RSelectAlbum albumFac,
        RStringFac stringFac
    ){
        this.selectAlbum=albumFac;
        this.stringFac=stringFac;
    }

    @Override
    public Song createInstance() {
        Album album = selectAlbum.createInstance();
        Song newSong = new Song(
            String.valueOf(
                trackIdGen.addAndGet(1)
            ),
            stringFac.createInstance(),
            album.getGenre(),
            random.nextInt(1000) + 1000,
            new ArrayList<>(),
            idGen.addAndGet(1),
            album
        );

        album.getSongs().add(newSong);

        return newSong;
    }

}
