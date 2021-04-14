package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.jatek.Jatek;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;


public class FinishController {

    public FinishController(){}

    @FXML
    private Label finishMessage;

    @FXML
    private Button exitGame;

    @FXML
    private Button menu;

    @FXML
    private void initialize(){
        finishMessage.setText(Main.getJatek().getUzenet().get(Main.getJatek().getUzenet().size() - 1));

        exitGame.setOnAction( event -> {
            System.exit(0);
        });

        menu.setOnAction( event -> {
            try{
                Main.setRoot("fxml/vidampark");
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
