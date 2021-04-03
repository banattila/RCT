package hu.banattila.jatek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.balesetek.*;

import java.util.HashSet;
import java.util.Set;

public class BalesetekInit {

    protected static Set<Baleset> createBalesetek(JatekSzintek szint){
        Set<Baleset> balesetek = new HashSet<>();
        balesetek.add(new RosszVattaCukor(BalesetNevek.ROSSZ_VATTA, szint));
        balesetek.add(new Birsag(BalesetNevek.BIRSAG, szint));
        balesetek.add(new Elsullyed(BalesetNevek.ELSULLYED, szint));
        balesetek.add(new Kisiklik(BalesetNevek.KISIKLIK, szint));
        balesetek.add(new Szetesik(BalesetNevek.SZETESIK, szint));
        return balesetek;
    }
}
