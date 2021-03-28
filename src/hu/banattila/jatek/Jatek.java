package hu.banattila.jatek;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.balesetek.Baleset;
import hu.banattila.modellek.emberek.Jatekos;
import hu.banattila.modellek.emberek.Szemelyzet;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;

import java.util.Random;
import java.util.Set;

public class Jatek {

    private Jatekos jatekos;
    private static int elteltNapok;
    private Set<Baleset> balesetek;
    private final JatekSzintek szint;
    private int napiLatogatok;
    private int varhatoBevetel;
    private int varhatoKiadas;
    private String uzenet;

    public Jatek(String jatekosNev, JatekSzintek szint){
        this.szint = szint;
        this.jatekos = new Jatekos(jatekosNev, szint);
        this.balesetek = BalesetekInit.createBalesetek(szint);
        this.napiLatogatok = 10;
        this.varhatoBevetel = 0;
        this.varhatoKiadas = 0;
    }

    // majd a mentések betöltéséhez fog kelleni, de kiegészítve több mindennel, csak el ne felejtsem:)
/*    public Jatek(JatekSzintek szint){
        this.szint = szint;
    }
*/
    private void napEltelik(){
        this.varhatoKiadas = 0;
        elteltNapok++;
        System.out.println("------------------ A várható napi bevétel: " + this.varhatoBevetel);
        karbantartoHatekonysag();
        this.varhatoKiadas += karbantartoFizetes();
        this.varhatoKiadas += konyveloFizetes();
        System.out.println("------------------ A várható napi kiadás: " + this.varhatoKiadas);
        this.jatekos.setPenz(this.getJatekos().getPenz() + this.varhatoBevetel - this.varhatoKiadas);
        System.out.println("------------------- A napnak vége lett ------------------------------------");
        checkReklamok();
        latogatokKalk();
        this.varhatoBevetel = bevetelKalk();

    }

    private int konyveloFizetes(){
        if (jatekos.getKonyvelo() != null){
            jatekos.getKonyvelo().setFizetes(this.varhatoBevetel);
            return jatekos.getKonyvelo().getFizetes();
        }
        return 0;
    }

    private void karbantartoHatekonysag(){
        this.jatekos.getKarbantartok()
                .forEach(it ->{
                    int esely = new Random().nextInt(3);
                    it.setEselyCsokkentesre(esely);
                });
    }

    private double karbantartoFizetes(){
        this.jatekos.getKarbantartok()
                .forEach(it ->
                        it.setFizetes(it.getEselyCsokkentesre() * this.varhatoBevetel / 100));
        return this.getJatekos().getKarbantartok()
                .stream().map(Szemelyzet::getFizetes).reduce(0, Integer::sum);
    }

    private int bevetelKalk(){
        Integer bevetel = this.jatekos.getJatekok()
                .stream()
                .map(Jatekok::getNyeresegLatogatonkent)
                .reduce(0, Integer::sum);

        return bevetel * napiLatogatok;
    }

    private int kiadasKalk(){
        int kiadas = 0;

        return kiadas;
    }

    private void checkReklamok(){
        getJatekos().getReklamok()
                .forEach(Reklamok::ervennyesseg);
    }

    private void latogatokKalk(){
        int eredmeny =  this.jatekos.getReklamok()
                .stream()
                .filter(Reklamok::isMegrendelve)
                .map(Reklamok::getUjLatogatokNaponta)
                .reduce(0, Integer::sum);
        this.napiLatogatok += eredmeny;
    }

    public void napVege(){
        napEltelik();

    }

    public void jatekosReklamoz(String mit){
        this.uzenet = this.jatekos.reklamoz(mit);
    }

    public void jatekosFejleszt(String mit){
        this.uzenet = this.jatekos.fejlesztes(mit);
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

    public String getUzenet(){
        return this. uzenet;
    }

    public void setUzenet(String uzenet){
        this.uzenet = uzenet;
    }

    @Override
    public String toString(){
        return "Ez a játék " + elteltNapok + "-ik napja. A játék szintje: " + this.getSzint() + ". A napi látogatók száma: "
                + this.napiLatogatok + ". A várható bevétel: " + this.varhatoBevetel + ", illetve a kiadás: " + this.varhatoKiadas
                + "\n" + this.getJatekos();
    }
}
