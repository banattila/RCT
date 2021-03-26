package hu.banattila.modellek.emberek;

import hu.banattila.kivetelek.AlkalmazottReklam;
import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.modellek.JatekSzintek;
import hu.banattila.modellek.epuletek.*;
import hu.banattila.modellek.reklamok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Jatekos {
    private String nev;
    private double penz;
    private final Set<Epuletek> epuletek;
    private final List<Karbantarto> karbantartok;
    private Konyvelo konyvelo;
    private final Set<Reklamok> reklamok;
    private final JatekSzintek szint;

    public Jatekos(String nev, JatekSzintek szint) {
        this.nev = nev;
        this.penz = 150000;
        this.epuletek = new HashSet<>();
        this.karbantartok = new ArrayList<>();
        this.reklamok = new HashSet<>();
        this.szint = szint;
        initReklamok();
        initEpuletek();
    }

    private void initReklamok() {
        this.reklamok.add(new Szorolapozas(this.szint));
        this.reklamok.add(new UjsagHirdetes(this.szint));
        this.reklamok.add(new OriasPlakat(this.szint));
        this.reklamok.add(new TVReklam(this.szint));
        this.reklamok.add(new AdSense(this.szint));
    }

    private void initEpuletek() {
        this.epuletek.add(new VattaCukros(this.szint));
        this.epuletek.add(new Korhinta(this.szint));
        this.epuletek.add(new Csonakazo(this.szint));
        this.epuletek.add(new Szellemvasut(this.szint));
        this.epuletek.add(new HullamVasut(this.szint));
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

    public void reklamoz(String mit) throws AlkalmazottReklam {

        var reklam = this.reklamok.stream().filter(it -> it.getNev().equals(mit)).filter(Reklamok::isMegrendelve).findFirst().get();
        if (reklam == null) {
            throw new AlkalmazottReklam(reklam.getNev());
        } else {
            reklam.megrendel();
        }
    }
}
