package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.mentes.Mentesek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;

public class JatekBetoltesKontroller {

    public JatekBetoltesKontroller(){}

    ObservableList<String> mentesLista= FXCollections.observableList(Mentesek.osszesMentes());

    @FXML
    ListView<String> uzenetek;

    @FXML
    private Label noSaved;

    @FXML
    private Button vissza;

    private void setVissza(){
        vissza.setOnAction( event -> {
            try {
                Main.setRoot("fxml/vidampark");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private Button betolt;

    private void betolt(){
        betolt.setOnAction( event -> {
            String mentes = uzenetek.getFocusModel().getFocusedItem();
            Main.setJatek(Mentesek.betoltes(mentes+ ".txt"));
            try {
                Main.setRoot("fxml/jatek");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    @FXML
    private void initialize(){
        if (mentesLista.size() == 0){
            noSaved.setText("Nincs mentett játék");
        } else {
            noSaved.setText("");
        }
        uzenetek.setItems(mentesLista);
        setVissza();
        betolt();
    }
}
