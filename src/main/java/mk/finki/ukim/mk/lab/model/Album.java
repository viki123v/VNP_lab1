package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NonNull private String name;
    @NonNull private String genre;
    @NonNull private String releaseYear;

    @OneToMany(mappedBy = "album",fetch=FetchType.EAGER)
    @NonNull private List<Song> songs;
}
