package hu.banattila.modellek.emberek;

public class Karbantarto extends Szemelyzet {

    private double eselyCsokkentesre;
    public Karbantarto(String nev) {
        super(nev);
    }

    public void setEselyCsokkentesre(double eselyCsokkentesre){
        this.eselyCsokkentesre = eselyCsokkentesre;
    }

    public double getEselyCsokkentesre(){
        return this.eselyCsokkentesre;
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
