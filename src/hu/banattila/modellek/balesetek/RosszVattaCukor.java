package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.BalesetNevek;
import hu.banattila.enumok.JatekSzintek;

public class RosszVattaCukor extends Baleset{

    public RosszVattaCukor(BalesetNevek nev, JatekSzintek szint){
        super(nev.name(), szint,1000, 0, 16);
    }
}
