package org.farmas.model.tools;

import org.farmas.App;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RessourcesScanner {

    public RessourcesScanner() {
    }

    ;

    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(App.class.getResource(dir).getPath()).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    /**
     * read all files in a subfolder of resources/org/farmas/json/
     *
     * @param dir subfolder (ex : questions )
     * @return list of JSONObject
     */
    public static List<JSONObject> readJSONFilesFromRessources(String dir) {
        try {
            Set<String> files = RessourcesScanner.listFilesUsingJavaIO("json/" + dir);
            List<JSONObject> jsonObjects = new ArrayList<>();
            files.forEach(file -> {
                try {
                    InputStream inputStreamQuestions = App.class.getResourceAsStream("json/" + dir + "/" + file);
                    JSONParser parserJSON = new JSONParser();
                    JSONObject jsonFile = (JSONObject) parserJSON.parse(new InputStreamReader(inputStreamQuestions, StandardCharsets.UTF_8));
                    jsonObjects.add(jsonFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            return jsonObjects;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
