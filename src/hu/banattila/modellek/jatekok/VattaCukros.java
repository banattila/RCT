package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class VattaCukros extends Jatekok {

    public VattaCukros(JatekSzintek jatekSzint) {
        super(JatekNevek.VATTACUKROS.name(), 10, jatekSzint);
        initKoltseg(jatekSzint, 50000, 100000, 150000);
    }
}
