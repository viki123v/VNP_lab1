package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.factories.impl.RSongFactory;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class SongRepository {

    private List<Song> songs;

    public SongRepository(
        RSongFactory rSongFactory
    ){
        songs= IntStream.range(0,5).mapToObj(i -> rSongFactory.createInstance()).toList();
    }

    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream()
                    .filter(song -> song.getTrackId().equals(trackId))
                    .findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song){
        //Mozda trebat da proverish dali veke ima nekoj artist pred da go presh dodavanjeto
        song.getPerformers().add(artist);
        return artist;
    }
}
