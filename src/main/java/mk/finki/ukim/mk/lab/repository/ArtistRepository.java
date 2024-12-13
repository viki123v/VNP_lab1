package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Artist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
