package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.factories.impl.RAlbumFacotry;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.repository.AlbumRepository;
import mk.finki.ukim.mk.lab.services.interfaces.AlbumService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final RandomFactory<Album> albumFacotry; 

    public AlbumServiceImpl(
        AlbumRepository albumRepository,
        RAlbumFacotry albumFacotry
    ){
        this.albumFacotry=albumFacotry;
        this.albumRepository=albumRepository; 
    }

    @Override
    public List<Album> findAll() {
       return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long albumId) {
        return albumRepository.findById(albumId);
    }
    
//    @PostConstruct
    public void populate(){
        List<Album> albums=
                IntStream.range(0,5)
                    .mapToObj(i -> albumFacotry.createInstance())
                    .collect(Collectors.toCollection(ArrayList::new));
        albumRepository.saveAll(albums); 
    }
}
