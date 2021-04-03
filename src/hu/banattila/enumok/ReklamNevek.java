package hu.banattila.enumok;

public enum ReklamNevek {

    ADSENSE("AdSense"),
    ORIASPLAKAT("Óriásplakát"),
    SZOROLAPOZAS("Szórólapozás"),
    TVREKLAM("Tv Reklám"),
    UJSAGHIRDETES("Újsághirdetés");

    private final String nev;
    ReklamNevek(String nev){
        this.nev = nev;
    }

    public String getNev() {
        return nev;
    }
}
