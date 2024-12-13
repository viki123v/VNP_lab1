package mk.finki.ukim.mk.lab.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mk.finki.ukim.mk.lab.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {
}

