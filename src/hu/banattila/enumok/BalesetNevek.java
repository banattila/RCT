package hu.banattila.enumok;

public enum BalesetNevek {
    ROSSZ_VATTA("Rossz ízű volt a vattacukor!"),
    SZETESIK("Szétesett a körhinta!"),
    ELSULLYED("Elsüllyedt a csónakod!"),
    KISIKLIK("Kisiklott a hullámvasút!"),
    BIRSAG("Sajnos megbírságolt az adóhatóság.");

    private final String nev;

    BalesetNevek(String nev) {
        this.nev = nev;
    }

    public String getNev() {
        return nev;
    }
}
