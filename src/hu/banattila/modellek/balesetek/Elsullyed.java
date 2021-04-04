package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Elsullyed extends Baleset{

    public Elsullyed(BalesetNevek nev, JatekSzintek szint){
        super(nev.getNev(), szint,50000,  10, 4, JatekNevek.CSONAKAZO.getNev());
    }
}
