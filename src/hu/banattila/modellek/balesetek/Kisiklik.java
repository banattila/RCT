package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class Kisiklik extends Baleset{

    public Kisiklik(BalesetNevek nev, JatekSzintek szint){
        super(nev.getNev(), szint,100000,  20, 2, JatekNevek.HULLAMVASUT.name());
    }
}
