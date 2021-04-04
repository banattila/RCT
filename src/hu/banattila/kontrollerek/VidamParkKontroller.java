package hu.banattila.kontrollerek;

import hu.banattila.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class VidamParkKontroller {

    @FXML
    private Button newGame;

    @FXML
    private Button loadGame;

    public VidamParkKontroller() {
    }

    @FXML
    public void newGame(){
        try {
            Main.setRoot("fxml/ujJatek");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
