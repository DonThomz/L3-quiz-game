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
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("primary"));
        //stage.setScene(scene);

        /*URL url = new URL("https://opentdb.com/api.php?amount=10&category=20&difficulty=easy");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode" + responseCode);
        } else {
            Scanner sc = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();
            while (sc.hasNext()) {
                inline.append(sc.nextLine());
            }
            //System.out.println("\nJSON data in string format");
            //System.out.println(inline);
            sc.close();

            JSONParser parse = new JSONParser();
            try {
                JSONObject jobj = (JSONObject) parse.parse(String.valueOf(inline));
                JSONArray jsonarr_1 = (JSONArray) jobj.get("results");
                for (int i = 0; i < jsonarr_1.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonarr_1.get(i);
                    System.out.println(jsonObject.get("question"));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }*/


        Themes themes = new Themes();
        themes.selectFiveTheme();

        stage.show();
    }

}