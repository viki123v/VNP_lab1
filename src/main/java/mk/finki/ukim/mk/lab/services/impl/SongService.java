package mk.finki.ukim.mk.lab.services.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService implements mk.finki.ukim.mk.lab.services.interfaces.SongService {
    private SongRepository songRepository;

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Song findByTrackId(String trackId)  {
        return songRepository.findByTrackId(trackId).get();
    }

}

