package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Artist;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist,Long>{
    List<Artist> findAll(); 
}
