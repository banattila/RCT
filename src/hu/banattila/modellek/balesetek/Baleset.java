package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.JatekSzintek;

public class Baleset {

    private String nev;
    private JatekSzintek szint;
    private double kiadas;
    private double latogatoCsokkenes;

    public Baleset(Balesetek balesetek, JatekSzintek szint) {
        this.nev = balesetek.name();
        this.szint = szint;
        this.kiadas = 0;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public JatekSzintek getSzint() {
        return szint;
    }

    public void setSzint(JatekSzintek szint) {
        this.szint = szint;
    }

    public double getKiadas() {
        return kiadas;
    }

    public void setKiadas(double kiadas) {
        this.kiadas = kiadas;
    }
}
