package hu.banattila.kivetelek;

public class MaximumEpuletSzint extends Exception {

    public MaximumEpuletSzint(String nev) {
        super(nev + " elérte a maximális fejlesztési szintet.");
    }
}
