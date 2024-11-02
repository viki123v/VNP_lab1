package mk.finki.ukim.mk.lab.factories.impl;

import mk.finki.ukim.mk.lab.factories.interfaces.RandomFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RStringFac implements RandomFactory<String> {

    private static Random random = new Random();

    private String randomLetter(){
        boolean nextUppercase= random.nextBoolean();
        char nextLetter;

        if(nextUppercase){
            nextLetter=(char)('A'+random.nextInt(26));
        }else{
            nextLetter=(char) ('a'+random.nextInt(26));
        }

        return String.valueOf(nextLetter);
    }

    @Override
    public String createInstance() {
        int length = 1 + random.nextInt(20);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(randomLetter());
        }

        return builder.toString();
    }

}
