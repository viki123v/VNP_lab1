package mk.finki.ukim.mk.lab.services.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.services.interfaces.ArtistService;
import mk.finki.ukim.mk.lab.services.interfaces.SongService;
import mk.finki.ukim.mk.lab.util.SongMapper;
import mk.finki.ukim.mk.lab.util.SongMapperImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;
    private final SongRepository songRepository;
    private final ArtistService artistService;

    public SongServiceImpl(
        SongMapperImpl mapper,
        SongRepository songRepository,
        ArtistServiceImpl artistService
    ){
        this.songRepository=songRepository;
        this.songMapper=mapper;
        this.artistService=artistService;
    }

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

    @Override
    public void removeSongById(Long id) {
        songRepository.removeById(id);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return songRepository.findById(songId);
    }

    @Override
    public void updateSong(Long songId, SongDTO dto) {
        try{
            Song song = songMapper.song(dto);
            findById(songId)
                .ifPresent(selectedSong -> {
                    selectedSong.setToAlbum(song.getToAlbum());
                    selectedSong.setReleaseYear(song.getReleaseYear());
                    selectedSong.setGenre(song.getGenre());
                    selectedSong.setTrackId(song.getTrackId());
                    selectedSong.setPerformers(song.getPerformers());
                    selectedSong.setTitle(song.getTitle());
                });
        }catch (Exception ignore){}
    }

    @Override
    public void save(SongDTO dto) {
        try{
            songRepository.safeSave(
                songMapper.song(dto)
            );
        }catch (Exception ignore){}
    }

    @Override
    public Artist addArtistToSong(Long artistId, String trackId) {
        Song song = findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        if(song!=null && artist!=null)
            addArtistToSong(artist,song);

        return artist;
    }

}

