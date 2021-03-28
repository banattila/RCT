package hu.banattila.modellek.emberek;

import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.enumok.JatekSzintek;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Jatekos {
    private String nev;
    private int penz;
    private final Set<Jatekok> jatekok;
    private final List<Karbantarto> karbantartok;
    private Konyvelo konyvelo;
    private final Set<Reklamok> reklamok;
    private final JatekSzintek szint;

    public Jatekos(String nev, JatekSzintek szint) {
        this.nev = nev;
        this.szint = szint;
        this.penz = 150000;
        this.jatekok = ListaFactory.initJatekok(this.szint);
        this.karbantartok = new ArrayList<>();
        this.reklamok = ListaFactory.initReklamok(this.szint);
    }

    public void alkalmaz(Szemelyzet szemely) throws MaxSzemelyzetSzam {
        if (szemely instanceof Karbantarto) {
            setKarbantartok((Karbantarto) szemely);
        } else {
            setKonyvelo((Konyvelo) szemely);
        }
    }

    public void kirug(Szemelyzet szemely) {
        if (szemely instanceof Konyvelo) {
            this.konyvelo = null;
        } else {
            this.karbantartok.removeIf(it -> it.getNev().equals(szemely.getNev()));
        }
    }

    public String fejlesztes(String mit){
        Jatekok jatek = this.jatekok.stream().filter(it -> it.getNev().equals(mit)).findFirst().get();
         return jatek.fejleszt(this);
    }

    public String reklamoz(String mit)  {
        Reklamok reklam = this.reklamok.stream()
                .filter(it -> it.getNev().equals(mit))
                .findFirst()
                .get();

        if (reklam.isMegrendelve()){
            return reklam.getNev() + " már meg van rendelve " + reklam.getHanyadikNapja() + " napja.";
        }

        reklam.megrendel();
        return "Sikeresen megrendelted a " + reklam.getNev() + " -ot " + reklam.getIdoTartam() + " napra";
    }

    public Set<Reklamok> getReklamok(){
        return this.reklamok;
    }
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getPenz() {
        return penz;
    }

    public void setPenz(int penz) {
        this.penz = penz;
    }

    public Set<Jatekok> getJatekok() {
        return jatekok;
    }

    public List<Karbantarto> getKarbantartok() {
        return karbantartok;
    }

    public void setKarbantartok(Karbantarto karbantarto) throws MaxSzemelyzetSzam {
        if (this.karbantartok.size() < 5) {
            this.karbantartok.add(karbantarto);
        } else {
            throw new MaxSzemelyzetSzam("karbantarto");
        }
    }

    public Konyvelo getKonyvelo() {
        return konyvelo;
    }

    public void setKonyvelo(Konyvelo konyvelo) throws MaxSzemelyzetSzam {
        if (this.konyvelo == null) {
            this.konyvelo = konyvelo;
        } else {
            throw new MaxSzemelyzetSzam("könyvelőt");
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("A játékos neve: ")
                .append(getNev())
                .append(" akinek ")
                .append(getPenz())
                .append(" fabatkája van.\n\n")
                .append("Játékai:\n\n");
        this.getJatekok().forEach(
                it -> {
                    sb.append(it);
                    sb.append("\n");
                });

        sb.append("Reklámjai:\n\n");
        this.reklamok.forEach(
                it -> {
                    sb.append(it);
                    sb.append("\n");
                }
        );
        sb.append("Alkalmazottai:\n");
        sb.append((this.konyvelo == null)?"":this.konyvelo + "\n");
        this.karbantartok.forEach(
                it -> {
                    sb.append(it);
                    sb.append("\n");
                }
        );
        sb.append((this.konyvelo == null && this.karbantartok.size() == 0)?"Nincsenek alkalmazottai.":"");
        return sb.toString();
    }
}
