package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song,Long> {
   List<Song> findAll();  
   Optional<Song> findByTrackId(String trackId);
}

//@Repository
// public class SongRepository {

//     private List<Song> songs;

//     public SongRepository(
//         RSongFactory rSongFactory
//     ){
//         songs= IntStream.range(0,5).mapToObj(i -> rSongFactory.createInstance())
//                         .collect(Collectors.toCollection(ArrayList::new));
//     }

//     public List<Song> findAll() {
//         return songs;
//     }

//     public Optional<Song> findByTrackId(String trackId) {
//         return songs.stream()
//                     .filter(song -> song.getTrackId().equals(trackId))
//                     .findFirst();
//     }

//     public Artist addArtistToSong(Artist artist, Song song){
//         //Mozda trebat da proverish dali veke ima nekoj artist pred da go presh dodavanjeto
//         song.getPerformers().add(artist);
//         return artist;
//     }

//     public void removeById(Long id) {
//         songs.removeIf(song -> song.getId().equals(id));
//     }

//     public Optional<Song> findById(Long songId) {
//         return songs.stream().filter(song -> song.getId().equals(songId)).findFirst();
//     }

//     public void save(Song song) {
//         songs.add(song);
//     }

//     public void safeSave(Song song){
//         songs.removeIf(persistedSong->persistedSong.getId().equals(song.getId()));
//         save(song);
//     }

// }
