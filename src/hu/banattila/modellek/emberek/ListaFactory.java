package hu.banattila.modellek.emberek;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.jatekok.*;
import hu.banattila.modellek.reklamok.*;

import java.util.HashSet;
import java.util.Set;

public class ListaFactory {

    protected static Set<Reklamok> initReklamok(JatekSzintek szint){
        Set<Reklamok> reklamok = new HashSet<>();
        reklamok.add(new Szorolapozas(szint));
        reklamok.add(new UjsagHirdetes(szint));
        reklamok.add(new OriasPlakat(szint));
        reklamok.add(new TVReklam(szint));
        reklamok.add(new AdSense(szint));
        return reklamok;
    }

    protected static Set<Jatekok> initJatekok(JatekSzintek szint){
        Set<Jatekok> jatekok = new HashSet<>();
        jatekok.add(new VattaCukros(szint));
        jatekok.add(new Korhinta(szint));
        jatekok.add(new Csonakazo(szint));
        jatekok.add(new Szellemvasut(szint));
        jatekok.add(new HullamVasut(szint));
        return jatekok;
    }
}
