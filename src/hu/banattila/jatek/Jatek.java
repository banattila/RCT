package hu.banattila.jatek;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.balesetek.Baleset;
import hu.banattila.modellek.emberek.Jatekos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Jatek {

    private final Jatekos jatekos;
    private static int elteltNapok;
    private final Set<Baleset> balesetek;
    private final JatekSzintek szint;
    private static int napiLatogatok;
    private static int varhatoBevetel;
    private static int varhatoKiadas;
    private List<String> uzenet;

    public Jatek(String jatekosNev, JatekSzintek szint) {
        this.szint = szint;
        this.jatekos = new Jatekos(jatekosNev, szint);
        this.balesetek = BalesetekInit.createBalesetek(szint);
        this.uzenet = new ArrayList<>();
        napiLatogatok = 10;
        varhatoBevetel = 0;
        varhatoKiadas = 0;
        elteltNapok = 1;
    }

    // majd a mentések betöltéséhez fog kelleni, de kiegészítve több mindennel, csak el ne felejtsem:)
/*    public Jatek(JatekSzintek szint){
        this.szint = szint;
    }
*/
    private void napEltelik() {
        if (jatekVege()) {
            elteltNapok = 1000;
            System.out.println("A játéknak vége, a játékos pénze: " + getJatekos().getPenz());
        } else {
            elteltNapok++;
            bevetelKalk();
            kiadasok();
            getUzenet().forEach(System.out::println);
            System.out.println();
            this.jatekos.setPenz(this.getJatekos().getPenz() + varhatoBevetel - varhatoKiadas);

            System.out.println("------------------- A napnak vége lett ------------------------------------");
            System.out.println("------------------- A napnak vége lett ------------------------------------");
            System.out.println("------------------- A napnak vége lett ------------------------------------");
            System.out.println("------------------ A várható napi bevétel: " + varhatoBevetel + " ----------------");
            System.out.println("------------------ A várható napi kiadás: " + varhatoKiadas + " ----------------");
            clearUzenetek();

        }


    }

    private boolean jatekVege() {
        return getJatekos().getPenz() < 0;
    }

    private void bevetelKalk() {
        NapiKalkulator.bevetelKalk(getJatekos().getReklamok(), getJatekos().getJatekok());
    }

    private void kiadasok() {
        NapiKalkulator.kiadasok(getJatekos().getKonyvelo(), getJatekos().getKarbantartok(), getUzenet(), getBalesetek(), getJatekos());
    }

    public void napVege() {
        napEltelik();
    }

    public void jatekosReklamoz(String mit) {
        this.uzenet.add(this.jatekos.reklamoz(mit));
    }

    public void jatekosFejleszt(String mit) {
        this.uzenet.add(this.jatekos.fejlesztes(mit));
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

    public List<String> getUzenet() {
        return this.uzenet;
    }

    public void clearUzenetek() {
        this.uzenet.clear();
    }

    public void addUzenet(String mit) {
        this.uzenet.add(mit);
    }

    public static int getNapiLatogatok() {
        return napiLatogatok;
    }

    public static void setNapiLatogatok(int napiLatogatok) {
        Jatek.napiLatogatok = napiLatogatok;
    }

    public static int getVarhatoBevetel() {
        return varhatoBevetel;
    }

    public static void setVarhatoBevetel(int varhatoBevetel) {
        Jatek.varhatoBevetel = varhatoBevetel;
    }

    public static int getVarhatoKiadas() {
        return varhatoKiadas;
    }

    public static void setVarhatoKiadas(int varhatoKiadas) {
        Jatek.varhatoKiadas = varhatoKiadas;
    }

    @Override
    public String toString() {
        return "Ez a játék " + getElteltNapok() + "-ik napja. A játék szintje: " + this.getSzint().getName() + ". A napi látogatók száma: "
                + napiLatogatok + ". A várható bevétel: " + varhatoBevetel + ", illetve a kiadás: " + varhatoKiadas
                + "\n" + this.getJatekos();
    }
}
