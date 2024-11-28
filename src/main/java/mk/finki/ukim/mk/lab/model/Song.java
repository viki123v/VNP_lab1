package mk.finki.ukim.mk.lab.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*; 
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song {
    @NonNull private String trackId;
    @NonNull private String title;
    @NonNull private String genre;
    @NonNull private Integer releaseYear;

    @OneToMany(fetch=FetchType.EAGER)
    @NonNull private List<Artist> performers;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @NonNull private Album toAlbum;
}
