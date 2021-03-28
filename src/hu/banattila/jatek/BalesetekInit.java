package hu.banattila.jatek;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.balesetek.Baleset;
import hu.banattila.modellek.balesetek.Balesetek;

import java.util.HashSet;
import java.util.Set;

public class BalesetekInit {

    protected static Set<Baleset> createBalesetek(JatekSzintek szint){
        Set<Baleset> balesetek = new HashSet<>();
        balesetek.add(new Baleset(Balesetek.BIRSAG, szint));
        balesetek.add(new Baleset(Balesetek.ELSULLYED, szint));
        balesetek.add(new Baleset(Balesetek.KISIKLIK, szint));
        balesetek.add(new Baleset(Balesetek.ELSULLYED, szint));
        balesetek.add(new Baleset(Balesetek.SZETESIK, szint));
        return balesetek;
    }
}
