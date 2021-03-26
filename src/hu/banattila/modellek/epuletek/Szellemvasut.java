package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public class Szellemvasut extends Epuletek {

    public Szellemvasut(JatekSzintek jatekSzint) {
        super("Szellem Vasút", 80, jatekSzint);
        initKoltseg(jatekSzint, 400000, 600000, 800000);
    }
}
