package hu.banattila.enumok;

public enum JatekSzintek {
    KONNYU("Könnyű"),
    KOZEPES("Közepes"),
    NEHEZ("Nehéz");

    private final String name;

    private JatekSzintek(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
