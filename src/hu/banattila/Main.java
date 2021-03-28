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
        System.out.println(jatek);
        System.out.println("-----------------------");
        jatek.jatekosFejleszt(JatekNevek.VATTACUKROS.name());
        System.out.println(jatek);
        jatek.setUzenet("");
        jatek.jatekosReklamoz(ReklamNevek.UJSAGHIRDETES.name());
        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");
        jatek.jatekosReklamoz(ReklamNevek.UJSAGHIRDETES.name());
        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");
        try {
            jatek.getJatekos().alkalmaz(new Karbantarto("Sanyi"));
            jatek.getJatekos().alkalmaz(new Karbantarto("Jani"));
            jatek.getJatekos().alkalmaz(new Karbantarto("Karcsi"));
            jatek.getJatekos().alkalmaz(new Karbantarto("Pali"));
            jatek.getJatekos().alkalmaz(new Karbantarto("Karcsi2"));
        } catch (MaxSzemelyzetSzam e) {
            jatek.setUzenet(e.getMessage());
        }


        try {
            jatek.getJatekos().alkalmaz(Konyvelo.konyvelotAlkalmaz("Jansi"));
        } catch (MaxSzemelyzetSzam e){
            jatek.setUzenet(e.getMessage());
        }
        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");

        jatek.napVege();
        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");
        try {
            jatek.getJatekos().alkalmaz(new Karbantarto("Gazsi"));
        } catch (MaxSzemelyzetSzam e){
            jatek.setUzenet(e.getMessage());
        }


        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");
        System.out.println(jatek);
        jatek.napVege();
        jatek.getJatekos().kirug(jatek.getJatekos().getKarbantartok().get(0));
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");
        jatek.napVege();
        jatek.jatekosReklamoz(ReklamNevek.UJSAGHIRDETES.name());
        System.out.println(jatek.getUzenet());
        jatek.setUzenet("");
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");jatek.napVege();
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");jatek.napVege();
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");jatek.napVege();
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");jatek.napVege();
        System.out.println(jatek);
        jatek.setUzenet("");
        System.out.println("-----------------------");
    }
}
