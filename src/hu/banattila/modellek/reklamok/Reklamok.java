package hu.banattila.modellek.reklamok;


import hu.banattila.enumok.JatekSzintek;

public abstract class Reklamok {

    private final String nev;
    private int hanyadikNapja;
    private double hatasfok;
    private final int idoTartam;
    private final int ALAP_UJLATOGATOK;
    private int ujLatogatokNaponta;
    private int koltseg;
    private boolean megrendelve;

    public Reklamok(String nev, JatekSzintek jatekSzintek, int koltseg, int ujLatogatokNaponta, int idoTartam) {
        this.nev = nev;
        this.idoTartam = idoTartam;
        this.megrendelve = false;
        init(jatekSzintek, ujLatogatokNaponta, koltseg);
        this.ALAP_UJLATOGATOK = this.ujLatogatokNaponta;
        this.hatasfok = 100.0;
    }

    private void init(JatekSzintek jatekSzintek, int ujLatogatokNaponta, int koltseg) {
        switch (jatekSzintek) {
            case KONNYU: {
                this.ujLatogatokNaponta = ujLatogatokNaponta;
                this.koltseg = koltseg;
                break;
            }
            case KOZEPES: {
                this.ujLatogatokNaponta = ujLatogatokNaponta / 2;
                this.koltseg = koltseg * 2;
                break;
            }
            case NEHEZ: {
                this.ujLatogatokNaponta = ujLatogatokNaponta / 4;
                this.koltseg = koltseg * 4;
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
        } else if (megrendelve) {
            incHanyadikNapja();
            eredmeny += getUjLatogatokNaponta();
        }
        ujlatogatoKalk();
        return eredmeny;
    }

    private void incHanyadikNapja() {
        this.hanyadikNapja++;
    }

    public void ujlatogatoKalk() {
        if (megrendelve) {
            this.ujLatogatokNaponta = (int) (getUjLatogatokNaponta() * 0.9);
            setHatasfok(getHatasfok() * 0.9);
        } else {
            this.ujLatogatokNaponta = (int) (getUjLatogatokNaponta() * 1.1);
            setHatasfok(getHatasfok() * 1.1);
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

    public void setUjLatogatokNaponta(int ujLatogatokNaponta) { this.ujLatogatokNaponta = ujLatogatokNaponta;}

    public int getHanyadikNapja() {
        return this.hanyadikNapja;
    }

    public int getKoltseg() {
        return this.koltseg;
    }

    public int getIdoTartam() {
        return this.idoTartam;
    }

    public double getHatasfok() {
        return this.hatasfok;
    }

    public int getALAP_UJLATOGATOK() {
        return ALAP_UJLATOGATOK;
    }

    public void setHanyadikNapja(int hanyadikNapja) {
        this.hanyadikNapja = hanyadikNapja;
    }

    public void setKoltseg(int koltseg) {
        this.koltseg = koltseg;
    }

    public void setMegrendelve(boolean megrendelve) {
        this.megrendelve = megrendelve;
    }

    public void setHatasfok(double hatasfok) {
        if (hatasfok > 100.0){
            this.hatasfok = 100.0;
        } else {
            this.hatasfok = hatasfok;
        }
    }

    @Override
    public String toString() {

        return getNev() +
                "-t maximum " +
                getIdoTartam() +
                " napra tudod alkalmazni. " +
                getUjLatogatokNaponta() +
                " új látogatót tud hozni naponta. \tJelenleg" +
                ((megrendelve) ? " meg van rendelve " + getHanyadikNapja() + " napja." : " nincs megrendelve.");
    }
}
