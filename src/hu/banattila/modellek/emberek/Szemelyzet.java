package hu.banattila.modellek.emberek;

import java.util.Random;

public abstract class Szemelyzet {

    private final String nev;
    protected double fizetes;

    public Szemelyzet(String nev){
        this.nev = nev;
    }

    public String getNev() {
        return this.nev;
    }

    public double getFizetes() {
        return this.fizetes;
    }
}
