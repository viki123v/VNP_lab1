package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import jakarta.persistence.FetchType;

import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue
    private Long id;
    
    @NonNull private String name;
    @NonNull private String genre;
    @NonNull private String releaseYear;

    @OneToMany(mappedBy = "toAlbum",fetch=FetchType.EAGER)
    @NonNull private List<Song> songs;
}
