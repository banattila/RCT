package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public class VattaCukros extends Epuletek {

    public VattaCukros(JatekSzintek jatekSzint) {
        super("Vattacukros", 10, jatekSzint);
        initKoltseg(jatekSzint, 50000, 100000, 150000);
    }
}
