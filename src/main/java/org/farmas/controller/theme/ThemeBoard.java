package org.farmas.controller.theme;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.controller.game.GameController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThemeBoard implements Initializable, InitController {

    private final GridPane gridPane;
    private final int NB_COLUMNS;
    private final int NB_ROWS;

    public ThemeBoard(int themeSize) {
        NB_COLUMNS = themeSize / 2;
        NB_ROWS = themeSize / 3;
        gridPane = new GridPane();
        for (int i = 0; i < NB_COLUMNS; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < NB_ROWS; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            gridPane.getRowConstraints().add(rowConstraints);
        }

        gridPane.setHgap(40);
        gridPane.setVgap(40);
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

    public void initData(String[] themes, GameController gameController) {
        for (int i = 0; i < NB_ROWS; i++) {
            for (int j = 0; j < NB_COLUMNS; j++) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("views/themeProfile.fxml"));
                    gridPane.add(loader.load(), j, i);
                    loader.<ThemeProfile>getController().initData(themes[j + i * NB_COLUMNS], gameController); // init data
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public void removeString(String name) {
        GameController.game.getPhaseII();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

}
