package hu.banattila.modellek.epuletek;

import hu.banattila.kivetelek.MaximumEpuletSzint;
import hu.banattila.kivetelek.NincsElegPenz;
import hu.banattila.modellek.JatekSzintek;
import hu.banattila.modellek.emberek.Jatekos;

public abstract class Epuletek implements Epul{
    private final int MAX_SZINT = 10;
    private final int ALAP_NYERESEG_LATOGATONKENT;
    private final String nev;
    private int szint;
    private int nyeresegLatogatonkent;
    private double fejlesztesKoltseg;

    public Epuletek(String nev, int alap, JatekSzintek jatekSzintek){
        this.nev = nev;
        this.szint = 0;
        switch (jatekSzintek){
            case KONNYU: alap = alap; break;
            case KOZEPES: alap = alap / 2; break;
            case NEHEZ: alap = alap / 4; break;
        }
        this.ALAP_NYERESEG_LATOGATONKENT = alap;
    }

    protected void initKoltseg(JatekSzintek jatekSzint, int konnyu, int kozepes, int nehez) {
        switch (jatekSzint) {
            case KONNYU:
                setFejlesztesKoltseg(konnyu);
                break;
            case KOZEPES:
                setFejlesztesKoltseg(kozepes);
            case NEHEZ:
                setFejlesztesKoltseg(nehez);
        }
    }

   @Override
    public String fejleszt(Jatekos jatekos) throws NincsElegPenz {

        String eredmeny = "";
        boolean penzEllenorzes = false;

        try{
            penzEllenorzes = tranzakcioEllenorzes(jatekos.getPenz());
        } catch (NincsElegPenz e){
            return e.getMessage();
        }

        if(penzEllenorzes){
            try {
                this.setSzint(this.getSzint() + 1);
            } catch (MaximumEpuletSzint e){
                return e.getMessage();
            }
            eredmeny = "Fejlesztés sikeres!";
            this.setFejlesztesKoltseg(this.getFejlesztesKoltseg() * 2);
        } else if (jatekos.getPenz() - this.getFejlesztesKoltseg() < 0){
        }
        jatekos.setPenz(jatekos.getPenz() - this.getFejlesztesKoltseg());
        return eredmeny;
   }

   private boolean tranzakcioEllenorzes(double jatekosPenze) throws NincsElegPenz{
        if (jatekosPenze > this.getFejlesztesKoltseg()){
            return true;
        } else {
            throw new NincsElegPenz(this.getNev()
                    + " fejlesztéséhez nincs elegendő pénz. A hiányzó pénz: "
                    + Math.abs(jatekosPenze - this.getFejlesztesKoltseg()));
        }
   }

    public String getNev() {
        return nev;
    }

    public int getSzint() {
        return szint;
    }

    public void setSzint(int szint) throws MaximumEpuletSzint{
        if (szint <= MAX_SZINT){
            this.szint = szint;
        } else {
            throw new MaximumEpuletSzint(this.getNev());
        }
    }

    public int getNyeresegLatogatonkent() {
        return nyeresegLatogatonkent;
    }

    public void setNyeresegLatogatonkent() {
        this.nyeresegLatogatonkent += this.nyeresegLatogatonkent;
    }

    public double getFejlesztesKoltseg() {
        return fejlesztesKoltseg;
    }

    public void setFejlesztesKoltseg(double fejlesztesKoltseg) {
        this.fejlesztesKoltseg = fejlesztesKoltseg;
    }
}
