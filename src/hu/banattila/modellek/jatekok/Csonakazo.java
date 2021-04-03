package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Csonakazo extends Jatekok {

    public Csonakazo(JatekSzintek jatekszint) {
        super(JatekNevek.CSONAKAZO.getNev(), 40, jatekszint);
        initKoltseg(jatekszint, 200000, 300000, 400000);

    }
}
