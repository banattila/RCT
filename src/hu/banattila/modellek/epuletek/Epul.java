package hu.banattila.modellek.epuletek;

import hu.banattila.kivetelek.NincsElegPenz;
import hu.banattila.modellek.JatekSzintek;

public interface Epul {
    void initKoltseg(JatekSzintek jatekSzint);
    boolean fejleszt() throws NincsElegPenz;
}
