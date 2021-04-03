package hu.banattila.modellek.jatekok;

import hu.banattila.kivetelek.NincsElegPenz;
import hu.banattila.modellek.emberek.Jatekos;

public interface Fejleszt {
    String fejleszt(Jatekos jatekos) throws NincsElegPenz;
}
