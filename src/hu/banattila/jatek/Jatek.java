package hu.banattila.jatek;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.balesetek.Baleset;
import hu.banattila.modellek.emberek.Jatekos;

import java.util.Set;

public class Jatek {

    private final Jatekos jatekos;
    private static int elteltNapok;
    private final Set<Baleset> balesetek;
    private final JatekSzintek szint;
    private static int napiLatogatok;
    private static int varhatoBevetel;
    private static int varhatoKiadas;
    private String uzenet;

    public Jatek(String jatekosNev, JatekSzintek szint){
        this.szint = szint;
        this.jatekos = new Jatekos(jatekosNev, szint);
        this.balesetek = BalesetekInit.createBalesetek(szint);
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
    private void napEltelik(){
        elteltNapok++;
        bevetelKalk();
        kiadasok();
        this.jatekos.setPenz(this.getJatekos().getPenz() + varhatoBevetel - varhatoKiadas);
        System.out.println("------------------ A várható napi bevétel: " + varhatoBevetel);
        System.out.println("------------------ A várható napi kiadás: " + varhatoKiadas);
        System.out.println("------------------- A napnak vége lett ------------------------------------");
        System.out.println("------------------- A napnak vége lett ------------------------------------");
        System.out.println("------------------- A napnak vége lett ------------------------------------");
    }

    private void bevetelKalk() {
        NapiKalkulator.bevetelKalk(getJatekos().getReklamok(), getJatekos().getJatekok());
    }

    private void kiadasok(){
        NapiKalkulator.kiadasok(getJatekos().getKonyvelo(), getJatekos().getKarbantartok());
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
    public String toString(){
        return "Ez a játék " + getElteltNapok() + "-ik napja. A játék szintje: " + this.getSzint() + ". A napi látogatók száma: "
                + napiLatogatok + ". A várható bevétel: " + varhatoBevetel + ", illetve a kiadás: " + varhatoKiadas
                + "\n" + this.getJatekos();
    }
}
