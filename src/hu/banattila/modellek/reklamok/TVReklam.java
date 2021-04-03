package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;

public class TVReklam extends Reklamok {

    public TVReklam(JatekSzintek jatekSzintek) {
        super(ReklamNevek.TVREKLAM.name(), jatekSzintek, 64000, 3200, 8);
    }
}
