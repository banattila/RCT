package hu.banattila.modellek.emberek;

public class Konyvelo extends Szemelyzet {

    private static Konyvelo konyvelo;

    private Konyvelo(String nev) {
        super(nev);
        this.fizetes = 0;
    }

    @Override
    public void setFizetes(int napiToke) {
        this.fizetes = (int)(napiToke * 0.02);
    }

    public int getFizetes(){
        return this.fizetes;
    }

    public static Konyvelo konyvelotAlkalmaz(String kit) {
        if (konyvelo == null) {
            konyvelo = new Konyvelo(kit);
        }
        return konyvelo;
    }
}
