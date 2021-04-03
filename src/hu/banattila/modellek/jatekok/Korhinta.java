package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Korhinta extends Jatekok {

    public Korhinta(JatekSzintek jatekSzint) {
        super(JatekNevek.KORHINTA.getNev(), 20, jatekSzint);
        initKoltseg(jatekSzint, 100000, 150000, 200000);
    }
}
