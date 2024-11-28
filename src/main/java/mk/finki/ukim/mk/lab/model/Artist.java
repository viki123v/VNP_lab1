package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String bio;
}
