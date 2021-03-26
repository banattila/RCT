package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public class Csonakazo extends Epuletek {

    public Csonakazo(JatekSzintek jatekszint) {
        super("Csónakázó tó", 40, jatekszint);
        initKoltseg(jatekszint, 200000, 300000, 400000);
    }
}
