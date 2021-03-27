package hu.banattila;

import hu.banattila.jatek.Jatek;
import hu.banattila.modellek.JatekSzintek;

public class Main {

    public static void main(String[] args) {

        Jatek jatek = new Jatek("Attila", JatekSzintek.KONNYU);
        jatek.getJatekos().fejlesztes("Hullámvasút");
        jatek.napVege();

        System.out.println(jatek.getJatekos());
    }
}
