package hu.banattila.kontrollerek;

import hu.banattila.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class VidamParkKontroller {

    @FXML
    private Button newGame;

    @FXML
    private Button loadGame;

    public VidamParkKontroller() {
    }

    private void newGame(){
        newGame.setOnAction( event -> {
            try {
                Main.setRoot("fxml/ujJatek");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadGame(){
        loadGame.setOnAction( event -> {
            try {
                Main.setRoot("fxml/jatekBetoltes");
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void initialize(){
        newGame();
        loadGame();
    }
}
