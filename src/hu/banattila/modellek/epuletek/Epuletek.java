package hu.banattila.modellek.epuletek;

import hu.banattila.modellek.JatekSzintek;

public abstract class Epuletek{
    private final String nev;
    private int szint;
    private int latogatotHoz;
    private long fejlesztesKoltseg;

    public Epuletek(String nev){
        this.nev = nev;
        this.szint = 0;
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



    public String getNev() {
        return nev;
    }

    public int getSzint() {
        return szint;
    }

    public void setSzint(int szint) {
        this.szint = szint;
    }

    public int getLatogatotHoz() {
        return latogatotHoz;
    }

    public void setLatogatotHoz(int latogatotHoz) {
        this.latogatotHoz = latogatotHoz;
    }

    public long getFejlesztesKoltseg() {
        return fejlesztesKoltseg;
    }

    public void setFejlesztesKoltseg(int fejlesztesKoltseg) {
        this.fejlesztesKoltseg = fejlesztesKoltseg;
    }
}
