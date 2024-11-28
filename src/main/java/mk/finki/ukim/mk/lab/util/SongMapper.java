package mk.finki.ukim.mk.lab.util;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Song;

public interface SongMapper {
 SongDTO dto(Song song);
 Song song(SongDTO dto);
}
