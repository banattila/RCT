package hu.banattila.modellek.balesetek;

public enum Balesetek {
    ROSSZ_VATTA("Rossz ízű vattacukor"),
    SZETESIK("Szétesik a körhinta"),
    ELSULLYED("Elsüllyed a csónak"),
    KISIKLIK("Kisiklik a hullámvasút"),
    BIRSAG("Bírság");

    private final String nev;

    private Balesetek(String nev) {
        this.nev = nev;
    }

    public String getNev() {
        return nev;
    }
}
