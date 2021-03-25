package hu.banattila.kivetelek;

public class MaxSzemelyzetSzam extends Exception{

    public MaxSzemelyzetSzam(String milyen){
        super("Nem alkalmazhatsz több " + milyen + "t.");
    }
}
