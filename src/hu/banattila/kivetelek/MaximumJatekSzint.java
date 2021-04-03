package hu.banattila.kivetelek;

public class MaximumJatekSzint extends Exception {

    public MaximumJatekSzint(String nev) {
        super(nev + " elérte a maximális fejlesztési szintet.");
    }
}
