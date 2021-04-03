package hu.banattila.modellek.balesetek;

import hu.banattila.enumok.JatekSzintek;

public abstract class Baleset {

    private final String nev;
    private final int kiadas;
    private final int latogatoCsokkenes;
    private final double esely;
    private final String hozzaTartozoJatek;

    public Baleset(String nev, JatekSzintek szint,int kiadas,  int latogatoCsokkenes, double esely, String hozzaTartozoJatek) {
        this.nev = nev;
        this.kiadas = kiadas;
        this.latogatoCsokkenes = latogatoCsokkenes;
        this.hozzaTartozoJatek = hozzaTartozoJatek;

        switch (szint){
            case KONNYU: break;
            case KOZEPES: esely *= 1.5; break;
            case NEHEZ: esely *= 2; break;
        }
        this.esely = esely;
    }

    public String getNev() {
        return nev;
    }

    public int getKiadas() {
        return kiadas;
    }

    public int getLatogatoCsokkenes() {
        return latogatoCsokkenes;
    }

    public double getEsely() {
        return esely;
    }

    public String getHozzaTartozoJatek() {
        return hozzaTartozoJatek;
    }
}
