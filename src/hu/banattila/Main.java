package hu.banattila;

import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.JatekSzintek;
import hu.banattila.enumok.ReklamNevek;
import hu.banattila.jatek.Jatek;
import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.modellek.emberek.Karbantarto;
import hu.banattila.modellek.emberek.Konyvelo;

public class Main {

    public static void main(String[] args) {

        Jatek jatek = new Jatek("Attila", JatekSzintek.KONNYU);

        while (Jatek.getElteltNapok() < 100){
            System.out.println(jatek);

            jatek.jatekosFejleszt(JatekNevek.VATTACUKROS.getNev());
            jatek.jatekosReklamoz(ReklamNevek.UJSAGHIRDETES.getNev());
            jatek.jatekosReklamoz(ReklamNevek.UJSAGHIRDETES.getNev());
            try {
                jatek.getJatekos().alkalmaz(new Karbantarto("Sanyi"));
                jatek.getJatekos().alkalmaz(new Karbantarto("Jani"));
                jatek.getJatekos().alkalmaz(new Karbantarto("Karcsi"));
                jatek.getJatekos().alkalmaz(new Karbantarto("Pali"));
                jatek.getJatekos().alkalmaz(new Karbantarto("Karcsi2"));
            } catch (MaxSzemelyzetSzam e) {
                jatek.addUzenet(e.getMessage());
            }

            try {
                jatek.getJatekos().alkalmaz(Konyvelo.konyvelotAlkalmaz("Jansi"));
            } catch (MaxSzemelyzetSzam e){
                jatek.addUzenet(e.getMessage());
            }
            try {
                jatek.getJatekos().alkalmaz(new Karbantarto("Gazsi"));
            } catch (MaxSzemelyzetSzam e){
                jatek.addUzenet(e.getMessage());
            }

            jatek.napVege();
        }
    }
}
