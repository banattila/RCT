package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Szellemvasut extends Jatekok {

    public Szellemvasut(JatekSzintek jatekSzint) {
        super(JatekNevek.SZELLEMVASUT.name(), 80, jatekSzint);
        initKoltseg(jatekSzint, 400000, 600000, 800000);
    }
}
