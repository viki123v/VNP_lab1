package mk.finki.ukim.mk.lab.services.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.repository.ArtistRepository;
import mk.finki.ukim.mk.lab.services.interfaces.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id)  {
        return artistRepository.findById(id).get();
    }

}
