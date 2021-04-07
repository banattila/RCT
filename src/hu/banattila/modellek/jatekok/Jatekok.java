package hu.banattila.modellek.jatekok;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.kivetelek.MaximumJatekSzint;
import hu.banattila.kivetelek.NincsElegPenz;
import hu.banattila.modellek.emberek.Jatekos;

public abstract class Jatekok implements Fejleszt {
    private final String id;
    private final int ALAP_NYERESEG_LATOGATONKENT;
    private final String nev;
    private int szint;
    private int nyeresegLatogatonkent;
    private int fejlesztesKoltseg;


    public Jatekok(String id, String nev, int alap, JatekSzintek jatekSzintek) {
        this.id = id;
        this.nev = nev;
        this.szint = 0;
        switch (jatekSzintek) {
            case KONNYU:
                break;
            case KOZEPES:
                alap = alap / 2;
                break;
            case NEHEZ:
                alap = alap / 4;
                break;
        }
        this.ALAP_NYERESEG_LATOGATONKENT = alap;
        setNyeresegLatogatonkent(alap);
    }

    protected void initKoltseg(JatekSzintek jatekSzint, int konnyu, int kozepes, int nehez) {
        switch (jatekSzint) {
            case KONNYU:
                setFejlesztesKoltseg(konnyu);
                break;
            case KOZEPES:
                setFejlesztesKoltseg(kozepes);
                break;
            case NEHEZ:
                setFejlesztesKoltseg(nehez);
                break;
        }
    }

    @Override
    public String fejleszt(Jatekos jatekos) {

        String eredmeny = "";
        boolean penzEllenorzes;

        try {
            penzEllenorzes = tranzakcioEllenorzes(jatekos.getPenz());
        } catch (NincsElegPenz e) {
            return e.getMessage();
        }

        if (penzEllenorzes) {
            setNyeresegLatogatonkent(this.getNyeresegLatogatonkent() + this.ALAP_NYERESEG_LATOGATONKENT);
            jatekos.setPenz(jatekos.getPenz() - this.getFejlesztesKoltseg());
            try {
                this.setSzint(this.getSzint() + 1);
            } catch (MaximumJatekSzint e) {
                return e.getMessage();
            }
            this.setFejlesztesKoltseg(this.getFejlesztesKoltseg() * 2);

            eredmeny = getNev() + " fejlesztése sikeres a " + getSzint() + ". szintre!";
        }

        return eredmeny;
    }

    private boolean tranzakcioEllenorzes(double jatekosPenze) throws NincsElegPenz {
        if (jatekosPenze >= this.getFejlesztesKoltseg()) {
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

    public void setSzint(int szint) throws MaximumJatekSzint {
        int maxSzint = 10;
        if (szint <= maxSzint) {
            this.szint = szint;
        } else {
            throw new MaximumJatekSzint(this.getNev());
        }
    }

    public int getNyeresegLatogatonkent() {
        return this.nyeresegLatogatonkent;
    }

    public void setNyeresegLatogatonkent(int nyeresegLatogatonkent) {
        this.nyeresegLatogatonkent = nyeresegLatogatonkent;
    }

    public int getFejlesztesKoltseg() {
        return this.fejlesztesKoltseg;
    }

    public void setFejlesztesKoltseg(int fejlesztesKoltseg) {
        this.fejlesztesKoltseg = fejlesztesKoltseg;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return this.nev + " szintje: " + getSzint() + ", nyeresége látogatónként: " + getNyeresegLatogatonkent() +
                ", és a következő szint fejlesztési költsége: " + getFejlesztesKoltseg();
    }
}
