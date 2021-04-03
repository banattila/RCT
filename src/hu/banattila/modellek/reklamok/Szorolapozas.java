package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;

public class Szorolapozas extends Reklamok {

    public Szorolapozas(JatekSzintek jatekSzintek) {
        super(ReklamNevek.SZOROLAPOZAS.name(), jatekSzintek, 1000, 50, 1);
    }
}
