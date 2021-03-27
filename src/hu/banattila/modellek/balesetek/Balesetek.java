package hu.banattila.modellek.balesetek;

public enum Balesetek {
    ROSSZ_VATTA("Rossz ízű volt a vattacukor!"),
    SZETESIK("Szétesett a körhinta!"),
    ELSULLYED("Elsüllyedt a csónakod!"),
    KISIKLIK("Kisiklott a hullámvasút!"),
    BIRSAG("Sajnos megbírságolt az adóhatóság.");

    private final String nev;

    private Balesetek(String nev) {
        this.nev = nev;
    }
}
