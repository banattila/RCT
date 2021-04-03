package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class HullamVasut extends Jatekok {

    public HullamVasut(JatekSzintek jatekSzint) {
        super(JatekNevek.HULLAMVASUT.getNev(), 160, jatekSzint);
        initKoltseg(jatekSzint, 800000, 1200000, 1600000);
    }
}
