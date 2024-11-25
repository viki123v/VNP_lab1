package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
