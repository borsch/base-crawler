package com.github.borsch.crawler.xml;

import com.github.borsch.crawler.domain.PageDescription;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class LocalXmlProcessor implements IXmlProcessor {

    private final Unmarshaller unmarshaller;

    public LocalXmlProcessor() {
        try {
            JAXBContext context = JAXBContext.newInstance(PageDescription.class);

            this.unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Could not initialize JAXB", e);
        }
    }

    public PageDescription parse(String location) {
        try {
            URL url = LocalXmlProcessor.class.getResource(location);

            if (url == null) {
                throw new RuntimeException("Can't found local crawled description");
            }

            File file = Paths.get(url.toURI()).toFile();

            return (PageDescription) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException("Crawler description is bad formatted", e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Can't found local crawled description", e);
        }
    }
}
