package hu.banattila.modellek.emberek;

public class Karbantarto extends Szemelyzet {
    private int fizetes;

    public Karbantarto(String nev){
        super(nev);
    }


    public void setFizetes(int fizetes) {
        if (fizetes >= 0){
            this.fizetes = fizetes;
        } else {
            this.fizetes = 0;
        }
    }
}
