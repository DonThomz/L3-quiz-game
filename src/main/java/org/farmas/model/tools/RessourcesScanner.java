package org.farmas.model.tools;

import org.apache.commons.text.StringEscapeUtils;
import org.farmas.App;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RessourcesScanner {

    public RessourcesScanner() {
    }


    public static BiPredicate<String, String> testFileName = (s, s2) -> s.toUpperCase().contains(s2.toUpperCase());

    /**
     * Read files in resource folder with Reflection Library from Maven Dependency
     * @param dir reference path to the folder
     * @param extension extension of files
     * @param filters names of files that you want exclude
     * @return filenames
     */
    public static Set<String> listFilesUsingJavaIO(String dir, String extension, String... filters) {

        Reflections reflections = new Reflections(dir, new ResourcesScanner());
        Set<String> fileNames = reflections.getResources(Pattern.compile(".*\\."+extension));
        return fileNames.stream().filter(name ->{
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
        }).collect(Collectors.toSet());
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
            Set<String> files = RessourcesScanner.listFilesUsingJavaIO("org.farmas.json."+dir, "json", filters);

            if(files != null && files.size() > 0) {
                JSONParser parserJSON = new JSONParser();
                List<JSONObject> jsonObjects = new ArrayList<>();
                files.forEach(file -> {
                    try {
                        InputStream is = App.class.getClassLoader().getResourceAsStream(file);
                        assert is != null;
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        String fileJSON = reader.lines().collect(Collectors.joining());
                        JSONObject jsonFile = (JSONObject) parserJSON.parse(fileJSON);
                        jsonObjects.add(jsonFile);
                    } catch (Exception ex) {
                        System.err.println("Error loading JSON file and parse");
                        System.err.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                });
                return jsonObjects;
            }else return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
