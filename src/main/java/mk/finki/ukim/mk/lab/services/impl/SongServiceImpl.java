package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.factories.impl.RSongFactory;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.SongRepository;
import mk.finki.ukim.mk.lab.services.interfaces.AlbumService;
import mk.finki.ukim.mk.lab.services.interfaces.ArtistService;
import mk.finki.ukim.mk.lab.services.interfaces.SongService;
import mk.finki.ukim.mk.lab.util.SongMapper;
import mk.finki.ukim.mk.lab.util.SongMapperImpl;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;
    private final SongRepository songRepository;
    private final ArtistService artistService;
    private final RandomFactory<Song> rSongFactory;

    public SongServiceImpl(
        SongMapperImpl mapper,
        SongRepository songRepository,
        ArtistServiceImpl artistService,
        RSongFactory songFactory
    ) {
        this.songRepository = songRepository;
        this.songMapper = mapper;
        this.artistService = artistService;
        this.rSongFactory = songFactory;
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId).get();
    }

    @Override
    public void removeSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return songRepository.findById(songId);
    }

    @Override
    public void updateSong(Long songId, SongDTO dto) {
        try {
            Song song = songMapper.song(dto);
            findById(songId)
                    .ifPresent(selectedSong -> {
                        selectedSong.setAlbum(song.getAlbum());
                        selectedSong.setReleaseYear(song.getReleaseYear());
                        selectedSong.setGenre(song.getGenre());
                        selectedSong.setTrackId(song.getTrackId());
                        selectedSong.setPerformers(song.getPerformers());
                        selectedSong.setTitle(dto.getTitle());
                        songRepository.save(selectedSong);
                    });
        } catch (Exception e) {
        }
    }

    @Override
    public void save(SongDTO dto) {
        try {
            songRepository.save(
                    songMapper.song(dto)
            );
        } catch (Exception ignore) {
        }
    }

    @Override
    public Artist addArtistToSong(Long artistId, String trackId) {
        Song song = findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        if (song != null && artist != null)
            addArtistToSong(artist, song);

        return artist;
    }

    @PostConstruct
    public void populate() {
        List<Song> songs = IntStream.range(0, 5).mapToObj(i -> rSongFactory.createInstance())
                .collect(Collectors.toCollection(ArrayList::new));
        songRepository.saveAll(songs);
    }

}
