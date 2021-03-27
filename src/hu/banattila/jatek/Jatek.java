package hu.banattila.jatek;

import hu.banattila.modellek.JatekSzintek;
import hu.banattila.modellek.balesetek.Baleset;
import hu.banattila.modellek.emberek.Jatekos;
import hu.banattila.modellek.epuletek.Epuletek;

import java.util.Random;
import java.util.Set;

public class Jatek {

    private Jatekos jatekos;
    private static int elteltNapok;
    private Set<Baleset> balesetek;
    private final JatekSzintek szint;
    private int napiLatogatok;
    private double varhatoBevetel;
    private double varhatoKiadas;

    public Jatek(String jatekosNev, JatekSzintek szint){
        this.szint = szint;
        this.jatekos = new Jatekos(jatekosNev, szint);
        this.balesetek = BalesetekInit.createBalesetek(szint);
        napiLatogatok = 10;
        this.varhatoBevetel = 0;
        this.varhatoKiadas = 0;
    }

    // majd a mentések betöltéséhez fog kelleni, de kiegészítve több mindennel, csak el ne felejtsem:)
/*    public Jatek(JatekSzintek szint){
        this.szint = szint;
    }
*/
    private void napEltelik(){
        elteltNapok++;
        this.varhatoBevetel = bevetelKalk();
        karbantartoHatekonysag();
        karbantartoFizetes();
        konyveloFizetes();
    }

    private double konyveloFizetes(){
        if (jatekos.getKonyvelo() != null){
            jatekos.getKonyvelo().setFizetes(this.varhatoBevetel);
            return jatekos.getKonyvelo().getFizetes();
        }
        return 0;
    }

    private void karbantartoHatekonysag(){
        this.jatekos.getKarbantartok()
                .forEach(it ->{
                    double esely = 3 *  new Random().nextDouble();
                    it.setEselyCsokkentesre(esely);
                });
    }

    private void karbantartoFizetes(){
        this.jatekos.getKarbantartok()
                .forEach(it ->
                        it.setFizetes(it.getEselyCsokkentesre() * this.varhatoBevetel));
    }

    private double bevetelKalk(){
        Double bevetel = this.jatekos.getEpuletek()
                .stream()
                .map(Epuletek::getNyeresegLatogatonkent)
                .reduce(0.0, Double::sum);

        jatekos.getKarbantartok().stream()
                .forEach(it -> it.setFizetes(bevetel * it.getEselyCsokkentesre()));

        return bevetel * napiLatogatok;
    }

    private double kiadasKalk(){
        double kiadas = 0;

        return kiadas;
    }

    private void latogatokKalk(){

    }

    public void napVege(){
        napEltelik();

    }

    public Jatekos getJatekos() {
        return jatekos;
    }

    public static int getElteltNapok() {
        return elteltNapok;
    }

    public Set<Baleset> getBalesetek() {
        return balesetek;
    }

    public JatekSzintek getSzint() {
        return szint;
    }
}
