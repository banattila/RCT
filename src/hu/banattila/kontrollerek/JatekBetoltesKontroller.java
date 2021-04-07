package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.mentes.Mentesek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class JatekBetoltesKontroller {

    public JatekBetoltesKontroller(){}

    ObservableList<String> mentesLista= FXCollections.observableList(Mentesek.osszesMentes());

    @FXML
    ListView<String> uzenetek;




    @FXML
    private void initialize(){
        uzenetek.setItems(mentesLista);
        uzenetek.setOnMouseClicked( event -> {
            String mentes = uzenetek.getFocusModel().getFocusedItem();
            Main.setJatek(Mentesek.betoltes(mentes+ ".txt"));
            try {
                Main.setRoot("fxml/jatek");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}
