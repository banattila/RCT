package hu.banattila.modellek.epuletek;

import hu.banattila.kivetelek.NincsElegPenz;
import hu.banattila.modellek.JatekSzintek;
import hu.banattila.modellek.emberek.Jatekos;

public interface Epul {
    String fejleszt(Jatekos jatekos) throws NincsElegPenz;
}
