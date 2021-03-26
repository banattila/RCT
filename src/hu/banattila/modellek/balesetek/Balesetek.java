package hu.banattila.modellek.balesetek;

public enum Balesetek {
    ROSSZ_VATTA("Rossz ízű vattacukor"),
    SZETESIK(""),
    ELSULLYED(""),
    KISIKLIK(""),
    BIRSAG("");

    private final String nev;

    private Balesetek(String nev){
        this.nev = nev;
    }
}
