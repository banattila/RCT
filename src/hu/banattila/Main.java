package hu.banattila;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/vidampark.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("/hu/banattila/style/gamePane.css");

        primaryStage.setTitle("Roller Coaster Tycoon");
        primaryStage.getIcons().add(new Image("/hu/banattila/imgs/icon.jpeg"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
