package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;

public class RosszVattaCukor extends Baleset{

    public RosszVattaCukor(BalesetNevek nev, JatekSzintek szint){
        super(nev.getNev(), szint,1000, 0, 16, JatekNevek.VATTACUKROS.name());
    }
}
