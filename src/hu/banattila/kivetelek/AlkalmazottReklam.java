package hu.banattila.kivetelek;

public class AlkalmazottReklam extends Exception {

    public AlkalmazottReklam(String nev) {
        super(nev + " már használatban van,");
    }
}
