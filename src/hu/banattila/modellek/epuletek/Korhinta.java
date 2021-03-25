package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public class Korhinta extends Epuletek{

    public Korhinta(JatekSzintek jatekSzint){
        super("Körhinta");
        initKoltseg(jatekSzint, 100000, 150000, 200000);
    }
}
