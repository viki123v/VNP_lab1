package mk.finki.ukim.mk.lab.util;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Song;

public interface SongMapper {
 Song song(SongDTO dto) throws Exception;
 SongDTO dto(Song song);
}
