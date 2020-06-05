package org.farmas.controller.game;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.farmas.App;
import org.farmas.controller.InitController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThemeBoard implements Initializable, InitController {

    private final GridPane gridPane;
    private final int NB_COLUMNS;
    private final int NB_ROWS;

    ThemeBoard(int themeSize) {
        NB_COLUMNS = themeSize / 2;
        NB_ROWS = themeSize / 3;
        System.out.println(NB_COLUMNS + " " + NB_ROWS);
        gridPane = new GridPane();
        for (int i = 0; i < NB_COLUMNS; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < NB_ROWS; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            gridPane.getRowConstraints().add(rowConstraints);
        }

        gridPane.setHgap(20);
        gridPane.setVgap(20);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void setupServices() {

    }

    public void initData(String[] themes) {
        for (int i = 0; i < NB_ROWS; i++) {
            for (int j = 0; j < NB_COLUMNS; j++) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/profile.fxml"));
                    gridPane.add(loader.load(), j, i);
                    loader.<PlayerProfile>getController().setupTitleName(themes[j + i * NB_COLUMNS]); // setup the name
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
