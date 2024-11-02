package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.factories.impl.RArtistFactory;
import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class ArtistRepository {

    private List<Artist> artistList;
    public List<Artist> findAll() {return artistList;}

    public ArtistRepository(RArtistFactory artistFactory){
        artistList = IntStream.range(0,5).mapToObj(i -> artistFactory.createInstance()).toList();
    }

    public Optional<Artist> findById(Long id) {
        return artistList.stream()
                         .filter(artist -> artist.getId().equals(id))
                         .findFirst();
    }

}
