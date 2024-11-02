package mk.finki.ukim.mk.lab.services.interfaces;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;

public interface ArtistService{
//    List<Author> listArtists();
    List<Artist> listArtists();
    Artist findById(Long id);
}
