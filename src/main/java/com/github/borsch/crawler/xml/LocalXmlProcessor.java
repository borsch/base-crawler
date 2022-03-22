package com.github.borsch.crawler.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalXmlProcessor extends IXmlProcessor {

    @Override
    protected InputStream getRulesSource(final String location) {
        URL url = LocalXmlProcessor.class.getResource(location);

        if (url == null) {
            throw new RuntimeException("Can't found local crawled description");
        }

        try {
            return Files.newInputStream(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Can't create input stream from local file: " + location, e);
        }
    }
}
