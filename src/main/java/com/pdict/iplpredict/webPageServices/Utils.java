package com.pdict.iplpredict.webPageServices;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static String loadFile(String file) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        String fileContents = "";

        try (InputStream inputStream = classLoader.getResourceAsStream(file)) {
            fileContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContents;
    }
}
