package hu.banattila.modellek.emberek;

import hu.banattila.modellek.JatekSzintek;
import hu.banattila.modellek.epuletek.*;
import hu.banattila.modellek.reklamok.*;

import java.util.HashSet;
import java.util.Set;

public class InitListak {

    protected static Set<Reklamok> initReklamok(JatekSzintek szint){
        Set<Reklamok> reklamok = new HashSet<>();
        reklamok.add(new Szorolapozas(szint));
        reklamok.add(new UjsagHirdetes(szint));
        reklamok.add(new OriasPlakat(szint));
        reklamok.add(new TVReklam(szint));
        reklamok.add(new AdSense(szint));
        return reklamok;
    }

    protected static Set<Epuletek> initEpuletek(JatekSzintek szint){
        Set<Epuletek> epuletek = new HashSet<>();
        epuletek.add(new VattaCukros(szint));
        epuletek.add(new Korhinta(szint));
        epuletek.add(new Csonakazo(szint));
        epuletek.add(new Szellemvasut(szint));
        epuletek.add(new HullamVasut(szint));
        return epuletek;
    }
}
