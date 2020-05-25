package org.farmas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.farmas.model.game.Game;
import org.farmas.model.players.PlayerSet;
import org.farmas.model.themes.Themes;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static PlayerSet playerSet;

    private static Scene scene;

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        // init playerSet
        playerSet = new PlayerSet(20);
        Game game = new Game();

        launch();
    }

    @Override
    public void start(Stage stage){
        //scene = new Scene(loadFXML("primary"));
        //stage.setScene(scene);

        stage.show();
    }

}