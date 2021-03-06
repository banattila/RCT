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
    private static long varhatoBevetel;
    private static long varhatoKiadas;
    private static List<String> uzenet;

    public Jatek(String jatekosNev, JatekSzintek szint) {
        this.szint = szint;
        this.jatekos = new Jatekos(jatekosNev, szint);
        this.balesetek = BalesetekInit.createBalesetek(szint);
        uzenet = new ArrayList<>();
        napiLatogatok = 10;
        varhatoBevetel = 0;
        varhatoKiadas = 0;
        elteltNapok = 1;
    }

    private void napEltelik() {
        if (jatekVege()) {
            elteltNapok = 1000;
        } else {
            clearUzenetek();
            elteltNapok++;
            bevetelKalk();
            kiadasok();
            this.jatekos.setPenz(this.getJatekos().getPenz() + varhatoBevetel - varhatoKiadas);
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
        uzenet.add(this.jatekos.reklamoz(mit));
    }

    public void jatekosFejleszt(String mit) {
        uzenet.add(this.jatekos.fejlesztes(mit));
    }

    public Jatekos getJatekos() {
        return jatekos;
    }

    public void setElteltNapok(int elteltNapok) {
        Jatek.elteltNapok = elteltNapok;
    }

    public int getElteltNapok() {
        return elteltNapok;
    }

    public Set<Baleset> getBalesetek() {
        return balesetek;
    }

    public JatekSzintek getSzint() {
        return szint;
    }

    public List<String> getUzenet() {
        return uzenet;
    }

    public void clearUzenetek() {
        uzenet.clear();
    }

    public static void addUzenet(String mit) {
        uzenet.add(mit);
    }

    public static int getNapiLatogatok() {
        return napiLatogatok;
    }

    public static void setNapiLatogatok(int napiLatogatok) {
        Jatek.napiLatogatok = napiLatogatok;
    }

    public static long getVarhatoBevetel() {
        return varhatoBevetel;
    }

    public static void setVarhatoBevetel(long varhatoBevetel) {
        Jatek.varhatoBevetel = varhatoBevetel;
    }

    public static void setVarhatoKiadas(long varhatoKiadas) {
        Jatek.varhatoKiadas = varhatoKiadas;
    }

    @Override
    public String toString() {
        return "Ez a j??t??k " + getElteltNapok() + "-ik napja. A j??t??k szintje: " + this.getSzint().getName() + ". A napi l??togat??k sz??ma: "
                + napiLatogatok + ". A v??rhat?? bev??tel: " + varhatoBevetel + ", illetve a kiad??s: " + varhatoKiadas
                + "\n" + this.getJatekos();
    }
}
