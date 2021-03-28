package hu.banattila.modellek.emberek;

public abstract class Szemelyzet {

    private final String nev;
    protected int fizetes;

    public Szemelyzet(String nev) {
        this.nev = nev;
    }

    public String getNev() {
        return this.nev;
    }

    public int getFizetes() {
        return this.fizetes;
    }

    abstract void setFizetes(int fizetes);

    @Override
    public String toString(){
        return getNev() + " fizetése: " + getFizetes() + ".";
    }
}
