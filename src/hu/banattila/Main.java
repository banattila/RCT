package hu.banattila;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.jatek.Jatek;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Jatek jatek;
    private static Scene scene;

    public static void setJatek(Jatek j){
        jatek = j;
    }
    public static Jatek getJatek(){
        return jatek;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    @Override
    public void start(Stage primaryStage) {

        try {
            scene = new Scene(loadFXML("fxml/vidampark"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
