package hu.banattila.modellek.emberek;

import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.modellek.epuletek.Epuletek;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Jatekos {
    private String nev;
    private double penz;
    private Set<Epuletek> epuletek;
    private List<Karbantarto> karbantartok;
    private Konyvelo konyvelo;

    public Jatekos(String nev){
        this.nev = nev;
        this.penz = 150000;
        this.karbantartok = new ArrayList<>();
    }

    public void alkalmaz(Szemelyzet szemely) throws MaxSzemelyzetSzam{
        if (szemely instanceof Karbantarto){
            setKarbantartok((Karbantarto) szemely);
        } else {
            setKonyvelo((Konyvelo) szemely);
        }
    }

    public void kirug(Szemelyzet szemely){
        if (szemely instanceof Konyvelo){
            this.konyvelo = null;
        } else {
            for (int i = 0; i < this.karbantartok.size(); i++){
                if (this.karbantartok.get(i) != null && karbantartok.get(i).getNev().equals(szemely.getNev())){
                    this.karbantartok.remove(szemely);
                }
            }
        }
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public double getPenz() {
        return penz;
    }

    public void setPenz(double penz) {
        this.penz = penz;
    }

    public Set<Epuletek> getEpuletek() {
        return epuletek;
    }

    public void setEpuletek(Set<Epuletek> epuletek) {
        this.epuletek = epuletek;
    }

    public List<Karbantarto> getKarbantartok() {
        return karbantartok;
    }

    public void setKarbantartok(Karbantarto karbantarto) throws MaxSzemelyzetSzam{
        if (this.karbantartok.size() < 5){
            this.karbantartok.add(karbantarto);
        } else {
            throw new MaxSzemelyzetSzam("karbantarto");
        }
    }

    public Konyvelo getKonyvelo() {
        return konyvelo;
    }

    public void setKonyvelo(Konyvelo konyvelo) throws MaxSzemelyzetSzam{
        if (this.konyvelo == null){
            this.konyvelo = konyvelo;
        } else {
            throw new MaxSzemelyzetSzam("konyvelo");
        }
    }
}
