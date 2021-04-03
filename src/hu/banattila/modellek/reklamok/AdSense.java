package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;

public class AdSense extends Reklamok {

    public AdSense(JatekSzintek jatekSzintek) {
        super(ReklamNevek.ADSENSE.getNev(), jatekSzintek, 256000, 6400, 16);
    }
}
