package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;

public abstract class Reklamok {

    private final String nev;
    private int hanyadikNapja;
    private double hatasfok;
    private final int idoTartam;
    private int ujLatogatokNaponta;
    private double koltseg;
    private boolean megrendelve;


    public Reklamok(String nev, JatekSzintek jatekSzintek, double koltseg, int ujLatogatokNaponta, int idoTartam) {
        this.nev = nev;
        this.idoTartam = idoTartam;
        this.megrendelve = false;
        init(jatekSzintek, koltseg, ujLatogatokNaponta);
        this.hatasfok = 100.0;
    }

    private void init(JatekSzintek jatekSzintek, double koltseg, int ujLatogatokNaponta) {
        switch (jatekSzintek) {
            case KONNYU: {
                this.koltseg = koltseg;
                this.ujLatogatokNaponta = ujLatogatokNaponta;
                break;
            }
            case KOZEPES: {
                this.koltseg = koltseg * 2;
                this.ujLatogatokNaponta = ujLatogatokNaponta / 2;
                break;
            }
            case NEHEZ: {
                this.koltseg = koltseg * 4;
                this.ujLatogatokNaponta = ujLatogatokNaponta / 4;
                break;
            }
        }
    }

    public void ervennyesseg() {
        boolean eredmeny = this.idoTartam > this.hanyadikNapja;
        if (eredmeny && !megrendelve) {
            this.hanyadikNapja = 0;
            this.megrendelve = false;
        } else if(megrendelve){
            incHanyadikNapja();
        }
        setHatasfok();
    }

    private void incHanyadikNapja() {
        this.hanyadikNapja++;
    }

    private void setUjLatogatokNaponta() {
        this.ujLatogatokNaponta = (int)(getUjLatogatokNaponta() * this.hatasfok / 100);
    }

    private void checkHatasfok(double hatasfok) {
        if (hatasfok < 100) {
            this.hatasfok = hatasfok;
        } else {
            this.hatasfok = 100;
        }
    }

    private void setHatasfok() {
        if (this.megrendelve) {
            checkHatasfok(this.hatasfok * 0.9);
        } else {
            checkHatasfok(this.hatasfok * 1.1);
        }
        setUjLatogatokNaponta();
    }

    public void megrendel() {
        this.megrendelve = true;
    }

    public String getNev() {
        return this.nev;
    }

    public boolean isMegrendelve() {
        return this.megrendelve;
    }

    public int getUjLatogatokNaponta() {
        return this.ujLatogatokNaponta;
    }

    public int getHanyadikNapja() {
        return this.hanyadikNapja;
    }

    public double getKoltseg() {
        return this.koltseg;
    }

    public int getIdoTartam() {
        return this.idoTartam;
    }

    public double getHatasfok() {
        return this.hatasfok;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNev())
                .append("-t maximum ")
                .append(getIdoTartam())
                .append(" napra tudod alkalmazni.")
                .append(getUjLatogatokNaponta() + " új látogatót tud hozni naponta. \tJelenleg")
                .append((megrendelve) ? " meg van rendelve " + getHanyadikNapja() + " napja." : " nincs megrendelve.");

        return sb.toString();
    }
}
