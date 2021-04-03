package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekSzintek;

public class Birsag extends Baleset{

    public Birsag(BalesetNevek nev, JatekSzintek szint){
        super(nev.name(), szint, 2, 0, 1, "Bírság");
    }
}
