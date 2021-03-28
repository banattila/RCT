package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;

public class OriasPlakat extends Reklamok {

    public OriasPlakat(JatekSzintek jatekSzintek) {
        super(ReklamNevek.ORIASPLAKAT.name(), jatekSzintek, 16000, 800, 4);
        ;
    }
}
