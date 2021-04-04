package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.enumok.JatekSzintek;
import hu.banattila.jatek.Jatek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UjJatekKontroller {

    public UjJatekKontroller(){
    }

    String jatekosNev = "";
    JatekSzintek szint = JatekSzintek.KOZEPES;

    ObservableList<String> szintek = FXCollections.observableArrayList(
            JatekSzintek.KONNYU.getName(),
            JatekSzintek.KOZEPES.getName(),
            JatekSzintek.NEHEZ.getName()
    );

    @FXML
    private ChoiceBox<String> nehezseg;

    @FXML
    private Button kezd;

    private void kezdes(){
        kezd.setOnAction( event ->{
            jatekosNev = nev.getText();
            switch (nehezseg.getValue()) {
                case "Könnyű": szint = JatekSzintek.KONNYU; break;
                case "Közepes":  szint = JatekSzintek.KOZEPES; break;
                case "Nehéz":  szint = JatekSzintek.NEHEZ; break;
            }
            Main.setJatek(new Jatek(jatekosNev, szint));
            try {
                Main.setRoot("fxml/jatek");
            }catch (IOException exception){
                exception.printStackTrace();
            }
        });
    }

    @FXML
    private TextField nev;

    @FXML
    private void initialize(){
        nehezseg.setItems(szintek);
        kezdes();

    }
}
