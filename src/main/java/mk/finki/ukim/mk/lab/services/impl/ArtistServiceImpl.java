package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.factories.impl.RArtistFactory;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.repository.ArtistRepository;
import mk.finki.ukim.mk.lab.services.interfaces.ArtistService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.stream.IntStream;
import java.util.ArrayList; 
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final RandomFactory<Artist> artisFactory; 

    public ArtistServiceImpl(
        ArtistRepository artistRepository,
        RArtistFactory artistFactory
    ){
        this.artistRepository=artistRepository;
        this.artisFactory=artistFactory; 
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id)  {
        return artistRepository.findById(id).get();
    }

    @PostConstruct
    public void populate(){
       List<Artist> artistList = IntStream.range(0,5).mapToObj(i -> artisFactory.createInstance())
                              .collect(Collectors.toCollection(ArrayList::new));
        artistRepository.saveAll(artistList); 
    }
}
