package org.farmas;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.farmas.model.players.PlayerSet;

import java.io.IOException;
import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Stage window;
    public static PlayerSet playerSet;

    private static Scene scene;

    public static void main(String[] args) {

        // init playerSet
        playerSet = new PlayerSet(20);

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("home"));
        stage.setScene(scene);
        // Add a closing button (as requested)
        stage.setOnCloseRequest(confirmCloseEventHandler);
        stage.show();
    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(App.class.getResource("views/" + fxml + ".fxml"));
    }

    public static void setScene(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        window.setScene(new Scene(root));
    }

    private final EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Êtes-vous sûr de vouloir quitter ?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Quitter");
        closeConfirmation.setHeaderText("Confirmer la fermeture");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(window);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (closeResponse.isPresent()) {
            if (!ButtonType.OK.equals(closeResponse.get())) {
                event.consume();
            }
        }
    };


}