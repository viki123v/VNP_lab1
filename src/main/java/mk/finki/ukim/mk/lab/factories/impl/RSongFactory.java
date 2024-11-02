package mk.finki.ukim.mk.lab.factories.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RSongFactory implements RandomFactory<Song> {

    private static Long nextId=0L;
    private Random random = new Random();
    @NonNull private RStringFac stringFac;

    @Override
    public Song createInstance() {
        return new Song(
            (nextId++).toString(),
            stringFac.createInstance(),
            stringFac.createInstance(),
            random.nextInt(2000,2024),
            new ArrayList<>()
        );
    }

}
