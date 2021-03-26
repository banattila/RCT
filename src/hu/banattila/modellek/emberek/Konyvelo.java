package hu.banattila.modellek.emberek;

public class Konyvelo extends Szemelyzet {

    private double fizetes;
    private static Konyvelo konyvelo;

    private Konyvelo(String nev) {
        super(nev);
        this.fizetes = 0;
    }

    public void setFizetes(double napiToke) {
        this.fizetes = napiToke * 0.02;
    }

    public static Konyvelo konyvelotAlkalmaz(String kit) {
        if (konyvelo == null) {
            konyvelo = new Konyvelo(kit);
        }
        return konyvelo;

    }
}
