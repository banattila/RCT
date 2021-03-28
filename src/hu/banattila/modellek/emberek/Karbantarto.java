package hu.banattila.modellek.emberek;

public class Karbantarto extends Szemelyzet {

    private int eselyCsokkentesre;
    public Karbantarto(String nev) {
        super(nev);
    }

    public void setEselyCsokkentesre(int eselyCsokkentesre){
        this.eselyCsokkentesre = eselyCsokkentesre;
    }

    public int getEselyCsokkentesre(){
        return this.eselyCsokkentesre;
    }

    @Override
    public void setFizetes(int fizetes) {
        if (fizetes >= 0) {
            this.fizetes = fizetes;
        } else {
            this.fizetes = 0;
        }
    }
}
