package mk.finki.ukim.mk.lab.factories.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import mk.finki.ukim.mk.lab.model.Artist;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RArtistFactory implements RandomFactory<Artist> {

    private static Long next_id= 0L;

    @NonNull private RStringFac stringFac;

    @Override
    public Artist createInstance() {
        return new Artist(
            next_id++,
            stringFac.createInstance(),
            stringFac.createInstance(),
            stringFac.createInstance()
        );
    }

}
