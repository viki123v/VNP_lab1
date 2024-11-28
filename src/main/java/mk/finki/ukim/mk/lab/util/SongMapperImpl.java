package mk.finki.ukim.mk.lab.util;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.services.impl.AlbumServiceImpl;
import mk.finki.ukim.mk.lab.services.interfaces.AlbumService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SongMapperImpl implements SongMapper {

    private AlbumService albumService;

    public SongMapperImpl(AlbumServiceImpl albumServiceImpl) {
        this.albumService = albumServiceImpl;
    }

    @Override
    public Song song(SongDTO dto) {
        Album album = albumService.findById(dto.getAlbumId()).get();
        List<Artist> preformers = dto.getPreformers();
        preformers = preformers == null ? new ArrayList<>() : preformers;

        return new Song(
            dto.getTrackId(),
            dto.getTitle(),
            dto.getGenre(),
            dto.getReleaseYear(),
            preformers,
            album
        );
    }

    public SongDTO dto(Song song) {
    SongDTO dto = new SongDTO();

    dto.setAlbumId(song.getToAlbum().getId());
    dto.setReleaseYear(song.getReleaseYear());
    dto.setGenre(song.getGenre());
    dto.setTrackId(song.getTrackId());
    dto.setTitle(song.getTitle());
    dto.setId(song.getId());
    dto.setPreformers(song.getPerformers());

    return dto;
}
}
