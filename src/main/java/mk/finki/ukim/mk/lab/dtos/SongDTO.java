package mk.finki.ukim.mk.lab.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.lab.model.Artist;

import java.util.List;

@Data
@NoArgsConstructor
public class SongDTO {
    private String title;
    private String trackId;
    private String genre;
    private Integer releaseYear;
    private Long albumId;
    private Long id ;
    private List<Artist> preformers;
}
