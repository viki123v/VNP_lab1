package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album {
    @Id
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;

    @OneToMany(mappedBy = "toAlbum")
    private List<Song> songs;
}
