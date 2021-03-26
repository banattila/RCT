package hu.banattila.modellek.emberek;

public class Karbantarto extends Szemelyzet {

    public Karbantarto(String nev) {
        super(nev);
    }

    @Override
    public void setFizetes(double fizetes) {
        if (fizetes >= 0) {
            this.fizetes = fizetes;
        } else {
            this.fizetes = 0;
        }
    }
}
