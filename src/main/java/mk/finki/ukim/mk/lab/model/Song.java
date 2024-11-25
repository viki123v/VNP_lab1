package mk.finki.ukim.mk.lab.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ElementCollection
    private List<Artist> performers;
    @Id
    private Long id;
    @ManyToOne
    private Album toAlbum;
}
