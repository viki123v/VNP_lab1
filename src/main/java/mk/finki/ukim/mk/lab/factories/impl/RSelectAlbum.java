package mk.finki.ukim.mk.lab.factories.impl;

import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RSelectAlbum implements RandomFactory<Album> {

    private final List<Album> albums;
    private static Random random = new Random();

    public RSelectAlbum(AlbumRepository repository){
        this.albums=repository.findAll(); 
    }

    @Override
    public Album createInstance() {
        return albums.get(
            random.nextInt(albums.size())
        );
    }

}
