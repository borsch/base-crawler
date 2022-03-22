package com.github.borsch.crawler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static String getResourceContent(String resourceName) {
        try {
            Path path = Paths.get("src/test/resources/" + resourceName);

            return String.join("", Files.readAllLines(path));
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't read resource: " + resourceName, e);
        }

    }

}
