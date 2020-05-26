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
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RessourcesScanner {

    public RessourcesScanner() {
    }


    public static BiPredicate<String, String> testFileName = (s, s2) -> s.toUpperCase().contains(s2.toUpperCase());

    public static Set<String> listFilesUsingJavaIO(String dir, String... filters) {
        return Stream.of(Objects.requireNonNull(new File(App.class.getResource(dir).getPath()).listFiles((dir1, name) -> {
            boolean test = false;
            if (filters.length == 0) return true;
            for (String filter : filters
            ) {
                if (testFileName.test(name, filter)) {
                    test = true;
                    break;
                }
            }
            return test;
        }))).filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    /**
     * read all files in a subfolder of resources/org/farmas/json/
     *
     * @param dir     subfolder (ex : questions )
     * @param filters list of string to filter subfolder
     * @return list of JSONObject
     */
    public static List<JSONObject> readJSONFilesFromRessources(String dir, String... filters) {
        try {
            Set<String> files = RessourcesScanner.listFilesUsingJavaIO("json/" + dir, filters);
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
