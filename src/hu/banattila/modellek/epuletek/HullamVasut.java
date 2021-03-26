package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public class HullamVasut extends Epuletek{

    public HullamVasut(JatekSzintek jatekSzint){
        super("Hullámvasút", 160, jatekSzint);
        initKoltseg(jatekSzint, 800000, 1200000, 1600000);
    }
}
