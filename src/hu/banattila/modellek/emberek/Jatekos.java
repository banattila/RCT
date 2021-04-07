package hu.banattila.modellek.emberek;


import hu.banattila.enumok.JatekSzintek;
import hu.banattila.jatek.Jatek;
import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Jatekos {
    private String nev;
    private long penz;
    private  Set<Jatekok> jatekok;
    private  List<Karbantarto> karbantartok;
    private Konyvelo konyvelo;
    private  Set<Reklamok> reklamok;

    public Jatekos(String nev, JatekSzintek szint) {
        this.nev = nev;
        this.penz = 150000;
        this.jatekok = ListaFactory.initJatekok(szint);
        this.karbantartok = new ArrayList<>();
        this.reklamok = ListaFactory.initReklamok(szint);
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

        if (getPenz() - reklam.getKoltseg() >= 0){
            reklam.megrendel();
            setPenz(getPenz() - reklam.getKoltseg());
            return "Sikeresen megrendelted a " + reklam.getNev() + " -ot " + reklam.getIdoTartam() + " napra";
        } else {
            return "Nincs elég pénzed megrendelni a " + reklam.getNev() + "-t.";
        }

    }

    public void konyvelotAlkalmaz(){
        if (this.konyvelo == null){
            this.konyvelo = Konyvelo.konyvelotAlkalmaz();
        } else {
            Jatek.addUzenet("Már alkalmazod " + getKonyvelo().getNev() + "-t.");
        }
    }

    public void karbantartotKirug(String kit){
        getKarbantartok().removeIf(ember -> ember.getNev().equals(kit));
    }

    public void konyvelotKirug(){
        Jatek.addUzenet("Kirúgtad " + getKonyvelo().getNev() + "t, a könyvelődet.");
        this.konyvelo = null;
    }

    public void karbantartotFelvesz(String kit) throws MaxSzemelyzetSzam {
        if (this.karbantartok.size() < 5) {
            this.karbantartok.add(new Karbantarto(kit));
        } else {
            throw new MaxSzemelyzetSzam("karbantarto");
        }
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

    public long getPenz() {
        return penz;
    }

    public void setPenz(long penz) {

        if (penz < Long.MAX_VALUE){
            this.penz = penz;
        }
    }

    public Set<Jatekok> getJatekok() {
        return jatekok;
    }

    public List<Karbantarto> getKarbantartok() {
        return karbantartok;
    }

    public void setJatekok(Set<Jatekok> jatekok) {
        this.jatekok = jatekok;
    }

    public void setKarbantartok(List<Karbantarto> karbantartok) {
        this.karbantartok = karbantartok;
    }

    public void setKonyvelo(Konyvelo konyvelo) {
        this.konyvelo = konyvelo;
    }

    public void setReklamok(Set<Reklamok> reklamok) {
        this.reklamok = reklamok;
    }

    public Konyvelo getKonyvelo() {
        return konyvelo;
    }

    public void setKonyvelo() {
        this.konyvelo = Konyvelo.konyvelotAlkalmaz();
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
