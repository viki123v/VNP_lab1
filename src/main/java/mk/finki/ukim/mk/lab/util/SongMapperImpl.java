package mk.finki.ukim.mk.lab.util;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.services.impl.AlbumServiceImpl;
import mk.finki.ukim.mk.lab.services.interfaces.AlbumService;
import org.springframework.stereotype.Component;

@Component
public class SongMapperImpl implements SongMapper {

    private AlbumService albumService;

    public SongMapperImpl(AlbumServiceImpl albumServiceImpl){
        this.albumService=albumServiceImpl;
    }

    public Song song(SongDTO songDTO) throws Exception{
        return albumService.findById(songDTO.getAlbumId())
            .map(album -> new Song(
                songDTO.getTrackId(),
                songDTO.getTitle(),
                songDTO.getGenre(),
                songDTO.getReleaseYear(),
                songDTO.getPreformers(),
                songDTO.getId(),
                album
            )).orElseThrow(Exception::new);
    }

    public SongDTO dto(Song song){
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
