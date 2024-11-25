package mk.finki.ukim.mk.lab.factories.impl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RSelectAlbum implements RandomFactory<Album> {

    private final AlbumRepository albumRepository;
    private static Random random = new Random();

    public RSelectAlbum(AlbumRepository repository){
        albumRepository=repository;
    }

    @Override
    public Album createInstance() {
        List<Album> allAlbums = albumRepository.findAll();
        return allAlbums.get(
            random.nextInt(allAlbums.size())
        );
    }

}
