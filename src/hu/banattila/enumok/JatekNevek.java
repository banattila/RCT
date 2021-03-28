package hu.banattila.enumok;

public enum JatekNevek {

    CSONAKAZO("Csónakázó tó"),
    HULLAMVASUT("Hullámvasút"),
    KORHINTA("Körhinta"),
    SZELLEMVASUT("Szellemvasút"),
    VATTACUKROS("Vatta cukor árus");

    private final String nev;

    JatekNevek(String nev){
        this.nev = nev;
    }
}
