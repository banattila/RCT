package hu.banattila.enumok;

public enum ReklamNevek {

    ADSENSE("AdSense"),
    ORIASPLAKAT("Óriásplakát"),
    SZOROLAPOZAS(""),
    TVREKLAM(""),
    UJSAGHIRDETES("");

    private final String nev;
    ReklamNevek(String nev){
        this.nev = nev;
    }
}
