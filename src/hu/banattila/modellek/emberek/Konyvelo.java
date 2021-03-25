package hu.banattila.modellek.emberek;

public class Konyvelo extends Szemelyzet {

    private int fizetes;
    private static Konyvelo konyvelo;

    private Konyvelo(String nev) {
        super(nev);
        this.fizetes = 0;
    }

    public int getFizetes() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes = fizetes;
    }

    public static Konyvelo konyvelotAlkalmaz(String kit) {
        if (konyvelo == null) {
            konyvelo = new Konyvelo(kit);
        }
        return konyvelo;

    }
}
