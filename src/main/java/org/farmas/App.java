package org.farmas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.farmas.model.game.Game;
import org.farmas.model.players.PlayerSet;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Stage window;
    public static PlayerSet playerSet;

    private static Scene scene;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(App.class.getResource("views/" +fxml+".fxml"));
    }

    public static void main(String[] args) {

        // init playerSet
        playerSet = new PlayerSet(20);
        Game game = new Game();
        Game game2 = new Game();
        Game game3 = new Game();

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("home"));
        stage.setScene(scene);

        stage.show();
    }

}