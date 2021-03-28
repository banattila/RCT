package hu.banattila.jatek;

import hu.banattila.modellek.emberek.Karbantarto;
import hu.banattila.modellek.emberek.Konyvelo;
import hu.banattila.modellek.emberek.Szemelyzet;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class NapiKalkulator {

    /**
     * Kiadások kalkulálása
     */

    /*Könyvelő bére*/
    private static int konyveloFizetes(Konyvelo konyvelo) {
        if (konyvelo != null) {
            konyvelo.setFizetes(Jatek.getVarhatoBevetel());
            return konyvelo.getFizetes();
        }
        return 0;
    }

    /*Karbantartók bére*/
    private static void karbantartoHatekonysag(List<Karbantarto> karbantatok) {
        karbantatok.forEach(it -> {
            int esely = new Random().nextInt(3) + 1;
            it.setEselyCsokkentesre(esely);
        });
    }

    private static int karbantartoFizetes(List<Karbantarto> karbantartok) {
        karbantartoHatekonysag(karbantartok);
        karbantartok.forEach(it ->
                it.setFizetes(it.getEselyCsokkentesre() * Jatek.getVarhatoBevetel() / 100));
        return karbantartok
                .stream().map(Szemelyzet::getFizetes).reduce(0, Integer::sum);
    }


    /*Összes kiadás*/
    protected static void kiadasok(Konyvelo konyvelo, List<Karbantarto> karbantarok) {
        int eredmeny = 0;
        eredmeny += konyveloFizetes(konyvelo);
        eredmeny += karbantartoFizetes(karbantarok);
        Jatek.setVarhatoKiadas(eredmeny);
    }

    /**
     * Bevételek és a napi látogatók kalkulálása
     */

    private static int ujLatogatokSzama(Set<Reklamok> reklamok) {
        int latogatok = 0;
        for (Reklamok reklam : reklamok) {
            latogatok += reklam.ervennyesseg();
        }
        return latogatok;
    }

    private static int bevetel(Set<Jatekok> jatekok, int napiLatogatok) {
        int eredmeny = 0;
        for (Jatekok jatek : jatekok) {
            if (jatek.getSzint() > 0) {
                eredmeny += jatek.getNyeresegLatogatonkent() * napiLatogatok;
            }
        }
        return eredmeny;
    }

    protected static void bevetelKalk(Set<Reklamok> reklamok, Set<Jatekok> jatekok) {
        Jatek.setVarhatoBevetel(Jatek.getVarhatoBevetel() + bevetel(jatekok, Jatek.getNapiLatogatok()));
        Jatek.setNapiLatogatok(Jatek.getNapiLatogatok() + ujLatogatokSzama(reklamok));
    }
}
