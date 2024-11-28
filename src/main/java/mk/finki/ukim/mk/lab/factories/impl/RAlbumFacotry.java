package mk.finki.ukim.mk.lab.factories.impl;

import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Album;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RAlbumFacotry implements RandomFactory<Album> {

    private static Random yearGen = new Random();
    private RandomFactory<String> stringFac;

    public RAlbumFacotry(RStringFac stringFac){
        this.stringFac=stringFac;
    }

    @Override
    public Album createInstance() {
        return new Album(
            stringFac.createInstance(),
            stringFac.createInstance(),
            String.valueOf(yearGen.nextInt(1000) + 1000),
            new ArrayList<>()
        );
    }

}
