package org.farmas.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.farmas.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable, InitController {

    @FXML
    private JFXButton playButton;
    @FXML
    private JFXButton rulesButton;
    @FXML
    private JFXButton quitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupListeners();
    }


    @Override
    public void setupListeners() {
        playButton.setOnAction(event -> {
            try {
                App.setScene("game");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        quitButton.setOnAction(event -> App.window.close());
    }

    @Override
    public void setupServices() {

    }

    public void initData() {

    }
}
