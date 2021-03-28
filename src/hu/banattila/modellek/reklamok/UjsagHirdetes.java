package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;

public class UjsagHirdetes extends Reklamok {

    public UjsagHirdetes(JatekSzintek jatekSzintek) {
        super(ReklamNevek.UJSAGHIRDETES.name(), jatekSzintek, 4000, 200, 2);
    }
}
