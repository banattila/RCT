package hu.banattila.modellek.reklamok;

import hu.banattila.enumok.JatekSzintek;

public abstract class Reklamok {

    private final String nev;
    private int hanyadikNapja;
    private double hatasfok;
    private final int idoTartam;
    private final int ALAP_UJLATOGATOK;
    private int ujLatogatokNaponta;
    private double koltseg;
    private boolean megrendelve;


    public Reklamok(String nev, JatekSzintek jatekSzintek, double koltseg, int ujLatogatokNaponta, int idoTartam) {
        this.nev = nev;
        this.idoTartam = idoTartam;
        this.megrendelve = false;
        this.ALAP_UJLATOGATOK = ujLatogatokNaponta;
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

    public int ervennyesseg() {
        int eredmeny = 0;
        boolean ervenyes = this.idoTartam > this.hanyadikNapja;
        if (!ervenyes) {
            this.hanyadikNapja = 0;
            this.megrendelve = false;
        } else if (ervenyes && megrendelve) {
            incHanyadikNapja();
            eredmeny += getUjLatogatokNaponta();
        }
        setUjLatogatokNaponta();
        return eredmeny;
    }

    private void incHanyadikNapja() {
        this.hanyadikNapja++;
    }

    public void setUjLatogatokNaponta() {
        if (megrendelve) {
            this.ujLatogatokNaponta = (int) (getUjLatogatokNaponta() * 0.9);
        } else {
            this.ujLatogatokNaponta = (int) (getUjLatogatokNaponta() * 1.1);
        }

        if (getUjLatogatokNaponta() > ALAP_UJLATOGATOK){
            this.ujLatogatokNaponta = ALAP_UJLATOGATOK;
        }
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
