package com.pdict.iplpredict;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WebPageServiceUtils {

    public static InputStream loadFile(String file) {
        ClassLoader classLoader = WebPageServiceUtils.class.getClassLoader();

        return classLoader.getResourceAsStream(file);
    }
}
