package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
   Optional<Song> findByTrackId(String trackId);
   List<Song> findAllByAlbum_Id(Long albumId);
}
