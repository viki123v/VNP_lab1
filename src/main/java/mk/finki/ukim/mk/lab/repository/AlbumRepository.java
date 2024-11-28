package mk.finki.ukim.mk.lab.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mk.finki.ukim.mk.lab.model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Long>{
    List<Album> findAll(); 
}

