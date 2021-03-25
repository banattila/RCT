package hu.banattila.modellek.emberek;

public abstract class Szemelyzet {

    private final String nev;

    public Szemelyzet(String nev){
        this.nev = nev;
    }

    public String getNev() {
        return nev;
    }
}
