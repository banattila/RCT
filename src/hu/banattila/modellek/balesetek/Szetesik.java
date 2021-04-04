package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Szetesik extends Baleset{

    public Szetesik(BalesetNevek nev, JatekSzintek szint){
        super(nev.getNev(), szint,25000, 5, 8, JatekNevek.KORHINTA.getNev());
    }
}
