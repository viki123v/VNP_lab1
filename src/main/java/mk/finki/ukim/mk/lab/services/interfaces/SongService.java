package mk.finki.ukim.mk.lab.services.interfaces;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService{
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Artist addArtistToSong(Long artistId, String trackId);
    Song findByTrackId(String trackId);
    void removeSongById(Long id);

    Optional<Song> findById(Long songId);

    void updateSong(Long songId, SongDTO dto);

    void save(SongDTO dto);
}
