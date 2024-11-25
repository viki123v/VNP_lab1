package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.factories.impl.RAlbumFacotry;
import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class AlbumRepository {
    private List<Album> albums;

    public AlbumRepository(
        RAlbumFacotry albumFacotry
    ){
        albums=IntStream.range(0,5)
            .mapToObj(i -> albumFacotry.createInstance())
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Album> findAll(){
        return albums;
    }

    public Optional<Album> findById(Long albumId) {
        return albums.stream().filter(album -> album.getId().equals(albumId)).findFirst();
    }

}
